package org.prj.arachne.configuration;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.file.Path;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class ApiDocumentConfiguration {

	@Bean
	public Docket swaggerSpringMvcPlugin() {

		return new Docket(DocumentationType.SWAGGER_2)
				
				.apiInfo(apiInfo())
				.select()
				   			
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())



				
				.build();
				
	}

	private ApiInfo apiInfo() {
		Contact contact=new Contact("Arachne", "http://www.ksr.pe.kr", "Arachne0823@gmail.com");
		String license="본 프로젝트는 Apache2.0 License를 따릅니다.";
		String licenseUrl="http://www.apache.org/licenses/LICENSE-2.0";
		String title="아라크네 프로젝트  서버 Api문서 ";
		String description="본 문서는 아라크네 프로젝트의 인증시스템 관련 API를 서술하고있습니다.";



		return new ApiInfoBuilder()
				.contact(contact)
				.license(license)
				.licenseUrl(licenseUrl)
				.title(title)
				.description(description)
				.version("version1.0418")
				.termsOfServiceUrl("2018-04-18")

				.build();


	}
	
}