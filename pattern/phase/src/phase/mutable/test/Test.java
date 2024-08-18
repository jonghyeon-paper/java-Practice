package phase.mutable.test;

import phase.mutable.MutablePhaseChain;

public class Test {

    public static void main(String[] args) {
//        Red a = new MutablePhaseChain<String, String, Car>("ttt")
//                .start(new PhaseCar())
//                .next(new PhaseFruit())
//                .next(new PhaseRed())
//                .end();
//        
//System.out.println(a);

//        new PhaseBuilder<String, String, Car, PhaseCar>(new PhaseCar());
        Red b = MutablePhaseChain.start("zzz", new PhaseCar())
                .next(new PhaseFruit())
                .next(new PhaseRed())
                .end();
        
        System.out.println(b);
    }

}
