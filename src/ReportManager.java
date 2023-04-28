public class ReportManager {
    public YearReport YearReport;
    public MonthReport MonthReport;

    public ReportManager(YearReport YearReport, MonthReport MonthReport) {
        this.YearReport = YearReport;
        this.MonthReport = MonthReport;
    }

    public boolean check(){
        return false;
    }
}
