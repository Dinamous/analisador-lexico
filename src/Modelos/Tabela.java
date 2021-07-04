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
import java.util.List;
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
        
    public List<Celula> celulas  = new ArrayList<Celula>();
    String [] colunas = {"Id", "Lexema", "Token","Valor Inicial", "Escopo", "Linhas","Colunas","Linha"};
    DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);
    JPanel painelFundo;
    JTable tabela;
    JScrollPane barraRolagem;
    
    Object [][] dados = {
        {"0", "aaaa", "AAA","0", "-", "-","-","0"},
        {"1", "aaaa", "AAA","0", "-", "-","-","1"},
        {"2", "aaaa", "AAA","0", "-", "-","-","2"},
        {"3", "aaaa", "AAA","0", "-", "-","-","2"},
        
    };
    
    Object valor[][];
   
    public Tabela() {
          
    }

       public void criaJanela(){
           
        for (int i = 0; i < celulas.size()-1; i++){  
            int id = celulas.get(i).id;
            String lexema = celulas.get(i).lexema;
            String valor = celulas.get(i).valor_inicial;
            String escopo = celulas.get(i).escopo;
            String lin = celulas.get(i).lin;
            String col = celulas.get(i).col;
            String token = celulas.get(i).token;
            int linha = celulas.get(i).linha;
           
            Object[] data = {id, lexema,token,valor,escopo,lin,col,linha};

            tableModel.addRow(data);   
            
        }
           
           
           
           

        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(1, 1));
        tabela = new JTable(tableModel);
        barraRolagem = new JScrollPane(tabela);
        painelFundo.add(barraRolagem);
        Font font = new Font("Monospaced", Font.PLAIN, 18);

//              

        getContentPane().add(painelFundo);
//        setLocationRelativeTo(null);
        setSize(900, 500);
        setVisible(true);
    }
    

}
