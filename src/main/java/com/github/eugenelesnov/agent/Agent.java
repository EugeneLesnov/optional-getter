package com.github.eugenelesnov.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author Eugene Lesnov
 */
public class Agent {

    public static void premain(String args, Instrumentation instrumentation) {
        instrumentation.addTransformer(new ClassTransformer());
    }

    public static void agentmain(String args, Instrumentation instrumentation) {
        instrumentation.addTransformer(new ClassTransformer());
    }
}
