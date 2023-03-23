package autoservice.service;

import autoservice.model.Master;
import autoservice.model.Order;
import java.math.BigDecimal;

public interface MoneyManager {
    BigDecimal calculatePrice(Order order);

    BigDecimal calculateMasterSalary(Master master);
}
