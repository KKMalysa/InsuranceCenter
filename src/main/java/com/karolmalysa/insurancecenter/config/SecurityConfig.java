package com.karolmalysa.insurancecenter.config;

import com.karolmalysa.insurancecenter.model.components.CompanyClientComponnent;
import com.karolmalysa.insurancecenter.model.components.EmployeeComponnent;
import com.karolmalysa.insurancecenter.model.entities.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmployeeComponnent employeeComponnent;
    @Autowired
    private CompanyClientComponnent companyClientComponnent;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/swagger-ui/**").hasAnyRole(UserRoles.ADMIN.name(), UserRoles.USER.name(), UserRoles.CLIENT_PREMIUM.name())
                .antMatchers("/v3/**").permitAll()
                .antMatchers("/claims/**").hasAnyRole(UserRoles.ADMIN.name())
                .antMatchers("/companyClient/**").hasAnyRole(UserRoles.ADMIN.name())
                .anyRequest().authenticated()
                .and()
//                .httpBasic();
                .formLogin();

        http.csrf().disable();

//        http.oauth2Login()
//                .successHandler(oauth2AuthenticationSuccessHandler());

    }

    @Bean
    public AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/successfulLogin");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = passwordEncoder();

        //for dev needs users
        auth.inMemoryAuthentication()
                .withUser("KKM").password(encoder.encode("79988")).roles(String.valueOf(UserRoles.ADMIN))
                .and()
                .withUser("user1").password(encoder.encode("1234")).roles(String.valueOf(UserRoles.USER))
                .and()
                .withUser("client1").password(encoder.encode("1234")).roles(String.valueOf(UserRoles.CLIENT_PREMIUM));

        auth.userDetailsService(employeeComponnent)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

        auth.userDetailsService(companyClientComponnent)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


