package ru.feolika.Tasks.w2d2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class Starter {
    private static final Logger logger = LogManager.getLogger(ru.feolika.Tasks.w1d7.Starter.class);

    public static class SomeMemoryClass {
        private String string;
        private byte[] array;

        public SomeMemoryClass(byte[] array, String string) {
            this.string = string;
            this.array = array;
        }
    }

    public static void main(String[] args) {
        try {
            List<SomeMemoryClass> objects = new ArrayList<>();
            while (true) {
                byte[] bytes = new byte[1024 * 1024];
                String str = "some string";
                objects.add(new SomeMemoryClass(bytes, str));
                if (Math.random() > 0.6) {
                    objects.remove((int) (Math.random() * objects.size()));
                }
            }
        } catch (OutOfMemoryError e) {
            logger.error(e.getMessage(), e);
        }

    }
}
