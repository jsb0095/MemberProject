package com.its.memberproject.entity;

import com.its.memberproject.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
@Table(name = "memberProject_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "memberEmail",unique = true)
    private String memberEmail;
    @Column(name = "memberPassword",nullable = false)
    private String memberPassword;
    @Column(name ="memberName")
    private String memberName;
    @Column(name = "memberMobile")
    private String memberMobile;
    @Column(name = "memberProfileName")
    private String memberProfileName;

    public static MemberEntity save(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberProfileName(memberDTO.getMemberProfileName());
        return memberEntity;
    }
}
