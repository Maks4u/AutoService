package autoservice.service.impl;

import autoservice.model.CarOwner;
import autoservice.repository.CarOwnerRepository;
import autoservice.service.CarOwnerService;
import org.springframework.stereotype.Service;

@Service
public class CarOwnerServiceImpl implements CarOwnerService {
    private final CarOwnerRepository carOwnerRepository;

    public CarOwnerServiceImpl(CarOwnerRepository carOwnerRepository) {
        this.carOwnerRepository = carOwnerRepository;
    }

    @Override
    public CarOwner saveOrUpdate(CarOwner entity) {
        return carOwnerRepository.save(entity);
    }

    @Override
    public CarOwner get(Long id) {
        return carOwnerRepository.getById(id);
    }
}
