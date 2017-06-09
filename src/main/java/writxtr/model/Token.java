package writxtr.model;

/**
 * Created by danalm on 2017-06-09.
 */
public class Token {
    private TokenType type;
    private int start;
    private int length;

    public Token(TokenType type, int startIndex, int length) {
        this.type = type;
        this.start = startIndex;
        this.length = length;
    }

    public TokenType getType() { return type; }
    public int getStartIndex() { return start; }
    public int getLength() { return length; }
}
