package likelion.springboothan.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Item {
    @GeneratedValue @Id
    private Long Id;

    private String brand;
    private String name;
    private Integer price;
    private Integer stockQuantity;
    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItemList= new ArrayList<>();

    public void addStock(int quantity){
        this.stockQuantity+=quantity;
    }

    public void removeStock(int quantity){
        int restStock=this.stockQuantity-quantity;
        if(restStock<0){
            throw new IllegalStateException("need more Stock");
        }
        this.stockQuantity=restStock;
    }
}
