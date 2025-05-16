public class task1 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Ошибка: необходимо передать два аргумента — n и m.");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        if (n <= 0 || m <= 0) {
            System.out.println("n и m должны быть положительными числами.");
            return;
        }

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }

        StringBuilder path = new StringBuilder();

        int currentIndex = 0;
        do {
            path.append(array[currentIndex]);
            currentIndex = (currentIndex + m - 1) % n;
        } while (currentIndex != 0);

        System.out.println("Путь: " + path);
    }
}