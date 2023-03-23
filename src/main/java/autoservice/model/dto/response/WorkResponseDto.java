package autoservice.model.dto.response;

import autoservice.model.Work;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class WorkResponseDto {
    private Long id;
    private Long orderId;
    private Long masterId;
    private BigDecimal price;
    private Work.Status status;
}
