package com.spring.jwt.jwtrevision.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserPricipleDetailsService userPricipleDetailsService;
    public SecurityConfiguration(UserPricipleDetailsService userPricipleDetailsService){
        this.userPricipleDetailsService = userPricipleDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                authenticationProvider(authenticationProvider());

//        auth
//             .inMemoryAuthentication()
//             .withUser("ahmed")
//             .password(passwordEncoder().encode("ahmed123"))
////             .roles("ADMIN")
//             .authorities("ACCESS_BASIC1", "ROLE_ADMIN")
//             .and()
//             .withUser("yasser")
//             .password(passwordEncoder()
//             .encode("yasser123"))
////             .roles("MANAGER")
//             .authorities("ACCESS_BASIC2", "ROLE_MANAGER")
//             .and()
//             .withUser("kareem").password(passwordEncoder().encode("kareem123")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .anyRequest().authenticated()
                .antMatchers("/api/v1/main").permitAll()
                .antMatchers("/api/v1/profile").authenticated()
                .antMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .antMatchers("/api/v1/management").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("api/v1/basic/mybasic").hasAuthority("ACCESS_BASIC1")
                .antMatchers("/api/v1/basic/allbasic").hasAuthority("ACCESS_BASIC2")
                .and()
                .formLogin()
                .loginProcessingUrl("/signin")
                .loginPage("/api/v1/login")
                .usernameParameter("user")
                .passwordParameter("pass")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/api/v1/main");
//                .httpBasic();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userPricipleDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
