/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Element;
import main.Analizador;
 
 
public class IDE extends JFrame{
	private static JTextArea jta;
	private static JTextArea lines;
        private static JButton button;
        private Analizador analizador;
 
	public IDE( Analizador analizador){
		super("Análise Léxica de Código Fortran");
                this.analizador = analizador;
	}

 
 
	public  void createAndShowGUI(){
               
            
            
            
		JFrame frame = new IDE(analizador);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                JPanel panel = new JPanel();
                
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
		
 
		Font font = new Font("Monospaced", Font.PLAIN, 18);
		jta = new JTextArea();
		lines = new JTextArea("1");
 
		lines.setBackground(Color.LIGHT_GRAY);
		lines.setEditable(false);
                lines.setFont(font);
                lines.setMargin(new Insets(10,10,10,10));
                
                jta.setBackground(Color.DARK_GRAY);
                jta.setFont(font);
                jta.setForeground(Color.WHITE);
                jta.setMargin(new Insets(10,20,10,30));
                jta.setCaretColor(Color.WHITE);
 
		jta.getDocument().addDocumentListener(new DocumentListener(){
			public String getText(){
				int caretPosition = jta.getDocument().getLength();
				Element root = jta.getDocument().getDefaultRootElement();
				String text = "1" + System.getProperty("line.separator");
				for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
					text += i + System.getProperty("line.separator");
				}
				return text;
			}
			@Override
			public void changedUpdate(DocumentEvent de) {
				lines.setText(getText());
			}
 
			@Override
			public void insertUpdate(DocumentEvent de) {
				lines.setText(getText());
			}
 
			@Override
			public void removeUpdate(DocumentEvent de) {
				lines.setText(getText());
			}
 
		});
                jta.setText("program input1\n" +
"\n" +
"	implicit none\n" +
"	integer num1,num2,num3		!aqui temos um comentario\n" +
"       integer a= 5+3*2, b = (4/2)**3	!temos atribuicoes com valores numericos\n" +
"	integer n = a + b/ 3\n"+
"	complex complexNum\n" +
"	real m(5,15), v(10)\n" +
"	\n" +
"	m(1,1) = 2.0\n" +
"	v(7)= 3.0\n" +
"\n" +
"	m(1,2) =m(1,1)*v(7)\n" +
"\n" +
"	write(\"Teste produto:\")\n" +
"	write(m(1,2))\n" +
"\n" +
"	write(\"Digite o primeiro valor:\")\n" +
"	read(num1)\n" +
"\n" +
"	read(num2)\n" +
"\n" +
"	num3 = soma(num1,num2)\n" +
"\n" +
"	write(\"Soma:\")\n" +
"	write(num3)\n" +
"	\n" +
"stop\n" +
"end\n" +
"\n" +
"integer function soma(num1,num2)\n" +
"	integer num1, num2\n" +
"	integer aux\n" +
"	aux=num1+num2\n" +
"	aux=num1-num2\n" +
"	aux=num1/num2\n" +
"	aux=num1*num2\n" +
"	aux=num1**num2\n" +
"	soma = aux\n" +
"	return\n" +
"end");
                
                //configurando o scroll
                JScrollPane jsp = new JScrollPane();
                jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                inputpanel.setMaximumSize(new Dimension(800, 50));
                JButton button = new JButton("Analizar Código");
                
                
                //função que coleta o código do textArea
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Get the contents of the JTextArea component.

                        if( jta.getText() != null ){
                            
                            analizador.setCodigoTotal(jta.getText());
                            
                            
                            analizador.IniciaAnalise();
                            
                            
                        }
                    }
                });
                
                DefaultCaret caret = (DefaultCaret) jta.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                
                //adicionando os componentes na tela
                panel.add(jsp);
                inputpanel.add(button);
                panel.add(inputpanel);
                
                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                
		jsp.getViewport().add(jta);
		jsp.setRowHeaderView(lines);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
 

                frame.getContentPane().add(BorderLayout.CENTER, panel);     
                frame.pack();
		frame.setLocationByPlatform(true);
		frame.setSize(1000,600);
                frame.setResizable(false);
		frame.setVisible(true);
                frame.setLocationRelativeTo(null);
	}
 
      
        
	public void Run() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
}