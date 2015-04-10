package danielalmgrundstrom.writxtr.beans;

public class SaveCompleteEvent {
	private String name;
	
	public SaveCompleteEvent(String name){
		this.name = name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
