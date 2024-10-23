package au.com.peercore.peercorecould.repo;

import au.com.peercore.peercorecould.dao.CompetitorActivityDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompetitorRepo extends MongoRepository<CompetitorActivityDao, String> {

    CompetitorActivityDao existsByCompetitor(String competitor);
    Page<CompetitorActivityDao> findAllByCompetitor(String tenantId, Pageable pageable);


}
