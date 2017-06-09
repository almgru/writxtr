package writxtr.model;

/**
 * Created by danalm on 2017-06-09.
 */
public enum TokenType {
    LineComment,
    MultilineComment,
    DocComment,
    Annotation,
    Number,
    String,
    Operator,
    Delimiter,
    Whitespace,
    Identifier,
    Unknown
}
