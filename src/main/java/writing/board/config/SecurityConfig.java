package writing.board.config;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import writing.board.entity.MemberRole;

@EnableWebSecurity
@AllArgsConstructor
@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private final AuthenticationSuccessHandler authenticationSuccessHandler;


    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("static/**", "/css/**", "/js/**", "/img/**", "/lib/**", "images/**");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/html/login", "html/register", "html/board").permitAll()
                .antMatchers("/html/member", "/html/update", "/html/delete", "/html/write").hasRole("USER")
                .antMatchers("/member/admin").hasRole("ADMIN");//antMatcher: ?: 한 글자, *: 임의의 파일 , **: 임의의 경로
        http.formLogin()
                .loginPage("/html/login")
                .successHandler(authenticationSuccessHandler)
//                .defaultSuccessUrl("/html/board")
                .usernameParameter("email")
                .failureUrl("/html/error")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/html/logout")).logoutSuccessUrl("/html/board").invalidateHttpSession(true)
                .and()
                .exceptionHandling().accessDeniedPage("/html/denied");

        http.csrf().disable();
    }


}
