import java.util.ArrayList;
import java.util.HashMap;

public class ReportManager {
    public YearReport YearReport;
    public MonthReport MonthReport;

    public ReportManager(YearReport YearReport, MonthReport MonthReport) {
        this.YearReport = YearReport;
        this.MonthReport = MonthReport;
    }

    HashMap<Integer, HashMap<Boolean, Integer>> MonthsData;

    public void calculatingMonthsValues() {
        MonthsData = new HashMap<Integer, HashMap<Boolean, Integer>>();
        //MonthReport.monthsReports
        for (int i = 1; i < MonthReport.monthsReports.size(); i++) {  //обходим хэшмапу
            int expense = 0;
            int revenue = 0;
            for (int i1 = 0; i1 < MonthReport.monthsReports.get(i).size(); i1++) { //обходим arraylist.size()
                // считаем сумму путем умножения и в зависимости от того трата это или нет складываем в хэшмапу
                int localSum = MonthReport.monthsReports.get(i).get(i1).quantity * MonthReport.monthsReports.get(i).get(i1).sum_of_one;
                if (MonthReport.monthsReports.get(i).get(i1).is_expense) {
                    expense += localSum;
                } else {
                    revenue += localSum;
                }
                //нужно обойти и посчитать сумму
            }
            HashMap<Boolean, Integer> datetoInsert = new HashMap<Boolean, Integer>();
            datetoInsert.put(true, expense);
            datetoInsert.put(false, revenue);
            MonthsData.put(i, datetoInsert);
            /* получаем мапу где:
             ключ это номер месяца
             Значение это мапа с булевым значением;
             false - доход
             true - расход
             */
        }
    }

    public boolean check() {
        calculatingMonthsValues(); // по-хорошему выкинуть в класс month этот метод
        ArrayList<Integer> monthWithError = new ArrayList<>();
        for (int i = 1; i < YearReport.yearsReport.size(); i++) {
            int a = MonthsData.get(i).get(false);
            int b = MonthsData.get(i).get(true);
            if (YearReport.yearsReport.get(i).is_expense){
                if(YearReport.yearsReport.get(i).amount==b){
                    //всё ок. схождение по месяцу происходит благодаря позиции в списке
                    //проверяем суммы и идем дальше
                } else if (YearReport.yearsReport.get(i).amount!=b) {
                    monthWithError.add(i);
                    System.out.println("Ошибка расходов в месяце - "+i);
                }
            }
            else if (!YearReport.yearsReport.get(i).is_expense){
                if(YearReport.yearsReport.get(i).amount==a){
                    //всё ок. схождение по месяцу происходит благодаря позиции в списке
                    //проверяем суммы и идем дальше
                } else if (YearReport.yearsReport.get(i).amount!=b) {
                    monthWithError.add(i);
                    System.out.println("Ошибка доходов в месяце - "+i);
                }
            }
            //if (YearReport.yearsReport.get(i).is_expense&&M)
        }
        if (monthWithError.isEmpty()){
            System.out.println("Ошибок нет");
            return false;
        }
        else {
            System.out.println("Ошибки есть");
            return true;
        }
    }

}
