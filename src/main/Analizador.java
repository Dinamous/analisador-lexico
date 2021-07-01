/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
    
    List<String> palavras_reservadas = List.of(v);
    String codigo_total = "";
    String Linhas[];

    public String[] getLinhas() {
        return Linhas;
    }

    public void setLinhas(String[] Linhas) {
        this.Linhas = Linhas;
    }

    public String getCodigo_total() {
        return codigo_total;
    }

    public void setCodigo_total(String codigo_total) {
        this.codigo_total = codigo_total;
    }
    
    public void QuebraCodigoEmLinhas(){
        //esta função coleta o codigo total o separa em posições de 
        //vetor cada linha uma posição
        
        String valor[] = getCodigo_total().split("\\r?\\n") ;
        setLinhas(valor);
        
    }
    
    public void IniciaAnalise(){
        QuebraCodigoEmLinhas();
        
    
}
    
}
