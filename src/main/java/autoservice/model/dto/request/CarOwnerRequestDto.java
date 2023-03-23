package autoservice.model.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class CarOwnerRequestDto {
    private List<Long> carsId;
    private List<Long> ordersId;
}
