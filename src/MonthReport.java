import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class MonthReport {

    public HashMap<Integer, ArrayList<Month>> monthsReports = new HashMap<Integer, ArrayList<Month>>();
    public ArrayList<Month> monthReport; //Используется в проверках в main классе, см строки: 29, 38.

    public void loadFile(){  //"m.20210" + i + ".csv"
        for (int i = 1; i <= 3; i++) {
            monthReport = new ArrayList<>();
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


      // Трата - значение, Не трата - значение
    public ArrayList<HashMap<Boolean, Integer>> monthsArray = new ArrayList<>(); //Используется в ReportManager см. строки: 26
    // index (номер месяца-1) - hashmap
    void calculateValues() {

        HashMap<Boolean, Integer> report = new HashMap<Boolean, Integer>();
        for (int i = 0; i <monthsReports.size(); i++) {

            report = new HashMap<Boolean, Integer>();
            int expense = 0;
            int revenue = 0;

            for (Month month : monthsReports.get(i+1)){
                int curSum = 0;
                curSum = month.sum_of_one*month.quantity;
                if (month.is_expense){
                    expense+=curSum;
                }
                else{
                    revenue+=curSum;
                }
            }
            report.put(false, expense);
            report.put(true, revenue);
            monthsArray.add(report);
        }
    }
    HashMap<String, Integer> getMostProfitableItem(Integer monthNumber){
        String itemName = null;
        int profit=0;

        HashMap<String, Integer> mostProfitableItem = new HashMap<String, Integer>();
        for (Month month : monthsReports.get(monthNumber)) {
            int curSum = month.quantity*month.sum_of_one;
            if (!month.is_expense){
                if(curSum>profit){
                    profit=curSum;
                    itemName = month.item_name;
                }
            }

        }
        mostProfitableItem.put(itemName, profit);
        return mostProfitableItem;
    }

    HashMap<String, Integer> getMostExpensiveItem(Integer monthNumber){
        String itemName = null;
        int Expense=0;

        HashMap<String, Integer> mostExpensiveItem = new HashMap<String, Integer>();
        for (Month month : monthsReports.get(monthNumber)) {
            int curSum = month.quantity*month.sum_of_one;
            if (month.is_expense){
                if(curSum>Expense){
                    Expense=curSum;
                    itemName = month.item_name;
                }
            }

        }
        mostExpensiveItem.put(itemName, Expense);
        return mostExpensiveItem;
    }
    String getMonthNames(Integer monthNumber){
        String[] monthNames = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        return monthNames[monthNumber-1];
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


