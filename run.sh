#!/bin/bash

# Compile Java files
javac ChatServer.java ChatClient.java

# Start server in background
java ChatServer &
SERVER_PID=$!
sleep 2  # Wait for server initialization

# Start clients using Windows start command
for i in {1..3}; do
  start javaw -cp "$(pwd)" ChatClient
done

# Cleanup instruction
echo "Press Ctrl+C to stop all processes"
trap "kill $SERVER_PID" SIGINT
wait
