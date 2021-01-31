package com.github.eugenelesnov.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author Eugene Lesnov
 */
public class Agent {

    public static void premain(Instrumentation instrumentation) {
        instrumentation.addTransformer(new ClassTransformer());
    }
}
