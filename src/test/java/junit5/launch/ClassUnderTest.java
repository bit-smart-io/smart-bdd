package junit5.launch;

import junit5.extension.testwatcher.debug.DebugExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This is used by TestDebugLauncher
 */
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(DebugExtension.class)
class ClassUnderTest {

    /**
     * adds interceptBeforeAllMethod
     */
    @BeforeAll
    public static void beforeAll() {
    }

    /**
     * adds interceptBeforeEachMethod
     */
    @BeforeEach
    public void beforeEach() {
    }

    /**
     * where is interceptAfterAllMethod?
     */
    @AfterAll
    public static void afterAll() {
    }

    /**
     * adds interceptAfterEachMethod
     */
    @AfterEach
    public void afterEach() {
    }

    @Order(0)
    @Test
    void firstTest() {
    }

    @Order(1)
    @Test
    void secondTest() {
    }

    @ParameterizedTest
    @Order(2)
    @ValueSource(ints = { 2, 4, 8 })
    void thirdParamTest(int value) {
        assertThat(value % 2).isEqualTo(0);
    }
}