package org.example.api.controller;

import org.example.api.server.ResponseBody;

public class TestController {
    String requestMethod;
    String requestPath;
    ResponseBody responseBody;

    public TestController() {}
    public TestController(String requestMethod, String requestPath,  ResponseBody responseBody) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
        this.responseBody = responseBody;
    }

    public void TestMethod() {
        if ("GET".equals(requestMethod)) {
            switch (requestPath) {
                case "/test-title":
                    responseBody.add("title", getTestBoardTitle());
                    break;
                case "/test-content":
                    responseBody.add("content", "본문");
                    break;
                case "/test-writer":
                    responseBody.add("writer", "개구리");
                    break;
                case "/test-createdAt":
                    responseBody.add("createdAt", "2023-03-08T10:00:00+09:00");
                    break;
                default:
                    // 경로가 지원되지 않는 경우
                    break;
            }
        } else if ("POST".equals(requestMethod)) {
            // POST 요청 처리
            switch (requestPath) {
                case "/test-title":
                    break;
                case "/test-content":
                    postTestBoardContent(responseBody);
                    break;
                case "/test-writer":
                    break;
                case "/test-createdAt":
                    break;
                default:
                    // 경로가 지원되지 않는 경우
                    break;
            }
        } else {
            // 지원하지 않는 HTTP 메소드인 경우 처리
        }
    }

    public String getTestBoardTitle() {
        return "Test controller 이용한 글 제목";
    }
    public void postTestBoardContent(ResponseBody responseBody) {
        responseBody.add("content", "Test controller 이용한 content");
    }
}
