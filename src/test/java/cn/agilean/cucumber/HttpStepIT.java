package cn.agilean.cucumber;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = CucumberRunner.DIR)
public class HttpStepIT {

    static final String DIR = "src/test/features/sample_login_mock-api.feature";

    @ClassRule
    public static ExternalResource webapp = new ExternalResource() {

        private ConfigurableApplicationContext context;

        /*
         * (non-Javadoc)
         *
         * @see org.junit.rules.ExternalResource#before()
         */
        @Override
        protected void before() throws Throwable {
            System.setProperty("logging.level.org.apache.http.wire", "DEBUG");
            System.setProperty("logging.pattern.console", "%clr(%d{HH:mm:ss}){faint}%clr(:){faint} %m%n");
            context = SpringApplication.run(Application.class, new String[]{});
        }

        /*
         * (non-Javadoc)
         *
         * @see org.junit.rules.ExternalResource#after()
         */
        @Override
        protected void after() {
            context.close();
        }

    };

}
