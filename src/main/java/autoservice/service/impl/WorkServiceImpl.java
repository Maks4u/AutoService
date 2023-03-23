package autoservice.service.impl;

import autoservice.model.Work;
import autoservice.repository.WorkRepository;
import autoservice.service.WorkService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WorkServiceImpl implements WorkService {
    private final WorkRepository workRepository;

    public WorkServiceImpl(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Override
    public Work saveOrUpdate(Work entity) {
        return workRepository.save(entity);
    }

    @Override
    public Work get(Long id) {
        return workRepository.getById(id);
    }

    @Override
    public Work changeStatus(Long id, Work.Status status) {
        Work work = workRepository.getById(id);
        work.setStatus(status);
        return workRepository.save(work);
    }

    @Override
    public List<Work> getAllUnpaidWorkByMasterId(Long id) {
        return workRepository.getAllUnpaidByMasterId(id, Work.Status.UNPAID);
    }
}
