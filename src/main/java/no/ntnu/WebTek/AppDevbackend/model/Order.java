package no.ntnu.WebTek.AppDevbackend.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private double purchaseAmount;
    private String orderDate;
    private Long userId;
    private Long productId;

    public Order(Long id, double purchaseAmount, String orderDate, Long userId, Long productId) {
        this.id = id;
        this.purchaseAmount = purchaseAmount;
        this.orderDate = orderDate;
        this.userId = userId;
        this.productId = productId;
    }

    public Order() {

    }


    public double getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setCustomerId(Long customerId) {
        this.userId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
