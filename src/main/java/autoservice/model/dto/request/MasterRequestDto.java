package autoservice.model.dto.request;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MasterRequestDto {
    @NotNull
    private String name;
    private List<Long> doneOrdersId;
}
