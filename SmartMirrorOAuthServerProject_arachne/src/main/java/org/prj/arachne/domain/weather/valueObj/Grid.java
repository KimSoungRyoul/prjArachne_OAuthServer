package org.prj.arachne.domain.weather.valueObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grid {
	
	private String village;
	private String city;
	private String country;
	private double latitude;
	private double longtitude;

}
