package writxtr.model;

import javafx.util.Pair;
import writxtr.ui.WritxtrTextArea;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by danalm on 2017-06-05.
 */
public class SyntaxDocumentFilter extends DocumentFilter {
    private final JTextPane pane;

    private static final Map<String, SimpleAttributeSet> SYNTAX_PATTERNS;

    static {
        Map<String, SimpleAttributeSet> temp = new HashMap<>();

        List<String> keywords = Arrays.asList("abstract", "assert", "boolean", "break", "byte", "case", "catch",
                "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "final",
                "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface",
                "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static",
                "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void",
                "volatile", "while");

        SimpleAttributeSet keywordsAttrs = new SimpleAttributeSet();
        keywordsAttrs.addAttribute(StyleConstants.ColorConstants.Foreground, Color.ORANGE);

        for (String s : keywords) {
            temp.put(s, keywordsAttrs);
        }

        List<String> comments = Arrays.asList("/*", "/**", "//");
        SimpleAttributeSet commentAttrs = new SimpleAttributeSet();
        commentAttrs.addAttribute(StyleConstants.ColorConstants.Foreground, Color.GREEN);

        for (String s : comments) {
            temp.put(s, commentAttrs);
        }

        SimpleAttributeSet defaultAttrs = new SimpleAttributeSet();
        defaultAttrs.addAttribute(StyleConstants.ColorConstants.Foreground, Color.LIGHT_GRAY);
        temp.put("default", defaultAttrs);

        SYNTAX_PATTERNS = Collections.unmodifiableMap(temp);
    }

    public SyntaxDocumentFilter(JTextPane pane) {
        this.pane = pane;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        text = text.replaceAll("\t", "    ");

        super.insertString(fb, offset, text, attr);
        updateText(fb, offset, text);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
        updateText(fb, offset, "");
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        text = text.replaceAll("\t", "    ");

        super.replace(fb, offset, length, text, attrs);
        updateText(fb, offset, text);
    }

    private void updateText(FilterBypass fb, int offset, String text) throws BadLocationException {
        // Decided from text inserted text content what lines to consider
        int changedLineStart = getLineOfOffset(offset) + 1;
        int changedLineEnd = changedLineStart + text.split(System.getProperty("line.separator")).length - 1;

        /*
        String rawText = fb.getDocument().getText(0, fb.getDocument().getLength());
        List<String> rawLines = Arrays.asList(rawText.split(System.getProperty("line.separator")));
        List<WritxtrLine> linesToCheck = new ArrayList<>();

        for (int lineIndex = changedLineStart - 1; lineIndex < changedLineEnd && lineIndex < rawLines.size(); lineIndex++) {
            String lineContents = rawLines.get(lineIndex);

            int lineStart = getLineStartOffset(lineIndex);
            int lineEnd = lineStart + lineContents.length();

            linesToCheck.add(new WritxtrLine(lineContents, lineIndex, lineStart, lineEnd));
        }
        */

        // Find tokens in the selected lines
        List<Token> tokens = tokenize(fb.getDocument().getText(0, fb.getDocument().getLength()));


        // Add syntax to tokens
        for (WritxtrLine line : linesToCheck) {
            String lineContents = line.getContent();
            List<String> wordsInLine = Arrays.asList(lineContents.split("\\s+"));
            int lineOffset = line.getStartIndex();

            if (lineContents.startsWith("//")) {
                SimpleAttributeSet lineStyle = SYNTAX_PATTERNS.getOrDefault("//", SYNTAX_PATTERNS.get("default"));
                pane.getStyledDocument().setCharacterAttributes(lineOffset, lineContents.length(), lineStyle, true);
            } else {
                for (int i = 0, from = 0; i < wordsInLine.size(); i++) {
                    String word = wordsInLine.get(i);

                    int startIndex = lineContents.indexOf(word, from) + lineOffset;

                    from += word.length();
                    while (from + lineOffset + 1 < line.getEndIndex()
                            && !lineContents.isEmpty()
                            && Character.isWhitespace(lineContents.charAt(from))) {
                        from++;
                    }

                    SimpleAttributeSet wordStyle = SYNTAX_PATTERNS.getOrDefault(word, SYNTAX_PATTERNS.get("default"));
                    pane.getStyledDocument().setCharacterAttributes(startIndex, word.length(), wordStyle, true);
                }
            }
        }
    }

    private List<Token> tokenize(String text) {
        List<Token> tokens = new ArrayList<>();

        List<LexicalRule> rules = new ArrayList<>();
        //rules.add(new LexicalRule(TokenType.String, ""));
        rules.add(new LexicalRule(TokenType.LineComment, "//"));
        //rules.add(new LexicalRule(TokenType.Annotation, "@"));
        //rules.add(new LexicalRule(TokenType.Delimiter, ""));
        //rules.add(new LexicalRule(TokenType.DocComment, "/**"));
        //rules.add(new LexicalRule(TokenType.Whitespace, " "));
        //rules.add(new LexicalRule(TokenType.Identifier, ""));
        //rules.add(new LexicalRule(TokenType.MultilineComment, "/*"));
        //rules.add(new LexicalRule(TokenType.Number, "[0-9]*"));
        //rules.add(new LexicalRule(TokenType.Operator, "[+|-|/|*|%]"));
        //rules.add(new LexicalRule(TokenType.Unknown, ""));

        Matcher matcher;
        int len = text.length();
        int i = 0;

        while (i < len) {

            for (LexicalRule rule : rules) {
                matcher = Pattern.compile(rule.rule).matcher(text);

                if (matcher.find(i)) {
                    String match = matcher.group();
                    tokens.add(new Token(rule.type, matcher.start(), match.length()));
                    i += match.length();

                    break;
                }
            }
        }



        return tokens;
    }

    private int getLineOfOffset(int offset) throws BadLocationException {
        Document doc = pane.getDocument();

        if (offset < 0) {
            throw  new BadLocationException("negative offset", offset);
        } else if (offset > doc.getLength()) {
            throw  new BadLocationException("offset too big", offset);
        }

        Element map = doc.getDefaultRootElement();
        return map.getElementIndex(offset);
    }

    private int getLineStartOffset(int line) throws BadLocationException {
        Element map = pane.getDocument().getDefaultRootElement();

        if (line < 0) {
            throw new BadLocationException("Negative line", -1);
        } else if (line >= map.getElementCount()) {
            throw new BadLocationException("No such line", pane.getDocument().getLength() + 1);
        } else {
            Element lineElem = map.getElement(line);
            return lineElem.getStartOffset();
        }
    }

    private class LexicalRule {
        private TokenType type;
        private String rule;

        public LexicalRule(TokenType type, String rule) {
            this.type = type;
            this.rule = rule;
        }
    }
}