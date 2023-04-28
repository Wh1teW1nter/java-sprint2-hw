public class Main {

    public static void main(String[] args) {
        // Поехали!

        YearReport yearReport = new YearReport("resources/y.2021.csv");

        MonthReport monthReport = new MonthReport();
        monthReport.loadFile("resources/m.202101.csv");
        monthReport.loadFile("resources/m.202102.csv");
        monthReport.loadFile("resources/m.202103.csv");

        ReportManager reportManager = new ReportManager(yearReport, monthReport);
        boolean answer = reportManager.check();
        System.out.println("Заебись");
    }




    /*public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Конвертировать валюту");
        System.out.println("2 - Получить совет");
        System.out.println("3 - Ввести трату");
        System.out.println("4 - Показать траты по категориям");
        System.out.println("5 - Показать самую большую трату в выбранной категории");
        System.out.println("6 - Очистить таблицу трат");
        System.out.println("7 - Вернуть сумму всех трат");
        System.out.println("8 - Удалить категорию");
        System.out.println("9 - Получить имя самой дорогой категории");
        System.out.println("0 - Выход");
    } */
}

