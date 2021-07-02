/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import com.sun.tools.javac.util.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        String auxLexemas[] = getLexemas();
        
        //removendo os espaços em branco gerados
        for(String lexema : auxLexemas){
            lexema = lexema.replaceAll("\\s","");
        }
        
        setLexemas(auxLexemas);
          
    }

    public void SepararLexemasPorSimbolo() {
        //chamado a função própia para separa as strings por typo de variavel
        String regex = "\\s+|(?=\\\"|\\(|\\)|\\=|\\+|\\-|\\*+|\\/|\\,)|(?<=\\\"|\\(|\\)|\\=|\\+|\\-|\\*+|\\/|\\,)|\\s";
        

        this.lexemas = this.conteudo.trim().split(regex);
        
        //removendo os espaços em branco gerados inuteis
        
        System.out.println(Arrays.toString(getLexemas()));
       
              
    }
    
    
    
    //funções necessárias paara fazer o split
     public static String[] splitByCharacterType(String str) {
      return splitByCharacterType(str, false);
  }
     
     
     private static String[] splitByCharacterType(String str, boolean camelCase) {
    if (str == null) {
      return null;
    }
    if (str.length() == 0) {
      return new String[0];
    }
    char[] c = str.toCharArray();
    List list = new ArrayList();
    int tokenStart = 0;
    int currentType = Character.getType(c[tokenStart]);
    for (int pos = tokenStart + 1; pos < c.length; pos++) {
      int type = Character.getType(c[pos]);
      if (type == currentType) {
        continue;
      }
      if (camelCase && type == Character.LOWERCASE_LETTER
          && currentType == Character.UPPERCASE_LETTER) {
        int newTokenStart = pos - 1;
        if (newTokenStart != tokenStart) {
          list.add(new String(c, tokenStart, newTokenStart - tokenStart));
          tokenStart = newTokenStart;
        }
      } else {
        list.add(new String(c, tokenStart, pos - tokenStart));
        tokenStart = pos;
      }
      currentType = type;
    }
    list.add(new String(c, tokenStart, c.length - tokenStart));
    return (String[]) list.toArray(new String[list.size()]);
  }
   
    
}
