package writing.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import writing.board.entity.PostWritten;
import writing.board.entity.QMember;
import writing.board.entity.QPostWritten;
import writing.board.entity.QPreference;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Log4j2
public class SearchRepositoryImpl extends QuerydslRepositorySupport implements SearchRepository  {
    public SearchRepositoryImpl() {
        super(PostWritten.class);
    }
    @Override
    public PostWritten search1() {
        log.info("search1.....");

        QPostWritten written = QPostWritten.postWritten;
        QPreference preference = QPreference.preference;
        QMember member = QMember.member;

        JPQLQuery<PostWritten> jpqlQuery = from(written);
        jpqlQuery.leftJoin(member).on(written.writer.eq(member.nickname));
        jpqlQuery.leftJoin(preference).on(preference.post_written_no.eq(written.no));

      //  jpqlQuery.select(written).where(written.no.eq(1L));
      //  List<PostWritten> result = jpqlQuery.fetch();
        JPQLQuery<Tuple> tupleJPQLQuery = jpqlQuery.select(written, member.nickname, preference.good.count(), preference.bad.count());
        tupleJPQLQuery.groupBy(written);
        List<Tuple> result = tupleJPQLQuery.fetch();
        return null;
    }

    @Override
    @Transactional
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        log.info("검색시작");
        QPostWritten written = QPostWritten.postWritten;
        QPreference preference = QPreference.preference;
        QMember member = QMember.member;

        JPQLQuery<PostWritten> jpqlQuery = from(written);
        jpqlQuery.leftJoin(member).on(written.writer.eq(member.nickname));
        jpqlQuery.leftJoin(preference).on(preference.post_written_no.eq(written.no));

        JPQLQuery<Tuple> tupleJPQLQuery = jpqlQuery.select(written, member.nickname, preference.good.count(), preference.bad.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = written.no.gt(0L);

        booleanBuilder.and(expression);

        log.info("위치확인 1");
        if(type != null) {
            String[] typeArr = type.split("");

            //검색조건 작성
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for(String t : typeArr) {
                switch (t) {
                    case "t" :
                        conditionBuilder.or(written.post_name.contains(keyword));
                        break;
                    case "c" :
                        conditionBuilder.or(written.post_content.contains(keyword));
                        break;
                    case "w" :
                        conditionBuilder.or(written.writer.contains(keyword));
                        break;
                    }
                }
            booleanBuilder.and(conditionBuilder);
            }

            tupleJPQLQuery.where(booleanBuilder);

            log.info("위치확인 2");
            // order by
            Sort sort = pageable.getSort();

            // tuple.orderBy(written.no.desc());
            sort.stream().forEach(order -> {
                Order direction = order.isAscending()? Order.ASC:Order.DESC;

                String prop = order.getProperty();

                PathBuilder orderByExpression = new PathBuilder(PostWritten.class, "postWritten");

                tupleJPQLQuery.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
            });

            tupleJPQLQuery.groupBy(written);

            // page 처리
            tupleJPQLQuery.offset(pageable.getOffset());
            tupleJPQLQuery.limit(pageable.getPageSize());

            log.info("내용확인 : "+tupleJPQLQuery);
            List<Tuple> result = tupleJPQLQuery.fetch();
            log.info("result :"+result);

            Long count = tupleJPQLQuery.fetchCount(); // count 얻는 방법

            log.info("검색 끝");
            return new PageImpl<Object[]>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);

        }

    }



