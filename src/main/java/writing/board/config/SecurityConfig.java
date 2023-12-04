package writing.board.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/member/all").permitAll()
                .antMatchers("/member/member").hasRole("USER")
                .antMatchers("/member/admin").hasRole("ADMIN");//antMatcher: ?: 한 글자, *: 임의의 파일 , **: 임의의 경로
        http.formLogin()
                .loginPage("/member/login")
                .loginProcessingUrl("/member/loginProc")
                .defaultSuccessUrl("/member/member")
                .usernameParameter("email")
                .failureUrl("/member/error")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")).logoutSuccessUrl("/member/login").invalidateHttpSession(true);
        //http.csrf().disable();
    }

}
