package org.foi.nwtis.ihuzjak.zadaca_2.podaci;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor()
public class AerodromProblem {

	@Getter
    @Setter 
    @NonNull 
	String ident;
	
	@Getter
    @Setter 
    @NonNull 
	String description;
	
	@Getter
    @Setter 
    @NonNull 
	String stored;
}
