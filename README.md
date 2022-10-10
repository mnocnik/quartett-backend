GraphQL Server for Angular-Training

During experiments about Angular, I need a backend, which provides some data, and later on will be
able to CRUD data.

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

* GitHub is a friend, but even I do understand Public-Key-Infrastructure, I had issues granting
  myself access to my account xD. (docs for the win)
* spring-boot 2.7 added 'spring-boot-starter-graphql' --> everything changed
* closed some missings in orm-mapping by annotation
* git remote set-url origin git@github.com:<USER>/<PROJECT>.git 
