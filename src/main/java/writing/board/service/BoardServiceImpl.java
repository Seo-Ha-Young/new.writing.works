package writing.board.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import writing.board.dto.PageRequestDTO;
import writing.board.dto.PageResultDTO;
import writing.board.dto.PostWrittenDTO;
import writing.board.entity.PostWritten;
import writing.board.entity.QPostWritten;
import writing.board.repository.PostWrittenRepository;
import writing.board.repository.SearchRepository;


import java.util.List;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final PostWrittenRepository postRepository;
    private final SearchRepository searchRepository;

    @Override
    public PageResultDTO<PostWrittenDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("no").descending());
        Predicate booleanBuilder = getSearch(requestDTO);
        log.info("검색 타입 2" + booleanBuilder);
        Page<Object[]> result = searchRepository.searchPage(
                requestDTO.getType(),
                requestDTO.getKeyword(),
                requestDTO.getPageable(Sort.by("no").descending())
        );
        log.info("검색 결과 :" + result);
        Function<Object[], PostWrittenDTO> fn = (entity -> {
            log.info("정보"+entity[0]+"//"+entity[2]+"//"+entity[3]);
            PostWrittenDTO dto = entitiesToDTO((PostWritten) entity[0], (Long) entity[2], (Long) entity[3]);

            log.info("가져온 정보 " + dto);
            return dto;
        });
        log.info("검색 내용 " + requestDTO.getKeyword());
        log.info("검색 결과 2 " + fn);
        return new PageResultDTO<>(result, fn);

    }

//    @Override
//    public PageResultDTO<PostWrittenDTO, Object[]> getList(PageRequestDTO requestDTO) {
//        Pageable pageable = requestDTO.getPageable(Sort.by("no").descending());
//        Page<Object[]> result = postRepository.getListPage(pageable);
//        Function<Object[], PostWrittenDTO> fn = (entity -> entitiesToDTO(
//                (PostWritten) entity[0],
//                (Long) entity[1],
//                (Long) entity[2]
//        ));
//        return new PageResultDTO<>(result, fn);
//
//    }

    @Override
    public PostWrittenDTO read(Long no) {
        List<Object[]> result = postRepository.getPostWithAll(no);
        log.info("개별 정보"+"//"+result.get(0)[0]+"//"+result.get(0)[1]+"//"+result.get(0)[2]);
        PostWritten postWritten = (PostWritten) result.get(0)[0];
        Long goodCnt = (Long) result.get(0)[2];
        Long badCnt = (Long) result.get(0)[1];
        postRepository.getPostWritten_no(no);
        return entitiesToDTO(postWritten, goodCnt, badCnt);
    }

    @Override
    public void remove(Long postNo) {
        log.info("삭제할 번호 "+postNo);
        PostWritten post = postRepository.findPost_no(postNo);
        postRepository.delete(post);
    }


    private Predicate getSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        log.info("검색 타입 "+type);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QPostWritten qPostWritten = QPostWritten.postWritten;
        String keyword = requestDTO.getKeyword();
        BooleanExpression expression = qPostWritten.no.gt(0L);
        booleanBuilder.and(expression);
        if(type == null || type.trim().length() == 0) {
            return  booleanBuilder;
        }
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("t")){
            conditionBuilder.or(qPostWritten.post_name.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qPostWritten.post_content.contains(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qPostWritten.writer.contains(keyword));
        }
        booleanBuilder.and(conditionBuilder);
        log.info("검색할 내용 "+booleanBuilder);
        log.info("검색기능 작동 완료");
        return booleanBuilder;
    }

}
