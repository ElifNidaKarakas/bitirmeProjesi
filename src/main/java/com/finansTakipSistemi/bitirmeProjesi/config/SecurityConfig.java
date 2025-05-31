package com.finansTakipSistemi.bitirmeProjesi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.Customizer;

@Configuration  // Bu sınıfın bir Spring yapılandırma sınıfı olduğunu belirtir
public class SecurityConfig {

    // SecurityFilterChain bean'i tanımlanır, HTTP güvenlik yapılandırmasını içerir
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF koruması devre dışı bırakılıyor (örneğin stateless API'ler için uygundur)
                .csrf(AbstractHttpConfigurer::disable)

                // CORS (Cross-Origin Resource Sharing) kontrolü devre dışı bırakılıyor
                .cors(AbstractHttpConfigurer::disable)

                // Yetkilendirme kuralları tanımlanıyor
                .authorizeHttpRequests((authorize) -> authorize

                        // POST isteğiyle /api/register endpoint'ine herkes erişebilir (kayıt için)
                        .requestMatchers(HttpMethod.POST, "/api/register").permitAll()

                        // Swagger dokümantasyonu için erişim izni veriliyor (dokümantasyon sayfaları için)
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()

                        // /api/users/all endpoint'ine sadece "admin" yetkisine sahip kullanıcılar erişebilir
                        .requestMatchers("/api/users/all").hasAuthority("admin")

                        // Diğer tüm endpoint'ler için kimlik doğrulama (authentication) zorunludur
                        .anyRequest().authenticated()
                )

                // Temel HTTP kimlik doğrulama yöntemi kullanılıyor (kullanıcı adı/şifre ile giriş)
                .httpBasic(Customizer.withDefaults());

        // SecurityFilterChain nesnesi döndürülür
        return http.build();
    }
}
