package org.example.task13;

import org.testng.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//V6. ITestListener method task (3, 4); ISuiteListener, IExecutionListener...(1, 4)

public class CustomListener implements ITestListener, ISuiteListener, IExecutionListener {
    private static final Logger LOGGER = LogManager.getLogger(CustomListener.class);

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("onTestSuccess(): Test PASSED -> {}", result.getName());
        ITestListener.super.onTestSuccess(result);
    }


    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.error("onTestFailure(): Test FAILED -> {}", result.getName());
        if (result.getThrowable() != null) {
            LOGGER.error("Cause: ", result.getThrowable());
        }
        LOGGER.info("Video will be saved automatically by video-recorder-testng (mode=FAILED_ONLY).");
    }

    @Override
    public void onStart(ISuite suite) {
        LOGGER.info("onStart(ISuite): Starting suite '{}'", suite.getName());
        ISuiteListener.super.onStart(suite);
    }

    @Override
    public void onFinish(ISuite suite) {
        LOGGER.info("onFinish(ISuite): Finished suite '{}'", suite.getName());
        ISuiteListener.super.onFinish(suite);
    }

    @Override
    public void onExecutionStart() {
        LOGGER.info("onExecutionStart(): Test execution started.");
        IExecutionListener.super.onExecutionStart();
    }

    @Override
    public void onExecutionFinish() {
        LOGGER.info("onExecutionFinish(): Test execution finished.");
        IExecutionListener.super.onExecutionFinish();
    }
}
