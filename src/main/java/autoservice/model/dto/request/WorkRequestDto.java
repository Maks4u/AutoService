package autoservice.model.dto.request;

import autoservice.model.Work;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WorkRequestDto {
    private Long orderId;
    private Long masterId;
    @Min(0)
    private BigDecimal price;
    @NotNull
    private Work.Status status;
}
