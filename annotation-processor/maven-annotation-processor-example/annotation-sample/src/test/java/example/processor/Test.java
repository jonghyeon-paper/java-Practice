package example.processor;

import example.sample.Sample;
import example.sample.Sample2;
import example.sample.Sample2Builder;
import example.sample.SampleBuilder;

public class Test {

    public static void main(String[] args) {
        Sample sample = new SampleBuilder().setId(1).setName("abc").setEnable(true).build();
        System.out.println(sample);

        Sample2 sample2 = new Sample2Builder().setId(2).setName("qwe").build();
        System.out.println(sample2);
    }
}
