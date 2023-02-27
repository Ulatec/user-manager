# user-manager
<p>Basic user manager to be used with <a href="https://github.com/Ulatec/multi-project-frontend">multi-project-frontend</a></p>

# Notes
<li>Store and retrieve individual application settings for a user.</li>
<li>Uses Eureka Naming Server as an API gateway.</li>
<li>Endpoints require a valid JWT token. Uses Spring-Boot-Okta-Starter for remote JWT token validation </li>
<li>Support for Stripe payments with Stripe's PaymentIntent API</li>
<li>Manage User's access subscription to the product/platform.</li>