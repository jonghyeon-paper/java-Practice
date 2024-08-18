package phase.mutable;

import java.util.Objects;

public class MutablePhaseChain<S, IN, OUT> {

    private S source;
    private IN in;
    private MutablePhase<S, IN, OUT> phase;

//    public MutablePhaseChain(S source) {
//        this(source, (IN) source, null);
//    }

    private MutablePhaseChain(S source, IN in, MutablePhase<S, IN, OUT> phase) {
        this.source = source;
        this.in = in;
        this.phase = phase;
    }

//    public MutablePhaseChain<S, IN, OUT> source(S source) {
//        this.source = source;
//        return this;
//    }

//    public MutablePhaseChain<S, IN, OUT> start(MutablePhase<S, IN, OUT> phase) {
//        this.phase = phase;
//        return this;
//    }
    
    public <N_OUT> MutablePhaseChain<S, OUT, N_OUT> next(MutablePhase<S, OUT, N_OUT> nextPhase) {
        return new MutablePhaseChain<>(source, phase.execute(source, in), nextPhase);
    }
    
    public OUT end() {
        Objects.requireNonNull(phase);
        return phase.execute(source, in);
    }

//    public static <S, OUT> MutablePhaseChainBuilder<S, OUT> builder() {
//        return new MutablePhaseChainBuilder<S, OUT>();
//    }
    
    public static <S, OUT> MutablePhaseChain<S, S, OUT> start(S source, MutablePhase<S, S, OUT> phase) {
        return new MutablePhaseChain<S, S, OUT>(source, source, phase);
    }

//    public static class MutablePhaseChainBuilder<S, OUT> {
//
//        private S source;
//
//        public MutablePhaseChainBuilder<S, OUT> source(S source) {
//            this.source = source;
//            return this;
//        }
//
//        public MutablePhaseChain<S, S, OUT> start(MutablePhase<S, S, OUT> phase) {
//            return new MutablePhaseChain<S, S, OUT>(source, source, phase);
//        }
//    }
}
