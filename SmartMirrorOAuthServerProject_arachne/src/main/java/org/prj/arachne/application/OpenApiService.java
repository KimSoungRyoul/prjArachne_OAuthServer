package org.prj.arachne.application;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.domain.weather.SimpleWeather;
import org.prj.arachne.domain.weather.WeatherForecast;
import org.prj.arachne.domain.weather.repository.FcsTextRepository;
import org.prj.arachne.domain.weather.repository.FscPieceRepository;
import org.prj.arachne.domain.weather.repository.SimpleWeatherRepository;
import org.prj.arachne.domain.weather.repository.WeatherForecastRepositroy;
import org.prj.arachne.util.weather.SKTWeatherOpenApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class OpenApiService {


	private SKTWeatherOpenApiUtil sktwApi;


	private WeatherForecastRepositroy wfRepo;


	private FscPieceRepository fpRepo;


	private FcsTextRepository ftRepo;

	private SimpleWeatherRepository simpleWeatherRepository;


	@Transactional
	public SimpleWeather requestwSimpleWeatherForecast(String lat, String lon, Long memberId){

		SimpleWeather simpleWeather=null;

		simpleWeather= simpleWeatherRepository.findTopByGridLatitudeAndGridLongtitudeAndWeatherOwnerMemberId(Double.parseDouble(lat),Double.parseDouble(lon),memberId);

		if(simpleWeather==null){
			log.info("simpleWeather::: db에 저장된 날씨정보가 없습니다 Api를 통해 데이터를 가져옵니다......");

			simpleWeather = sktwApi.requestSimpleWeatherForecaset(lat,lon);
			simpleWeather.setWeatherOwner(new MemberAccount(memberId));
			simpleWeatherRepository.save(simpleWeather);

		}

		return simpleWeather;


	}

	@Transactional
	public SimpleWeather requestwSimpleWeatherForecast(Long memberId){

		SimpleWeather simpleWeather=null;

		simpleWeather= simpleWeatherRepository.findTopByWeatherOwnerMemberId(memberId);



		return simpleWeather;


	}






	@Transactional
	public WeatherForecast requestwForecast(String city, String county, String village) {

		WeatherForecast wf;

		wf = wfRepo.findTop1ByGridCityAndGridCountryAndGridVillageOrderByReleaseTime(city, county, village);


		if (wf == null) {
			log.info("db에 저장된 날씨정보가 없습니다 Api를 통해 데이터를 가져옵니다......");

			wf = sktwApi.requestWeatherForecast(city, county, village);
			fpRepo.save(wf.getFcsPieceList());
			ftRepo.save(wf.getFcstextPair());
			wfRepo.save(wf);
		}else {
			wf.getFcsPieceList();
			wf.getFcstextPair();
			
		}
		return wf;
	}

	@Transactional
	public WeatherForecast requestwForecast(String latitude, String longitude) {

		WeatherForecast wf;

		wf = wfRepo.findTop1ByGridLatitudeAndGridLongtitude(Double.parseDouble(latitude),Double.parseDouble(longitude));


		if (wf == null) {
			log.info("db에 저장된 날씨정보가 없습니다 Api를 통해 데이터를 가져옵니다......");
			wf = sktwApi.requestWeatherForecast(latitude,longitude);

		}else {
			wf.getFcsPieceList();
			wf.getFcstextPair();

		}
		return wf;
	}


	@Transactional
	public void registerWeatherForcaset(WeatherForecast wf) {

		fpRepo.save(wf.getFcsPieceList());
		ftRepo.save(wf.getFcstextPair());

		wfRepo.save(wf);

	}

}
