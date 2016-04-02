import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import by.epamlab.beans.Test;
import by.epamlab.service.EqualityAnalyzer;

public class Runner {

	public static void main(String[] args) {
		Class<Test> test = Test.class;
		Class<?>[] constructorParameterTypes = {String.class, String.class, String.class};
		
		Class<EqualityAnalyzer> equalityAnalyzer = EqualityAnalyzer.class;
		Class<?>[] methodParameterTypes = {Object.class, Object.class};
		try {
			Constructor<Test> constructor = test.getConstructor(constructorParameterTypes);
			Test obj1 = constructor.newInstance("qwe", "asd", "zxc");
			Test obj2 = constructor.newInstance("qwe", "asd", "zxcv");
			Test obj3 = constructor.newInstance("qwer", "asd", "zxc");
			
			Method equalObjects = equalityAnalyzer.getMethod("equalObjects", methodParameterTypes);
			
			boolean isEqual = (boolean) equalObjects.invoke(equalityAnalyzer, obj1, obj2);
			printResult(isEqual);
			
			isEqual = (boolean) equalObjects.invoke(equalityAnalyzer, obj1, obj3);
			printResult(isEqual);
			
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
	}
	
	private static void printResult(boolean isEqual) {
		System.out.println("Objects are " + (isEqual ? "equal" : "not equal"));
		System.out.print('\n');
	}

}
