/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Componentes.IDE;


/**
 *
 * @author matheus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Analizador analizador = new Analizador();
        
        //chamando funções preparativas 
        
        analizador.ConcatVetore();
        
        
        //isso mostra a interface em tela
        new IDE(analizador).Run();
         
         
         
        
       
    }
    
}
