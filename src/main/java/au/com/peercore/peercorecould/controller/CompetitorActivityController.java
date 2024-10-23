package au.com.peercore.peercorecould.controller;

import au.com.peercore.peercorecould.dao.CommonConstants;
import au.com.peercore.peercorecould.dao.CompetitorActivityDao;
import au.com.peercore.peercorecould.service.CompetitorActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("v1/competitorActivity")
@CrossOrigin
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
        System.out.println("CAME HERE");
        return competitorActivityService.getAllActivity(pageNumber, pageSize, headers);
    }

    @GetMapping("/getSelectedCompetitor/{id}")
    public ResponseEntity getSelectedCompetitor(@PathVariable String id){
        return competitorActivityService.getSelectedCompetitor(id);
    }
}
