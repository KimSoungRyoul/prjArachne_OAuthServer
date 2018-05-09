package org.prj.arachne.presentation.basicAuth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import io.swagger.annotations.*;
import org.prj.arachne.application.MemberAuthenticationService;
import org.prj.arachne.application.MirrorSettingService;
import org.prj.arachne.application.OpenApiService;
import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.domain.member.MemberMirrorSettingInfo;
import org.prj.arachne.domain.weather.SimpleWeather;
import org.prj.arachne.presentation.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Api(value = "basicAuthorize",description = "기본 인증 방식(가장 단순한 방식입니다.)")
@RestController
@RequestMapping("/basic")
public class AuthenticationApi {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MemberAuthenticationService memberAuthService;


	@Autowired
	private MirrorSettingService mirrorSettingService;


	@Autowired
	private OpenApiService openApiService;

	@ApiOperation(value="기본 인증 방식",response=AuthenticationToken.class,produces="application/json")
	@ApiImplicitParams({

	}
	)
	@PostMapping("/authorize")
	public AuthenticationToken login(@ApiParam(name = "인증 정보",value = "Email과 비밀번호",required = true)
			@RequestBody AuthenticationRequest authenticationRequest, HttpSession session) {
		String userEmail = authenticationRequest.getUserEmail();
		String password = authenticationRequest.getPassword();
		
		//log.info(authenticationRequest.toString());
		UsernamePasswordAuthenticationToken token;
		
		token= new UsernamePasswordAuthenticationToken(userEmail, password);
		
		//log.info(token.toString());
		Authentication authentication = authenticationManager.authenticate(token);
		
		//log.info("------------넘어가나?-------------");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				SecurityContextHolder.getContext());


		MemberAccount memberAccount = memberAuthService.readMemberAccount(userEmail);


		MirrorSettingDTO mirrorSettingDTO=new MirrorSettingDTO(mirrorSettingService.requestMirrorSetting(memberAccount.getMemberId()));

		SimpleWeather simpleWeather= openApiService.requestwSimpleWeatherForecast(memberAccount.getMemberId());


		return new AuthenticationToken(memberAccount.getMemberId(), memberAccount.getEmail(),
										memberAccount.getAuthorities(), session.getId(), mirrorSettingDTO,simpleWeather );
	}
	
	
	@ExceptionHandler(BadCredentialsException.class)
	public Map<String, Object> WrongIdentificationInfoException(){
		
		Map<String, Object> entity=new HashMap<>();
		
		
		entity.put("status", new StatusEntity("Authorize(로그인)", ArachneStatus.UNAUTHORIZED, "존재하지 않는 계정이거나 비밀번호가 틀렸습니다"));
		
		
		return entity;
	}
}
