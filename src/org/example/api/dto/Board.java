package org.example.api.dto;

import org.example.api.controller.BoardController;

import java.time.LocalDateTime;
import java.util.UUID;
public class Board {
    private UUID id;
    private String title;
    private String content;
    private int type;
    private UUID member_id;
    private LocalDateTime createdAt;

    private static Board INSTANCE;

    private Board() {

    }
    public static Board getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Board();
        }

        return INSTANCE;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public UUID getMember_id() {
        return member_id;
    }

    public void setMember_id(UUID member_id) {
        this.member_id = member_id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }



    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", member_id='" + member_id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
