package br.ufc.comp.qalc.frontend;

import br.ufc.comp.qalc.frontend.token.*;

import java.io.IOException;

/**
 * Analisador Léxico da linguagem.
 * <p>
 * Funciona como uma fonte de tokens, de onde o Analisador Sintático
 * deverá ler.
 *
 * @see Source
 */
public class Scanner {

    /**
     * Último token reconhecido.
     */
    protected Token currentToken;
    /**
     * Fonte de caracteres de onde extrair os tokens.
     */
    protected Source source;

    /**
     * Constrói um Analisador Léxico a partir de uma fonte de caracteres.
     *
     * @param sourceStream Fonte a ser utilizada.
     */
    public Scanner(Source sourceStream) {
        // FIXME Lidar corretamente com as exceções.
        this.source = sourceStream;
    }

    /**
     * Consome caracteres da fonte até que eles componham um lexema de
     * um dos tokens da linguagem, coinstrói um objeto representando esse
     * token associado ao lexema lido e o retorna.
     *
     * @return Token reconhecido.
     * @throws IOException Caso haja problema na leitura da fonte.
     */
    public Token getNextToken() throws IOException {
        // TODO Reconhecimento de tokens

        if (source.getCurrentChar() == Source.EOF) {
            return new EOFToken(source.getCurrentLine(), source.getCurrentColumn());
        } else if (Character.isDigit(source.getCurrentChar())) { // NumberToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (Character.isDigit(source.getCurrentChar()));

            String stringValue = lexema.toString();

            return new NumberToken(currentLine, lexemeStart, stringValue);
        }
        else if(source.getCurrentChar() == '@'){
            StringBuilder lexeme = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            do{
                lexeme.append(source.getCurrentChar());
                source.advance();
            }while(Character.isLetter(source.getCurrentChar()));
            String stringValue = lexeme.toString();

            return new FunctionIdentifierToken(currentLine, lexemeStart, stringValue);
        }
        else if(source.getCurrentChar() == '$'){
            StringBuilder lexeme = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            do{
                lexeme.append(source.getCurrentChar());
                source.advance();
            }while(Character.isLetter(source.getCurrentChar()));

            return  new VariableIdentifierToken(currentLine, lexemeStart, lexeme.toString());
        }

        else if(source.getCurrentChar() == '$'  || source.getCurrentChar()=='?'){
            StringBuilder lexeme = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            int isZero = 0;
            do{
                if(source.getCurrentChar() == '0'){
                    isZero++;
                }
                else{
                    isZero = 0;
                }
                lexeme.append(source.getCurrentChar());
                source.advance();
            }while(Character.isLetter(source.getCurrentChar()) && (isZero < 2) );

            return  new ResultIdentifierToken(currentLine, lexemeStart, lexeme.toString());
        }


        else if(source.getCurrentChar() == '+'){
            StringBuilder lexeme = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            lexeme.append(source.getCurrentChar());
            source.advance();
            return new PlusToken(currentLine, lexemeStart, lexeme.toString());
        }

        else if(source.getCurrentChar() == '-'){
            StringBuilder lexeme = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            lexeme.append(source.getCurrentChar());
            source.advance();
            return new MinusToken(currentLine, lexemeStart, lexeme.toString());
        }
        else if(source.getCurrentChar() == '*'){
            StringBuilder lexeme = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            lexeme.append(source.getCurrentChar());
            source.advance();
            return new TimesToken(currentLine, lexemeStart, lexeme.toString());
        }
        else if(source.getCurrentChar() == '='){
            StringBuilder lexeme = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            lexeme.append(source.getCurrentChar());
            source.advance();
            return new AtribToken(currentLine, lexemeStart, lexeme.toString());
        }
        else if(source.getCurrentChar() == '('){
            StringBuilder lexeme = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            source.advance();
            lexeme.append(source.getCurrentChar());

            return new LparenToken(currentLine, lexemeStart, lexeme.toString());
        }
        else if(source.getCurrentChar() == ')'){
            StringBuilder lexeme = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            lexeme.append(source.getCurrentChar());
            source.advance();
            return new RparenToken(currentLine, lexemeStart, lexeme.toString());
        }
        else if(source.getCurrentChar() == ';'){
            StringBuilder lexeme = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            lexeme.append(source.getCurrentChar());
            source.advance();
            return new CommaToken(currentLine, lexemeStart, lexeme.toString());
        }
        else if(source.getCurrentChar() == ','){
            StringBuilder lexeme = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            lexeme.append(source.getCurrentChar());
            source.advance();
            return new SemiToken(currentLine, lexemeStart, lexeme.toString());
        }
        // TODO Recuperação de erros.

        return null;
    }

    /**
     * Obtém o último token reconhecido.
     *
     * @return O último token reconhecido.
     */
    public Token getCurrentToken() {
        return currentToken;
    }
}
