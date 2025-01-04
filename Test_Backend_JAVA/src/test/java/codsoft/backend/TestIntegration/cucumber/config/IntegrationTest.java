package codsoft.backend.TestIntegration.cucumber.config;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@ActiveProfiles("cucumber")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest
@UseMySqlContainer
public @interface IntegrationTest {
}


