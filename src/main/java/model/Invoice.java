package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice implements IBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Column()
    private LocalDateTime dateOfCreation;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false, length = 10)
    private String clientNip;

    @Column(nullable = false)
    private String clientAddress;

    @Column(nullable = false, columnDefinition = " tinyint default 0")
    private boolean ifPaid = false;

    private LocalDateTime dateOfRelease;
    private LocalDateTime dateOfPayment;

    @Formula(value = "(SELECT SUM(p.price * p.stock) from product p where p.invoice_id = id)")
    private Double billValue;

    @Formula(value = "(SELECT COUNT(*) from product p where p.invoice_id = id)")
    private int amountOfProduct;

    // @JoinColumn(name = "product.invoice_id")
    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Cascade(value = {org.hibernate.annotations.CascadeType.REMOVE})
    private List<Product> product;

    public Invoice(String clientName, String clientNip, String clientAddress) {
        this.clientName = clientName;
        this.clientNip = clientNip;
        this.clientAddress = clientAddress;
    }
}
