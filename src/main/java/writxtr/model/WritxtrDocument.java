package writxtr.model;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by danalm on 2017-06-05.
 */
public class WritxtrDocument {

    private Collection<String> rawLines;

    public WritxtrDocument(Document doc) throws BadLocationException {
        rawLines = Arrays.asList(doc.getText(0, doc.getLength()).split("\\n"));
    }

    public Collection<String> getRawLines() {
        return rawLines;
    }
}
