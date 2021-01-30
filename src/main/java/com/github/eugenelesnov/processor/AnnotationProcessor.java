package com.github.eugenelesnov.processor;

import com.github.eugenelesnov.annotation.OptionalGetter;
import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author Eugene Lesnov
 */
@SupportedAnnotationTypes({"com.github.com.github.eugenelesnov.annotation.OptionalGetter"})
@SupportedSourceVersion(SourceVersion.RELEASE_11)
@AutoService(AnnotationProcessor.class)
public class AnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        if (annotations.size() == 0) {
            return false;
        }

        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(OptionalGetter.class);
        elements.forEach(element -> {

            OptionalGetter optionalGetter = element.getAnnotation(OptionalGetter.class);

            if (element.getKind().isField()) {

            }
        });

        return false;
    }
}