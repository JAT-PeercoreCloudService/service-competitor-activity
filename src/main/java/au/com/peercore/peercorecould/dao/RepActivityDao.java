package au.com.peercore.peercorecould.dao;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "repActivity")
@Data
@Builder
@ToString
public class RepActivityDao {

    @Id
    private String id;
    private String customerId;
    private String customerName;
    private String spId;
    private String spName;
    private String longitude;
    private String latitude;
    private Object photo;
    private String note;
}
