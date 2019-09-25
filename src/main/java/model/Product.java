package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product implements IBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double price;

    @Column(nullable = false)
    @Formula(value = "(case when (taxType='PRODUCT') then (price*0.23) when (taxType='SERVICES') then (price*0.8)end)")
    private double taxValue;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TaxType taxType;

    private int stock;

    @ToString.Exclude
    @ManyToOne()
    private Invoice invoice;

    public Product(String name, double price, TaxType taxType, int stock) {
        this.name = name;
        this.price = price;
        this.taxType = taxType;
        this.stock = stock;
    }
}