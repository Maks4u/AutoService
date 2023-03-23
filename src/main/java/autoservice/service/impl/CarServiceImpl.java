package autoservice.service.impl;

import autoservice.model.Car;
import autoservice.repository.CarRepository;
import autoservice.service.CarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car saveOrUpdate(Car entity) {
        return carRepository.save(entity);
    }

    @Override
    public Car get(Long id) {
        return carRepository.getById(id);
    }
}
