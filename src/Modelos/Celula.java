/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author matheus
 */
public class Celula {
    
    public int id ;
    public String lexema;
    public String token;
    public String valor_inicial;
    public String escopo;
    public String lin;
    public String col;
    public int linha;

    public Celula() {
    }

    public Celula(int id, String lexema, String token, String valor_inicial, String escopo, String lin, String col, int linha) {
        this.id = id;
        this.lexema = lexema;
        this.token = token;
        this.valor_inicial = valor_inicial;
        this.escopo = escopo;
        this.lin = lin;
        this.col = col;
        this.linha = linha;
    }

    
   
     
    
            
}
