package com.its.memberproject.controller;

import com.its.memberproject.dto.MemberDTO;
import com.its.memberproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/saveForm")
    public String saveForm(){
        return "member/saveForm";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) throws IOException {
        memberService.save(memberDTO);
        return "member/loginForm";
    }
    @GetMapping("/loginForm")
    public String loginForm(){
        return "member/loginForm";
    }
    @PostMapping("/login")
    public String login (@ModelAttribute MemberDTO memberDTO, HttpSession httpSession){
        httpSession.setAttribute("memberEmail",memberDTO.getMemberEmail());
        httpSession.setAttribute("id",memberDTO.getId());
      MemberDTO memberDTO1 =  memberService.loginCheck(memberDTO);
      if(memberDTO1 !=null){
          return "index";
      }else {
          return "member/loginForm";
      }
    }
    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "index";
    }
}
