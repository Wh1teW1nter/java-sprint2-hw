import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class MonthReport {

public HashMap<Integer, ArrayList<Month>> monthsReports = new HashMap<Integer, ArrayList<Month>>();
public ArrayList<Month> monthReport= new ArrayList<>();

    public void loadFile(){  //"m.20210" + i + ".csv"
        for (int i = 1; i <= 3; i++) {
            List<String> content = readFileContents("resources/m.20210" + i + ".csv");
            for (int i1 = 1; i1 < content.size(); i1++) {
                String line = content.get(i1);
                String[] parts = line.split(",");
                String item_name = parts[0];
                boolean is_expense = Boolean.parseBoolean(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                int sum_of_one = Integer.parseInt(parts[3]);

                Month month = new Month(item_name, is_expense, quantity, sum_of_one);
                monthReport.add(month);


            }
            monthsReports.put(i, monthReport);
        }
    }




    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}


