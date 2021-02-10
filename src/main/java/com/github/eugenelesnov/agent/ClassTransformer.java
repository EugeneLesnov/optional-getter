package com.github.eugenelesnov.agent;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * @author Eugene Lesnov
 */
public class ClassTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {

        // the place for some magic..

        return new byte[0];
    }
}