package autoservice.service.impl;

import autoservice.model.Master;
import autoservice.model.Work;
import autoservice.repository.MasterRepository;
import autoservice.service.MasterService;
import autoservice.service.MoneyManager;
import autoservice.service.WorkService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MasterServiceImpl implements MasterService {
    private final MasterRepository masterRepository;
    private final WorkService workService;
    private final MoneyManager moneyManager;

    public MasterServiceImpl(MasterRepository masterRepository,
                             WorkService workService,
                             MoneyManager moneyManager) {
        this.masterRepository = masterRepository;
        this.workService = workService;
        this.moneyManager = moneyManager;
    }

    @Override
    public Master saveOrUpdate(Master entity) {
        return masterRepository.save(entity);
    }

    @Override
    public Master get(Long id) {
        return masterRepository.getById(id);
    }

    @Override
    public BigDecimal getSalary(Long id) {
        BigDecimal masterSalary = moneyManager.calculateMasterSalary(masterRepository.getById(id));
        List<Work> unpaidWorkByMasterId = workService.getAllUnpaidWorkByMasterId(id);
        for (Work work: unpaidWorkByMasterId) {
            work.setStatus(Work.Status.PAID);
            workService.saveOrUpdate(work);
        }
        return masterSalary;
    }
}
