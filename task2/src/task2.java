import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Укажите два файла как аргументы: <circleFile> <pointsFile>");
            return;
        }

        String circleFilePath = args[0];
        String pointsFilePath = args[1];

        try {
            // Чтение окружности
            Scanner circleScanner = new Scanner(new File(circleFilePath));
            double centerX = circleScanner.nextDouble();
            double centerY = circleScanner.nextDouble();
            double radius = circleScanner.nextDouble();
            circleScanner.close();

            // Чтение точек
            Scanner pointsScanner = new Scanner(new File(pointsFilePath));

            while (pointsScanner.hasNextDouble()) {
                double x = pointsScanner.nextDouble();
                double y = pointsScanner.nextDouble();

                double distanceSquared = Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2);
                double radiusSquared = Math.pow(radius, 2);

                if (Math.abs(distanceSquared - radiusSquared) < 1e-6) {
                    System.out.println(0); // на окружности
                } else if (distanceSquared < radiusSquared) {
                    System.out.println(1); // внутри
                } else {
                    System.out.println(2); // снаружи
                }
            }

            pointsScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}

