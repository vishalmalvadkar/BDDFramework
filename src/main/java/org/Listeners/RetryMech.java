package org.Listeners;

import org.Utilities.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryMech implements IRetryAnalyzer {
    private static final Logger logger = LoggerUtil.getLogger(RetryMech.class);
    private int retryCount = 0;
    private static final int maxRetryCount = 3; // Retry thrice

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            logger.warn("Retrying test: " + result.getMethod().getMethodName() + " | Attempt: " + (retryCount + 1));
            retryCount++;
            return true;
        }
        return false;
    }
}
