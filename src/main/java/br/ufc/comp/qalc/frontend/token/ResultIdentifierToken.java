package br.ufc.comp.qalc.frontend.token;

/**
 * Classe que representa um token do tipo (RESID).
 */
public class ResultIdentifierToken extends Token {
    /**
     * Número correspondente ao que o lexema deste token representa.
     * <p>
     * Só é inicializado quando solicitado.
     *
     * @see #interpretAttributes()
     */
    protected long resultNumber;

    public ResultIdentifierToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    /**
     * Para este tipo de token, descarta o {@code $} e converte o lexema em um número do tipo
     * {@code double}, caso não tenha feito ainda.
     *
     * @see Token#interpretAttributes()
     */
    @Override
    public void interpretAttributes() {
        // TODO Se o lexema ainda existir, desconsiderar o `$` e interpretar o resto como um long,
        //      atribuindo ao campo `resultNumber`.
        if(stringValue != null && stringValue.charAt(0) == '$'){
            if(stringValue.charAt(stringValue.length() - 1) != ';')
                throw new IllegalArgumentException("O lexema não possui uma configuração válida");
            if(allZeros())
                throw new IllegalArgumentException("O lexema não pode conter uma seqência d emais de um zero");
            else if(someLetter())
                throw new IllegalArgumentException("O lexema de resultado não pode conter letras");
            else
                resultNumber = Long.parseLong(parseLexema());
        }

    }

    @Override
    public String getTokenIdentifier() {
        return "RESID";
    }

    //Transforma o stringValue em um lexema de resultado válido sem o '$' e sem ';'
    public String parseLexema() {
        String aux = "";
        int i = 1;

        while(stringValue.charAt(i) != ';'){
            aux += stringValue.charAt(i);
            i++;
        }

        return aux;
    }

    public boolean allZeros()
    {
        int i = 1;

        while(stringValue.charAt(i) != ';')
        {
            if(stringValue.charAt(i) != '0')
                return false;
            i++;
        }
        return true;

    }

    public boolean someLetter()
    {
        int i = 1;

        while(stringValue.charAt(i) != ';') {
            if( Character.isLetter(stringValue.charAt(i)))
                return true;
            i++;
        }

        return false;
    }

}
