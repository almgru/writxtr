package writxtr.ui;

import javafx.embed.swing.SwingNode;
import javafx.util.Pair;
import writxtr.model.SyntaxDocumentFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WritxtrTextArea extends SwingNode {
    private static final long serialVersionUID = 3556985998540653598L;

    private AbstractDocument doc;
    private JTextPane editor;

    private boolean textChanged;

    public WritxtrTextArea() {
        createAndSetSwingContent();
    }

    public void init() {

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

    public void setTextChanged(boolean b) {
        textChanged = b;
    }

    public boolean getTextChanged() {
        return textChanged;
    }

    public void deleteText() {
        editor.replaceSelection("");
    }

    private void createAndSetSwingContent() {
        SwingUtilities.invokeLater(() -> {
            editor = new JTextPane();
            editor.setEnabled(true);
            editor.setEditable(true);
            editor.setFont(new Font("Monospaced", Font.PLAIN, 12));
            editor.setCaretPosition(0);
            editor.setCaretColor(Color.LIGHT_GRAY);
            doc = (AbstractDocument) editor.getStyledDocument();
            editor.setBackground(Color.DARK_GRAY);

            doc.setDocumentFilter(new SyntaxDocumentFilter(editor));

            JScrollPane scrollPane = new JScrollPane(editor);
            setContent(scrollPane);

            /*
             Workaround for bug causing JTextPane to not be selectable when embedded in a SwingNode.
             */
            setOnMouseReleased(event -> requestFocus());
        });
    }
}
