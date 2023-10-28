package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * User: Z6PWA
 * Date: 28.10.2023
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
  @Bean
  public static NoOpPasswordEncoder passwordEncoder() {
    return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception
  {
    auth.inMemoryAuthentication().withUser("user").password("user").roles("BASIC");
    auth.inMemoryAuthentication().withUser("advanced").password("advanced").roles("ADVANCED");
    auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http.csrf().disable();

    http.authorizeRequests()
      .mvcMatchers(HttpMethod.GET, "/basic")
      .hasAnyRole("BASIC", "ADVANCED", "ADMIN")
      .mvcMatchers(HttpMethod.GET, "/advanced")
      .hasAnyRole("ADVANCED", "ADMIN")
      .mvcMatchers(HttpMethod.GET, "/admin")
      .hasAnyRole("ADMIN")
      .anyRequest()
      .fullyAuthenticated()
      .and()
      .httpBasic();
  }
}