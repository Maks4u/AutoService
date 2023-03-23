package autoservice.controller;

import autoservice.model.Work;
import autoservice.model.dto.request.WorkOnlyStatusRequestDto;
import autoservice.model.dto.request.WorkRequestDto;
import autoservice.model.dto.response.WorkResponseDto;
import autoservice.service.WorkService;
import autoservice.service.mapper.impl.WorkMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class WorkController {
    private final WorkService workService;
    private final WorkMapper workMapper;

    public WorkController(WorkService workService,
                          WorkMapper workMapper) {
        this.workService = workService;
        this.workMapper = workMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create Work")
    WorkResponseDto save(@RequestBody
                         @Validated
                         @ApiParam(value = "Work parameters") WorkRequestDto workRequestDto) {
        Work work = workService.saveOrUpdate(workMapper.toModel(workRequestDto));
        return workMapper.toDto(work);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Work")
    WorkResponseDto update(@PathVariable
                           @ApiParam(value = "Work ID") Long id,
                           @RequestBody
                           @Validated
                           @ApiParam(value = "Work parameters") WorkRequestDto workRequestDto) {
        Work work = workService.saveOrUpdate(workMapper.toModel(workRequestDto));
        work.setId(id);
        return workMapper.toDto(work);
    }

    @PutMapping("/{id}/status")
    @ApiOperation(value = "Change Work status by ID")
    WorkResponseDto changeStatus(@PathVariable
                                 @ApiParam(value = "Work ID") Long id,
                                 @RequestBody
                                 @Validated
                                 @ApiParam(value = "Work status") WorkOnlyStatusRequestDto status) {
        Work work = workService.changeStatus(id, status.getStatus());
        return workMapper.toDto(work);
    }
}
