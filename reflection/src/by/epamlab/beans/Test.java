package by.epamlab.beans;

import by.epamlab.annotation.Equal;
import by.epamlab.enums.CompareBy;

public class Test {
	@Equal(compareby = CompareBy.VALUE)
	private String fieldA;
	
	@Equal(compareby = CompareBy.REFERENCE)
	private String fieldB;
	
	@SuppressWarnings("unused")
	private String fieldC;
	
	public Test() {
		
	}
	
	public Test(String fieldA, String fieldB, String fieldC) {
		this.fieldA = fieldA;
		this.fieldB = fieldB;
		this.fieldC = fieldC;
	}
}
