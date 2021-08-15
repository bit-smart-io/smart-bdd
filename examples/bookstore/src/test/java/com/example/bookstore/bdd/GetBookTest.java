package com.example.bookstore.bdd;

import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ReportExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetBookTest {

	@Autowired
	private TestRestTemplate template;

    private ResponseEntity<String> response;

    @Test
    public void getBook() {
        whenGetBookIsCalled();
        thenTheBookIsReturned();
    }

    private void whenGetBookIsCalled() {
        response = template.getForEntity("/book", String.class);
    }

    private void thenTheBookIsReturned() {
        assertThat(response.getBody()).isEqualTo("Book");
    }
}
