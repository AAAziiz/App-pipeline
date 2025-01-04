package codsoft.backend.TestIntegration.cucumber.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.MySQLContainer;

@Component
public class MySqlTestContainer implements InitializingBean, DisposableBean {
    private static final Logger logger = LoggerFactory.getLogger(MySqlTestContainer.class);
    private static MySQLContainer<?> mysqlContainer;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (mysqlContainer == null) {
            logger.info("Starting MySQL Container...");
            mysqlContainer = new MySQLContainer<>("mysql:8.0")
                    .withDatabaseName("arwa_backend")
                    .withUsername("root")
                    .withPassword("test")
                    .withInitScript("migration/initialise_database_import.sql")
                    .withReuse(true);
            mysqlContainer.start();
            logger.info("MySQL Container started with JDBC URL: {}", mysqlContainer.getJdbcUrl());
            logger.info("MySQL Container logs: {}", mysqlContainer.getLogs());
        }
    }

    @Override
    public void destroy() throws Exception {
        if (mysqlContainer != null && mysqlContainer.isRunning()) {
            logger.info("Stopping MySQL Container...");
            mysqlContainer.stop();
        }
    }

    public MySQLContainer<?> getMySQLContainer() {
        return mysqlContainer;
    }
}
