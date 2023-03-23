package autoservice.service;

import autoservice.model.Master;
import java.math.BigDecimal;

public interface MasterService extends GenericService<Master> {
    BigDecimal getSalary(Long id);
}
