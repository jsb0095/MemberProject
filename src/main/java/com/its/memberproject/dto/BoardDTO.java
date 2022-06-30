package com.its.memberproject.dto;

import com.its.memberproject.entity.BoardEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedDate;
    private MultipartFile boardFile;
    private String boardFileName;

    public BoardDTO(Long id, String boardTitle, String boardWriter, String boardContents, int boardHits, LocalDateTime boardCreatedDate, String boardFileName) {
    }




    public static BoardDTO findById(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedDate(boardEntity.getBoardCreatedDate());
        boardDTO.setBoardFileName(boardEntity.getBoardFileName());
        return boardDTO;

    }


    public static BoardDTO search(BoardEntity boardEntity1) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity1.getId());
        boardDTO.setBoardTitle(boardEntity1.getBoardTitle());
        boardDTO.setBoardWriter(boardEntity1.getBoardWriter());
        boardDTO.setBoardContents(boardEntity1.getBoardContents());
        boardDTO.setBoardHits(boardEntity1.getBoardHits());
        boardDTO.setBoardCreatedDate(boardEntity1.getBoardCreatedDate());
        boardDTO.setBoardFileName(boardEntity1.getBoardFileName());
        return boardDTO;
    }
}
