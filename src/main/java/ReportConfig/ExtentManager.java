package ReportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/extentReport/HTMLReport.html");
        reporter.config().setReportName("CMS-OPEN-API HTML Report");
        reporter.config().setDocumentTitle("CMS-OPEN-API HTML Report");
        reporter.config().setTimelineEnabled(true);
        reporter.config().setEncoding("utf-8");
        reporter.config().setTheme(Theme.STANDARD);

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("InfoPlus", "Vuke");
        extentReports.setSystemInfo("Project", "CMS-OPEN-API");
        extentReports.setSystemInfo("Team", "ATTeam");
        extentReports.setSystemInfo("JDK version", System.getProperty("java.version"));
        return extentReports;
    }
}
