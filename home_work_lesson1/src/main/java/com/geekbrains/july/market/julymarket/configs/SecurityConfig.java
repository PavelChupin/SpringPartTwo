package com.geekbrains.july.market.julymarket.configs;

import com.geekbrains.july.market.julymarket.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity  //(debug = true)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UsersService usersService;

    @Autowired
    public void setUserService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      /*  http.authorizeRequests()
                .antMatchers("/security/**").authenticated()
                .antMatchers("/admins/**").hasAnyRole("ADMIN", "SUPERADMIN") // ROLE_ADMIN, ROLE_SUPERADMIN
                //.antMatchers("/emails/**").hasAnyAuthority("CAN_READ_EMAIL") // CAN_READ_EMAIL
                .anyRequest().permitAll()
                .and()
                .formLogin()

               //Страница авторизации
               .loginPage("/login")
                 //Перенаправление на страницу после авторизации
                .loginProcessingUrl("/authenticate")
                .permitAll()
                .and()
                //Активирует разлогирование
                .logout()
                //Перенаправление на страницу после разлогирования
                .logoutSuccessUrl("/")
                .permitAll();*/
        http.authorizeRequests()
                .antMatchers("/users/**").authenticated()
                .antMatchers("/users/**").hasAnyRole("ADMIN")
                .antMatchers("/orders/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                //Страница авторизации
                .loginPage("/login")
                //Поинт запрос от которого получаем поле ввода логина и пароля
                .loginProcessingUrl("/authenticate")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usersService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    //Задание фиксированных пользователей
        /*@Bean
          public UserDetailsService users() {
            UserDetails user = User.builder()
                    .username("user")
                    .password("{bcrypt}$2y$12$mFUdPh8.ESnhu.eyDjxrYuSigUIOboDP94mt7vuNhf604Yw0iuKQa")
                    .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2y$12$mFUdPh8.ESnhu.eyDjxrYuSigUIOboDP94mt7vuNhf604Yw0iuKQa")
                .roles("USER", "ADMIN")
               .build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/
//Задание фиксированных пользователей через JDBC
//    @Bean
//    public JdbcUserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2y$12$mFUdPh8.ESnhu.eyDjxrYuSigUIOboDP94mt7vuNhf604Yw0iuKQa")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2y$12$mFUdPh8.ESnhu.eyDjxrYuSigUIOboDP94mt7vuNhf604Yw0iuKQa")
////                .roles("USER", "ADMIN")
//                .authorities("ROLE_USER", "ROLE_ADMIN", "CAN_READ_SOMETHING")
//                .build();
//
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        if (users.userExists(user.getUsername())) {
//            users.deleteUser(user.getUsername());
//        }
//        if (users.userExists(admin.getUsername())) {
//            users.deleteUser(admin.getUsername());
//        }
//        users.createUser(user);
//        users.createUser(admin);
//        return users;
//    }
//

}
