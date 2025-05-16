import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;



public class task3 {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Укажите три аргумента: <values.json> <tests.json> <report.json>");
            return;
        }

        String valuesPath = args[0];
        String testsPath = args[1];
        String reportPath = args[2];

        try {
            // Чтение values.json
            String valuesContent = new String(Files.readAllBytes(Paths.get(valuesPath)));
            JSONObject valuesObj = new JSONObject(valuesContent);
            JSONArray valuesArray = valuesObj.getJSONArray("values");

            Map<Integer, String> valueMap = new HashMap<>();
            for (int i = 0; i < valuesArray.length(); i++) {
                JSONObject val = valuesArray.getJSONObject(i);
                valueMap.put(val.getInt("id"), val.getString("value"));
            }

            // Чтение tests.json
            String testsContent = new String(Files.readAllBytes(Paths.get(testsPath)));
            JSONObject testsObj = new JSONObject(testsContent);
            JSONArray testsArray = testsObj.getJSONArray("tests");

            // Обработка и запись результата
            fillValues(testsArray, valueMap);
            JSONObject report = new JSONObject();
            report.put("tests", testsArray);

            Files.write(Paths.get(reportPath), report.toString(2).getBytes());
            System.out.println("Файл report.json успешно создан.");

        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }

    private static void fillValues(JSONArray tests, Map<Integer, String> valueMap) {
        for (int i = 0; i < tests.length(); i++) {
            JSONObject test = tests.getJSONObject(i);
            int id = test.getInt("id");

            if (valueMap.containsKey(id)) {
                test.put("value", valueMap.get(id));
            }

            if (test.has("values")) {
                JSONArray inner = test.getJSONArray("values");
                fillValues(inner, valueMap);
            }
        }
    }
}
