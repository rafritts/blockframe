#!/bin/bash

cd ..

command -v mvn 2>&1 1>/dev/null
is_mvn_in_path=$?
if [ ${is_mvn_in_path} ]; then
    mvn package
fi

command -v java 2>&1 1>/dev/null
is_java_in_path=$?
if [ ${is_java_in_path} ]; then
    java -jar target/blockframe-1.0-SNAPSHOT-jar-with-dependencies.jar
fi