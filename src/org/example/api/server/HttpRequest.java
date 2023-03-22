package org.example.api.server;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {

    private final RequestLine requestLine;
    public HttpRequest(BufferedReader br) throws IOException {
        this.requestLine = new RequestLine(br.readLine());
        // 헤더랑 바디를 파싱할때 공백으로 구분
        br.lines().forEach(System.out::println);

        /*
            Content-Type: application/json
            Content-Length: 82
            Host: localhost:8080
            Connection: Keep-Alive
            User-Agent: Apache-HttpClient/4.5.13 (Java/17.0.5)
            Accept-Encoding: br,deflate,gzip,x-gzip

            {
              "loginId": "도토리",
              "password": "도토리묵",
              "nickname": "감자"


            control(맥북에선 커맨드) + s + r 로 완전 새로고침
         */
    }

    public HttpMethod getMethod(){
        return requestLine.getMethod();
    }

    public String getUrlPath() {
        return requestLine.getUrlPath();
    }

    public QueryStrings getQueryStrings() {
        return requestLine.getQueryStrings();
    }

}