package phase.mutable.test;

public class Car {

    private String type;
    private Integer price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car [type=" + type + ", price=" + price + "]";
    }
}
