package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {
		AddPlace addPlace = new AddPlace();
		Location location = new Location();
		List<String> types = new ArrayList<>();

		addPlace.setAccuracy(50);
		addPlace.setAddress(address);
		addPlace.setLanguage(language);
		addPlace.setWebsite("https://rahuldesai.com");
		addPlace.setPhone_number("(+91) 901 889 7755");
		addPlace.setName(name);
		types.add("Housing Society");
		types.add("co-operative");
		addPlace.setTypes(types);
		location.setLat(-32.4930);
		location.setLng(33.4563);
		addPlace.setLocation(location);

		return addPlace;
	}

	public String deletePlacePayload(String place_id) {
		return "{\r\n   \"place_id\":\"" + place_id + "\"\r\n}";
	}
}
