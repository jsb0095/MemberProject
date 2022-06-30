package com.its.memberproject.service;

import com.its.memberproject.dto.CommentDTO;
import com.its.memberproject.entity.BoardEntity;
import com.its.memberproject.entity.CommentEntity;
import com.its.memberproject.entity.MemberEntity;
import com.its.memberproject.repository.BoardRepository;
import com.its.memberproject.repository.CommentRepository;
import com.its.memberproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    public CommentDTO save(CommentDTO commentDTO) {
      MemberEntity memberEntity =  memberRepository.findByMemberEmail(commentDTO.getCommentWriter()).get();
       BoardEntity boardEntity = boardRepository.findById(commentDTO.getBoardId()).get();
        CommentEntity commentEntity =commentRepository.save(CommentEntity.save(commentDTO,memberEntity,boardEntity));
       return CommentDTO.save(commentEntity);
    }

    @Transactional
    public List<CommentDTO> findAll(Long board_id) {
      BoardEntity boardEntity =  boardRepository.findById(board_id).get();
      List<CommentEntity> commentEntityList = boardEntity.getCommentEntityList();
      List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
           commentDTOList.add(CommentDTO.toDTO(commentEntity));
        }
        return  commentDTOList;
    }
}
