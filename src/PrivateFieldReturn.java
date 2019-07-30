
import java.lang.reflect.Field;

public class PrivateFieldReturn {
	
	private String variable1;
	private String variable2;
	
	public PrivateFieldReturn() {
		this.variable1 = "value1";
		this.variable2 = "value2";
	}
	
	public Object getVariable(String variableName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Object result = null;
		Field field = this.getClass().getDeclaredField(variableName);
		System.out.println(field.isAccessible());
		if (field.isAccessible()) {
			result = field.get(this);
			System.out.println("process check(1) > " + result);
		} else {
			field.setAccessible(true);
			if (field.isAccessible()) {
				result = field.get(this);
				System.out.println("process check(2) > " + result);
			}
		}
		return result;
	}
}
