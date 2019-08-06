import com.google.gson.GsonBuilder;

import object.Person;

public class TimestampConverUseGson {

	public static void main(String[] args) {
		String jsonFormatData = "{\"name\":\"abc\",\"attendTime\":\"2019-08-07-01-43-38\"}";
		Person personData = new GsonBuilder().setDateFormat("yyyy-MM-dd-HH-mm-ss").create().fromJson(jsonFormatData, Person.class);
		System.out.println(personData);
	}

}
