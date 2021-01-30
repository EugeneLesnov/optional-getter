package com.github.eugenelesnov.processor;

/**
 * @author Eugene Lesnov
 */
public class GetterMethodConstructor {

    private static final String LINE_BREAK = System.getProperty("line.separator");
    private static final String WHITE_SPACE = " ";

    public String constructGetterMethod(String fieldName, String returnType) {
        return new StringBuilder().append(LINE_BREAK)
                .append("public")
                .append(WHITE_SPACE)
                .append(returnType)
                .append(WHITE_SPACE)
                .append(createMethodName(fieldName))
                .append("() {")
                .append(LINE_BREAK)
                .append(createMethodBody(fieldName))
                .append(LINE_BREAK)
                .append("}").toString();
    }

    private String createMethodName(String name) {
        return new StringBuilder()
                .append("get")
                .append(Character.toUpperCase(name.charAt(0)))
                .append(name.substring(1))
                .append("Optional").toString();
    }

    private String createMethodBody(String fieldName) {
        return new StringBuilder()
                .append("return Optional.of(")
                .append(fieldName)
                .append(");").toString();
    }
}