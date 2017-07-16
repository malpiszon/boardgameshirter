package net.malpiszon.boardgameshirter;

import static springfox.documentation.builders.PathSelectors.regex;

import javax.print.Doc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BoardgameshirterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardgameshirterApplication.class, args);
	}

	/**
	 * Swagger configuration.
	 * @return Docket for Swagger
	 */
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("api")
				.apiInfo(apiInfo())
				.select()
				.paths(regex("/api/v1.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Board Game Shirter")
				.description("API documentation")
				.contact(new Contact("malpiszon", "https://malpiszon.net", "alan@malpiszon.net"))
				.version("1.0")
				.build();
	}
}
