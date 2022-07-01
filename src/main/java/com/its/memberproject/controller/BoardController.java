package com.its.memberproject.controller;

import com.its.memberproject.common.PagingConst;
import com.its.memberproject.dto.BoardDTO;
import com.its.memberproject.dto.CommentDTO;
import com.its.memberproject.dto.MemberDTO;
import com.its.memberproject.service.BoardService;
import com.its.memberproject.service.CommentService;
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
    private final CommentService commentService;
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
        model.addAttribute("boardList",boardDTOPage);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardDTOPage.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardDTOPage.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/paging";
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id,Model model){
      BoardDTO boardDTO=  boardService.findById(id);
          List<CommentDTO> commentDTOList=  commentService.findAll(id);
          model.addAttribute("commentList",commentDTOList);
        model.addAttribute("boardDTO",boardDTO);
        return "board/detail";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        boardService.deleteById(id);
        return "redirect:/board/";
    }
    @GetMapping("/updateForm/{id}")
    public String updateForm(@PathVariable Long id,Model model){
       BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardDTO",boardDTO);
        return "board/updateForm";

    }
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO) throws IOException {
        boardService.update(boardDTO);
        return "redirect:/board/";
    }
    @GetMapping("/search")
    public String search(@RequestParam("q")String q,@RequestParam("q2")String q2,Model model){
       List<BoardDTO> boardDTOList = boardService.search(q,q2);
        System.out.println(boardDTOList);
       model.addAttribute("boardDTOList",boardDTOList);
        return "board/search";
    }


}
