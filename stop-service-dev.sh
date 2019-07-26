#!/usr/bin/env bash

# start service
docker-compose -p comment \
  -f "./src/main/docker/docker-compose.yml" \
  -f "./src/main/docker/docker-compose-dev.yml" \
  down