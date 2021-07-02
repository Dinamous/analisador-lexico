/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import com.sun.tools.javac.util.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author matheus
 */
public class Linha {
    
    private String lexemas[];
    private int linha;
    private String conteudo;

    public Linha() {
    }

    public Linha(String[] lexemas, int linha, String conteudo) {
        this.lexemas = lexemas;
        this.linha = linha;
        this.conteudo = conteudo;
    }

    public String[] getLexemas() {
        return lexemas;
    }

    public void setLexemas(String[] lexemas) {
        this.lexemas = lexemas;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void RemoveEspacosEmBranco(){
        this.lexemas = this.conteudo.trim().split("\\s");
        System.out.println(Arrays.toString(this.lexemas));
    }
   
    
}
