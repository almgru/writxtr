package writxtr.beans;

public class LoadURLEvent {
	private String uRLContent;
	private String uRL;
	
	public LoadURLEvent() {
	}

	public void setURLContent(String uRLContent) {
		this.uRLContent = uRLContent;
	}

	public String getURLContent() {
		return uRLContent;
	}
	
	public void setURL(String uRL){
		this.uRL = uRL;
	}
	
	public String getURL(){
		return uRL;
	}
}
