package com.its.memberproject.controller;

import com.its.memberproject.dto.BoardDTO;
import com.its.memberproject.dto.MemberDTO;
import com.its.memberproject.service.BoardService;
import com.its.memberproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;
    @GetMapping("/saveForm/{id}")
    public String boardSave(@PathVariable Long id, Model model){
      MemberDTO memberDTO = memberService.findById(id);
      model.addAttribute(memberDTO);
        return "board/saveForm";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
        boardService.save(boardDTO);
        return "board/findAll";
    }
}
