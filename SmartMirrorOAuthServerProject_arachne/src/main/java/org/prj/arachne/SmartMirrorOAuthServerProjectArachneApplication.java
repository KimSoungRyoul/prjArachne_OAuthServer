package org.prj.arachne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class SmartMirrorOAuthServerProjectArachneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartMirrorOAuthServerProjectArachneApplication.class, args);
	}
}
