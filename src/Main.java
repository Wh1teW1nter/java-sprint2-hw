import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        // Поехали!

        /*
        Привет, Ирек!
        Спасибо за ревью!
        В коде я комментариями пометил, где используются переменные которые ты попросил внести внутри методов.
        Те же переменные, что реально не используются, я либо удалил, либо убрал внутри методов.
        Также провел все иные правки указанные в ревью.
        Еще раз спасибо и приятного дня!
        */

        Scanner scanner = new Scanner(System.in);
        YearReport yearReport = new YearReport();;
        MonthReport monthReport = new MonthReport();
        ReportManager reportManager = new ReportManager(yearReport, monthReport);



        while(true) {
            Menu.printMenu();
            switch (MenuCommand.findByCode(scanner.nextInt())) {
                case READ_MONTH_FILES:
                    monthReport.loadFile();
                    break;
                case READ_YEAR_FILES:
                    yearReport.loadFile();
                    break;
                case CHECK_REPORTS:
                    if(!(monthReport.monthReport == null)&&!(yearReport.yearsReport == null)){
                        reportManager.check();
                    }
                    else{
                        System.out.println("Необходимо считать отчеты");
                    }
                    break;
                case PRINT_MONTH_INFO:

                    if(!(monthReport.monthReport == null)){
                        reportManager.printMonthInformation();}
                    else{
                        System.out.println("Необходимо считать месячный отчет");
                    }
                    break;
                case PRINT_YEAR_INFO:
                    if(!(yearReport.yearsReport == null)){
                        reportManager.printYearInformation();
                    }
                    else {
                        System.out.println("Необходимо считать годовой отчет");
                    }
                    break;
                case EXIT:
                    System.out.println("Пока-пока :)");
                    return;
                default:
                    System.out.println("Такой команды нет");
            }
        }

    }






}

