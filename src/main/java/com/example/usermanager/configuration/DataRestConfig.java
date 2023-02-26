package com.example.usermanager.configuration;


import com.example.usermanager.entity.User;
import com.example.usermanager.entity.Setting;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class DataRestConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] allowedOrgins;
    private EntityManager entityManager;

    @Autowired
    public DataRestConfig(EntityManager entityManager){
        this.entityManager = entityManager;
    }



    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] invalidActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};
        disableHttpMethods(User.class, config, invalidActions);
        disableHttpMethods(Setting.class,config,invalidActions);
        exposeIds(config);
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(allowedOrgins);
    }

    private static void disableHttpMethods(Class classType, RepositoryRestConfiguration config, HttpMethod[] invalidActions) {
        config.getExposureConfiguration().forDomainType(classType)

                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(invalidActions)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(invalidActions));
    }

    private void exposeIds(RepositoryRestConfiguration repositoryRestConfiguration){

        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        List<Class> entityClasses = new ArrayList<>();

        for(EntityType entityType : entities){
            entityClasses.add(entityType.getJavaType());
        }

        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        repositoryRestConfiguration.exposeIdsFor(domainTypes);
    }
}
