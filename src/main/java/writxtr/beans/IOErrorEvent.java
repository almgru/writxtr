package writxtr.beans;

public class IOErrorEvent {
	private String errorMessage;
	private Exception exception;
	
	public IOErrorEvent(){
		
	}
	
	public IOErrorEvent(String errorMessage, Exception exception){
		this.errorMessage = errorMessage;
		this.exception = exception;
	}
	
	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}
	
	public void setException(Exception exception){
		this.exception = exception;
	}
	
	public String getErrorMessage(){
		return errorMessage;
	}
	
	public Exception getException(){
		return exception;
	}
}
