import java.util.ArrayList;
import java.util.List;

public class Token {
    private Type key;
    private String value;

    public Token(Type key, String value) {
        this.key = key;
        this.value = value;
    }

    public Type getKey() {
        return key;
    }

    public void setKey(Type key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "key ='" + key + '\'' +
                ", value ='" + value + '\'' +
                '}';
    }

    public enum Type{
        VAR, ASSIGN_OP, LOG_OP, NUMBER, WHILE_W, LFB, RFB, OP, LRB, RRB;
    }

    private static String getSubstring(String code, int i){

        int j = i;
        for(; j < code.length();){
            if(Character.isLetter(code.charAt(j))){
                j++;
            }
            else{
                if(Character.isDigit(code.charAt(j))){
                    j++;
                }
                else{
                    if((code.charAt(j) == '<' || code.charAt(j) == '=' || code.charAt(j) == '>') && code.charAt(j+1) == '='){
                        return code.substring(i,j+1);
                    }
                    return code.substring(i,j);}
                }

        }
        return null;
    }

    public static List<Token> tokenMaker(String code){
        List<Token> tokens = new ArrayList<>();
        for(int i = 0; i < code.length();){
            switch (code.charAt(i)){
                case '(':
                    tokens.add(new Token(Type.LRB, "("));
                    i++;
                    break;
                case ')':
                    tokens.add(new Token(Type.RRB, ")"));
                    i++;
                    break;
                case '{':
                    tokens.add(new Token(Type.LFB, "{"));
                    i++;
                    break;
                case '}':
                    tokens.add(new Token(Type.RFB, "}"));
                    i++;
                    break;
                case '+':
                    tokens.add(new Token(Type.OP, "+"));
                    i++;
                    break;
                case '-':
                    tokens.add(new Token(Type.OP, "-"));
                    i++;
                    break;
                case '*':
                    tokens.add(new Token(Type.OP, "*"));
                    i++;
                    break;
                case '/':
                    tokens.add(new Token(Type.OP, "/"));
                    i++;
                    break;
                case '=':
                    tokens.add(new Token(Type.ASSIGN_OP, "="));
                    i++;
                    break;
                case '>':
                    tokens.add(new Token(Type.ASSIGN_OP, ">"));
                    i++;
                    break;
                case '<':
                    tokens.add(new Token(Type.ASSIGN_OP, "<"));
                    i++;
                    break;
                default:
                    if (Character.isWhitespace(code.charAt(i))){
                        i++;
                    }
                    else {
                        String substr = getSubstring(code, i);
                        i+=substr.length();
                        if(substr.equals("while"))
                            tokens.add(new Token(Type.WHILE_W, substr));
                        else{
                            if(substr.equals("<=") || substr.equals("==") || substr.equals(">="))
                                tokens.add(new Token(Type.LOG_OP, substr));
                            else{
                                if(Character.isDigit(substr.charAt(0)))
                                    tokens.add(new Token(Type.NUMBER, substr));
                                else
                                    tokens.add(new Token(Type.VAR,substr));
                            }
                        }
                    }
            }
        }
        return tokens;
    }

}
