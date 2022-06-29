package com.its.memberproject.controller;

import com.its.memberproject.common.PagingConst;
import com.its.memberproject.dto.BoardDTO;
import com.its.memberproject.dto.MemberDTO;
import com.its.memberproject.service.BoardService;
import com.its.memberproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

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
        return "redirect:/board/";
    }
    @GetMapping
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<BoardDTO> boardDTOPage = boardService.paging(pageable);
          List<BoardDTO> boardDTOList =  boardService.findAll();
          model.addAttribute("List",boardDTOList);
        model.addAttribute("boardList",boardDTOPage);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardDTOPage.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardDTOPage.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/paging";
    }
}
