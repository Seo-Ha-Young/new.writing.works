package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import writing.board.dto.PreferenceDTO;
import writing.board.entity.Preference;
import writing.board.service.PreferenceService;


import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@Log4j2
@RequestMapping("/like")
@RequiredArgsConstructor
public class PreferenceController {
    private final PreferenceService preferenceService;

    @GetMapping("/{post_no}/all")
    public ResponseEntity<List<PreferenceDTO>> getLikeList(@PathVariable("post_no")  Long post_no) {
        log.info("추천번호정보"+post_no);
        List<PreferenceDTO> preferenceDTOList = preferenceService.find_post_no(post_no);
        return new ResponseEntity<>(preferenceDTOList, HttpStatus.OK);
    }

    @PostMapping("/{post_no}/id")
    public ResponseEntity<String> findNicknameWithLike(@RequestBody PreferenceDTO preferenceDTO) throws Exception {
        List<Preference> nickname = preferenceService.find_nickname(preferenceDTO);
        log.info("추천 닉네임 정보 : "+preferenceDTO.getId());
        log.info("DB 추천 닉네임 정보 : "+nickname.isEmpty());
        String word;
        if(nickname.isEmpty() == true){
            word = "아직 기록이 없습니다";
        } else {
            if(nickname.get(0).getGood() == null )
                word = "이미 비추천을 했습니다";
            else {
                word = "이미 추천을 했습니다.";
            }
        }
        return new ResponseEntity<>(word, HttpStatus.OK);
    }
    @PostMapping("/{post_no}/good")
    public ResponseEntity<String> addGoodCnt(@RequestBody PreferenceDTO preferenceDTO) throws Exception {
        log.info("add Preference");
        log.info("추천 정보:"+preferenceDTO);
        List<Preference> nickname = preferenceService.find_nickname(preferenceDTO);
        log.info("닉네임이 이미 존재하는지 확인"+ nickname);
        String word;
        if(nickname.isEmpty()) {
            Preference preference = preferenceService.register(preferenceDTO);
            word = "이 글을 추천하셨습니다.";
            log.info("저장된 추천 정보 : "+preference);
        } else {
            preferenceService.remove(preferenceDTO.getId());
            word = "추천을 취소하셨습니다";
        }
        return new ResponseEntity<>(word, HttpStatus.OK);
    }
    @PostMapping("/{post_no}/bad")
    public ResponseEntity<String> addBodCnt(@RequestBody PreferenceDTO preferenceDTO) throws Exception {
        log.info("add Preference");
        log.info("추천 정보:"+preferenceDTO);
        List<Preference> nickname = preferenceService.find_nickname(preferenceDTO);
        log.info("닉네임이 이미 존재하는지 확인"+ nickname);
        String word;
        if(nickname.isEmpty()) {
            Preference preference = preferenceService.register(preferenceDTO);
            word = "이 글을 비추천하셨습니다.";
            log.info("저장된 추천 정보 : "+preference);
        } else {
            preferenceService.remove(preferenceDTO.getId());
            word = "비추천을 취소하셨습니다";
        }
        return new ResponseEntity<>(word, HttpStatus.OK);
    }

}
