package au.com.peercore.peercorecould.repo;

import au.com.peercore.peercorecould.dao.CompetitorActivityDao;
import au.com.peercore.peercorecould.dao.RepActivityDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RepActivityRepo extends MongoRepository<RepActivityDao, String> {
    @Query("{'customerId': { '$regex': ?0, '$options': 'i' }}")
    Page<RepActivityDao> getResultByKeyword(String userName, Pageable pageable);
}
