package org.example.api.server;


import org.example.api.controller.CustomController;
import org.example.api.controller.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class CustomServer {

    private final int port;
    private static final Logger log = LoggerFactory.getLogger(CustomServer.class);
    public CustomServer(int port) {
        this.port = port;
    }

    CustomController customController = new CustomController();

    public void start() {
        try(ServerSocket socket = new ServerSocket(port)) {
            log.info("[CustomServer] started {} port", port);

            Socket clientSocket;

            log.info("[CustomServer] waiting for client");

            while((clientSocket = socket.accept()) != null) {
                log.info("[CustomServer] client connected!!");
              try(OutputStream out = clientSocket.getOutputStream()) {
                  DataOutputStream dos = new DataOutputStream(out);
                  /* 1. 라인별로 파싱하여 리퀘스트 객체 생성 ...*/

                  HttpResponse response = new HttpResponse(dos);
                  Map<String, String> headers = new HashMap<>();
                  ResponseBody responseBody = new ResponseBody();

                  /* request path를 파싱하는 코드 */
                  // HTTP 요청 라인에서 첫 번째 슬래시(/) 이후의 문자열을 추출
                  // 클라이언트로부터 입력 스트림을 받아서 BufferedReader로 래핑하고, 첫 번째 라인을 읽어들임
                  // 이 라인에서 스페이스(space)로 구분된 토큰들을 분리하여 첫 번째 토큰이 HTTP 메소드(GET, POST 등)이고, 두 번째 토큰이 요청 경로(path)
                  // 이 경로를 requestPath 변수에 저장
//                  BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
//                  String requestLine = reader.readLine();
//                  String[] requestLineTokens = requestLine.split(" ");
//                  String requestPath = requestLineTokens[1];
//
//                  // POST http://localhost:8080/title
//                  if (requestPath.equals("/title")) {
//                      responseBody.add("title", "글제목");
//                  // POST http://localhost:8080/content
//                  } else if (requestPath.equals("/content")) {
//                      responseBody.add("content", "본문");
//                  } else {
//                      responseBody.add("title", "글제목");
//                      responseBody.add("content", "본문");
//                      responseBody.add("writer", "개구리");
//                      responseBody.add("createdAt", "2023-03-08T10:00:00+09:00");
//                  };

                  BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                  String requestLine = reader.readLine();
                  String[] requestLineTokens = requestLine.split(" ");
                  String requestMethod = requestLineTokens[0];
                  String requestPath = requestLineTokens[1];

                  if ("GET".equals(requestMethod)) {
                      switch (requestPath) {
                          case "/title":
                              responseBody.add("title", customController.getBoardTitle());
                              break;
                          case "/content":
                              responseBody.add("content", "본문");
                              break;
                          case "/writer":
                              responseBody.add("writer", "개구리");
                              break;
                          case "/createdAt":
                              responseBody.add("createdAt", "2023-03-08T10:00:00+09:00");
                              break;
                          default:
                              // 경로가 지원되지 않는 경우
                              break;
                      }
                  } else if ("POST".equals(requestMethod)) {
                      // POST 요청 처리
                      switch (requestPath) {
                          case "/title":
                              break;
                          case "/content":
                              customController.postBoardContent(responseBody);
                              break;
                          case "/writer":
                              break;
                          case "/createdAt":
                              break;
                          default:
                              // 경로가 지원되지 않는 경우
                              break;
                      }
                  } else {
                      // 지원하지 않는 HTTP 메소드인 경우 처리
                  }

                  // 컨트롤러로 완전히 옮기기
                  TestController testController =
                          new TestController(requestMethod, requestPath, responseBody);
                  testController.TestMethod();

                  headers.put("Content-Type", "application/json;charset=UTF-8");
                  headers.put("Content-Length", String.valueOf(responseBody.length()));

                  response.setStatus(HttpStatus.OK);
                  log.info("[CustomServer] set status.");
                  response.setHeaders(headers);
                  log.info("[CustomServer] set headers.");
                  response.setBody(responseBody);
                  log.info("[CustomServer] set body : {}", responseBody.getJson());
                  response.send();
              }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
