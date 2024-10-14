package au.com.peercore.peercorecould.Controller;

import au.com.peercore.peercorecould.dao.CommonConstants;
import au.com.peercore.peercorecould.dao.CompetitorActivityDao;
import au.com.peercore.peercorecould.service.CompetitorActivityService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("v1/competitorActivity")
public class CompetitorActivityController {

    @Autowired
    private CompetitorActivityService competitorActivityService;

    @PostMapping("/saveActivityReturn")
    public ResponseEntity SaveActivityWithReturn(@RequestBody CompetitorActivityDao activityDao){
        return competitorActivityService.saveActivity(activityDao);
    }

    @GetMapping("/getAllActivity")

    public ResponseEntity getAllActivity(
            @RequestParam(defaultValue = CommonConstants.PAGE_NUMBER) int pageNumber,
            @RequestParam(defaultValue = CommonConstants.PAGE_SIZE) int pageSize,
            @RequestHeader Map<String, String> headers){
        String tenantId = headers.get("tenant");
        if (StringUtils.isEmpty(tenantId)) {
            return utils.OperationResponse.tenantNullMessage();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return competitorActivityService.getALlActivity();
    }

    @PostMapping("/getSelectedCompetitor")
    public ResponseEntity getSelectedCompetitor(@RequestBody String competitor){
        return competitorActivityService.getSelectedCompetitor(competitor);
    }
}
