package writxtr.beans;

public class SaveEvent {
	private String content;
	
	public SaveEvent(String content){
		this.content = content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
}
