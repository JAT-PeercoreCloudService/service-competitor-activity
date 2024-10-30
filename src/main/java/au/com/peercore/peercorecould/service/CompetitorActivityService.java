package au.com.peercore.peercorecould.service;

import au.com.peercore.peercorecould.dao.CompetitorActivityDao;
import au.com.peercore.peercorecould.utils.OperationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CompetitorActivityService {

   OperationResponse saveActivity(CompetitorActivityDao activityDao);
   ResponseEntity<CompetitorActivityDao> getAllActivity(int pageNumber, int pageSize, Map<String, String> headers, String userName);
   ResponseEntity<CompetitorActivityDao> getSelectedCompetitor(String competitor);


}
