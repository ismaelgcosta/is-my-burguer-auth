package br.com.ismyburguer.core.auth;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Setter
    @Value("${spring.security.oauth2.client.resourceserver.cognito.issuer-uri}")
    private String issuerUri;

    @Bean
    @Profile(value = {"dev", "production"})
    public SecurityFilterChain filterChain(HttpSecurity http, JwtDecoder jwtDecoder) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> {
                    authz
                            .requestMatchers(
                                    "/actuator/**",
                                    "/auth/token/**",
                                    "/login",
                                    "/logout",
                                    "/webjars/**",
                                    "/v3/api-docs/**",
                                    "/swagger-ui*/*swagger-initializer.js",
                                    "/swagger-ui*/**")
                            .permitAll();
                    authz.requestMatchers("/**").authenticated();
                })
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
			    .oauth2Client(Customizer.withDefaults())
                .authenticationProvider(new JwtAuthenticationProvider(jwtDecoder))
        ;
        return http.build();
    }

    @Bean
    @Profile(value = {"dev", "production"})
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation(issuerUri);
    }


}