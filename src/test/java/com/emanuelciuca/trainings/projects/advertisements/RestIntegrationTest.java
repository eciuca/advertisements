package com.emanuelciuca.trainings.projects.advertisements;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
public class RestIntegrationTest {

    @LocalServerPort
    private int port;

    protected String url(String relativePath) {
        return "http://localhost:" + port + relativePath;
    }


    // ACO-PUB
    //
}
