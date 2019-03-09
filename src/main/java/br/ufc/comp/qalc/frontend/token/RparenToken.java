package br.ufc.comp.qalc.frontend.token;

public class RparenToken extends Token {

    public RparenToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    @Override
    public String getTokenIdentifier() {

        return "RPAREN";
    }
}
