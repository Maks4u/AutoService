package autoservice.model.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class OrderProductsRequestDto {
    private List<Long> productsId;
}
