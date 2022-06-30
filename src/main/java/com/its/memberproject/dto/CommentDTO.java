package com.its.memberproject.dto;

import com.its.memberproject.entity.CommentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private Long boardId;
    private String commentWriter;
    private String commentContents;
    private LocalDateTime commentCreatedDate;

    public static CommentDTO save(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentCreatedDate(commentEntity.getBoardCreatedDate());
        return commentDTO;
    }

    public static CommentDTO toDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentCreatedDate(commentEntity.getBoardCreatedDate());
        return commentDTO;

    }
}
