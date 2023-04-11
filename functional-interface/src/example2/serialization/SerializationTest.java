package example2.serialization;

import java.util.ArrayList;
import java.util.List;

import example2.data.Items;
import example2.data.Sample2;

public class SerializationTest {

    public static void main(String[] args) {
        Serialization<Items> typeProps = new Serialization<>(object -> "type : " + object.name());
        Serialization<String> nameProps = new Serialization<>(object -> "name : " + object);
        Serialization<Double> priceProps = new Serialization<>(object -> "price : " + object.intValue());

        List<Sample2> somethingList = new ArrayList<>();
        somethingList.add(new Sample2(Items.HELMET, "king's crown", 1.45d));
        somethingList.add(new Sample2(Items.ARMOR, "mage plate", 3.50d));
        somethingList.add(new Sample2(Items.WEAPON, "long sword", 5.00d));

        for (Sample2 something : somethingList) {
            String itemProps = new StringBuffer().append(typeProps.serialize(something.getItem()))
                    .append(" / ")
                    .append(nameProps.serialize(something.getName()))
                    .append(" / ")
                    .append(priceProps.serialize(something.getPrice()))
                    .toString();
            System.out.println(itemProps);
        }
    }

}
