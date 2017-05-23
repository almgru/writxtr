package danielalmgrundstrom.writxtr.ui;

import javafx.embed.swing.SwingNode;

import javax.swing.*;
import java.awt.*;

public class WritxtrTextArea extends SwingNode
{
	private final JTextPane editor;
	private static final long serialVersionUID = 3556985998540653598L;

	private boolean textChanged;
	
	public WritxtrTextArea(){
		editor = new JTextPane();

		this.setContent(editor);
	}
	
	public void init(){
        editor.setFont(new Font("Monospaced", Font.PLAIN, 12));
	}

	public String getText() {
        return editor.getText();
    }

	public void setText(String text) {
        editor.setText(text);
    }

    public void selectAll() {
        editor.selectAll();
    }

	public void setTextChanged(boolean b){
		textChanged = b;
	}
	
	public boolean getTextChanged(){
		return textChanged;
	}
	
	public void deleteText(){
		editor.replaceSelection("");
	}
}
