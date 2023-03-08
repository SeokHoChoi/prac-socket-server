package org.example.api.repository;

import org.example.api.dto.Board;
import org.example.api.dto.Member;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BoardRepository {
    private static BoardRepository INSTANCE;
    private static String DATABASE_URL = "jdbc:mysql://localhost:3306/java_prac";
    private static String USERNAME = "java_parc";
    private static String PASSWORD = "asdf1234@";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private Connection conn;
    private Statement stmt;
    private BoardRepository() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static BoardRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BoardRepository();
        }

        return INSTANCE;
    }

    public void createBoard(Board board) {
        String query = "INSERT INTO board " +
                "(id, member_id, title, content, type, created_at)" +
                " VALUES (?, ?, ?, ?, ?, ?)";

//        Member member = board.getMember();
//        board.setId(UUID.randomUUID());
//        board.setMember(member);
//        board.setTitle("제목이다마");
//        board.setContent("내요이다");
//        board.setCreatedAt(LocalDateTime.now());


        try  {
            PreparedStatement ps = conn.prepareStatement(query);
//            UUID boardId = UUID.randomUUID();
//            board.setId(boardId);
//            board.setTitle("제목이다마");
//            board.setContent("내요이다");
//            board.setType(1);
//            board.setCreatedAt(LocalDateTime.now());
//            System.out.println(board.getMember_id().toString());
            int i =1;

            ps.setString(i++, board.getId().toString());
            ps.setString(i++, board.getMember_id().toString());
            ps.setString(i++, board.getTitle());
            ps.setString(i++, board.getContent());
            ps.setString(i++, String.valueOf(board.getType()));
            ps.setString(i, board.getCreatedAt().toString());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBoard(String boardId) {
        String query = "DELETE FROM board WHERE id=?";
        try  {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, boardId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBoard(String boardId,  Board board) {
        String query = "UPDATE board SET title = ?, content = ? WHERE id = ?";
        try  {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setString(3, boardId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Board> findAll() {
        String query = "SELECT * FROM board";
        ResultSet rs;
        List<Board> result = new ArrayList<>();

        try {
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                // "" 안에는 넣을 값이 아니라 컬럼명 이었음 ㄷㄷ
                UUID id = UUID.fromString(rs.getString("id"));
                String title = rs.getString("title");
                String content = rs.getString("content");
                // 참조키 이렇게 넣는거 맞나..? -> X 컬럼명 이었음
//                Member member = new Member();
//                Member member = rs.getString("member_id");
//                UUID member_id = (UUID) rs.getObject("member_id");
//                Member member = (Member)rs.getString("member_id");
                UUID memberId = UUID.fromString(rs.getString("member_id"));
                int type = Integer.parseInt(rs.getString("type"));
                String createdAtString = rs.getString("created_at");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime createdAt = LocalDateTime.parse(createdAtString, formatter);


                Board board = Board.getInstance();
                board.setId(id);
                board.setTitle(title);
                // 맞나??
                board.setMember_id(memberId);
                board.setType(type);
                board.setContent(content);
                board.setCreatedAt(createdAt);

                result.add(board);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
