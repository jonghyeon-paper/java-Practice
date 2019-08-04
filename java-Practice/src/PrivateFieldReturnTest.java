
public class PrivateFieldReturnTest {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		PrivateFieldReturn a = new PrivateFieldReturn();
		Object result = a.getVariable("variable1");
		System.out.println("final result > " + result);
	}

}
