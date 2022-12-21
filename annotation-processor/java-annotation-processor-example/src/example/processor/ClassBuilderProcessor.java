package example.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes(value = { "example.processor.ClassBuilder" })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ClassBuilderProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(ClassBuilder.class);
        if (annotatedElements.isEmpty()) {
            return true;
        }

        for (Element element : annotatedElements) {
            TypeElement clazz = (TypeElement) element;
            String className = clazz.getQualifiedName().toString();
            Set<String> ignoreFieldSet = clazz.getEnclosedElements().stream()
                    .filter(e -> e.getKind().isField())
                    .filter(e -> ((VariableElement) e).getAnnotation(ClassBuilderIgnore.class) != null)
                    .map(e -> ((VariableElement) e).getSimpleName().toString())
                    .collect(Collectors.toSet());
            Map<String, String> setterMethodMap = clazz.getEnclosedElements().stream()
                    .filter(e -> e.getKind().equals(ElementKind.METHOD))
                    .filter(e -> ((ExecutableElement) e).getSimpleName().toString().startsWith("set"))
                    .filter(e -> ((ExecutableElement) e).getParameters().size() == 1)
                    .filter(e -> !ignoreFieldSet.stream().anyMatch(item -> item.equalsIgnoreCase(((ExecutableElement) e).getSimpleName().toString().replaceFirst("set", ""))))
                    .collect(Collectors.toMap(e -> ((ExecutableElement) e).getSimpleName().toString(), e -> ((ExecutableElement) e).getParameters().get(0).asType().toString()));

            try {
                writeBuilderFile(className, setterMethodMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void writeBuilderFile(String className, Map<String, String> setterMap) throws IOException {
        String packageName = null;
        int lastDot = className.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = className.substring(0, lastDot);
        }

        String simpleClassName = className.substring(lastDot + 1);
        String builderClassName = className + "Builder";
        String builderSimpleClassName = builderClassName.substring(lastDot + 1);

        JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(builderClassName);
        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
            if (packageName != null) {
                out.print("package ");
                out.print(packageName);
                out.println(";");
                out.println();
            }

            out.print("public class ");
            out.print(builderSimpleClassName);
            out.println(" {");
            out.println();

            out.print("    private ");
            out.print(simpleClassName);
            out.print(" object = new ");
            out.print(simpleClassName);
            out.println("();");
            out.println();

            out.print("    public ");
            out.print(simpleClassName);
            out.println(" build() {");
            out.println("        return object;");
            out.println("    }");
            out.println();

            setterMap.entrySet().forEach(setter -> {
                String methodName = setter.getKey();
                String argumentType = setter.getValue();

                out.print("    public ");
                out.print(builderSimpleClassName);
                out.print(" ");
                out.print(methodName);

                out.print("(");

                out.print(argumentType);
                out.println(" value) {");
                out.print("        object.");
                out.print(methodName);
                out.println("(value);");
                out.println("        return this;");
                out.println("    }");
                out.println();
            });

            out.println("}");
        }
    }

}
