package io.bitsmart.bdd.learning.junit5.extension;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.logging.Logger;

@ExtendWith(TimingExtension.class)
public class TimingTest {
    private static final Logger logger = Logger.getLogger(LearningExtension.class.getName());

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter) {
        String callingTest = testInfo.getTestMethod().get().getName();
        logger.info("Before: " + callingTest);
        logger.info("Before testInfo: " + testInfo);
        logger.info("Before testReporter: " + testReporter);
    }

    @AfterEach
    void after(TestInfo testInfo, TestReporter testReporter) {
        String callingTest = testInfo.getTestMethod().get().getName();
        logger.info("After: " + callingTest);
        logger.info("After testInfo: " + testInfo);
        logger.info("Before testReporter: " + testReporter);
    }

    @Test
    void firstTest() {
        logger.info("1");
    }

    @Test
    void secondTest() {
        logger.info("2");
    }
}
