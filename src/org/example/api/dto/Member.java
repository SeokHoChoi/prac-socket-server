package org.example.api.dto;

import java.util.UUID;

public class Member {


    private UUID id;
    private String loginId;
    private String password;
    private String nickname;
    public UUID getId() {
        return id;
    }

    private static Member INSTANCE;

    private Member() {

    }
    public static Member getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Member();
        }

        return INSTANCE;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }


    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
