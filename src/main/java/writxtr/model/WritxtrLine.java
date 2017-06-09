package writxtr.model;

/**
 * Created by danalm on 2017-06-05.
 */
public class WritxtrLine {
    private String content;
    private int lineIndex;
    private int startIndex;
    private int endIndex;

    public WritxtrLine() {

    }

    public WritxtrLine(String content, int lineIndex, int startIndex, int endIndex) {
        this.content = content;
        this.lineIndex = lineIndex;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLineIndex() {
        return lineIndex;
    }

    public void setLineIndex(int lineIndex) {
        this.lineIndex = lineIndex;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}