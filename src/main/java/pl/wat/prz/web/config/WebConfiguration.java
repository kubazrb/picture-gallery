package pl.wat.prz.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@EntityScan("pl.wat.prz.engine.model")
@EnableJpaRepositories("pl.wat.prz.engine.repository")
@ComponentScan({"pl.wat.prz.engine.service", "pl.wat.prz.web.view.controller"})
public class WebConfiguration extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebConfiguration.class);
}
