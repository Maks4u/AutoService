package autoservice.service.impl;

import autoservice.model.Master;
import autoservice.model.Order;
import autoservice.model.Product;
import autoservice.model.Work;
import autoservice.service.MoneyManager;
import autoservice.service.WorkService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MoneyManagerImpl implements MoneyManager {
    private final WorkService workService;

    public MoneyManagerImpl(WorkService workService) {
        this.workService = workService;
    }

    @Override
    public BigDecimal calculatePrice(Order order) {
        BigDecimal totalPriceForWorks = order.getWorks()
                .stream()
                .map(Work::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        double maxPercentage = 100;
        BigDecimal worksDiscount = BigDecimal.valueOf((order
                .getWorks().size() * 2) / maxPercentage);
        BigDecimal totalWorksPriceWithDiscount = totalPriceForWorks
                .subtract(totalPriceForWorks.multiply(worksDiscount));
        BigDecimal totalPriceForProducts = order.getProducts()
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal productsDiscount = BigDecimal.valueOf(order
                .getProducts().size() / maxPercentage);
        BigDecimal totalProductsPriceWithDiscount = totalPriceForProducts
                .subtract(totalPriceForProducts.multiply(productsDiscount));
        if (order.getStatus().equals(Order.Status.REFUSE)) {
            return BigDecimal.valueOf(500L);
        }
        return totalWorksPriceWithDiscount.add(totalProductsPriceWithDiscount);
    }

    @Override
    public BigDecimal calculateMasterSalary(Master master) {
        List<Work> works = workService.getAllUnpaidWorkByMasterId(master.getId());
        BigDecimal totalPriceForWork = works.stream()
                .map(Work::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        double masterSalaryMultiplier = 0.3;
        return totalPriceForWork.multiply(BigDecimal.valueOf(masterSalaryMultiplier));
    }
}
