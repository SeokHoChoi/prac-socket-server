package org.example.api.server;

import org.example.api.controller.Controller;
import org.example.api.controller.MemberController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientRequestHandler implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(ClientRequestHandler.class);

    private final Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        HttpResponse response = null;
        try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {

            // request : 요청
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

            // 숙제 : 컨트롤러 구현하기!!!
            HttpRequest httpRequest = new HttpRequest(br);
            DataOutputStream dos = new DataOutputStream(out);
            HttpResponse httpResponse = new HttpResponse(dos);

            /*
                1. 라인별로 파싱하여 리퀘스트 객체 생성
                    1-1. METHOD
                    1-2. PATH
                    1-3. BODY
                    1-4. QueryString 등
             */

            // Path 파싱
            String urlPath = httpRequest.getUrlPath();

            if (urlPath.indexOf("/member") == 0) {
                Controller memberController = new MemberController();
                memberController.call(httpRequest, httpResponse);
            } else if (urlPath.indexOf("/board") == 0) {

            }

            // response : 응답

        } catch (IOException e) {
            log.error(e.getMessage());
            if (response != null) {
                response.setStatus(HttpStatus.SERVER_ERROR);
                response.send();
            }
        }
    }
}
