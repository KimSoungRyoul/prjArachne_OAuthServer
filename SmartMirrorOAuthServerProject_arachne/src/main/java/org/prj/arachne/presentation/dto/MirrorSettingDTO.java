package org.prj.arachne.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.prj.arachne.domain.member.MemberMirrorSettingInfo;

import java.lang.reflect.Member;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MirrorSettingDTO {

    //private Long settingOwnerId;

    private int posWeather;
    private int posCalendar;
    private int posTodoList;
    private int posCosRecom;
    private int posNews;
    private int posWatch;

    private String city;
    private String county;
    private String district;


    private String latitude;
    private String longitude;

    public MirrorSettingDTO(MemberMirrorSettingInfo memberMirrorSettingInfo) {

        this.setPosWeather(memberMirrorSettingInfo.getPosWeather());
        this.setPosCalendar(memberMirrorSettingInfo.getPosCalendar());
        this.setPosTodoList(memberMirrorSettingInfo.getPosTodoList());
        this.setPosCosRecom(memberMirrorSettingInfo.getPosCosRecom());
        this.setPosNews(memberMirrorSettingInfo.getPosNews());
        this.setPosWatch(memberMirrorSettingInfo.getPosWatch());

        this.setCity(memberMirrorSettingInfo.getCity());
        this.setCounty(memberMirrorSettingInfo.getCounty());
        this.setDistrict(memberMirrorSettingInfo.getDistrict());


        this.setLatitude(memberMirrorSettingInfo.getLatitude());
        this.setLongitude(memberMirrorSettingInfo.getLongitude());
    }



}
