GraphQL Server for Angular-Training

During expermiments about Angular, I need a backend, which provides some data, and later on will be able to CRUD data.

Tiny project which uses e.g.:
* spring-boot
* hibernate
* graphql

later: keycloak

during development:
 spring.profiles.active: profile_develop (grants every request: ROLE_DEV)
 
 TODO:
 * install docker-registry on private server
 
 Lessons learned:
 * spring-boot 2.7 added 'spring-boot-starter-graphql' --> everything changed
 * closed some missings in orm-mapping by annotation
 
