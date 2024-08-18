package phase.mutable.test;

import phase.mutable.MutablePhase;

public class PhaseFruit implements MutablePhase<String, Car, Fruit> {

    @Override
    public Fruit execute(String source, Car in) {
        Fruit ff = new Fruit();
        ff.setWhere(in.getType());
        return ff;
    }

}
