package dev.marvin.customermanager;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class PostgresqlContainerTest extends AbstractTestContainersTest {

    @Test
    void canStartPostgresDB() {
        assertThat(postgresContainer.isCreated()).isTrue();
        assertThat(postgresContainer.isRunning()).isTrue();
    }
}
