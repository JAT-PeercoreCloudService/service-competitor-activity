package au.com.peercore.peercorecould.service.repActivity;

import au.com.peercore.peercorecould.dao.CompetitorActivityDao;
import au.com.peercore.peercorecould.dao.RepActivityDao;
import au.com.peercore.peercorecould.dao.ResponseDao;
import au.com.peercore.peercorecould.repo.RepActivityRepo;
import au.com.peercore.peercorecould.utils.OperationResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class RepServiceImpl implements RepActivityService{

    Logger logger = LoggerFactory.getLogger(RepServiceImpl.class);

    @Autowired
    private RepActivityRepo repActivityRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseDao responseDao;

    @Override
    public OperationResponse saveActivity(RepActivityDao repActivityDao) {
        try{
            logger.info("save activity payload :: {}", repActivityDao);
            RepActivityDao data = repActivityRepo.save(modelMapper.map(repActivityDao, RepActivityDao.class));
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
    public ResponseEntity<OperationResponse> getAllActivity(int pageNumber,
                                                            int pageSize,
                                                            Map<String, String> headers,
                                                            String userName) {

        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(Sort.Direction.DESC, "id")  // 👈 Sort by id descending
        );

        Page<RepActivityDao> allActivity;

        try {
            if (!StringUtils.hasText(userName)) {
                allActivity = repActivityRepo.findAll(pageable);
            } else {
                allActivity = repActivityRepo.getResultByKeyword(userName, pageable);
            }

            responseDao.setCode("200");
            responseDao.setMessage("Successfully fetched data");
            responseDao.setContent(allActivity);
            return new ResponseEntity(responseDao, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            responseDao.setCode("500");
            responseDao.setMessage("Something went wrong");
            return new ResponseEntity(responseDao, HttpStatus.BAD_REQUEST);
        }
    }

//    public ResponseEntity<OperationResponse> getAllActivity(int pageNumber, int pageSize, Map<String, String> headers, String userName) {
//
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        String tenantId = headers.get("tenant");
//
//        Page<RepActivityDao> allActivity;
//
//        try{
//            if(userName.isEmpty()){
//                allActivity = repActivityRepo.findAll(pageable);
//            } else {
//                allActivity = repActivityRepo.getResultByKeyword(userName, pageable);
//            }
//
//            responseDao.setCode("200");
//            responseDao.setMessage("Successfully fetched data");
//
//            responseDao.setContent(allActivity);
//            return new ResponseEntity(responseDao, HttpStatus.ACCEPTED);
//        } catch (Exception e){
//            responseDao.setCode("500");
//            responseDao.setMessage("Something goes wrong");
//            return new ResponseEntity(responseDao, HttpStatus.BAD_REQUEST);
//        }
//
//    }

    @Override
    public ResponseEntity<OperationResponse> getSelectedCompetitor(String competitor) {

        logger.info("Inside of getSelectedCompetitor at service IMPL");
        RepActivityDao allActivity = repActivityRepo.findById(competitor).orElse(null);
        logger.info("Competitor Repo successfully passed: "+allActivity);

        responseDao.setCode("200");
        responseDao.setMessage("Successfully fetched data");
        responseDao.setContent(allActivity);
        logger.info("Return Data set: "+responseDao);
        return new ResponseEntity(responseDao, HttpStatus.ACCEPTED);

    }

}
