package user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import user.filter.TokenAuthenticationFilter;


@SpringBootApplication
@EnableJpaRepositories("user.repository")
@Configuration
@ComponentScan(value = "user")
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled=true)
public class App extends WebSecurityConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf()
                .disable()
                .addFilterAfter(restTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean(name = "restTokenAuthenticationFilter")
    public TokenAuthenticationFilter restTokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }
}
