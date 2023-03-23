package autoservice.model.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class CarOwnerResponseDto {
    private Long id;
    private List<Long> carsId;
    private List<Long> ordersId;
}
