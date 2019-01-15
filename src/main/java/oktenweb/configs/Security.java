package oktenweb.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

//DOWNLOAD https://www.getpostman.com/


@Configuration
public class Security extends WebSecurityConfigurerAdapter{

    //basicHttpSecurityPart1
 @Override
         protected void configure(AuthenticationManagerBuilder auth) throws Exception {
             auth.inMemoryAuthentication().withUser("asd").password("{noop}asd").roles("ADMIN");
             auth.inMemoryAuthentication().withUser("qwe").password("{noop}qwe").roles("USER");
         }
    //basicHttpSecurityPart2
     @Override
             protected void configure(HttpSecurity http) throws Exception {
                 http
                         .csrf().disable() // allows cross-browser requests
                         .authorizeRequests()
                         .antMatchers("/").hasAnyRole("USER","ADMIN")
                         .antMatchers("/admin/**").hasRole("ADMIN")
                         .antMatchers("/user/**").hasRole("USER")
                         .and()
                         .httpBasic() // support of basic http configuration
                         .realmName("xxx")
                         // setting if auth failed customBasicAuthEntryPoint() must be define
                         //.authenticationEntryPoint(customBasicAuthEntryPoint())
                         //if We don't need sessions to be created.
                         .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

             }

        private BasicAuthenticationEntryPoint customBasicAuthEntryPoint(){
            return new BasicAuthenticationEntryPoint();
        }

    //basicHttpSecurityPart4

                /* To allow Pre-flight [OPTIONS] request from browser */
//                @Override
//                public void configure(WebSecurity web) throws Exception {
//                    web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
//                }
           // }



}
