package danielalmgrundstrom.writxtr.ui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.text.Font;

public class WritxtrTextArea extends TextArea
{
	private static final long serialVersionUID = 3556985998540653598L;

	private boolean textChanged;
	
	public WritxtrTextArea(){
		setBorder(Border.EMPTY);
	}
	
	public void init(){
        setFont(new Font("Arial", 12));
	}
	
	public void setTextChanged(boolean b){
		textChanged = b;
	}
	
	public boolean getTextChanged(){
		return textChanged;
	}
	
	public void deleteText(){
		replaceSelection("");
	}
}
