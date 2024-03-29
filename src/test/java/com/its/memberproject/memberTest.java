package com.its.memberproject;

import com.its.memberproject.dto.MemberDTO;
import com.its.memberproject.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class memberTest {
    @Autowired private MemberService memberService;
    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("member/save 테스트")
    public void saveTest(){
        MemberDTO memberDTO = new MemberDTO("이메일@이메일","비밀번호1","이름",
                "010-1111-2222");
    }
    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("member/login 테스트")
    public void loginTest(){
      MemberDTO memberDTO = memberService.findById(2L);
     String testEmail = memberService.findByMemberEmail(memberDTO.getMemberEmail());
        assertThat(testEmail).isEqualTo(memberDTO.getMemberEmail());
    }
    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("member/delete 테스트")
    public void deleteTest(){

        memberService.deleteById(2L);
        MemberDTO memberDTO = memberService.findById(2L);
        assertThat(memberDTO).isNull();

    }
    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("member/update 테스트")
    public void updateTest(){
        MemberDTO memberDTO = memberService.findById(2L);
       MemberDTO updateDTO = new MemberDTO(memberDTO.getId(),memberDTO.getMemberEmail());
       updateDTO.setMemberEmail("3L");
       memberService.findById(updateDTO.getId());
       assertThat(memberDTO.getMemberEmail()).isNotEqualTo(updateDTO.getMemberEmail());
    }

}
