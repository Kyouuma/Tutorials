## WHAT IS IT?

We all do unit/integration tests. 

Sometimes we find ourselves in need of running a single instance of whatever we need inside a container from within our test classes.[ Postgresql, elasticsearch, selenium browsers..].

Or perhaps bootstrap our own application, scaling it up during a jenkins job then gracefully shutting it down?

Parallel Testing? 

Using a real database instead of an H2? 

The are multiple use cases. In this project i decided to use docker for functional tests with selenium. A complete sandboxed, portable & production grade environment. 




## TECHNOLOGIES

> TestContainer 1.12.5

> Selenium 3.141.59

> Junit 4.12

## Prerequisites

> Docker version 19
> 
> Windows Installation: https://docs.docker.com/docker-for-windows/install/
 >
 >  Linux Installation: https://runnable.com/docker/install-docker-on-linux
 
## COMMANDS

##### Run  Test
> ./mvnw clean verify

##### Generate JavaDoc For Test Classes

> mvn javadoc:test-javadoc
> ####### JavaDoc Path: target/site/testapidocs/index-all.html 

##### See Dependency Tree

> mvn dependency:tree

## NOTES
Disable Ryuk The Reaper for k8s using environment variables. K8s  can handle container lifecycle.

> TESTCONTAINERS_RYUK_DISABLED=true

