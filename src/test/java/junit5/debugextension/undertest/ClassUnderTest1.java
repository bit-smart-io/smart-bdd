package junit5.debugextension.undertest;

import junit5.debugextension.DebugExtension;
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
public class ClassUnderTest1 {

    /**
     * adds interceptBeforeAllMethod
     */
    @BeforeAll
    public static void class1_beforeAll() {
    }

    /**
     * adds interceptBeforeEachMethod
     */
    @BeforeEach
    public void class1_beforeEach() {
    }

    /**
     * where is interceptAfterAllMethod?
     */
    @AfterAll
    public static void class1_afterAll() {
    }

    /**
     * adds interceptAfterEachMethod
     */
    @AfterEach
    public void class1_afterEach() {
    }

    @Order(0)
    @Test
    void class1_firstTest() {
    }

    @Order(1)
    @Test
    void class1_secondTest() {
    }

    @ParameterizedTest
    @Order(2)
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void class1_thirdParamTest(String key) {
        assertThat(key).isNotNull();
    }
}