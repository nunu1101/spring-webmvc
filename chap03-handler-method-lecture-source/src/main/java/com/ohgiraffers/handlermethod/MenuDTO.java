package com.ohgiraffers.handlermethod;

/* DTO 를 작성할 때 커맨드 객체로 이용하기 위해서는 form의 name값과 일치하게 만들어야 한다. */
public class MenuDTO {

    private String name;
    private int price;
    private int categoryCode;
    private String orderableStatus;

    /* 커맨드 객체는 기본생성자를 이용하여 인스턴스를 만들기 때문에 기본생성자가 반드시 필요하다. */
    public MenuDTO(){}

    public MenuDTO(String name, int price, int categoryCode, String orderableStatus) {
        this.name = name;
        this.price = price;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    /* 요청 파라미터의 name 과 일치하는 필드의 setter를 이용하기 땜ㄴ에
    *  네이밍 룰에 맞는 setter 메소드가 작성되어야 한다.
    */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
