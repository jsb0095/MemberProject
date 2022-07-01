package com.its.memberproject.repository;

import com.its.memberproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    Optional<MemberEntity> findByMemberEmail (String memberEmail);
    Optional<MemberEntity> findByMemberEmailAndMemberPassword(String memberEmail, String memberPassword);

    MemberEntity searchMemberById(long parseLong);
}
