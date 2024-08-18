package phase.mutable.test;

import phase.mutable.MutablePhase;

public class PhaseCar implements MutablePhase<String, String, Car> {

    @Override
    public Car execute(String source, String in) {
        Car car = new Car();
        car.setType(source);
        return car;
    }

}
