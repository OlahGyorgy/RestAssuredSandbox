package utils.report;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;


public class LoggerWrapper {

    public ThreadLocal<Integer> stepCounter;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ExtentTest reporter;
    private String stepColor = "#4682B4";

    public LoggerWrapper(ExtentTest reporter) {
        this.reporter = reporter;
    }

    public LoggerWrapper(ExtentTest reporter, ThreadLocal<Integer> counter) {
        this.reporter = reporter;
        this.stepCounter = counter;
        this.stepCounter.set(0);
    }

    public LoggerWrapper(ExtentTest reporter, ThreadLocal<Integer> counter, String stepColor) {
        this.reporter = reporter;
        this.stepCounter = counter;
        this.stepCounter.set(0);
        this.stepColor = stepColor;
    }

    public void info(String msg) {
        this.reporter.info(msg);
        this.logger.info(msg);
    }

    public void step(String msg) {
        if (this.stepCounter != null) {
            int step = (Integer)this.stepCounter.get();
            StringBuilder var10000 = (new StringBuilder()).append("<span style=\"color:").append(this.stepColor).append("\">Step ");
            ++step;
            String reporterMsg = var10000.append(step).append("</span> --->  ").append(msg).toString();
            String loggerMsg = "Step " + step + " --->  " + msg;
            this.reporter.info(reporterMsg);
            this.logger.info(loggerMsg);
            this.stepCounter.set(step);
        } else {
            throw new IllegalArgumentException("Step counter is not defined!");
        }
    }

    public void info(String msg, Markup markup) {
        this.reporter.info(markup);
        this.logger.info(msg);
    }

    public void debug(String msg) {
        this.logger.debug(msg);
    }

    public void debug(String msg, Markup markup) {
        this.reporter.warning(markup);
        this.logger.debug(msg);
    }

    public void error(String msg) {
        this.reporter.fail(msg);
        this.logger.error(msg);
    }

    public void pass(String msg) {
        this.reporter.pass(msg);
        this.logger.info(msg);
    }

    public void fail(String msg) {
        this.reporter.fail(msg);
        this.logger.info(msg);
    }

    public void skip(String msg) {
        this.reporter.skip(msg);
        this.logger.info(msg);
    }
}