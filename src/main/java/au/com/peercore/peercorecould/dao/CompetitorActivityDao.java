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
    private String Id;
    private String Competitor;
    private String Territory;
    private String Promotion;
    private String PromotionType;
    private String Product;
    private Object Photo;
    private String Notes;

}
