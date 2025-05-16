import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class task4 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Укажите путь к файлу как аргумент командной строки.");
            return;
        }

        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return;
        }

        if (numbers.isEmpty()) {
            System.out.println("Файл пустой.");
            return;
        }

        Collections.sort(numbers);
        int median = numbers.get(numbers.size() / 2);

        int totalMoves = 0;
        for (int num : numbers) {
            totalMoves += Math.abs(num - median);
        }

        System.out.println(totalMoves);
    }
}