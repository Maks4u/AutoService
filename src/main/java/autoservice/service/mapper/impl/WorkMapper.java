package autoservice.service.mapper.impl;

import autoservice.model.Work;
import autoservice.model.dto.request.WorkRequestDto;
import autoservice.model.dto.response.WorkResponseDto;
import autoservice.service.MasterService;
import autoservice.service.OrderService;
import autoservice.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class WorkMapper implements Mapper<WorkRequestDto, WorkResponseDto, Work> {
    private final OrderService orderService;
    private final MasterService masterService;

    public WorkMapper(OrderService orderService,
                      MasterService masterService) {
        this.orderService = orderService;
        this.masterService = masterService;
    }

    @Override
    public WorkResponseDto toDto(Work object) {
        WorkResponseDto workResponseDto = new WorkResponseDto();
        workResponseDto.setId(object.getId());
        workResponseDto.setPrice(object.getPrice());
        workResponseDto.setStatus(object.getStatus());
        workResponseDto.setOrderId(object.getOrder().getId());
        workResponseDto.setMasterId(object.getMaster().getId());
        return workResponseDto;
    }

    @Override
    public Work toModel(WorkRequestDto dto) {
        Work work = new Work();
        work.setPrice(dto.getPrice());
        work.setStatus(dto.getStatus());
        work.setMaster(masterService.get(dto.getMasterId()));
        work.setOrder(orderService.get(dto.getOrderId()));
        return work;
    }
}
