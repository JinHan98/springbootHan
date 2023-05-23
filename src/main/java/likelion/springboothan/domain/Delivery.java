package likelion.springboothan.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Delivery {
    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private String city;
    private String state;
    private String street;
    private String zipcode;

    @OneToOne(mappedBy = "delivery")
    private Order order;

}
