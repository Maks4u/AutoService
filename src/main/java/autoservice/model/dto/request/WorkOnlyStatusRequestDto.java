package autoservice.model.dto.request;

import autoservice.model.Work;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WorkOnlyStatusRequestDto {
    @NotNull
    private Work.Status status;
}
