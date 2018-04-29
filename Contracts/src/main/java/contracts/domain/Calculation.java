package contracts.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(as = DefCalculationImpl.class)
@JsonSerialize(as = DefCalculationImpl.class)
public interface Calculation {
	
	float getEstateCoeff();
	
	float getBuildYearCoeff() throws NumberFormatException;
	
	float getAreaCoeff() throws NumberFormatException;
		
	void calculate() throws Exception;
	
}
