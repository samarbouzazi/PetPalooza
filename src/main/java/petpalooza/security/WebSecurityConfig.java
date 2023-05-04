package petpalooza.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import petpalooza.security.jwt.AuthEntryPointJwt;
import petpalooza.security.jwt.AuthTokenFilter;
import petpalooza.security.predifinedClasses.UserDetailsServiceImpl;

@Configuration
@Import(WebMvcConfig.class)
@EnableGlobalMethodSecurity(
     securedEnabled = true,
     jsr250Enabled = true,
    prePostEnabled = true)


public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//      return http.csrf().disable().authorizeRequests().anyRequest().permitAll().and().build();
//  }


        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/public/**", "/animal/**").permitAll()
                .antMatchers("/admin/user/list").hasRole("ADMIN")
                .antMatchers("/private/user/user").hasRole("USER")
                .antMatchers("/private/user/mod").hasRole("MANAGER")
                .antMatchers("/appointment/**", "/profile/**", "/Questions/**", "/Responses/**").authenticated();
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/public/logout"));
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/public/logout"))
                .logoutSuccessUrl("/logout.done").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
//
//    http.cors().and().csrf().disable()
//        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//        .authorizeRequests()
//            .antMatchers("/public/**").permitAll()
//            .antMatchers("/private/user/admin").hasRole("ADMIN")
//            .antMatchers("/private/user/user").hasRole("USER")
//            .antMatchers("/private/user/mod").hasRole("MANAGER")
//          .antMatchers("/animal/**", "/appointment/**", "/profile/**", "/Questions/**","/Responses/**", "/admin/**").authenticated();
////    http.logout().logoutRequestMatcher(new AntPathReque
// stMatcher("/public/logout"));
//  http  .logout().logoutRequestMatcher(new AntPathRequestMatcher("/public/logout"))
//            .logoutSuccessUrl("/logout.done").deleteCookies("JSESSIONID")
//            .invalidateHttpSession(true);
//
//    http.authenticationProvider(authenticationProvider());
//
//    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//
//    return http.build();
