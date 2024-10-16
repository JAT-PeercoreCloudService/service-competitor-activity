package au.com.peercore.peercorecould.service;

import au.com.peercore.peercorecould.dao.CompetitorActivityDao;
import au.com.peercore.peercorecould.dao.ResponseDao;
import au.com.peercore.peercorecould.repo.CompetitorRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CompetitorActivityServiceImpl implements CompetitorActivityService{

    @Autowired
    private CompetitorRepo competitorRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseDao responseDao;

    @Override
    public ResponseEntity saveActivity(CompetitorActivityDao activityDao){

        try{
            CompetitorActivityDao data = competitorRepo.save(modelMapper.map(activityDao, CompetitorActivityDao.class));
            responseDao.setCode("200");
            responseDao.setMessage("Activity Successfully Added");
            responseDao.setContent(data);

            return new ResponseEntity(responseDao, HttpStatus.ACCEPTED);
        } catch (Exception e){
            responseDao.setCode("200");
            responseDao.setMessage("Something Goes Wrong");
            return new ResponseEntity(responseDao, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity getAllActivity(int pageNumber, int pageSize, Map<String, String> headers){

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        String tenantId = headers.get("tenant");
        System.out.println("tenantId: "+tenantId);

        try{
            Page<CompetitorActivityDao> allActivity = competitorRepo.findAll(pageable);
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
        System.out.println("Send competitor: "+competitor);

//        CompetitorActivityDao allActivity = competitorRepo.findById(competitor).orElse(null);.=
        CompetitorActivityDao allActivity = competitorRepo.existsByCompetitor(competitor);

        System.out.println("Send allActivity: "+allActivity);
        responseDao.setCode("200");
        responseDao.setMessage("Successfully fetched data");
//        responseDao.setContent(modelMapper.map(allActivity, new TypeToken<ArrayList<CompetitorActivityDao>>(){}.getType()));
        return new ResponseEntity(responseDao, HttpStatus.ACCEPTED);
    }
}
