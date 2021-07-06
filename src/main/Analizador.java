/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Modelos.Celula;
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

    public String v[] = {"program", "implicit", "none", "integer", "real", "complex", "character", "logical", "read", "write", "if",
        "then", "else", "end", "go", "endif", "endgo", "to", "pause", "parameter", "while", "do", "call", "subroutine", "function", "return",
        ".eq.", ".ne.", ".lt.", ".le.", ".gt.", ".ge.", ".or.", ".and.", ".not.", "+", "-", "*", "/", "**", "(", ")", "\"", "//", "!", ".", ",", "=", "stop"};

    String t[] = {"PRO_INT", "TYPE_IMPLICIT", "TYPE_NONE", "TYPE_INTEGER", "TYPE_REAL", "TYPE_COMPLEX", "TYPE_CHARACTER", "TYPE_LOGICAL",
        "INPUT", "OUTPUT", "COND_IF", "COND_THEN", "COND_ELSE", "PRO_END", "FUNC_GO", "COND_END_IF", "COND_END_GO", "FUNC_TO",
        "FUNC_PAUSE", "PARAMS", "LOOP_WHILE", "LOOP_DO", "FUNC_CALL", "SUBROUTINE", "FUNC", "RET", "CB_EQUAL", "CB_NOTEQUAL",
        "CB_LOWERTHAN", "CB_LESSEQUAL", "CB_GREATERTHAN", "CB_GREATEREQUAL", "CB_OR", "CB_AND", "CB_NOT",
        "OP_PLUS", "OP_MINUS", "OP_MULTI", "OP_DIV", "OP_POW", "SIMB_OP_PAR", "SIMB_CL_PAR", "SIMB_QUOTE", "OP_CONCAT",
        "SIMB_EXCLAMATION", "SIMB_DOT", "SIMB_COMMA", "OP_EQUAL", "PRO_STOP"};

    List<VetordePalavras> palavras = new ArrayList<VetordePalavras>();
    String codigoTotal = "";
    String Linhas[];

    List<Linha> linhasLexemas = new ArrayList<Linha>();
    Tabela tabela = new Tabela();

    //variáveis sobre o erro
    Boolean existeErro = false;
    int linhaDoErro;

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

    public void QuebraCodigoEmLinhas() {
        //esta função coleta o codigo total o separa em posições de 
        //vetor cada linha uma posição

        String valor[] = getCodigoTotal().toLowerCase().split("\\r?\\n");
        setLinhas(valor);
    }

    public void IniciaAnalise() {
        QuebraCodigoEmLinhas();
        RemoveComentarios();
        AssimilarLinhasComLexemas();

        SeparandoLexemasComSimbolos();
        RemoveEspacosEmBranco();
        PopulaTabela();

        tabela.criaJanela();

    }

    public void ConcatVetore() {
        //adicionando a vetor de palavras reservadas com o vetor de token 
        //dentro de um mesmo arraylist
        for (int i = 0; i <= v.length - 1; i++) {
            VetordePalavras vt = new VetordePalavras(v[i], t[i]);
            palavras.add(vt);
        }
    }

    public void RemoveComentarios() {
        String linhasSemComentarios[] = getLinhas();

        //percorrendo todas as linhas
        for (int i = 0; i <= linhasSemComentarios.length - 1; i++) {

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
        for (int i = 0; i <= linhas.length - 1; i++) {
            Linha l = new Linha();
            l.setConteudo(linhas[i]);
            l.setLinha(i);

            linhasLexemas.add(l);
            //adiciona toda a linha para a o noco array contendo sua linha de origem
        }

    }

    private void RemoveEspacosEmBranco() {

        for (int i = 0; i <= linhasLexemas.size() - 1; i++) {

            linhasLexemas.get(i).RemoveEspacosEmBranco();

        }
    }

    private void SeparandoLexemasComSimbolos() {

        for (int i = 0; i <= linhasLexemas.size() - 1; i++) {

            linhasLexemas.get(i).SepararLexemasPorSimbolo();

        }
    }

    private void PopulaTabela() {
        int id = 0;
        String escopo = "";

        for (Linha l : linhasLexemas) {

            if (l.getLexemas().length > 0) {
        
                //verificando se a linha possui escopo
                if(l.getConteudo().contains("program")){
                    escopo = "global";
                    
                    //verificando se a linha possui funções ou subrotinas
                }else if (l.getConteudo().contains("function")){
                    escopo = l.getConteudo().substring(l.getConteudo().indexOf("function")+9, l.getConteudo().indexOf("("));
                }else if(l.getConteudo().contains("subroutine")){
                    escopo = l.getConteudo().substring(l.getConteudo().indexOf("subroutine")+11, l.getConteudo().indexOf("("));
                    
                }
                for (String lexema : l.getLexemas()) {

                    if (lexema.length() >= 1) {
                        Celula cel = new Celula();
                        cel.id = id;
                        cel.lexema = lexema;
                        
                        cel.lin = "-";
                        cel.col = "-";
                        cel.token = RetornaToken(lexema, l);

                        //verfica se o tipo do token pra ver se necessita 
                        //encontrar o valor de memória
                        if (cel.token.equals("NUM")) {
                            cel.valor_inicial = lexema;
                            cel.escopo = escopo;
                        } else if (cel.token.equals("ID")) {
                            cel.valor_inicial = CalculaValorInicial(l, lexema);
                            cel.escopo = escopo;
                        }else{
                            cel.valor_inicial = "-";
                            cel.escopo = "-";
                        }
                        
                        

                        cel.linha = l.getLinha();

                        //incrementa o id pro próximo lexema
                        id++;

                        //adiciona a nova linha na tabela
                        tabela.celulas.add(cel);
                    }

                }

            }

        }

    }

    private String RetornaToken(String lexema, Linha l) {

        //foreach que percorre o vetor de palavras que contem 
        //todas os lexemas reservados e tokens correspondentes
        for (VetordePalavras vetorPalavras : palavras) {

            if (vetorPalavras.palavra_reservada.equals(lexema)) {
                //se o lexema pertence ao vetor de palavras 
                //reservadas retorna seu devido token
                return vetorPalavras.token;
            }

        }

        //verificando se o lexema é um numero
        if (lexema.trim().matches("((\\+|-)?([0-9]+)(\\.[0-9]+)?)|((\\+|-)?\\.?[0-9]+)")) {
            return "NUM";
        }

        //verificando se o lexema é o conteudo das funções write()
        if (lexema.matches("\\\"(\\w|\\d|\\s|\\:)*\\\"")) {
            return "LITERAL";
        }

        //verificando de o lexema corresponde a um ID
        if (lexema.trim().matches("([a-z]+[0-9]*)+")) {

            return "ID";
        } else {
            return "ERRO";
        }

//        return "-";
    }

    private String CalculaValorInicial(Linha l, String lexema) {
        String linha = l.getConteudo();

        //apenas atribui o valor para um identificador
        //se ele posssui um lexema de = na linha dele
        if (linha.contains("=")) {

            int countIgual = linha.length() - linha.replaceAll("=", "").length();

            //se a linha possui apenas uma tribuição
            if (countIgual == 1) {
                int posIgual = linha.indexOf("=");
                int posLexema = linha.indexOf(lexema);

                //se o lexema pesquisado está antes do igual
                if (posLexema < posIgual) {
                    System.out.println(linha);
                    int posVirgula = linha.indexOf(",");
                    

                    //se a virgula tá depois do igual
                    if (posVirgula > posIgual) {

                        String atribuicoes[] = linha.split(",");
                        for (String atribuicao : atribuicoes) {
                            if (atribuicao.contains(lexema)) {
                                String ex[] = atribuicao.split("=");
                                return ex[ex.length - 1];
                            }
                        }
                        
                    //se a virgula tá antes do igual
                    } else {

                        String exp[] = linha.split("=");
                        return exp[exp.length - 1];
                    }

                } else {
                    return lexema;
                }

            //caso a linha tenha mais de uma atribuição
            } else {
                String splitLinha[] = linha.split(",");
                for(String atribuicao :splitLinha){
                    if(atribuicao.contains(lexema)){
                        String split[] = atribuicao.split("=");
                        return split[split.length-1];
                    }
                }

            }

        }else{
            return "-";
        }


        return "ERRO";
    }

    //função que transforma uma expressão arritmética de uma String
    //em ums solução viável
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') {
                    nextChar();
                }
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) {
                        x += parseTerm(); // addition
                    } else if (eat('-')) {
                        x -= parseTerm(); // subtraction
                    } else {
                        return x;
                    }
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) {
                        x *= parseFactor(); // multiplication
                    } else if (eat('/')) {
                        x /= parseFactor(); // division
                    } else {
                        return x;
                    }
                }
            }

            double parseFactor() {
                if (eat('+')) {
                    return parseFactor(); // unary plus
                }
                if (eat('-')) {
                    return -parseFactor(); // unary minus
                }
                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') {
                        nextChar();
                    }
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') {
                        nextChar();
                    }
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) {
                        x = Math.sqrt(x);
                    } else if (func.equals("sin")) {
                        x = Math.sin(Math.toRadians(x));
                    } else if (func.equals("cos")) {
                        x = Math.cos(Math.toRadians(x));
                    } else if (func.equals("tan")) {
                        x = Math.tan(Math.toRadians(x));
                    } else {
                        throw new RuntimeException("Unknown function: " + func);
                    }
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) {
                    x = Math.pow(x, parseFactor()); // exponentiation
                }
                return x;
            }
        }.parse();
    }
}
