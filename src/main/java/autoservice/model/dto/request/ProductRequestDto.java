package autoservice.model.dto.request;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotNull
    private String name;
    @Min(0)
    private BigDecimal price;
}
