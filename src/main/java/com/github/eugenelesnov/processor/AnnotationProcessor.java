package com.github.eugenelesnov.processor;

import com.github.eugenelesnov.annotation.OptionalGetter;
import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

        var elements = new ArrayList<>(roundEnvironment.getElementsAnnotatedWith(OptionalGetter.class));
        elements.forEach(element -> {
            if (element.getKind().isField()) {

                String className = ((TypeElement) elements.get(0)
                        .getEnclosingElement()).getQualifiedName().toString();

                Map<String, String> fieldMap = elements.stream().collect(Collectors.toMap(
                        field -> field.getSimpleName().toString(),
                        // obtaining the type
                        field -> ((ExecutableType) field.asType())
                                .getParameterTypes().get(0).toString()
                ));

            }
        });

        return true;
    }
}