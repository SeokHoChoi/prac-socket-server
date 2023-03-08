package org.example.api;

import org.example.api.controller.BoardController;
import org.example.api.dto.Board;
import org.example.api.repository.BoardRepository;
import org.example.api.repository.MemberRepository;

public class ExampleApplication {
    public static void main(String[] args) {
        MemberRepository memberRepository = MemberRepository.getInstance();
        BoardRepository boardRepository = BoardRepository.getInstance();
        BoardController boardController= BoardController.getInstance();
        Board board = Board.getInstance();
//        memberRepository.findAll().forEach(System.out::println);
//        boardRepository.findAll().forEach(System.out::println);
//        boardRepository.createBoard(board);
//        System.out.println(board.getId());

        //생성
        boardController.createBoard();

        //삭제 (여따 아이디 쑤셔넣으면됨. 뭔가 이상하지만 성공함)
//        boardController.deleteBoard("b3e8282c-a777-11ed-be72-0242ac110002");

        // 수정 (글 생성, 수정에서 한글로 넣으니까 터미널에서 조회시 ???? 로 나옴 뭔가 이상해서 확인해보니 영어는 잘됨.)
        // 원래는 한글도 잘됐고 나는 아무짓도 안했는데 갑자기 왜이럼?

//        boardController.updateBoard("48b91726-c7e0-4c24-bba4-ff447a780eee");
    }
}
