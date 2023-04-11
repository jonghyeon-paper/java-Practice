package example2.data;

public class Sample2 {

    public Sample2(Items item, String name, Double price) {
        this.item = item;
        this.name = name;
        this.price = price;
    }

    private Items item;
    private String name;
    private Double price;

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Sample [item=" + item + ", name=" + name + ", price=" + price + "]";
    }
}
