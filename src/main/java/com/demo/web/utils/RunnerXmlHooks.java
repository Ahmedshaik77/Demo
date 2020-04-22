package com.demo.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.Reporter;

public class RunnerXmlHooks {

    private final Logger RUNNERXMLHOOKS_LOGGER = LoggerFactory.getLogger(RunnerXmlHooks.class);

    public String getXmlParamValueThroughITestContext(ITestContext testContext, String paramKey) {

        try {
            String paramValue = testContext.getCurrentXmlTest().getParameter(paramKey);

            if (null == paramValue) {
                RUNNERXMLHOOKS_LOGGER.info("The Value for the Parameter in Runner XML named as " + paramKey + " is : NULL ");
                return null;
            } else {
                RUNNERXMLHOOKS_LOGGER.info("The Value for the Parameter in Runner XML named as " + paramKey + " is : " + paramValue);
                return paramValue;
            }
        } catch (Exception ex) {
            String exceptionData = ex.getCause().getMessage();
            RUNNERXMLHOOKS_LOGGER.error("Encountered with Exception while retrieving XML Paramater Values as : " + exceptionData);
            return null;
        }
    }


    public String getXmlParamValueThroughReporter(String paramKey) {
        try {
            String paramValue = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter(paramKey);
            if (null == paramValue) {
                RUNNERXMLHOOKS_LOGGER.info("The Value for the Parameter in Runner XML named as : " + paramKey + " is : NULL ");
                return null;
            } else {
                RUNNERXMLHOOKS_LOGGER.info("The Value for the Parameter in Runner XML named as " + paramKey + " is : " + paramValue);
                return paramValue;
            }
        } catch (Exception ex) {
            String exceptionData = ex.getCause().getMessage();
            RUNNERXMLHOOKS_LOGGER.error("Encountered with Exception while retrieving XML Paramater Values as : " + exceptionData);
            return null;
        }

    }
}
