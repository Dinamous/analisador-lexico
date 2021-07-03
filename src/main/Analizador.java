/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Modelos.Linha;
import Modelos.Tabela;
import Modelos.VetordePalavras;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author matheus
 */


public class Analizador {
    String v[] = { "program", "implicit", "none","integer" ,"real","complex","character","logical","read","print","if",
        "then","else","end","go","endif","endgo","to","pause","parameter","while","do","call","subroutine","function","return",
        ".eq.",".ne.",".lt.",".le.",".gt.",".ge.",".or.",".and.",".not.","+","-","*","/","**","(",")","\"", "//","!",".",","};
    
    String t[] = {"PRO_INT", "TYPE_IMPLICIT","TYPE_NONE","TYPE_INTEGER","TYPE_REAL","TYPE_COMPLEX","TYPE_CHARACTER","TYPE_LOGICAL",
        "INPUT","OUTPUT","COND_IF","COND_THEN","COND_ELSE","PRO_END","FUNC_GO","COND_END_IF","COND_END_GO","FUNC_TO",
        "FUNC_PAUSE","PARAMS","LOOP_WHILE", "LOOP_DO", "FUNC_CALL","SUBROUTINE","FUNC","RET", "CB_EQUAL","CB_NOTEQUAL",
        "CB_LOWERTHAN","CB_LESSEQUAL","CB_GREATERTHAN", "CB_GREATEREQUAL","CB_OR","CB_AND","CB_NOT",
        "OP_PLUS","OP_MINUS","OP_MULTI","OP_DIV", "OP_POW","SIMB_OP_PAR", "SIMB_CL_PAR", "SIMB_QUOTE","OP_CONCAT",
        "SIMB_EXCLAMATION","SIMB_DOT","SIMB_COMMA"};
    
    
    List<VetordePalavras> palavras  = new ArrayList<VetordePalavras>();
    String codigoTotal = "";
    String Linhas[];
    
    List<Linha> linhasLexemas = new ArrayList<Linha>();
    Tabela tabela = new Tabela();

    public String[] getLinhas() {
        return Linhas;
    }

    public void setLinhas(String[] Linhas) {
        this.Linhas = Linhas;
    }

    public String getCodigoTotal() {
        return codigoTotal;
    }

    public void setCodigoTotal(String codigoTotal) {
        this.codigoTotal = codigoTotal;
    }
    
    public void QuebraCodigoEmLinhas(){
        //esta função coleta o codigo total o separa em posições de 
        //vetor cada linha uma posição
        
        String valor[] = getCodigoTotal().split("\\r?\\n") ;
        setLinhas(valor);        
    }
    
    public void IniciaAnalise(){
        QuebraCodigoEmLinhas();
        RemoveComentarios();
        AssimilarLinhasComLexemas();
        
        SeparandoLexemasComSimbolos();
        RemoveEspacosEmBranco();
        
//        tabela.criaJanela();
        
        
    }
    
    public void ConcatVetore(){     
        //adicionando a vetor de palavras reservadas com o vetor de token 
        //dentro de um mesmo arraylist
        for(int i=0;i<=v.length-1;i++){
            VetordePalavras vt = new VetordePalavras(v[i],t[i]);          
            palavras.add(vt);   
        }
    }
    
    public void RemoveComentarios(){
        String linhasSemComentarios[] = getLinhas();
        
        //percorrendo todas as linhas
        for(int i=0; i<=linhasSemComentarios.length-1;i++){

            //verificando se a linha tem cometários
                int comentario = linhasSemComentarios[i].indexOf("!");
            
                //se houver comentários naquela linha se fará um corte na posição da "!"
                if (-1 != comentario) {
                    linhasSemComentarios[i] = linhasSemComentarios[i].substring(0, comentario);
                   
                }
        }

        //atualizando as linhas
        setLinhas(linhasSemComentarios);
        
 
    }


    private void AssimilarLinhasComLexemas() {
       
        String linhas[] = getLinhas();
        
        //percorrendo todas as linhas
        for(int i=0; i<=linhas.length-1;i++){
            Linha l = new Linha();
           l.setConteudo(linhas[i]);
           l.setLinha(i);
           
           linhasLexemas.add(l);
           //adiciona toda a linha para a o noco array contendo sua linha de origem
        }
          
    }

    private void RemoveEspacosEmBranco() {
        
        for(int i=0;i<=linhasLexemas.size()-1 ;i++){
            
            linhasLexemas.get(i).RemoveEspacosEmBranco();
            
        }
    }
    
    private void SeparandoLexemasComSimbolos() {
        
        for(int i=0;i<=linhasLexemas.size()-1 ;i++){
            
            linhasLexemas.get(i).SepararLexemasPorSimbolo();
            
        }
    }
        
    
}
