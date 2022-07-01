package com.its.memberproject.dto;

import com.its.memberproject.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberMobile;
    private MultipartFile memberProfileFile;
    private String memberProfileName;

    public MemberDTO(String memberEmail, String memberPassword, String memberName, String memberMobile) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberMobile = memberMobile;

    }

    public MemberDTO() {

    }

    public MemberDTO(Long id, String memberEmail) {
        this.id = id;
        this.memberEmail = memberEmail;
    }

    public MemberDTO(String memberEmail) {

    }

    public static MemberDTO findById(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberMobile(memberEntity.getMemberMobile());
        memberDTO.setMemberProfileName(memberEntity.getMemberProfileName());
        return memberDTO;

    }

    public static MemberDTO findByMemberEmail(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        return memberDTO;

    }

    public static MemberDTO searchMemberById(MemberEntity memberEntity) {
        MemberDTO memberDTO =new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        return memberDTO;
    }

//    public static MemberDTO loginCheck(MemberEntity memberEntity) {
//        MemberDTO memberDTO =new MemberDTO();
//        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
//        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
//        return memberDTO;
//    }
}
