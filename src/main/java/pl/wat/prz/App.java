package pl.wat.prz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.Locale;

@SpringBootApplication
public class App extends SpringBootServletInitializer{
        public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        Locale.setDefault(new Locale("pl", "PL"));
        return application.sources(App.class);
    }
}
