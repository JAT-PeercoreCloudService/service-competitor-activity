package au.com.peercore.peercorecould.dao;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "activity")
@Data
@Builder
@ToString
public class CompetitorActivityDao {

    @Id
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String competitor;
    private String territory;
    private String promotion;
    private String promotionType;
    private String product;
    private Object photo;
    private String notes;
    private String createdDateTime;
    private String latitude;
    private String longitude;

}
