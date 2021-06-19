package pl.stud.pw.EQSERVICE.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.stud.pw.EQSERVICE.Service.WebUserService;
import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final WebUserService webUserService;
    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource, WebUserService webUserService){
        this.dataSource = dataSource;
        this.webUserService = webUserService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Spring's Security DaoAuthenticationProvider is a simple authentication provider
     * that uses a Data Access Object (DAO) to retrieve user information from a relational database.
     * It leverages a UserDetailsService (as a DAO) in order to lookup the username, password and GrantedAuthority s.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(webUserService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login").authenticated()
                .antMatchers("/logged").authenticated()
                .antMatchers("/logged/*").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticateUser")
                .defaultSuccessUrl("/logged", true)
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();


    }

    /**
     * For enabling H2 database we need to disable csrf (do not do this on prod environment) and http headers
     * http.csrf().disable();
     * http.headers().frameOptions().disable();
     *
     * or we can set security to ignore h2 endpoint in configure(WebSecurity web) method. I took this approach.
     */

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

}