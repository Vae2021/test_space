package com.alipay.demo.service.sofamq;

/**
 * SOFAMQ 示例 pojo
 */
public class OrderPojo {

    private long id;
    private double price;
    private String address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrderPojo{" +
                "id=" + id +
                ", price=" + price +
                ", address='" + address + '\'' +
                '}';
    }
}
