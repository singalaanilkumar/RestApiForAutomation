package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

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
public class Listeners extends ExtentTestNGIReporterListener implements ITestListener {

    public static ThreadLocal<ExtentTest> extestTest = new ThreadLocal<ExtentTest>();
    ExtentReports extent = ExtentTestNGIReporterListener.extentReportGenerator();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extestTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extestTest.get().log(Status.PASS, "Test Case IS PASSED on " + result.getName() + " Method");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extestTest.get().log(Status.FAIL, "TEST CASE IS FAILED on " + result.getName() + " Method");
        extestTest.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extestTest.get().log(Status.SKIP, "TEST CASE IS FAILED on " + result.getName() + " Method");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
