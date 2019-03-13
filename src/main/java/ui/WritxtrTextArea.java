package writxtr.ui;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentListener;

public class WritxtrTextArea extends JTextArea {

	private static final long serialVersionUID = 3556985998540653598L;

	private boolean textChanged;
	
	public WritxtrTextArea(){
		setBorder(BorderFactory.createEmptyBorder());
	}
	
	public void init(){
		setFont(new Font("Arial", Font.PLAIN, 12));
	}
	
	public void setCaretListener(CaretListener caretListener){
		addCaretListener(caretListener);
	}
	
	public void setDocumenetListener(DocumentListener documentListener){
		getDocument().addDocumentListener(documentListener);
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
