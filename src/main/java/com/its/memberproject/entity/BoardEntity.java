package com.its.memberproject.entity;

import com.its.memberproject.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
@Table(name = "boardProject_table")
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    @Column
    private String boardTitle;
    @Column
    private String boardWriter;
    @Column
    private String boardContents;
    @Column
    private int boardHits;

    @Column
    private String boardFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;
    @OneToMany(mappedBy = "boardEntity",cascade = CascadeType.ALL,orphanRemoval = true ,fetch=FetchType.LAZY)
     List<CommentEntity> commentEntityList = new ArrayList<>();
    public static BoardEntity save(BoardDTO boardDTO,MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardWriter(memberEntity.getMemberEmail());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        boardEntity.setBoardFileName(boardDTO.getBoardFileName());
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;

    }

    public static BoardEntity update(BoardDTO boardDTO,MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        boardEntity.setBoardFileName(boardDTO.getBoardFileName());
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }
}
