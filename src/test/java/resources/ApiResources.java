package resources;

public enum ApiResources {

	AddPlace("maps/api/place/add/json"), GetPlace("maps/api/place/get/json"), DeletePlace("maps/api/place/delete/json");

	private String resource;

	ApiResources(String resource) {
		// TODO Auto-generated constructor stub
		this.resource = resource;
	}

	public String getResources() {
		// TODO Auto-generated method stub
		return resource;
	}

}


