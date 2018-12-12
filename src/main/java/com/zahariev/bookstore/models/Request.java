package com.zahariev.bookstore.models;

import javax.persistence.*;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String clientName;
    private Integer clientPhoneNumber;
    private String productType;
    private String productInfo;

    public Request() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public Integer getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(Integer clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }
}
