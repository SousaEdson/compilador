package com.ybadoo.tutoriais.cmp.tutorial11.exercicio01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class SemanticAnalysis {

    Reader source;

    public String parser(Reader source) {
        this.source = source;

        String linha = "";

        while(this.source != null) {
            char c = next();
            linha += c;
        }

        String[] linhas = linha.split("\n");

        int anterior = -1;
        boolean flag = true;

        for(int i=0; i<linhas.length; i++){
            int numeroDaLinha = Integer.valueOf(linhas[i].split(" ")[0]);
            System.out.println(numeroDaLinha);
            System.out.println(anterior);

            if(numeroDaLinha <= anterior){
                flag = false;
                break;
            }
            anterior = numeroDaLinha;
        }

        if(!flag){
            return "Erro na sequência da numeração";
        }

        if(!checkGoTo(linhas)){
            return "Linha inexistente após comando goto";
        }

        return "Análise semântica concluída com sucesso";

    }

    private boolean checkGoTo(String[] linhas){

        HashSet<Integer> numerosLinhas = new HashSet<Integer>();

        for(int i=0; i<linhas.length; i++){
            int numeroDaLinha = Integer.valueOf(linhas[i].split(" ")[0]);
            numerosLinhas.add(numeroDaLinha);
        }

        for(int i=0; i<linhas.length; i++){
            String[] splitLinha = linhas[i].split(" ");

            for(int j=0; j<splitLinha.length; j++){

                if(splitLinha[j].equals("goto")){
                    int valor = Integer.valueOf(splitLinha[j+1]);
                    if(!numerosLinhas.contains(valor)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private char next(){
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

}
