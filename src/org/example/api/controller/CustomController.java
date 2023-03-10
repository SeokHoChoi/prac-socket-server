package org.example.api.controller;


import org.example.api.server.ResponseBody;

public class CustomController {
    public String getBoardTitle() {
        return "controller 이용한 글 제목";
    }
    public void postBoardContent(ResponseBody responseBody) {
        responseBody.add("content", "controller 이용한 content");
    }
}
