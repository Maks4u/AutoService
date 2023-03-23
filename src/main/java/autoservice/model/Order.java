package autoservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @ToString.Exclude
    private Car car;
    private String description;
    private LocalDate takenDate;
    @OneToMany
    @ToString.Exclude
    private List<Work> works;
    @OneToMany
    @ToString.Exclude
    private List<Product> products;
    private Status status = Status.TAKEN;
    private BigDecimal totalPrice;
    private LocalDate endDate;

    public enum Status {
        TAKEN, IN_PROCESS, DONE, DONE_FAIL, PAID, REFUSE;
    }
}
