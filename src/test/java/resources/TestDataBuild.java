package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayLoad(String name, String language, String address) {
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");

		List<String> li = new ArrayList<>();
		li.add("shoe park");
		li.add("shop");
		ap.setTypes(li);

		Location l = new Location();
		l.setLat(38.383494);
		l.setLng(35.383494);
		ap.setLocation(l);

		return ap;

	}

}
