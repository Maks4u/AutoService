package autoservice.service;

import autoservice.model.Work;
import java.util.List;

public interface WorkService extends GenericService<Work> {
    Work changeStatus(Long id, Work.Status status);

    List<Work> getAllUnpaidWorkByMasterId(Long id);
}
