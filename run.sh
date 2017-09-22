#!/bin/bash
if [[ -z ${MAVEN_OPTS} ]]; then
    echo "The environment variable 'MAVEN_OPTS' is not set, setting it for you";
    MAVEN_OPTS="-Xms256m -Xmx2G -agentpath:/Users/davidanton/tools/hotswap/lib/hotswap-agent.jar"
fi
echo "MAVEN_OPTS is set to '$MAVEN_OPTS'";
mvn clean install alfresco:run -DskipTests=true