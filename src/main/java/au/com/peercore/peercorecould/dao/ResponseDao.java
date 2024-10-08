package au.com.peercore.peercorecould.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResponseDao {
    private String Code;
    private String message;
    private Object content;
}
