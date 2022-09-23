package org.codetechn.auth.config;

import org.codetechn.auth.jwt.JwtTokenFilter;
import org.codetechn.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = false,securedEnabled = false,jsr250Enabled = true)
public class ApplicationSecurity  {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  JwtTokenFilter jwtTokenFilter;


     @Bean
     public UserDetailsService userDetailsService(){
         return new UserDetailsService() {
             @Override
             public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
             return    userRepository.
                     findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User with " +username+ "not found"));
             }
         };
     }

   @Bean
   public SecurityFilterChain configure (HttpSecurity httpSecurity) throws Exception{
       httpSecurity.csrf().disable();
       httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       httpSecurity.authorizeRequests().antMatchers("/auth/**","/doc/**", "/users").permitAll().anyRequest().authenticated();
       httpSecurity.exceptionHandling()
               .authenticationEntryPoint( new MyauthException());
       httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
       return httpSecurity.build();
   }


  /*  @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .anyRequest().authenticated();
        httpSecurity.exceptionHandling()
                .authenticationEntryPoint( new MyauthException());
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }
    @Bean
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(
                username -> userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User with " +username+ "not found"))
        );
    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }


}
