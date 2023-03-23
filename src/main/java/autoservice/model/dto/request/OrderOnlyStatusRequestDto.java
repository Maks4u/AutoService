package autoservice.model.dto.request;

import autoservice.model.Order;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderOnlyStatusRequestDto {
    @NotNull
    private Order.Status status;
}
