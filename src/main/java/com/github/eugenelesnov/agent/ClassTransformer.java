package com.github.eugenelesnov.agent;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * @author Eugene Lesnov
 */
public class ClassTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(final ClassLoader loader,
                            final String className,
                            final Class<?> classBeingRedefined,
                            final ProtectionDomain protectionDomain,
                            final byte[] classfileBuffer) {

        byte[] byteCode = classfileBuffer;

        // the place for some magic..

        return byteCode;
    }
}