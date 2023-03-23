package autoservice.model.dto.response;

import autoservice.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String description;
    private LocalDate takenDate;
    private List<Long> servicesId;
    private List<Long> productsId;
    private Order.Status status;
    private BigDecimal totalPrice;
    private LocalDate endDate;
}
