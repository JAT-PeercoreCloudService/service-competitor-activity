package au.com.peercore.peercorecould.Controller;

import au.com.peercore.peercorecould.dao.CompetitorActivityDao;
import au.com.peercore.peercorecould.service.CompetitorActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/competitorActivity")
public class CompetitorActivityController {

    @Autowired
    private CompetitorActivityService competitorActivityService;

    @PostMapping("/saveActivityReturn")
    public ResponseEntity SaveActivityWithReturn(@RequestBody CompetitorActivityDao activityDao){
        return competitorActivityService.saveActivity(activityDao);
    }

    @GetMapping("/getAllActivity")
    public ResponseEntity getAllActivity(){
        return competitorActivityService.getALlActivity();
    }

    @PostMapping("/getSelectedCompetitor")
    public ResponseEntity getSelectedCompetitor(@RequestBody String competitor){
        return competitorActivityService.getSelectedCompetitor(competitor);
    }
}
