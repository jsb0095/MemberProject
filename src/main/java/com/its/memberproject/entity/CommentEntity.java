package com.its.memberproject.entity;

import com.its.memberproject.dto.CommentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter@Setter
@Table(name = "commentProject_table")
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @Column
    private String commentWriter;
    @Column
    private  String commentContents;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id")
    private MemberEntity memberEntity;

    public static CommentEntity save(CommentDTO commentDTO,MemberEntity memberEntity,BoardEntity boardEntity) {
        CommentEntity commentEntity =new CommentEntity();
        commentEntity.setCommentWriter(boardEntity.getBoardWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);
        commentEntity.setMemberEntity(memberEntity);

        return commentEntity;
    }
}
