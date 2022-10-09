#!/usr/bin/env bash

# mvn liquibase:rollback -Dliquibase.rollbackCount=1

mvn liquibase:dropAll
mvn liquibase:clearCheckSums
mvn liquibase:update
