package au.com.peercore.peercorecould.service;

import au.com.peercore.peercorecould.controller.CompetitorActivityController;
import au.com.peercore.peercorecould.dao.CompetitorActivityDao;
import au.com.peercore.peercorecould.dao.ResponseDao;
import au.com.peercore.peercorecould.repo.CompetitorRepo;
import au.com.peercore.peercorecould.utils.OperationResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CompetitorActivityServiceImpl implements CompetitorActivityService{

    Logger logger = LoggerFactory.getLogger(CompetitorActivityServiceImpl.class);

    @Autowired
    private CompetitorRepo competitorRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseDao responseDao;

    @Override
    public OperationResponse saveActivity(CompetitorActivityDao activityDao){

        try{
            logger.info("save activity payload :: {}", activityDao);
            CompetitorActivityDao data = competitorRepo.save(modelMapper.map(activityDao, CompetitorActivityDao.class));
            return OperationResponse.builder()
                    .message("Activity Successfully Added")
                    .statusCode(0)
                    .object(data)
                    .build();

        } catch (Exception e){
            logger.info("Error in saving activity :: {}",e.getMessage());
            return OperationResponse.builder()
                    .message("Error in saving activity")
                    .statusCode(-1)
                    .build();
        }
    }

    @Override
    public ResponseEntity getAllActivity(int pageNumber, int pageSize, Map<String, String> headers, String userName){

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        String tenantId = headers.get("tenant");

        Page<CompetitorActivityDao> allActivity;

        try{
            if(userName.isEmpty()){
                allActivity = competitorRepo.findAll(pageable);
            } else {
                allActivity = competitorRepo.getResultByKeyword(userName, pageable);
            }

            System.out.println("resutl of allActivity: "+allActivity);
            //Page<CompetitorActivityDao> allActivity = competitorRepo.findAllByTenantId(tenantId, pageable);
            responseDao.setCode("200");
            responseDao.setMessage("Successfully fetched data");
//            responseDao.setContent(modelMapper.map(allActivity, new TypeToken<ArrayList<CompetitorActivityDao>>(){}.getType()));
            responseDao.setContent(allActivity);
            return new ResponseEntity(responseDao, HttpStatus.ACCEPTED);
        } catch (Exception e){
            responseDao.setCode("500");
            responseDao.setMessage("Something goes wrong");
            return new ResponseEntity(responseDao, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<CompetitorActivityDao> getSelectedCompetitor(String competitor) {
        //CompetitorActivityDao allActivity = competitorRepo.findById(competitor).orElse(null);.=
        //CompetitorActivityDao allActivity = competitorRepo.existsByCompetitor(competitor);
        logger.info("Inside of getSelectedCompetitor at service IMPL");
        CompetitorActivityDao allActivity = competitorRepo.findById(competitor).orElse(null);
        logger.info("Competitor Repo successfully passed: "+allActivity);

        responseDao.setCode("200");
        responseDao.setMessage("Successfully fetched data");
        responseDao.setContent(allActivity);
        logger.info("Return Data set: "+responseDao);
        return new ResponseEntity(responseDao, HttpStatus.ACCEPTED);
    }
}
