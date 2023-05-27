package listeners;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


import java.io.File;
import java.io.IOException;
/*
needed dependency for this extent report
<dependency>
<groupId>com.aventstack</groupId>
<artifactId>extentreports</artifactId>
<version>5.0.9</version>
</dependency>

<dependency>
<groupId>commons-io</groupId>
<artifactId>commons-io</artifactId>
<version>2.11.0</version>
</dependency>

<dependency>
<groupId>org.freemarker</groupId>
<artifactId>freemarker</artifactId>
<version>2.3.30</version>
</dependency>
*/

public class ExtentTestNGIReporterListener {
    static ExtentReports extent;

    public static ExtentReports extentReportGenerator() {
        String path = System.getProperty("user.dir") + "\\ApiReports\\ExtentReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Zensar Extent Reports");
        reporter.config().setReportName("ExtentSparkReports - BFSI");
        extent = new ExtentReports();
        extent.setSystemInfo("Application", "Eform And Hrm");
        extent.setSystemInfo("Environment", "Dev");
        extent.setSystemInfo("Execution Mode", "Automated");
        extent.attachReporter(reporter);
        return extent;
    }

}
