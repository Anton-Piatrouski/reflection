package by.epamlab.service;

import java.lang.reflect.Field;

import by.epamlab.annotation.Equal;
import by.epamlab.beans.Test;
import by.epamlab.enums.CompareBy;

public class EqualityAnalyzer {
	public static boolean equalObjects(Object obj1, Object obj2) throws IllegalAccessException {
		boolean isEqual = true;
		
		@SuppressWarnings("unchecked")
		Class<Test> c1 = (Class<Test>) obj1.getClass();
		@SuppressWarnings("unchecked")
		Class<Test> c2 = (Class<Test>) obj2.getClass();
		
		Field[] fieldsObj1 = c1.getDeclaredFields();
		Field[] fieldsObj2 = c2.getDeclaredFields();
		
		final int FIELDS_AMOUNT = fieldsObj1.length;
		
		for (int i = 0; i < FIELDS_AMOUNT; i++) {
			Field field1 = fieldsObj1[i];
			Field field2 = fieldsObj2[i];
			
			field1.setAccessible(true);
			field2.setAccessible(true);
			
			String value1 = (String) field1.get(obj1);
			String value2 = (String) field2.get(obj2);
			String fieldName = field1.getName();
			
			if (field1.isAnnotationPresent(Equal.class)) {
				
				Equal ann = field1.getAnnotation(Equal.class);
				
				if (ann.compareby() == CompareBy.VALUE) {
					isEqual &= value1.equals(value2);
					System.out.println(fieldName + " compare by value");
				}
				if (ann.compareby() == CompareBy.REFERENCE) {
					isEqual &= (value1 == value2);
					System.out.println(fieldName + " compare by reference");
				}
			}
		}
		return isEqual;
	}
}
