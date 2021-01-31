package com.github.eugenelesnov.processor;

import com.github.eugenelesnov.annotation.OptionalGetter;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewMethod;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author Eugene Lesnov
 */
@SupportedAnnotationTypes({"com.github.com.github.eugenelesnov.annotation.OptionalGetter"})
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class AnnotationProcessor extends AbstractProcessor {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        if (annotations.size() == 0) {
            return false;
        }

        var elements = new ArrayList<>(roundEnvironment.getElementsAnnotatedWith(OptionalGetter.class));
        elements.forEach(element -> {
            if (element.getKind().isField()) {
                String className = obtainClassName(elements);
                String type = ((ExecutableType) element.asType()).getParameterTypes().get(0).toString();
                String field = element.getSimpleName().toString();

                ClassPool pool = ClassPool.getDefault();
                try {
                    CtClass ctClass = pool.get(className);
                    CtNewMethod.make(constructMethod(type, field), ctClass);
                } catch (Exception ex) {
                    error("An error occurred while creating optional getter", element);
                }
            }
        });

        return true;
    }

    private String constructMethod(String type, String field) {
        return new StringBuilder()
                .append("public ")
                .append(type)
                .append(" get").append(Character.toUpperCase(field.charAt(0)))
                .append(field.substring(1))
                .append("() { ")
                .append(LINE_SEPARATOR)
                .append("return Optional.of(").append(field).append(");")
                .append(LINE_SEPARATOR)
                .append("}").toString();
    }

    private String obtainClassName(ArrayList<? extends Element> elements) {
        return ((TypeElement) elements.get(0)
                .getEnclosingElement()).getQualifiedName().toString();
    }

    private void error(String msg, Element e) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, msg, e);
    }
}