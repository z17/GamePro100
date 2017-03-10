package result;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import service_client.client.ExecutorClient;
import service_client.client.TaskClient;
import service_client.client.UserClient;

@SpringBootApplication(scanBasePackages = "result")
@EnableJpaRepositories("result.repository")
@Configuration
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public UserClient getUserClient() {
        return new UserClient();
    }

    @Bean
    public TaskClient getTaskClient() {
        return new TaskClient();
    }

    @Bean
    public ExecutorClient getTaskExecutor() {
        return new ExecutorClient();
    }
}