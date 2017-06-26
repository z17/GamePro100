package result;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import service_client.client.ExecutorClient;
import service_client.client.TaskClient;
import service_client.client.UserClient;
import service_client.security.TokenAuthenticationFilter;
import service_client.security.TokenService;

@SpringBootApplication(scanBasePackages = "result")
@EnableJpaRepositories("result.repository")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled=true)
public class App extends WebSecurityConfigurerAdapter {

    @Value("${token.key}")
    private String tokenKey;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf()
                .disable()
                .addFilterAfter(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean(name = "tokenAuthenticationFilter")
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenService());
    }

    @Bean(name = "tokenService")
    public TokenService tokenService() {
        TokenService tokenService = new TokenService();
        tokenService.setKey(tokenKey);
        return tokenService;
    }
}