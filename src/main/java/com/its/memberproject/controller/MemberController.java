package com.its.memberproject.controller;

import com.its.memberproject.dto.MemberDTO;
import com.its.memberproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
      MemberDTO memberDTO1 =  memberService.loginCheck(memberDTO);
      if(memberDTO1 !=null){
        httpSession.setAttribute("memberEmail",memberDTO1.getMemberEmail());
        httpSession.setAttribute("id",memberDTO1.getId());
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
    @GetMapping("/myPage")
    public String myPage(HttpSession httpSession,Model model){
        Long id= (Long) httpSession.getAttribute("id");
      MemberDTO memberDTO = memberService.findById(id);
       model.addAttribute("memberDTO",memberDTO);
       return "member/myPage";
    }
    @GetMapping("/delete{id}")
    public String delete(@PathVariable Long id){
        memberService.deleteById(id);
        return "redirect:/member/logout";
    }
    @GetMapping("/updateForm{id}")
    public String updateForm(@PathVariable Long id,Model model){
       MemberDTO memberDTO = memberService.findById(id);
       model.addAttribute("memberData",memberDTO);
       return "member/updateForm";

    }
    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
    return "redirect:/member/myPage";
    }
}
