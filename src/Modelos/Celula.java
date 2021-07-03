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
    public float valor_inicial;
    public String escopo;
    public int lin;
    public int col;
    public int linha;

    public Celula() {
    }

    public Celula(int id, String lexema, String token, float valor_inicial, String escopo, int linha) {
        this.id = id;
        this.lexema = lexema;
        this.token = token;
        this.valor_inicial = valor_inicial;
        this.escopo = escopo;
        this.linha = linha;
    }
    
    
    
    
    public Celula(int id, String lexema, String token, float valor_inicial, String escopo, int lin, int col, int linha) {
       this.lexema = lexema;
       this.token = token;
       this.valor_inicial = valor_inicial;
       this.escopo = escopo;
       this.lin = lin;
       this.col = col;
       this.linha = linha;
       this.id = id;
       
   }

     
     
    public void IncrementaId() {
        this.id = id++;
    }
            
}
