package autoservice.model.dto.request;

import autoservice.model.Order;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDto {
    private Long carId;
    private String description;
    @NotNull
    private LocalDate takenDate;
    private List<Long> services;
    private List<Long> products;
    @NotNull
    private Order.Status status;
    private LocalDate endDate;
}
