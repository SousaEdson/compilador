package com.ybadoo.tutoriais.cmp.tutorial11.exercicio01;

/**
 * Classe responsavel pela representacao de um token
 */
public class Token
{
    /**
     * Tipo/classe do token
     */
    private int type;

    /**
     * Endereco na tabela de simbolos
     */
    private int address;

    /**
     * Numero da linha no codigo-fonte
     */
    private int line;

    /**
     * Numero da coluna no codigo-fonte
     */
    private int column;

    /**
     * Construtor para inicializar o token - sem tabela de simbolo
     *
     * @param type tipo/classe do token
     * @param line numero da linha no codigo-fonte
     * @param column numero da coluna no codigo-fonte
     */
    public Token(int type, int line, int column)
    {
        this(type, -1, line, column);
    }

    /**
     * Construtor para inicializar o token - com tabela de simbolo
     *
     * @param type tipo/classe do token
     * @param address endereco na tabela de simbolos
     * @param line numero da linha no codigo-fonte
     * @param column numero da coluna no codigo-fonte
     */
    public Token(int type, int address, int line, int column)
    {
        this.type = type;
        this.address = address;
        this.line = line;
        this.column = column;
    }

    /**
     * Retornar o tipo/classe do token
     *
     * @return tipo/classe do token
     */
    public int getType()
    {
        return type;
    }

    /**
     * Retornar o endereco na tabela de simbolos
     *
     * @return endereco na tabela de simbolos
     */
    public int getAddress()
    {
        return address;
    }

    /**
     * Retornar o numero da linha no codigo-fonte
     *
     * @return numero da linha no codigo-fonte
     */
    public int getLine()
    {
        return line;
    }

    /**
     * Retornar o numero da coluna no codigo-fonte
     *
     * @return numero da coluna no codigo-fonte
     */
    public int getColumn()
    {
        return column;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        if(getAddress() != -1)
        {
            return "[" + getType() + ", " + getAddress()+ ", ("
                    + getLine() + ", " + getColumn() + ")]" ;
        }
        else
        {
            return "[" + getType() + ", , ("
                    + getLine() + ", " + getColumn() + ")]" ;
        }
    }
}
