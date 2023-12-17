package writing.board.service;

import com.querydsl.core.BooleanBuilder;
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


import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final PostWrittenRepository postRepository;

    @Override
    public PageResultDTO<PostWrittenDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("no").descending());
        Page<Object[]> result = postRepository.getListPage(pageable);
        Function<Object[], PostWrittenDTO> fn = (entity -> entitiesToDTO(
                (PostWritten) entity[0],
                (Long) entity[1],
                (Long) entity[2]
        ));
        return new PageResultDTO<>(result, fn);

    }

    @Override
    public PostWrittenDTO read(Long no) {
        List<Object[]> result = postRepository.getPostWithAll(no);
        PostWritten postWritten = (PostWritten) result.get(0)[0];
        Long goodCnt = (Long) result.get(0)[1];
        Long badCnt = (Long) result.get(0)[2];
        postRepository.getPostWritten_no(no);
        return entitiesToDTO(postWritten, goodCnt, badCnt);
    }

    @Override
    public void remove(Long postNo) {
        log.info("삭제할 번호 "+postNo);
        PostWritten post = postRepository.findPost_no(postNo);
        postRepository.delete(post);
    }


    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QPostWritten qPostWritten = QPostWritten.postWritten;
        String keyword = requestDTO.getKeyword();
        BooleanExpression expression = qPostWritten.no.gt(0L);
        booleanBuilder.and(expression);
        if(type == null || type.trim().length() == 0) {
            return  booleanBuilder;
        }
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("c")){
            conditionBuilder.or(qPostWritten.post_content.contains(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qPostWritten.writer.contains(keyword));
        }
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

}
