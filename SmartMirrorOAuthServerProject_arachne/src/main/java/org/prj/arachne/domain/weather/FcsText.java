package org.prj.arachne.domain.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FcsText {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Date timeRelease;
	private String locationName;
	
	@Lob
	private String text;
	

}
