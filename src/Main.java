public class Main {

    public static void main(String[] args) {
        // Поехали!

        YearReport yearReport = new YearReport("resources/y.2021.csv");

        MonthReport monthReport = new MonthReport();
        monthReport.loadFile();

        ReportManager reportManager = new ReportManager(yearReport, monthReport);
        boolean answer = reportManager.check();
        System.out.println("Заебись");
    }




    /*public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");

        System.out.println("0 - Выход");
    } */
}

