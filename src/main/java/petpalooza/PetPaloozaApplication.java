package petpalooza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PetPaloozaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetPaloozaApplication.class, args);

    }
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("*")
//                        .allowCredentials(true)
//                        .maxAge(3600)
//
//                        .exposedHeaders("X-Custom-Header", "Authorization");
//
//                        ;
//            }
//        };
//    }

}
