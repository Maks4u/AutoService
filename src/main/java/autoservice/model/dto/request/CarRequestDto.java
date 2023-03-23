package autoservice.model.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarRequestDto {
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @Min(1950)
    @Max(2023)
    private Integer year;
    @Min(0L)
    private Long uniqueNumber;
    @NotNull
    private Long carOwnerId;
}
