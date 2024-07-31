package ru.feolika.Tasks.w1d3;

public class Starter {
    public static void main(String[] args) {
        Integer[] integers = {7,6,7,5,8,7};
        Double[] doubles = {2.1, 2.1 , 5.1, 4.1, 5.5, 4.1, 3.1};
        Object[] objects =  {7,6,7,5,8,7};

        MathBox<Integer> integerMathBox = new MathBox<Integer>(integers);
        MathBox<Double> doubleMathBox = new MathBox<Double>(doubles);
        ObjectBox objectBox = new ObjectBox(objects);

        checkFirstPathOfTask(integerMathBox, doubleMathBox);
        checkSecondPathOfTask(objectBox);

        System.out.println("\nПопытка положить Object в MathBox\n");
        try{
            integerMathBox.addObject("not a number");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Проверка класса MathBox на все его методы
     * @param integerMathBox MathBox(Integer)
     * @param doubleMathBox MathBox(Double)
     */
    private static void checkFirstPathOfTask(MathBox<Integer> integerMathBox, MathBox<Double> doubleMathBox){
        System.out.println("------------------Part_1------------------\n");
        integerMathBox.dump();
        doubleMathBox.dump();
        System.out.println();

        System.out.println("Сумма всех элементов integerMathBox = " + integerMathBox.summator());
        System.out.println("Сумма всех элементов doubleMathBox = " + doubleMathBox.summator());
        System.out.println();

        System.out.println("Деление всех элементов integerMathBox на -2 = " + integerMathBox.splitter(-2));
        System.out.println("Деление всех элементов doubleMathBox на 2 = " + doubleMathBox.splitter(2));
        System.out.println();

        System.out.println("Сравнивание друг с другом : " + integerMathBox.equals(doubleMathBox));
        System.out.println("Сравнивание integerMathBox с null : " + integerMathBox.equals(null));
        System.out.println("Сравнинвание с самим собой " + integerMathBox.equals(integerMathBox));
        System.out.println();

        System.out.println("HashCode integerMathBox : " + integerMathBox.hashCode());
        System.out.println("HashCode doubleMathBox : " + doubleMathBox.hashCode());
        System.out.println();

        System.out.println("Удаление '5' из integerMathBox -> " + integerMathBox.deleteObject(5));
        System.out.println("integerMathBox :" + integerMathBox.toString());
        System.out.println("Удаление '9' из integerMathBox -> " + integerMathBox.deleteObject(9));
        System.out.println("integerMathBox :" + integerMathBox.toString());
    }

    /**
     * Проверка класса ObjectBox на все его методы
     * @param objectBox ObjectBox
     */
    private static void checkSecondPathOfTask(ObjectBox objectBox){
        System.out.println("\n------------------Part_2------------------\n");
        objectBox.dump();
        System.out.println("Добавление 'string' в objectBox");
        objectBox.addObject("string");
        objectBox.dump();
        System.out.println("Удаление 'string' в objectBox -> " + objectBox.deleteObject("string"));
        objectBox.dump();
        System.out.println("Удаление '10' в objectBox -> " + objectBox.deleteObject("10"));
        objectBox.dump();
        System.out.println("HashCode integerMathBox : " + objectBox.hashCode());
    }
}
