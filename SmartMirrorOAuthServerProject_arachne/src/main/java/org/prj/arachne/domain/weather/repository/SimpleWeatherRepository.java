package org.prj.arachne.domain.weather.repository;

import org.prj.arachne.domain.weather.SimpleWeather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleWeatherRepository extends JpaRepository<SimpleWeather,Long> {

    SimpleWeather findTopByGridLatitudeAndGridLongtitudeAndWeatherOwnerMemberId(double latitude, double longtitude, Long weatherOwnerId);

    SimpleWeather findTopByWeatherOwnerMemberId(Long weatherOwnerId);


}
