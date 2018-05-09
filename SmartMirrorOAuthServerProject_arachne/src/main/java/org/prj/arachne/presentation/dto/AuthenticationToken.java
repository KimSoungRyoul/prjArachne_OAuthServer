package org.prj.arachne.presentation.dto;

import java.util.Collection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import org.prj.arachne.domain.member.MemberAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.prj.arachne.domain.weather.SimpleWeather;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "인증 토큰 리턴 값")
public class AuthenticationToken {

    @ApiModelProperty(value = "회원 고유 씨리얼 번호", example = "1", dataType = "Long")
    private Long memberSerialNum;

    @ApiModelProperty(value = "회원 계정", example = "KimSoungRyoul@gamil.com", dataType = "string")
    private String userEmail;

    @ApiModelProperty(value = "해당 토큰이 가진 권한", example = "NOMAL_USER", dataType = "string")
    private Collection<MemberAuthority> authorities;
    @ApiModelProperty(value = "인증토큰", dataType = "string", example = "we23weg4-24r223r32r")
    private String token;

    @ApiModelProperty(value = "거울세팅정보", dataType = "MirrorSettingDTO")
    private MirrorSettingDTO mirrorSettingDTO;

    @ApiModelProperty(value = "날씨 정보",dataType = "SimpleWeather")
    private SimpleWeather simpleWeather;


}

