package writxtr.view;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentListener;

class WritxtrTextArea extends JTextArea implements Editor {

	private static final long serialVersionUID = 3556985998540653598L;

	WritxtrTextArea(){
		setBorder(BorderFactory.createEmptyBorder());
	}
	
    @Override
	void init(){
		setFont(new Font("Arial", Font.PLAIN, 12));
	}
	
	public void setCaretListener(CaretListener caretListener){
		addCaretListener(caretListener);
	}
	
	public void setDocumenetListener(DocumentListener documentListener){
		getDocument().addDocumentListener(documentListener);
	}
	
    @Override
	void clearText(){
		replaceSelection("");
	}
}
