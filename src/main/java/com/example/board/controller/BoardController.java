package com.example.board.controller;

import com.example.board.domain.Board;
import com.example.board.domain.Member;
import com.example.board.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    /**
     * 1️⃣ 게시글 목록
     */
    @GetMapping
    public String list(Model model) {

        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);

        return "board/list";
    }

    /**
     * 2️⃣ 게시글 상세
     */
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id,
                         Model model) {

        Board board = boardService.findById(id);
        model.addAttribute("board", board);

        return "board/detail";
    }

    /**
     * 3️⃣ 글쓰기 폼
     */
    @GetMapping("/write")
    public String writeForm() {
        return "board/write";
    }

    /**
     * 4️⃣ 글쓰기 처리
     */
    @PostMapping("/write")
    public String write(Board board,
                        HttpSession session) {

        Member loginMember =
                (Member) session.getAttribute("loginMember");

        boardService.create(board, loginMember.getId());

        return "redirect:/board";
    }

    /**
     * 5️⃣ 수정 폼
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id,
                           Model model) {

        Board board = boardService.findById(id);
        model.addAttribute("board", board);

        return "board/edit";
    }

    /**
     * 6️⃣ 수정 처리
     */
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       String title,
                       String content,
                       HttpSession session) {

        Member loginMember =
                (Member) session.getAttribute("loginMember");

        boardService.update(id, title, content, loginMember);

        return "redirect:/board/" + id;
    }

    /**
     * 7️⃣ 삭제
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         HttpSession session) {

        Member loginMember =
                (Member) session.getAttribute("loginMember");

        boardService.delete(id, loginMember);

        return "redirect:/board";
    }
}
