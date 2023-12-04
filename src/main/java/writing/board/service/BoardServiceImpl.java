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


import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final PostWrittenRepository postRepository;

    @Override
    public PageResultDTO<PostWrittenDTO, PostWritten> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("no").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);
        Page<PostWritten> result = postRepository.findAll(booleanBuilder, pageable);
        Function<PostWritten, PostWrittenDTO> fn = (entity -> entitiesToDTO(entity));
        return new PageResultDTO<>(result, fn);

    }

    @Override
    public PostWrittenDTO read(Long no) {
        Optional<PostWritten> result = postRepository.findById(no);
        postRepository.getPostWritten_no(no);
        return result.isPresent() ? entitiesToDTO(result.get()) : null;
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
