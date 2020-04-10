package com.ybadoo.tutoriais.cmp.tutorial11.exercicio01;

import java.io.IOException;
import java.io.Reader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Classe responsavel pela analise lexica da linguagem de programacao
 * SIMPLE 15.01
 */
public class LexicalAnalysis
{
    /**
     * Arquivo com o codigo-fonte
     */
    private Reader source;

    /**
     * Lexema em reconhecimento
     */
    private Lexeme lexeme;

    /**
     * Tokens identificados na analise lexica
     */
    private List<Token> tokens;

    /**
     * Numero da linha no codigo-fonte
     */
    private int line;

    /**
     * Numero da coluna no codigo-fonte
     */
    private int column;

    /**
     * Problemas na analise lexica
     */
    private boolean error;

    /**
     * Tabela de simbolos da analise lexica
     */
    private Map<String, Integer> symbolTable;

    /**
     * Construtor padrao
     */
    public LexicalAnalysis()
    {
        line = 1;

        column = 0;

        error = false;
    }

    /**
     * Adicionar os identificadores e as constantes numericas a tabela de
     * simbolos
     *
     * @param lexema identificador e/ou constante numerica
     * @return posicao do lexema e/ou constante numerica na tabela de
     *         simbolos
     */
    private int addSymbolTable(String lexeme)
    {
        if(!symbolTable.containsKey(lexeme))
        {
            symbolTable.put(lexeme, symbolTable.size());
        }

        return symbolTable.get(lexeme);
    }

    /**
     * Adicionar o lexema a lista de tokens
     */
    private void addToken()
    {
        if(lexeme.getType() != Symbol.ERROR)
        {
            if((lexeme.getType() == Symbol.INTEGER) ||
                    (lexeme.getType() == Symbol.VARIABLE))
            {
                tokens.add(lexeme.toToken(addSymbolTable(lexeme.getTerm())));
            }
            else
            {
                tokens.add(lexeme.toToken());
            }
        }
        else
        {
            System.out.println("Token não reconhecido: " + lexeme);

            error = true;
        }
    }

    /**
     * Retornar a tabela de simbolos da analise lexica
     *
     * @return tabela de simbolos da analise lexica
     */
    public Map<String, Integer> getSymbolTable()
    {
        return symbolTable;
    }

    /**
     * Retornar os tokens identificados na analise lexica
     *
     * @return tokens identificados na analise lexica
     */
    public List<Token> getTokens()
    {
        return tokens;
    }

    /**
     * Retornar o caracter do token para analise
     *
     * @return caracter do token para analise
     */
    private char next()
    {
        int caracter = 0;

        if(source != null)
        {
            try
            {
                caracter = source.read();

                if(caracter == '\r')
                {
                    caracter = source.read();
                }

                if(caracter > 0)
                {
                    column = column + 1;

                    return (char) caracter;
                }
                else
                {
                    source.close();

                    source = null;
                }
            }
            catch (IOException exception)
            {
                exception.printStackTrace();
            }
        }

        return 0;
    }

    /**
     * Realizar a analise lexica do codigo-fonte
     *
     * @param source arquivo com o codigo-fonte de entrada
     * @return sucesso ou nao na analisa lexica
     */
    public boolean parser(Reader source)
    {
        this.source = source;

        tokens = new LinkedList<Token>();

        symbolTable = new LinkedHashMap<String, Integer>();

        while(this.source != null)
        {
            q0();
        }

        return error;
    }

