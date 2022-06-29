package com.its.memberproject.repository;

import com.its.memberproject.entity.BaseEntity;
import com.its.memberproject.entity.BoardEntity;
import com.its.memberproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    @Modifying
    @Query("update BoardEntity b set b.boardHits = b.boardHits+1 where b.id = :id")
    void hitsAdd(Long id);
}
