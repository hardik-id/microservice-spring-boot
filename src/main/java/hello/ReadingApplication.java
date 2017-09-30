package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableCircuitBreaker
@SpringBootApplication
public class ReadingApplication extends WebMvcConfigurerAdapter {

  @Bean
  public RestTemplate rest(RestTemplateBuilder builder) {
    return builder.build();
  }

  public static void main(String[] args) {
    System.setProperty("spring.devtools.restart.enabled", "true");
    SpringApplication.run(ReadingApplication.class, args);
  }

  /*@Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurerAdapter() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:4201");
      }
    };
  }*/

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("http://localhost:4201", "http://localhost:8787", "http://*.semicolon.guru", "http://*.amazonaws.com", "http://todo.semicolon.guru.s3-website-eu-west-1.amazonaws.com")
            .allowedMethods("POST", "GET",  "PUT", "OPTIONS", "DELETE", "PATCH")
            .allowedHeaders("X-Auth-Token", "Content-Type")
            .exposedHeaders("custom-header1", "custom-header2")
            .allowCredentials(false)
            .maxAge(4800);
  }
}
