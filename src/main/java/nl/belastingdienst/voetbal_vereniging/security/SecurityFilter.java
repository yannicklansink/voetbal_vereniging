package nl.belastingdienst.voetbal_vereniging.security;

import nl.belastingdienst.voetbal_vereniging.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Create and configure an instance of a Password encoder to encrypt the users passwords.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return customUserDetailsService;
//    }

//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder)
//            throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(customUserDetailsService)
//                .passwordEncoder(passwordEncoder)
//                .and()
//                .build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf -> Cross Site Request Forgery. An attack that forces an end user to execute unwanted actions on a web application in which they're currently authenticated.
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/authenticated").permitAll()
                .antMatchers("/users").permitAll()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/books").hasRole("USER")
                .antMatchers("/**").denyAll()
                .and()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); //adds a filter before the position of the specified filter class.

        return http.build();
    }
}
