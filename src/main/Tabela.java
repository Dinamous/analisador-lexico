/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author matheus
 */
public class Tabela {
    
    public int id;
    public String lexema;
    public String token;
    public float valor_inicial;
    public String escopo;
    public int lin;
    public int col;
    public int linha;
    

    public Tabela() {
    }

    public Tabela( String lexema, String token, float valor_inicial, String escopo, int lin, int col, int linha) {
        this.lexema = lexema;
        this.token = token;
        this.valor_inicial = valor_inicial;
        this.escopo = escopo;
        this.lin = lin;
        this.col = col;
        this.linha = linha;
    }

    public void IncrementaId() {
        this.id = id++;
    }
    
    
    
    
    
    
    
    
}
