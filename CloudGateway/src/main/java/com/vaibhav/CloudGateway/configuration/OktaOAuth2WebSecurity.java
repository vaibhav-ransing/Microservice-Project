package com.vaibhav.CloudGateway.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class OktaOAuth2WebSecurity {

//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
//
//        http
//                .authorizeExchange()
//                .anyExchange().authenticated()
//                .and()
//                .oauth2Login()
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
//        return http.build();
//    }

    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        http
                .authorizeExchange(authorize -> authorize
                        .anyExchange().authenticated()
                )
                .oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                );
        return http.build();
    }

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(
//            ServerHttpSecurity http) {
//        return http.authorizeExchange(exchanges -> exchanges
//                        .anyExchange().authenticated())
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login"))
//                .logout(logoutSpec -> logoutSpec.logoutUrl("/logout"))
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)  //.csrf(csrf -> csrf.disable())
//                .build();
//    }

}
