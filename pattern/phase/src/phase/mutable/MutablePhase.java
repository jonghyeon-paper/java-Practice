package phase.mutable;

public interface MutablePhase<S, IN, OUT> {

    OUT execute(S source, IN in);
}
