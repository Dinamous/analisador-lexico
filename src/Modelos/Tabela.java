/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author matheus
 */
public class Tabela extends JFrame{
        
    ArrayList<Celula> celulas ;
    String [] colunas = {"Id", "Lexema", "Token","Valor Inicial", "Escopo", "Linhas","Colunas","Linha"};
    
    JPanel painelFundo;
    JTable tabela;
    JScrollPane barraRolagem;
    
    Object [][] dados = {
        {"0", "aaaa", "AAA","0", "-", "-","-","0"},
        {"1", "aaaa", "AAA","0", "-", "-","-","1"},
        {"2", "aaaa", "AAA","0", "-", "-","-","2"},
        {"3", "aaaa", "AAA","0", "-", "-","-","2"},
        
    };
   
    public Tabela() {
          
    }

       public void criaJanela(){

        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(1, 1));
        tabela = new JTable(dados, colunas);
        barraRolagem = new JScrollPane(tabela);
        painelFundo.add(barraRolagem);
        Font font = new Font("Monospaced", Font.PLAIN, 18);

//              

        getContentPane().add(painelFundo);
        setLocationRelativeTo(null);
        setSize(900, 300);
        setVisible(true);
    }
    

}
