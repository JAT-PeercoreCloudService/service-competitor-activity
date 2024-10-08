package au.com.peercore.peercorecould.service;

import au.com.peercore.peercorecould.dao.CompetitorActivityDao;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompetitorActivityService {
   
   ResponseEntity<CompetitorActivityDao> saveActivity(CompetitorActivityDao activityDao);
   ResponseEntity<CompetitorActivityDao> getALlActivity();
   ResponseEntity<CompetitorActivityDao> getSelectedCompetitor(String competitor);
}
