package autoservice.model.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderTotalPriceResponseDto {
    private BigDecimal totalPrice;
}
