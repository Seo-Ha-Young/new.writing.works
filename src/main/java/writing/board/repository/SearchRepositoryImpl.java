package writing.board.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import writing.board.entity.PostWritten;
import writing.board.entity.QMember;
import writing.board.entity.QPostWritten;
import writing.board.entity.QPreference;

import java.util.List;

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

}
