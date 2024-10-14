package au.com.peercore.peercorecould.service;

import au.com.peercore.peercorecould.dao.CompetitorActivityDao;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CompetitorActivityService {
   
   ResponseEntity<CompetitorActivityDao> saveActivity(CompetitorActivityDao activityDao);
   ResponseEntity<CompetitorActivityDao> getAllActivity(int pageNumber, int pageSize, Map<String, String> headers);
   ResponseEntity<CompetitorActivityDao> getSelectedCompetitor(String competitor);
}
