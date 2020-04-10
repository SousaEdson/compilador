/*************************************************************************
 * Copyright (C) 2009/2020 - Cristiano Lehrer (cristiano@ybadoo.com.br)  *
 *                  Ybadoo - Solucoes em Software Livre (ybadoo.com.br)  *
 *                                                                       *
 * Permission is granted to copy, distribute and/or modify this document *
 * under the terms of the GNU Free Documentation License, Version 1.3 or *
 * any later version published by the  Free Software Foundation; with no *
 * Invariant Sections,  no Front-Cover Texts, and no Back-Cover Texts. A *
 * A copy of the  license is included in  the section entitled "GNU Free *
 * Documentation License".                                               *
 *                                                                       *
 * Ubuntu 16.10 (GNU/Linux 4.8.0-39-generic)                             *
 * OpenJDK Version "1.8.0_121"                                           *
 * OpenJDK 64-Bit Server VM (build 25.121-b13, mixed mode)               *
 *************************************************************************/

package com.ybadoo.tutoriais.cmp.tutorial11.exercicio01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


/**
 * Classe responsavel pelo compilador da linguagem de programacao
 * SIMPLE 15.01
 */
public class Compiler
{
    /**
     * Metodo principal da linguagem de programacao Java
     *
     * @param args argumentos da linha de comando (nao utilizado)
     */
    public static void main(String[] args)
    {
        if(args.length != 1)
        {
            System.err.println("Por favor, informe o arquivo a ser compilado!");

            return;
        }

        BufferedReader source = null;

        try
        {
            source = new BufferedReader(new FileReader(new File(args[0])));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }

        System.out.println("Início da análise léxica");

        LexicalAnalysis lexical = new LexicalAnalysis();

        if(!lexical.parser(source))
        {
            for(String key: lexical.getSymbolTable().keySet())
            {
                System.out.println(lexical.getSymbolTable().get(key) + " : " + key);
            }

            for(Token token: lexical.getTokens())
            {
                System.out.println(token);
            }
        }

        System.out.println("Fim da análise léxica");

        // TODO: 09/04/2020  analise sintática


        source = null;

        try
        {
            source = new BufferedReader(new FileReader(new File(args[0])));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }

        System.out.println("Início da análise semântica");
        SemanticAnalysis semanticAnalysis = new SemanticAnalysis();

        String flag = "";

        try{
            flag = semanticAnalysis.parser(source);
        }catch(Exception excp){
            flag = "Não foi possível efetuar a análise semântica";
        }
        System.out.println(flag);


        System.out.println("Fim da análise semântica");

    }
}