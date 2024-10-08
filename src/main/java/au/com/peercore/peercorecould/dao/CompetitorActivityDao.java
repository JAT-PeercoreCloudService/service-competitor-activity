package au.com.peercore.peercorecould.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "activity")
@Data
@Builder
public class CompetitorActivityDao {

    @Id
    private String id;
    private String competitor;
    private String territory;
    private String promotion;
    private String promotionType;
    private String product;
    private Object photo;
    private String notes;

}
