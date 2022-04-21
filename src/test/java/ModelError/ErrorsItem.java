package ModelError;

import java.util.List;

public class ErrorsItem{
	private List<String> path;
	private Extensions extensions;
	private List<LocationsItem> locations;
	private String message;

	public void setPath(List<String> path){
		this.path = path;
	}

	public List<String> getPath(){
		return path;
	}

	public void setExtensions(Extensions extensions){
		this.extensions = extensions;
	}

	public Extensions getExtensions(){
		return extensions;
	}

	public void setLocations(List<LocationsItem> locations){
		this.locations = locations;
	}

	public List<LocationsItem> getLocations(){
		return locations;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}