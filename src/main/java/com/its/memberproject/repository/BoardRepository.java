package com.its.memberproject.repository;

import com.its.memberproject.entity.BaseEntity;
import com.its.memberproject.entity.BoardEntity;
import com.its.memberproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
}
