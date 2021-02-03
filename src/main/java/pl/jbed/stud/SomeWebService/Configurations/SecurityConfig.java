package pl.jbed.stud.SomeWebService.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import pl.jbed.stud.SomeWebService.Service.CustomerService;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomerService service;
    private DataSource dataSource;

    @Autowired
    public SecurityConfig(CustomerService service, DataSource dataSource) {
        this.service = service;
        this.dataSource = dataSource;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }


    /**
     * Spring's Security DaoAuthenticationProvider is a simple authentication provider
     * that uses a Data Access Object (DAO) to retrieve user information from a relational database.
     * It leverages a UserDetailsService (as a DAO) in order to lookup the username, password and GrantedAuthority s.
    */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(service);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/service/login").authenticated()
                .antMatchers("/service/logged").authenticated()
                .antMatchers("/service/logged/*").authenticated()
                .and()
                .rememberMe().tokenRepository(persistentTokenRepository())
                .rememberMeParameter("remember-me")
                .useSecureCookie(true)
                .and()
                .formLogin()
                .loginPage("/service/login")
                .loginProcessingUrl("/authenticateUser")
                .defaultSuccessUrl("/service/logged", true)
                .permitAll()
                .and()
                .logout().permitAll();

    }

}













