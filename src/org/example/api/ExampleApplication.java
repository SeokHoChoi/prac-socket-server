package org.example.api;

import org.example.api.controller.BoardController;
import org.example.api.dto.Board;
import org.example.api.repository.BoardRepository;
import org.example.api.repository.MemberRepository;
import org.example.api.server.CustomServer;

public class ExampleApplication {
    public static void main(String[] args) {
       new CustomServer(8080).start();
    }
}
