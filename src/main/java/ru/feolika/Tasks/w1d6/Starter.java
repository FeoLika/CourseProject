package ru.feolika.Tasks.w1d6;

import java.util.Scanner;

public class Starter {
    public static void main(String[] args) {
        String pathToSomeClass = "src/ru/feolika/Tasks/w1d6/SomeClass";
        String className = "ru.feolika.Tasks.w1d6.SomeClass";
        String code = getInputConsole();
        CustomClassCompiler customClassCompiler = new CustomClassCompiler();

        customClassCompiler.writeCodeToClass(code, pathToSomeClass);
        customClassCompiler.compile(pathToSomeClass);
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        customClassCompiler.runMethod(pathToSomeClass, systemClassLoader, className);
    }

    /**
     * Считывает код из консоли
     *
     * @return тело метода doWork в виде строки
     */
    public static String getInputConsole() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder code = new StringBuilder();
        String line;
        while ((line = scanner.nextLine()) != null && !line.isBlank()) {
            code.append("\t\t");
            code.append(line).append("\n");
        }
        code.deleteCharAt(code.length() - 1);

        return code.toString();
    }


}
