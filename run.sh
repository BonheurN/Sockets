#!/bin/bash
# Compile Java files
javac ChatServer.java ChatClient.java

# Start server in background
java ChatServer &
SERVER_PID=$!
sleep 2  # Wait for server initialization

# Start clients (modify number as needed)
for i in {1..3}; do
  x-terminal-emulator -e "java ChatClient" &
done

# Cleanup instruction
echo "Press Ctrl+C to stop all processes"
trap "kill $SERVER_PID" SIGINT
wait
