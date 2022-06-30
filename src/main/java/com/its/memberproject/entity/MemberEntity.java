package com.its.memberproject.entity;

import com.its.memberproject.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
@Table(name = "memberProject_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true)
    private String memberEmail;
    @Column(nullable = false)
    private String memberPassword;
    @Column
    private String memberName;
    @Column
    private String memberMobile;
    @Column
    private String memberProfileName;
    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static MemberEntity save(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberProfileName(memberDTO.getMemberProfileName());
        return memberEntity;
    }

    public static MemberEntity update(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberProfileName(memberDTO.getMemberProfileName());
        return memberEntity;
    }

}
