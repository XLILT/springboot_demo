@echo off

set APPID=%1

if not defined APPID (
    echo Usage:
    echo     %0 APPID [GROUPID]
    goto END
)

set GRPID=%2
if not defined GRPID (
    set GRPID=com.xl
)

mvn archetype:generate ^
    -B -DarchetypeGroupId=io.github.sivalabs.maven.archetypes ^
    -DarchetypeArtifactId=spring-boot-java-basic-archetype ^
    -DarchetypeVersion=1.0.0 ^
    -DgroupId=%GRPID% ^
    -DartifactId=%APPID% ^
    -Dversion=1.0-SNAPSHOT ^
    -Dpackage=%GRPID%.%APPID%

:END
