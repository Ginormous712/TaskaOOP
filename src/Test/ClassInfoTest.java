package Test;

import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import Tasks.task4.SampleClass;

class ClassInfoTest {

    @Test
    void printClassInfo() {
        Class<?> clazz = SampleClass.class;


        System.out.println("Class Name: " + clazz.getName());

        System.out.println("\nFields:");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println("Name: " + field.getName());
            System.out.println("Type: " + field.getType());
            System.out.println("Modifiers: " + Modifier.toString(field.getModifiers()));
            System.out.println();
        }

        System.out.println("\nConstructors:");
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            System.out.println("Name: " + constructor.getName());
            System.out.println("Modifiers: " + Modifier.toString(constructor.getModifiers()));
            Parameter[] parameters = constructor.getParameters();
            System.out.println("Parameters:");
            for (Parameter parameter : parameters) {
                System.out.println("  " + parameter.getName() + ": " + parameter.getType());
            }
            System.out.println();
        }

        System.out.println("\nMethods:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("Name: " + method.getName());
            System.out.println("Return Type: " + method.getReturnType());
            System.out.println("Modifiers: " + Modifier.toString(method.getModifiers()));
            Parameter[] parameters = method.getParameters();
            System.out.println("Parameters:");
            for (Parameter parameter : parameters) {
                System.out.println("  " + parameter.getName() + ": " + parameter.getType());
            }
            System.out.println();
        }
    }
}
