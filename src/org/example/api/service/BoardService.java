package org.example.api.service;

import org.example.api.dto.Board;
import org.example.api.repository.BoardRepository;

import java.sql.DriverManager;
import java.sql.SQLException;

public class BoardService {

    BoardRepository boardRepository = BoardRepository.getInstance();
    private static BoardService INSTANCE;

    private BoardService() {

    }
    public static BoardService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BoardService();
        }

        return INSTANCE;
    }

    public void createBoard(Board board) {
//        System.out.println(board.getId());

        boardRepository.createBoard(board);
    }

    public void deleteBoard(String boardId) {
        boardRepository.deleteBoard(boardId);
    }

    public void updateBoard(String boardId, Board board) {
//        System.out.println(board.getContent());
        boardRepository.updateBoard(boardId, board);
    }
}
