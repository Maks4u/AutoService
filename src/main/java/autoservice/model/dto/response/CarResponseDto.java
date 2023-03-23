package autoservice.model.dto.response;

import lombok.Data;

@Data
public class CarResponseDto {
    private Long id;
    private String brand;
    private String model;
    private Integer year;
    private Long uniqueNumber;
    private Long carOwnerId;
}
