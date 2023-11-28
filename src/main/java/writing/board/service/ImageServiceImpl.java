package writing.board.service;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import writing.board.dto.ImageDTO;
import writing.board.dto.PageRequestDTO;
import writing.board.dto.PageResultDTO;
import writing.board.entity.Image;
import writing.board.entity.QImage;
import writing.board.repository.ImageRepository;


import javax.transaction.Transactional;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public Long register(ImageDTO imageDTO) {
        Map<String, Object> entityMap = dtoToEntity(imageDTO);
        Image image = (Image) entityMap.get("image");
        imageRepository.save(image);
        return image.getNo();
    }

    @Override
    public PageResultDTO<ImageDTO, Image> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("no").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);
        Page<Image> result = imageRepository.findAll(booleanBuilder, pageable);
        Function<Image, ImageDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QImage qImage= QImage.image;
        String keyword = requestDTO.getKeyword();
        BooleanExpression expression = qImage.no.gt(0L);
        booleanBuilder.and(expression);
        if(type == null || type.trim().length() == 0) {
            return  booleanBuilder;
        }
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("c")){
            conditionBuilder.or(qImage.img_name.contains(keyword));
        }
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }
}
