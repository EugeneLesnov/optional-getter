package com.github.eugenelesnov;

import com.github.eugenelesnov.processor.AnnotationProcessor;
import org.joor.CompileOptions;
import org.joor.Reflect;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Eugene Lesnov
 */
public class OptionalGetterTest {

    @Test
    public void shouldCreateOptionalGetterForField() throws Exception {
        AnnotationProcessor annotationProcessor = new AnnotationProcessor();
        Reflect generated = Reflect.compile(
                "com.github.eugenelesnov.TestClass",
                new StringBuilder()
                        .append("package com.github.eugenelesnov;\n")
                        .append("\n")
                        .append("import com.github.eugenelesnov.annotation.OptionalGetter;\n")
                        .append("\n")
                        .append("public class TestClass {\n")
                        .append("\n")
                        .append("    @OptionalGetter\n")
                        .append("    private String testField;\n")
                        .append("}").toString(),
                new CompileOptions().processors(annotationProcessor)
        ).create();

        assertNotNull(generated.getClass().getMethod("getTestField"));
    }
}
