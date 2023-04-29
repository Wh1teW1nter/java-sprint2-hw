import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ReportManager {
    public YearReport YearReport;
    public MonthReport MonthReport;

    public ReportManager(YearReport YearReport, MonthReport MonthReport) {
        this.YearReport = YearReport;
        this.MonthReport = MonthReport;
    }

    HashMap<Integer, HashMap<Boolean, Integer>> MonthsData;



    public boolean check() {

        MonthReport.calculateValues();

        ArrayList<Integer> monthWithError = new ArrayList<>();

        for (int i = 0; i < 3; i++){

            ArrayList<HashMap<Boolean, Integer>> check = MonthReport.monthsArray;       // index (номер месяца-1) - hashmap
                                                                                        // Трата - значение, Не трата - значение
            int monthExpense = check.get(i).get(false);
            int monthRevenue = check.get(i).get(true);
            int yearExpense = YearReport.getExpenseForMOnth(i+1);
            int yearRevenue = YearReport.getRevenueForMOnth(i+1);

            if (monthExpense==yearExpense && monthRevenue==yearRevenue){

            }
            else {
                System.out.println("Всё не ок - месяц - "+(i+1));
                monthWithError.add(i+1);
            }
        }

        if (monthWithError.isEmpty()){
            System.out.println("Ошибок нет");
            return false;
        }
        else {
            System.out.println("Ошибки есть");
            System.out.println(monthWithError);
            return true;
        }

}

public void printMonthInformation(){
    MonthReport.calculateValues();
    for (int i = 0; i < MonthReport.monthsArray.size(); i++) {
        String itemName;
        Integer value;
        System.out.println(MonthReport.getMonthNames(i+1));

        System.out.println("Самый прибыльный товар: " +MonthReport.getMostProfitableItem(i+1).keySet().toArray()[0]+
                ". На сумму: " +MonthReport.getMostProfitableItem(i+1).get(MonthReport.getMostProfitableItem(i+1).keySet().toArray()[0]));
        System.out.println("Самая большая трата: "+MonthReport.getMostExpensiveItem(i+1).keySet().toArray()[0]+
                ". На сумму: " +MonthReport.getMostExpensiveItem(i+1).get(MonthReport.getMostExpensiveItem(i+1).keySet().toArray()[0]));
    }

}

public void printYearInformation(){
    System.out.println(YearReport.yearNumberForReturn);
    for (int i = 0; i < 3; i++) {
        System.out.println("Прибыль за "+(i+1)+ "-й месяц: "+(YearReport.getRevenueForMOnth(i+1)-YearReport.getExpenseForMOnth(i+1)));
    }
    System.out.println("Средний расход: "+YearReport.averageExpense());

    System.out.println("Средний доход: "+YearReport.averageRevenue());
}

}
