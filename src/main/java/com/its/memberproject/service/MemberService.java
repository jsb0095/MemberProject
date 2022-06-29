package com.its.memberproject.service;

import com.its.memberproject.dto.MemberDTO;
import com.its.memberproject.entity.MemberEntity;
import com.its.memberproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long save(MemberDTO memberDTO) throws IOException {
        MultipartFile profileFile = memberDTO.getMemberProfileFile();
        String profileFileName = profileFile.getOriginalFilename();
        profileFileName = System.currentTimeMillis()+"_"+profileFileName;
        String savePath = "C:\\Springboot_img\\" +profileFileName;
        if(profileFile.isEmpty()){
            profileFile.transferTo(new File(savePath));
        }
        memberDTO.setMemberProfileName(profileFileName);
      MemberEntity memberEntity = MemberEntity.save(memberDTO);
        Long saveId =  memberRepository.save(memberEntity).getId();
        return saveId;
    }

    public MemberDTO findById(Long id) {
      Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);
      if(memberEntityOptional.isPresent()){
          MemberEntity memberEntity = memberEntityOptional.get();
          return MemberDTO.findById(memberEntity);
      }else {
          return null;
      }
    }

    public String findByMemberEmail(String memberEmail) {
     Optional<MemberEntity> memberEntityOptional = memberRepository.findByMemberEmail(memberEmail);
     if(memberEntityOptional.isPresent()){
         MemberEntity memberEntity = memberEntityOptional.get();
        return MemberDTO.findByMemberEmail(memberEntity).getMemberEmail();
     }else {
         return null;
     }
    }

    public MemberDTO loginCheck(MemberDTO memberDTO) {
   Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmailAndMemberPassword
           (memberDTO.getMemberEmail(), memberDTO.getMemberPassword());
   if(optionalMemberEntity.isPresent()){
       MemberEntity memberEntity = optionalMemberEntity.get();
      return MemberDTO.findById(memberEntity);
   }else {
       return null;
   }



    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public void update(MemberDTO memberDTO) {
       MemberEntity memberEntity= MemberEntity.update(memberDTO);
       memberRepository.save(memberEntity);
    }
}
