package au.com.peercore.peercorecould.controller;

import au.com.peercore.peercorecould.dao.CommonConstants;
import au.com.peercore.peercorecould.dao.CompetitorActivityDao;
import au.com.peercore.peercorecould.service.CompetitorActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("v1/competitorActivity")
@CrossOrigin
public class CompetitorActivityController {

    Logger logger = LoggerFactory.getLogger(CompetitorActivityController.class);

    @Autowired
    private CompetitorActivityService competitorActivityService;

    @PostMapping("/saveActivityReturn")
    public ResponseEntity SaveActivityWithReturn(@RequestBody CompetitorActivityDao activityDao){
        logger.info("create competitor activity started");
        return competitorActivityService.saveActivity(activityDao);
    }

    @GetMapping("/getAllActivity")
    public ResponseEntity getAllActivity(
            @RequestParam(defaultValue = CommonConstants.PAGE_NUMBER) int pageNumber,
            @RequestParam(defaultValue = CommonConstants.PAGE_SIZE) int pageSize,
            @RequestHeader Map<String, String> headers){
        logger.info("get all competitor activity records started");
        return competitorActivityService.getAllActivity(pageNumber, pageSize, headers);
    }

    @GetMapping("/getSelectedCompetitor/{id}")
    public ResponseEntity getSelectedCompetitor(@PathVariable String id){
        logger.info("get selected ID competitor activity records started. Id is ");
        return competitorActivityService.getSelectedCompetitor(id);
    }
}
