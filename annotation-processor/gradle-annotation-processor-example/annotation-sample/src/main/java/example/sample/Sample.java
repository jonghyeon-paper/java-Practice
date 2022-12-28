package example.sample;

import example.processor.ClassBuilder;

@ClassBuilder
public class Sample {

    private int id;

    private String name;

    private Boolean enable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Sample [id=" + id + ", name=" + name + ", enable=" + enable + "]";
    }
}
