#!/usr/bin/env bash
CURRENT=$(pwd)

# start service
docker-compose -p comment \
  -f "$CURRENT/src/main/docker/docker-compose.yml" \
  -f "$CURRENT/src/main/docker/docker-compose-dev.yml" \
  down