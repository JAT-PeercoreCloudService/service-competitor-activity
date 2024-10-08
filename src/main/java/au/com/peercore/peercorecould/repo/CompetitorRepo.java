package au.com.peercore.peercorecould.repo;

import au.com.peercore.peercorecould.dao.CompetitorActivityDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitorRepo extends MongoRepository<CompetitorActivityDao, String> {
    List<CompetitorActivityDao> existsByCompetitor(String competitor);
}
