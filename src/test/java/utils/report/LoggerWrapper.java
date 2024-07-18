package utils.report;

import com.aventstack.extentreports.ExtentTest;


public class LoggerWrapper {

    public ThreadLocal<Integer> stepCounter;
    private final ExtentTest reporter;


    public LoggerWrapper(ExtentTest reporter, ThreadLocal<Integer> counter) {
        this.reporter = reporter;
        this.stepCounter = counter;
        this.stepCounter.set(0);
    }

}