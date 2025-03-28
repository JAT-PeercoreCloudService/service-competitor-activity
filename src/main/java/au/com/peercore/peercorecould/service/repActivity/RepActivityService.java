package au.com.peercore.peercorecould.service.repActivity;


import au.com.peercore.peercorecould.dao.CompetitorActivityDao;
import au.com.peercore.peercorecould.dao.RepActivityDao;
import au.com.peercore.peercorecould.utils.OperationResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface RepActivityService{

    OperationResponse saveActivity(RepActivityDao repActivityDao);
    ResponseEntity<OperationResponse> getAllActivity(int pageNumber, int pageSize, Map<String, String> headers, String userName);
    ResponseEntity<OperationResponse> getSelectedCompetitor(String competitor);
}
