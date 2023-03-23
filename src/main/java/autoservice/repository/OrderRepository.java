package autoservice.repository;

import autoservice.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("FROM Order o JOIN FETCH o.car c WHERE c.carOwner.id = :id")
    List<Order> getOrdersByCarCarOwnerId(Long id);

    @Query("FROM Order o INNER JOIN FETCH o.works w WHERE w.master.id = :id")
    List<Order> getOrdersByWorksMasterId(Long id);

}
