/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rtools.senha.utilitarios;

/**
 *
 * @author Administrador
 */
import java.text.DecimalFormat;   
import java.text.DecimalFormatSymbols;   
import java.util.Locale;   
  
/**  
 * Classe que padroniza a internacionalizacao de valores monetarios  
 * @version 0.1  
 * @see java.util.Locale  
 * @see java.text.DecimalFormat  
 * @see java.text.DecimalFormatSymbols  
 */  
public final class Moeda {   
       
    /**  
     * Simbolos especificos do Dolar Americano  
     */  
    private static final DecimalFormatSymbols DOLAR = new DecimalFormatSymbols(Locale.US);   
    /**  
     * Mascara de dinheiro para Dolar Americano  
     */  
    public static final DecimalFormat DINHEIRO_DOLAR = new DecimalFormat("###,###,##0.00",DOLAR);   
    /**  
     * Simbolos especificos do Euro  
     */  
    private static final DecimalFormatSymbols EURO = new DecimalFormatSymbols(Locale.GERMANY);   
    /**  
     * Mascara de dinheiro para Euro  
     */  
    public static final DecimalFormat DINHEIRO_EURO = new DecimalFormat("� ###,###,##0.00",EURO);   
    /**  
     * Locale Brasileiro  
     */  
    private static final Locale BRAZIL = new Locale("pt","BR");   
    /**  
     * S�mbolos especificos do Real Brasileiro  
     */  
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);   
    /**  
     * Mascara de dinheiro para Real Brasileiro  
     */  
    public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("� ###,###,##0.00",REAL);   
       
    /**  
     * Mascara texto com formatacao monetaria  
     * @param valor Valor a ser mascarado  
     * @param moeda Padrao monetario a ser usado  
     * @return Valor mascarado de acordo com o padrao especificado  
     * Ex: String m = Moeda.mascaraDinheiro(100,Moeda.DINHEIRO_REAL);
     * m = R$ 100.00
     */  
    public static String mascaraDinheiro(double valor, DecimalFormat moeda){   
        return moeda.format(valor);   
    }   
} 