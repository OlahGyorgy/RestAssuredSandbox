package utils.report;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    Reporter reporter = new Reporter();

    public TestListener() {
    }

    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        String currentClassname = "Test class: ".concat(result.getTestClass().getRealClass().getSimpleName());
        String methodName = result.getMethod().getMethodName();
        String[] categories = result.getMethod().getGroups();
        this.reporter.makeLeft(result.getMethod().getDescription(), currentClassname, methodName, categories);
    }

    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        this.reporter.addResult(result);
    }

    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        this.reporter.addResult(result);
    }

    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        this.reporter.addResult(result);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
        this.reporter.addResult(result);
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
        this.reporter.addResult(result);
    }

    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        context.setAttribute(Reporter.REPORTER, this.reporter);
    }

    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
