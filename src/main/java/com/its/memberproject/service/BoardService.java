package com.its.memberproject.service;

import com.its.memberproject.common.PagingConst;
import com.its.memberproject.dto.BoardDTO;
import com.its.memberproject.dto.MemberDTO;
import com.its.memberproject.entity.BaseEntity;
import com.its.memberproject.entity.BoardEntity;
import com.its.memberproject.entity.MemberEntity;
import com.its.memberproject.repository.BoardRepository;
import com.its.memberproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Long save(BoardDTO boardDTO) throws IOException {
        MultipartFile profileFile = boardDTO.getBoardFile();
        String profileFileName = profileFile.getOriginalFilename();
        profileFileName = System.currentTimeMillis()+"_"+profileFileName;
        String savePath = "C:\\Springboot_img\\" +profileFileName;
        if(!profileFile.isEmpty()){
            profileFile.transferTo(new File(savePath));
        }
        boardDTO.setBoardFileName(profileFileName);
        MemberEntity memberEntity=  memberRepository.findByMemberEmail(boardDTO.getBoardWriter()).get();

        BoardEntity boardEntity = BoardEntity.save(boardDTO,memberEntity);
        Long saveId =  boardRepository.save(boardEntity).getId();
        return saveId;

    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();

        page = (page == 1)? 0: (page-1);
        Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));

        Page<BoardDTO> boardList = boardEntities.map(


                board -> new BoardDTO(board.getId(),
                        board.getBoardTitle(),
                        board.getBoardWriter(),
                        board.getBoardContents(),
                        board.getBoardHits(),
                        board.getBoardCreatedDate(),
                        board.getBoardFileName()

                ));
        return boardList;
    }

    public List<BoardDTO> findAll() {
       List<BoardEntity> boardEntityList = boardRepository.findAll();
       List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity: boardEntityList
             ) {
           BoardDTO boardDTO= BoardDTO.findById(boardEntity);
           boardDTOList.add(boardDTO);
        }
        return boardDTOList;
    }


    @Transactional
    public BoardDTO  findById(Long id) {
        boardRepository.hitsAdd(id);
       Optional<BoardEntity> boardEntityOptional = boardRepository.findById(id);
       if(boardEntityOptional.isPresent()){
           BoardEntity boardEntity = boardEntityOptional.get();
          return BoardDTO.findById(boardEntity);
       }else {
           return null;
       }
    }

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

    public void update(BoardDTO boardDTO) {
       BoardEntity boardEntity = BoardEntity.update(boardDTO);
       boardRepository.save(boardEntity);
    }

    public List<BoardDTO> search(String q,String q2) {
        if (q2.equals("boardTitle")) {
        List<BoardEntity> boardEntityList = boardRepository.findByBoardTitleContaining(q);
            List<BoardDTO> boardDTOList = new ArrayList<>();
            for (BoardEntity boardEntity: boardEntityList
            ) {BoardEntity boardEntity1= boardEntity;
                BoardDTO boardDTO =   BoardDTO.search(boardEntity1);
                boardDTOList.add(boardDTO);

            }
            return boardDTOList;
        }else {
            List<BoardEntity> boardEntityList = boardRepository.findByBoardWriterContaining(q);
            List<BoardDTO> boardDTOList = new ArrayList<>();
            for (BoardEntity boardEntity : boardEntityList
            ) {
                BoardEntity boardEntity1 = boardEntity;
                BoardDTO boardDTO = BoardDTO.search(boardEntity1);
                boardDTOList.add(boardDTO);

            }
            return boardDTOList;
        }


    }
}

