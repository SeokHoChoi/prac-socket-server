package org.example.api.repository;

import org.example.api.dto.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemberRepository {
    private static MemberRepository INSTANCE;
    // 애초에 이렇게 접속을하면 디비에 바로 접속됨. use 디비이름 이런걸 안써도.
    private static String DATABASE_URL = "jdbc:mysql://localhost:3306/java_prac";
    // private static String TARGET_DATABASE = "이사갈 DB URL";
    // 그럼 username, password 도 그거에 맞는게 있을거고 드라이버도 그거에 맞게 로드하면됨.
    // 이런 마이그레이션도 써먹을수 있겠지?
    private static String USERNAME = "java_parc";
    private static String PASSWORD = "asdf1234@";
    // jdbc는 인터페이스임. 인터페이스를 쓰는 이유는 각 디비가 jdbc만 구현하면
    // 모든 사용자가 jdbc만 이용해서 해당 디비를 사용할수있게 만들기 위해서.
    // 키보드 생각하셈.
    // 이건 오라클 mysql 마리아디비 포스트sql 등등 다 다르니 주의
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";

    private Connection conn;
    private Statement stmt;
    // 이렇게하면 리플렉션이 시작되는거임.
    // 리플렉션을 통해서 자바의 핵심기능을 다 쓸수있음.
    // 스프링짤떄는 디펜던시를 계속 추가를 하지?
    // 그럼 필요한 디비를 다 임폴트하지?
    // 근데 이거는 내가 언제든지 마음만 먹으면 패키지의 경로만 알고있으면
    // 클래스를 나중에, 그러니까 프로젝트를 시작하고 언제든지 아무때나 클래스를 로드할수있음.
    // 즉 클래스 로드를 나중에 실행시킬수 있는거임.
    private MemberRepository() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MemberRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MemberRepository();
        }

        return INSTANCE;
    }

    public UUID findId() {
        String query = "SELECT id FROM member WHERE login_id='test1'";
        ResultSet rs;

        UUID id = null;
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = UUID.fromString(rs.getString("id"));
                System.out.println(id + "나오나?");


                Member member = Member.getInstance();
                member.setId(id);

                return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    public List<Member> findAll() {
        String query = "SELECT * FROM member";
        ResultSet rs;
        List<Member> result = new ArrayList<>();

        try {
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                String loginId = rs.getString("login_id");
                String password = rs.getString("password");
                String nickname = rs.getString("nickname");

                Member member = Member.getInstance();
                member.setId(id);
                member.setLoginId(loginId);
                member.setPassword(password);
                member.setNickname(nickname);

                result.add(member);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
