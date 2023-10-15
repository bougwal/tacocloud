package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import tacos.User;
import tacos.data.UserRepository;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo){
        return username -> {
            User user = userRepo.findByUsername(username); 
            if(user != null) {
                return user; 
            }
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
            .antMatchers("/design", "/orders").access("hasRole('USER')")
            .antMatchers("/", "/**").access("permitAll")
    
          .and()
            .formLogin()
              .loginPage("/login")
    
          .and()
            .logout()
              .logoutSuccessUrl("/")
    
          // Make H2-Console non-secured; for debug purposes
          .and()
            .csrf()
              .ignoringAntMatchers("/h2-console/**")
    
          // Allow pages to be loaded in frames from the same origin; needed for H2-Console
          .and()
            .headers()
              .frameOptions()
                .sameOrigin()
          ;
      }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {

    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(encoder());

  }

}
