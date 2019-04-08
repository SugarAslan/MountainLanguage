import java.util.List;

public class Lexer {

    public static void main(String[] args) {
        String code =   "a=32 " +

                        "while(a<12){" +

                            "a=a*3" +

                        "}";

        List<Token> tokens;

        tokens = Token.tokenMaker(code);

        System.out.println(tokens);
    }


}
