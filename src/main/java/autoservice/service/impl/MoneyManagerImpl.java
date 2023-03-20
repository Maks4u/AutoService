package autoservice.service.impl;

import autoservice.model.Master;
import autoservice.model.Order;
import autoservice.model.Product;
import autoservice.model.Work;
import autoservice.service.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceManagerImpl implements PriceManager {
    private final double MAX_PERCENTAGE = 100;
    private final double MASTER_SALARY_MULTIPLIER = 0.3;
    private final WorkService workService;

    public PriceManagerImpl(WorkService workService) {
        this.workService = workService;
    }

    @Override
    public BigDecimal calculatePrice(Order order) {
        BigDecimal totalPriceForWorks = order.getWorks()
                .stream()
                .map(Work::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal worksDiscount = BigDecimal.valueOf((order.getWorks().size() * 2) / MAX_PERCENTAGE);
        BigDecimal totalWorksPriceWithDiscount = totalPriceForWorks
                .subtract(totalPriceForWorks.multiply(worksDiscount));
        BigDecimal totalPriceForProducts = order.getProducts()
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal productsDiscount = BigDecimal.valueOf(order.getProducts().size() / MAX_PERCENTAGE);
        BigDecimal totalProductsPriceWithDiscount = totalPriceForProducts
                .subtract(totalPriceForProducts.multiply(productsDiscount));
        return totalWorksPriceWithDiscount.add(totalProductsPriceWithDiscount);
    }

    @Override
    public BigDecimal calculateMasterSalary(Master master) {
        List<Work> works = workService.getAllUnpaidWorkByMasterId(master.getId());
        BigDecimal totalPriceForWork = works.stream()
                .map(Work::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal masterSalary = totalPriceForWork.multiply(BigDecimal.valueOf(MASTER_SALARY_MULTIPLIER));
        return null;
    }
}
