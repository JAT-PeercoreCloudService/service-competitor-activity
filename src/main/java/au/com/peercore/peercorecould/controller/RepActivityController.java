package au.com.peercore.peercorecould.controller;

import au.com.peercore.peercorecould.dao.CommonConstants;
import au.com.peercore.peercorecould.dao.RepActivityDao;
import au.com.peercore.peercorecould.service.repActivity.RepActivityService;
import au.com.peercore.peercorecould.utils.OperationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/repActivity")
@CrossOrigin
public class RepActivityController {


    Logger logger = LoggerFactory.getLogger(CompetitorActivityController.class);

    @Autowired
    private RepActivityService repActivityService;

    @PostMapping("/saveRepActivity")
    public ResponseEntity<OperationResponse> saveRepActivity(@RequestBody RepActivityDao repActivityDao){
        logger.info("create competitor activity started");
        return ResponseEntity.ok().body(repActivityService.saveActivity(repActivityDao));
    }

    @GetMapping("/getAllRepActivity")
    public ResponseEntity<OperationResponse> getAllRepActivity(
            @RequestParam(defaultValue = CommonConstants.PAGE_NUMBER) int pageNumber,
            @RequestParam(defaultValue = CommonConstants.PAGE_SIZE) int pageSize,
            @RequestParam(defaultValue = "") String customerId,
            @RequestHeader Map<String, String> headers
    ){
        logger.info("create competitor activity started");
        return repActivityService.getAllActivity(pageNumber, pageSize, headers, customerId);
    }
}
