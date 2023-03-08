package org.example.api.controller;

import org.example.api.dto.Board;
import org.example.api.dto.Member;
import org.example.api.repository.BoardRepository;
import org.example.api.repository.MemberRepository;
import org.example.api.service.BoardService;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.UUID;

public class BoardController {
    BoardService boardService = BoardService.getInstance();

   Member member = Member.getInstance();
    MemberRepository memberRepository = MemberRepository.getInstance();
    Board board = Board.getInstance();

    private static BoardController INSTANCE;

    private BoardController() {

    }
    public static BoardController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BoardController();
        }

        return INSTANCE;
    }

    public void createBoard() {

//        String query2 = "SELECT * FROM member";

//        System.out.println(memberRepository.findId());
        board.setId(UUID.randomUUID());
        // 근데 멤버id를 참조하는거니까 getId를 넣어야 하는거 아님??
        // board.setMember(member.getId());
        board.setMember_id(memberRepository.findId());
        board.setTitle("hi");
        board.setContent("내요이다");
        board.setType(1);
        board.setCreatedAt(LocalDateTime.now());

//        System.out.println(board.getId());
        boardService.createBoard(board);
    }


    public void deleteBoard(String boardId) {
        boardService.deleteBoard(boardId);
    }

    public void updateBoard(String boardId) {
        board.setTitle("update");
        board.setContent("수정내용이다");
//        System.out.println(board.getContent());
        boardService.updateBoard(boardId, board);
    }
}