    /**
     * Estado inicial do automato finito deterministico
     */
    private void q0()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                q04();
                break;
            case '\n' :
                q03();
                break;
            case ' ' :
                break;
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
                lexeme = new Lexeme(caracter, Symbol.INTEGER, line, column);
                q01();
                break;
            case 'a' :
            case 'b' :
            case 'c' :
            case 'd' :
            case 'f' :
            case 'h' :
            case 'j' :
            case 'k' :
            case 'm' :
            case 'n' :
            case 'o' :
            case 'q' :
            case 's' :
            case 't' :
            case 'u' :
            case 'v' :
            case 'w' :
            case 'x' :
            case 'y' :
            case 'z' :
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q02();
                break;
            case 'r' :
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q17();
                break;
            case 'i' :
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q19();
                break;
            case 'l' :
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q23();
                break;
            case 'p' :
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q25();
                break;
            case 'g' :
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q29();
                break;
            case 'e' :
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q32();
                break;
            case '+' :
                lexeme = new Lexeme(caracter, Symbol.ADD, line, column);
                q05();
                break;
            case '-' :
                lexeme = new Lexeme(caracter, Symbol.SUBTRACT, line, column);
                q06();
                break;
            case '*' :
                lexeme = new Lexeme(caracter, Symbol.MULTIPLY, line, column);
                q07();
                break;
            case '/' :
                lexeme = new Lexeme(caracter, Symbol.DIVIDE, line, column);
                q08();
                break;
            case '=' :
                lexeme = new Lexeme(caracter, Symbol.ASSIGNMENT, line, column);
                q09();
                break;
            case '<' :
                lexeme = new Lexeme(caracter, Symbol.LT, line, column);
                q10();
                break;
            case '>' :
                lexeme = new Lexeme(caracter, Symbol.GT, line, column);
                q11();
                break;
            case '!' :
                lexeme = new Lexeme(caracter, Symbol.ERROR, line, column);
                q16();
                break;
            default :
                lexeme = new Lexeme(caracter, Symbol.ERROR, line, column);
                q99();
        }
    }

    /**
     * Estado responsavel pelo reconhecimento da constante numerica inteira
     */
    private void q01()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
                lexeme.append(caracter, Symbol.INTEGER);
                q01();
                break;
            case '+' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ADD, line, column);
                q05();
                break;
            case '-' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.SUBTRACT, line, column);
                q06();
                break;
            case '*' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.MULTIPLY, line, column);
                q07();
                break;
            case '/' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.DIVIDE, line, column);
                q08();
                break;
            case '=' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ASSIGNMENT, line, column);
                q09();
                break;
            case '<' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.LT, line, column);
                q10();
                break;
            case '>' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.GT, line, column);
                q11();
                break;
            case '!' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ERROR, line, column);
                q16();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q01();
        }
    }

    /**
     * Estado responsavel pelo reconhecido do identificador
     */
    private void q02()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '+' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ADD, line, column);
                q05();
                break;
            case '-' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.SUBTRACT, line, column);
                q06();
                break;
            case '*' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.MULTIPLY, line, column);
                q07();
                break;
            case '/' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.DIVIDE, line, column);
                q08();
                break;
            case '=' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ASSIGNMENT, line, column);
                q09();
                break;
            case '<' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.LT, line, column);
                q10();
                break;
            case '>' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.GT, line, column);
                q11();
                break;
            case '!' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ERROR, line, column);
                q16();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q02();
        }
    }

    /**
     * Estado responsavel pelo reconhecimento do delimitador de nova linha
     */
    private void q03()
    {
        lexeme = new Lexeme('\n', Symbol.LF, line, column);

        addToken();

        line = line + 1;

        column = 0;
    }

    /**
     * Estado responsavel pelo reconhecimento do delimitador de fim de texto
     */
    private void q04()
    {
        lexeme = new Lexeme('\0', Symbol.ETX, line, column);

        addToken();
    }

    /**
     * Estado responsavel pelo reconhecido do operador aritmetico de
     * adicao (+)
     */
    private void q05()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.INTEGER, line, column);
                q01();
                break;
            case 'a' :
            case 'b' :
            case 'c' :
            case 'd' :
            case 'f' :
            case 'h' :
            case 'j' :
            case 'k' :
            case 'm' :
            case 'n' :
            case 'o' :
            case 'q' :
            case 's' :
            case 't' :
            case 'u' :
            case 'v' :
            case 'w' :
            case 'x' :
            case 'y' :
            case 'z' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q02();
                break;
            case 'r' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q17();
                break;
            case 'i' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q19();
                break;
            case 'l' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q23();
                break;
            case 'p' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q25();
                break;
            case 'g' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q29();
                break;
            case 'e' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q32();
                break;
            case '-' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.SUBTRACT, line, column);
                q06();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q05();
        }
    }

    /**
     * Estado responsavel pelo reconhecido do operador aritmetico de
     * subtracao (-)
     */
    private void q06()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.INTEGER, line, column);
                q01();
                break;
            case 'a' :
            case 'b' :
            case 'c' :
            case 'd' :
            case 'f' :
            case 'h' :
            case 'j' :
            case 'k' :
            case 'm' :
            case 'n' :
            case 'o' :
            case 'q' :
            case 's' :
            case 't' :
            case 'u' :
            case 'v' :
            case 'w' :
            case 'x' :
            case 'y' :
            case 'z' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q02();
                break;
            case 'r' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q17();
                break;
            case 'i' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q19();
                break;
            case 'l' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q23();
                break;
            case 'p' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q25();
                break;
            case 'g' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q29();
                break;
            case 'e' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q32();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q06();
        }
    }

    /**
     * Estado responsavel pelo reconhecido do operador aritmetico de
     * multiplicacao (*)
     */
    private void q07()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.INTEGER, line, column);
                q01();
                break;
            case 'a' :
            case 'b' :
            case 'c' :
            case 'd' :
            case 'f' :
            case 'h' :
            case 'j' :
            case 'k' :
            case 'm' :
            case 'n' :
            case 'o' :
            case 'q' :
            case 's' :
            case 't' :
            case 'u' :
            case 'v' :
            case 'w' :
            case 'x' :
            case 'y' :
            case 'z' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q02();
                break;
            case 'r' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q17();
                break;
            case 'i' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q19();
                break;
            case 'l' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q23();
                break;
            case 'p' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q25();
                break;
            case 'g' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q29();
                break;
            case 'e' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q32();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q07();
        }
    }

    /**
     * Estado responsavel pelo reconhecido do operador aritmetico de
     * divisao inteira (/)
     */
    private void q08()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.INTEGER, line, column);
                q01();
                break;
            case 'a' :
            case 'b' :
            case 'c' :
            case 'd' :
            case 'f' :
            case 'h' :
            case 'j' :
            case 'k' :
            case 'm' :
            case 'n' :
            case 'o' :
            case 'q' :
            case 's' :
            case 't' :
            case 'u' :
            case 'v' :
            case 'w' :
            case 'x' :
            case 'y' :
            case 'z' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q02();
                break;
            case 'r' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q17();
                break;
            case 'i' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q19();
                break;
            case 'l' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q23();
                break;
            case 'p' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q25();
                break;
            case 'g' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q29();
                break;
            case 'e' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q32();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q08();
        }
    }

    /**
     * Estado responsavel pelo reconhecido do operador de atribuicao (=)
     */
    private void q09()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.INTEGER, line, column);
                q01();
                break;
            case 'a' :
            case 'b' :
            case 'c' :
            case 'd' :
            case 'f' :
            case 'h' :
            case 'j' :
            case 'k' :
            case 'm' :
            case 'n' :
            case 'o' :
            case 'q' :
            case 's' :
            case 't' :
            case 'u' :
            case 'v' :
            case 'w' :
            case 'x' :
            case 'y' :
            case 'z' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q02();
                break;
            case 'r' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q17();
                break;
            case 'i' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q19();
                break;
            case 'l' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q23();
                break;
            case 'p' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q25();
                break;
            case 'g' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q29();
                break;
            case 'e' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q32();
                break;
            case '=' :
                lexeme.append(caracter, Symbol.EQ);
                q12();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q09();
        }
    }

    /**
     * Estado responsavel pelo reconhecido do operador relacional
     * menor que (<)
     */
    private void q10()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.INTEGER, line, column);
                q01();
                break;
            case 'a' :
            case 'b' :
            case 'c' :
            case 'd' :
            case 'f' :
            case 'h' :
            case 'j' :
            case 'k' :
            case 'm' :
            case 'n' :
            case 'o' :
            case 'q' :
            case 's' :
            case 't' :
            case 'u' :
            case 'v' :
            case 'w' :
            case 'x' :
            case 'y' :
            case 'z' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q02();
                break;
            case 'r' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q17();
                break;
            case 'i' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q19();
                break;
            case 'l' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q23();
                break;
            case 'p' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q25();
                break;
            case 'g' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q29();
                break;
            case 'e' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q32();
                break;
            case '=' :
                lexeme.append(caracter, Symbol.LE);
                q13();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q10();
        }
    }

    /**
     * Estado responsavel pelo reconhecido do operador relacional
     * maior que (>)
     */
    private void q11()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.INTEGER, line, column);
                q01();
                break;
            case 'a' :
            case 'b' :
            case 'c' :
            case 'd' :
            case 'f' :
            case 'h' :
            case 'j' :
            case 'k' :
            case 'm' :
            case 'n' :
            case 'o' :
            case 'q' :
            case 's' :
            case 't' :
            case 'u' :
            case 'v' :
            case 'w' :
            case 'x' :
            case 'y' :
            case 'z' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q02();
                break;
            case 'r' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q17();
                break;
            case 'i' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q19();
                break;
            case 'l' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q23();
                break;
            case 'p' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q25();
                break;
            case 'g' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q29();
                break;
            case 'e' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q32();
                break;
            case '=' :
                lexeme.append(caracter, Symbol.GE);
                q14();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q11();
        }
    }

    /**
     * Estado responsavel pelo reconhecido do operador relacional
     * igual a (==)
     */
    private void q12()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.INTEGER, line, column);
                q01();
                break;
            case 'a' :
            case 'b' :
            case 'c' :
            case 'd' :
            case 'f' :
            case 'h' :
            case 'j' :
            case 'k' :
            case 'm' :
            case 'n' :
            case 'o' :
            case 'q' :
            case 's' :
            case 't' :
            case 'u' :
            case 'v' :
            case 'w' :
            case 'x' :
            case 'y' :
            case 'z' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q02();
                break;
            case 'r' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q17();
                break;
            case 'i' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q19();
                break;
            case 'l' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q23();
                break;
            case 'p' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q25();
                break;
            case 'g' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q29();
                break;
            case 'e' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q32();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q12();
        }
    }

    /**
     * Estado responsavel pelo reconhecido do operador relacional
     * maior ou igual a (>=)
     */
    private void q13()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.INTEGER, line, column);
                q01();
                break;
            case 'a' :
            case 'b' :
            case 'c' :
            case 'd' :
            case 'f' :
            case 'h' :
            case 'j' :
            case 'k' :
            case 'm' :
            case 'n' :
            case 'o' :
            case 'q' :
            case 's' :
            case 't' :
            case 'u' :
            case 'v' :
            case 'w' :
            case 'x' :
            case 'y' :
            case 'z' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q02();
                break;
            case 'r' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q17();
                break;
            case 'i' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q19();
                break;
            case 'l' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q23();
                break;
            case 'p' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q25();
                break;
            case 'g' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q29();
                break;
            case 'e' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q32();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q13();
        }
    }

    /**
     * Estado responsavel pelo reconhecido do operador relacional
     * menor ou igual a (<=)
     */
    private void q14()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.INTEGER, line, column);
                q01();
                break;
            case 'a' :
            case 'b' :
            case 'c' :
            case 'd' :
            case 'f' :
            case 'h' :
            case 'j' :
            case 'k' :
            case 'm' :
            case 'n' :
            case 'o' :
            case 'q' :
            case 's' :
            case 't' :
            case 'u' :
            case 'v' :
            case 'w' :
            case 'x' :
            case 'y' :
            case 'z' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q02();
                break;
            case 'r' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q17();
                break;
            case 'i' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q19();
                break;
            case 'l' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q23();
                break;
            case 'p' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q25();
                break;
            case 'g' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q29();
                break;
            case 'e' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q32();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q14();
        }
    }

    /**
     * Estado responsavel pelo reconhecido do operador relacional
     * diferente de (!=)
     */
    private void q15()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.INTEGER, line, column);
                q01();
                break;
            case 'a' :
            case 'b' :
            case 'c' :
            case 'd' :
            case 'f' :
            case 'h' :
            case 'j' :
            case 'k' :
            case 'm' :
            case 'n' :
            case 'o' :
            case 'q' :
            case 's' :
            case 't' :
            case 'u' :
            case 'v' :
            case 'w' :
            case 'x' :
            case 'y' :
            case 'z' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q02();
                break;
            case 'r' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q17();
                break;
            case 'i' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q19();
                break;
            case 'l' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q23();
                break;
            case 'p' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q25();
                break;
            case 'g' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q29();
                break;
            case 'e' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q32();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q15();
        }
    }

    /**
     * Estado responsavel pelo reconhecido do operador relacional
     * diferente de (!=)
     */
    private void q16()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '=' :
                lexeme.append(caracter, Symbol.NE);
                q15();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q16();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada rem
     */
    private void q17()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '+' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ADD, line, column);
                q05();
                break;
            case '-' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.SUBTRACT, line, column);
                q06();
                break;
            case '*' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.MULTIPLY, line, column);
                q07();
                break;
            case '/' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.DIVIDE, line, column);
                q08();
                break;
            case '=' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ASSIGNMENT, line, column);
                q09();
                break;
            case '<' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.LT, line, column);
                q10();
                break;
            case '>' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.GT, line, column);
                q11();
                break;
            case '!' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ERROR, line, column);
                q16();
                break;
            case 'e' :
                lexeme.append(caracter, Symbol.ERROR);
                q18();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q17();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada rem
     */
    private void q18()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case 'm' :
                lexeme.append(caracter, Symbol.REM);
                q34();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q18();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada if
     */
    private void q19()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '+' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ADD, line, column);
                q05();
                break;
            case '-' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.SUBTRACT, line, column);
                q06();
                break;
            case '*' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.MULTIPLY, line, column);
                q07();
                break;
            case '/' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.DIVIDE, line, column);
                q08();
                break;
            case '=' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ASSIGNMENT, line, column);
                q09();
                break;
            case '<' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.LT, line, column);
                q10();
                break;
            case '>' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.GT, line, column);
                q11();
                break;
            case '!' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ERROR, line, column);
                q16();
                break;
            case 'f' :
                lexeme.append(caracter, Symbol.IF);
                q39();
                break;
            case 'n' :
                lexeme.append(caracter, Symbol.ERROR);
                q20();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q19();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada input
     */
    private void q20()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case 'p' :
                lexeme.append(caracter, Symbol.ERROR);
                q21();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q20();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada input
     */
    private void q21()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case 'u' :
                lexeme.append(caracter, Symbol.ERROR);
                q22();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q21();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada input
     */
    private void q22()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case 't' :
                lexeme.append(caracter, Symbol.INPUT);
                q35();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q22();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada let
     */
    private void q23()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '+' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ADD, line, column);
                q05();
                break;
            case '-' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.SUBTRACT, line, column);
                q06();
                break;
            case '*' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.MULTIPLY, line, column);
                q07();
                break;
            case '/' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.DIVIDE, line, column);
                q08();
                break;
            case '=' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ASSIGNMENT, line, column);
                q09();
                break;
            case '<' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.LT, line, column);
                q10();
                break;
            case '>' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.GT, line, column);
                q11();
                break;
            case '!' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ERROR, line, column);
                q16();
                break;
            case 'e' :
                lexeme.append(caracter, Symbol.ERROR);
                q24();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q23();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada let
     */
    private void q24()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case 't' :
                lexeme.append(caracter, Symbol.LET);
                q36();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q24();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada print
     */
    private void q25()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '+' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ADD, line, column);
                q05();
                break;
            case '-' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.SUBTRACT, line, column);
                q06();
                break;
            case '*' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.MULTIPLY, line, column);
                q07();
                break;
            case '/' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.DIVIDE, line, column);
                q08();
                break;
            case '=' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ASSIGNMENT, line, column);
                q09();
                break;
            case '<' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.LT, line, column);
                q10();
                break;
            case '>' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.GT, line, column);
                q11();
                break;
            case '!' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ERROR, line, column);
                q16();
                break;
            case 'r' :
                lexeme.append(caracter, Symbol.ERROR);
                q26();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q25();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada print
     */
    private void q26()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case 'i' :
                lexeme.append(caracter, Symbol.ERROR);
                q27();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q26();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada print
     */
    private void q27()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case 'n' :
                lexeme.append(caracter, Symbol.ERROR);
                q28();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q27();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada print
     */
    private void q28()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case 't' :
                lexeme.append(caracter, Symbol.PRINT);
                q37();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q28();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada goto
     */
    private void q29()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '+' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ADD, line, column);
                q05();
                break;
            case '-' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.SUBTRACT, line, column);
                q06();
                break;
            case '*' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.MULTIPLY, line, column);
                q07();
                break;
            case '/' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.DIVIDE, line, column);
                q08();
                break;
            case '=' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ASSIGNMENT, line, column);
                q09();
                break;
            case '<' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.LT, line, column);
                q10();
                break;
            case '>' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.GT, line, column);
                q11();
                break;
            case '!' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ERROR, line, column);
                q16();
                break;
            case 'o' :
                lexeme.append(caracter, Symbol.ERROR);
                q30();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q29();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada goto
     */
    private void q30()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case 't' :
                lexeme.append(caracter, Symbol.ERROR);
                q31();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q30();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada goto
     */
    private void q31()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case 'o' :
                lexeme.append(caracter, Symbol.GOTO);
                q38();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q31();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada end
     */
    private void q32()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case '+' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ADD, line, column);
                q05();
                break;
            case '-' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.SUBTRACT, line, column);
                q06();
                break;
            case '*' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.MULTIPLY, line, column);
                q07();
                break;
            case '/' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.DIVIDE, line, column);
                q08();
                break;
            case '=' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ASSIGNMENT, line, column);
                q09();
                break;
            case '<' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.LT, line, column);
                q10();
                break;
            case '>' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.GT, line, column);
                q11();
                break;
            case '!' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ERROR, line, column);
                q16();
                break;
            case 'n' :
                lexeme.append(caracter, Symbol.ERROR);
                q33();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q32();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada end
     */
    private void q33()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            case 'd' :
                lexeme.append(caracter, Symbol.END);
                q40();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q33();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada rem
     */
    private void q34()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            default :
                q34();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada input
     */
    private void q35()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q35();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada let
     */
    private void q36()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q36();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada print
     */
    private void q37()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q37();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada goto
     */
    private void q38()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q38();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada if
     */
    private void q39()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q39();
        }
    }

    /**
     * Estado responsavel pelo reconhecido da palavra reservada end
     */
    private void q40()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                addToken();
                q04();
                break;
            case '\n' :
                addToken();
                q03();
                break;
            case ' ' :
                addToken();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q40();
        }
    }

    /**
     * Estado responsavel pelo reconhecimento do erro
     */
    private void q99()
    {
        char caracter = next();

        switch(caracter)
        {
            case 0 :
                q04();
                break;
            case '\n' :
                q03();
                break;
            case ' ' :
                break;
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.INTEGER, line, column);
                q01();
                break;
            case 'a' :
            case 'b' :
            case 'c' :
            case 'd' :
            case 'f' :
            case 'h' :
            case 'j' :
            case 'k' :
            case 'm' :
            case 'n' :
            case 'o' :
            case 'q' :
            case 's' :
            case 't' :
            case 'u' :
            case 'v' :
            case 'w' :
            case 'x' :
            case 'y' :
            case 'z' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q02();
                break;
            case 'r' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q17();
                break;
            case 'i' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q19();
                break;
            case 'l' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q23();
                break;
            case 'p' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q25();
                break;
            case 'g' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q29();
                break;
            case 'e' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.VARIABLE, line, column);
                q32();
                break;
            case '+' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ADD, line, column);
                q05();
                break;
            case '-' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.SUBTRACT, line, column);
                q06();
                break;
            case '*' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.MULTIPLY, line, column);
                q07();
                break;
            case '/' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.DIVIDE, line, column);
                q08();
                break;
            case '=' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ASSIGNMENT, line, column);
                q09();
                break;
            case '<' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.LT, line, column);
                q10();
                break;
            case '>' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.GT, line, column);
                q11();
                break;
            case '!' :
                addToken();
                lexeme = new Lexeme(caracter, Symbol.ERROR, line, column);
                q16();
                break;
            default :
                lexeme.append(caracter, Symbol.ERROR);
                q99();
        }
    }
}