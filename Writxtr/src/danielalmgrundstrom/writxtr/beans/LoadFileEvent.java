package danielalmgrundstrom.writxtr.beans;

public class LoadFileEvent {
	
	private String fileContent;
	private String name;
	
	public LoadFileEvent(){
	}
	
	public void setFileContent(String fileContent){
		this.fileContent = fileContent;
	}
	
	public String getFileContent(){
		return fileContent;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
}
