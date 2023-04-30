import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class YearReport {
    public ArrayList<Year> yearsReport;

    String yearNumberForReturn;


    public void loadFile(){
        yearsReport = new ArrayList<>();
        List<String> content = readFileContents(("resources/y.2021.csv"));
        yearNumberForReturn = "resources/y.2021.csv".split("\\.")[1];

        for (int i = 1; i < content.size(); i++) {
            String line = content.get(i);
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean is_expense = Boolean.parseBoolean(parts[2]);

            Year year = new Year(month, amount, is_expense);

            yearsReport.add(year);

        }
    }

    int getExpenseForMonth(Integer i){
        int returnValue = 0;
        for (Year year : yearsReport) {
            if(year.is_expense&&year.month==i){
                returnValue = year.amount;
            }
        }
        return returnValue;
    }

    int getRevenueForMonth(Integer i){
        int returnValue = 0;
        for (Year year : yearsReport) {
            if(!year.is_expense&&year.month==i){
                returnValue = year.amount;
            }
        }
        return returnValue;
    }

    int averageRevenue(){
        int revenue = 0;
        int iteration = 1;
        for (int i = 0; i < yearsReport.size(); i++) {
            revenue+=getRevenueForMonth(i+1);
            iteration++;
        }
        return revenue/iteration;
    }

    int averageExpense(){
        int expense = 0;
        int iteration = 1;
        for (int i = 0; i < yearsReport.size(); i++) {
            expense+=getExpenseForMonth(i+1);
            iteration++;
        }
        return expense/iteration;
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
