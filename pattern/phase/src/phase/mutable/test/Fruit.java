package phase.mutable.test;

public class Fruit {

    private String where;

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public Integer getSet() {
        return set;
    }

    public void setSet(Integer set) {
        this.set = set;
    }

    private Integer set;

    @Override
    public String toString() {
        return "Fruit [where=" + where + ", set=" + set + "]";
    }
}
