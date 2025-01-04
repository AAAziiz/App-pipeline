package codsoft.backend.TestIntegration.cucumber;
import codsoft.backend.BackendApplication;
import codsoft.backend.TestIntegration.cucumber.config.IntegrationTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@IntegrationTest
@CucumberContextConfiguration
@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", features = "src/test/resources/FeatureFiles")
@ActiveProfiles("cucumber")
public class CucumberSpringConfig {

    //@Autowired
    //private JdbcTemplate jdbcTemplate;
    //private static boolean databaseCleared = false;

//    @PostConstruct
//    public void setup() {
//        if (!databaseCleared) {
//            clearDatabase();
//            databaseCleared = true;
//        }
//    }

//    private void clearDatabase() {
//        try {
//            jdbcTemplate.execute("TRUNCATE TABLE users");
//            jdbcTemplate.execute("TRUNCATE TABLE hotels");
//            jdbcTemplate.execute("TRUNCATE TABLE flights");
//            jdbcTemplate.execute("TRUNCATE TABLE cars");
//
//        } catch (Exception e) {
//
//            System.err.println("Error while clearing the database: " + e.getMessage());
//        }
//    }


}
