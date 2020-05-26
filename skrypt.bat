cd .\Credit\ && mvn compile jib:dockerBuild && cd ..\Product\ && mvn compile jib:dockerBuild && cd ..\Customer\ && mvn compile jib:dockerBuild && cd ..\eureka-server\ && mvn compile jib:dockerBuild

