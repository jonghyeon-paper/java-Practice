package phase.mutable.test;

import phase.mutable.MutablePhase;

public class PhaseRed implements MutablePhase<String, Fruit, Red> {

    @Override
    public Red execute(String source, Fruit in) {
        Red rr = new Red();
        rr.setColor(in.getWhere());
        return rr;
    }

}
