#/bin/bash

docker run -it --rm \
  -p $PORT:$PORT \
  -e PORT=$PORT \
  -e JAVA_OPTS=-Xmx512m \
  sbmirror:latest
