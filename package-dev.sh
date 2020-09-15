#!/usr/bin/env bash
rm -rf platform.jar && mvn clean package -Dmaven.test.skip=true -Pdev && mv platform-manage/target/platform-manage-0.0.1-SNAPSHOT.jar platform.jar;