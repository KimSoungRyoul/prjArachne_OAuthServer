package org.prj.arachne.presentation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "인증 포멧")
public class AuthenticationRequest {

	@ApiModelProperty(example = "KimSoungRyoul@gmail.com")
	private String userEmail;

	@ApiModelProperty(example = "12345")
	private String password;
}
