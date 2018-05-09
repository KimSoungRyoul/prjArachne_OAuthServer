package org.prj.arachne.domain.weather.valueObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FcstDaily {

	private String todayMaxTem;
	private String tomorrowMaxTem;
	private String dayAfterTomorrowMaxTem;
	
	private String todayMinTem;
	private String tomorrowMinTem;
	private String dayAfterTomorrowMinTem;
	
	
	
}
