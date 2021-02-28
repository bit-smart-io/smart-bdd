package junit5.extension.testwatcher;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(TestResultsWatcher.class)
class TestResultsWatcherTest {

//    static WordifyClass wordify = new StubWordifyClass();

    @BeforeAll
    public static void reset() {
//        TestResultsWatcher.setWordify(wordify);
//        TestResultsWatcher.clearResults();
    }

    @AfterAll
    public static void afterAll() {
//        TestResultsWatcher.setWordify(new WordifyClass());
    }

    @Order(0)
    @ParameterizedTest
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void firstTest(String key) {
        assertThat(key).isNotNull();
        //final CapturePerTestRun capturePerTestRun = TestResultsWatcher.getExtensionCalls();
        //System.out.println(capturePerTestRun);
    }

//    @Order(1)
//    @Test
//    void generatesTestResults() {
//        assertThat(TestResultsWatcher.getResult().get(0)).isEqualTo("aString");
//        assertThat(TestResultsWatcher.getWordify()).isNotNull();
//    }

//    static class StubWordifyClass extends WordifyClass {
//        List<WordifyClassCapture> captureFor3Parameters = new ArrayList<>();
//        List<WordifyClassCapture> captureFor2Parameters = new ArrayList<>();
//
//        @Override
//        public String wordify(Class<?> clazz, String methodName) {
//            captureFor2Parameters.add(new WordifyClassCapture(clazz, methodName, emptyList()));
//            return "aString";
//        }
//
//        @Override
//        public String wordify(Class<?> clazz, String methodName, List<Object> parameterValues) {
//            captureFor3Parameters.add(new WordifyClassCapture(clazz, methodName, parameterValues));
//            return "aString";
//        }
//    }
//
//    static class WordifyClassCapture {
//        private final Class<?> clazz;
//        private final String methodName;
//        private final List<Object> parameterValues;
//
//        WordifyClassCapture(Class<?> clazz, String methodName, List<Object> parameterValues) {
//            this.clazz = clazz;
//            this.methodName = methodName;
//            this.parameterValues = parameterValues;
//        }
//
//        public Class<?> getClazz() {
//            return clazz;
//        }
//
//        public String getMethodName() {
//            return methodName;
//        }
//
//        public List<Object> getParameterValues() {
//            return parameterValues;
//        }
//    }
}