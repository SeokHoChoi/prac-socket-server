package org.example.api.server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class CustomServer {

    private final int port;
    private static final Logger log = LoggerFactory.getLogger(CustomServer.class);
    public CustomServer(int port) {
        this.port = port;
    }

    public void start() {
        try(ServerSocket socket = new ServerSocket(port)) {
            log.info("[CustomServer] started {} port", port);

            Socket clientSocket;

            log.info("[CustomServer] waiting for client");

            while((clientSocket = socket.accept()) != null) {
                log.info("[CustomServer] client connected!!");

                try(InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

                    String line;
                    while((line = br.readLine()) != "" && line != null) {
                        log.info(line);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
