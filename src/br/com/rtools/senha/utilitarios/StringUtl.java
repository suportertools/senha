package br.com.rtools.senha.utilitarios;

import java.security.*;
import java.net.*;



/*
	Utilit�rios para tratamento de strings.
	M�rcio A. Siena, 2003.
*/
public class StringUtl
{
	// Teste dos m�todos
	public static void main (String[] args)
	{
		String s;


		System.out.println("\n\n******************** Testa converteNullParaStr ********************");
		s = "abcd";
		System.out.println("converteNullParaStr(" + s + ")= [" + converteNullParaStr(s) + "]");

		s = null;
		System.out.println("converteNullParaStr(" + s + ")= [" + converteNullParaStr(s) + "]");


		System.out.println("\n\n******************** Testa MD5 ********************");
		s = "abcd";
		System.out.println("MD5(" + s + ")= [" + MD5(s) + "]");
		System.out.println("MD5(" + s + ", true )= [" + MD5(s, true) + "]");
		System.out.println("MD5(" + s + ", false)= [" + MD5(s, false) + "]");

		
		System.out.println("\n\n******************** Testa converteEntidadesHTML ********************");
		s = "a b c & \" < >";
		System.out.println("converteEntidadesHTML(" + s + ")= [" + converteEntidadesHTML(s) + "]");
		
		
		System.out.println("\n\n******************** Testa formataQueryString ********************");
		String[] nomes = new String[] {"PESO", "IDADE", "PROFISS�O", "ESPORTE", "ESPORTE"};
		String[] valores = new String[] {"78", "25", "M�dico Ortopedista", "Golf", "T�nis de mesa"};
		System.out.print("\nNomes: ");
		for (int i=0; i < nomes.length; i++) System.out.print(" | " + nomes[i]);
		System.out.print("\nValores: ");
		for (int i=0; i < valores.length; i++) System.out.print(" | " + valores[i]);

		System.out.println("\n");

		try {
			System.out.println("\nEncoding null:       \n" + formataQueryString(nomes, valores, null));
			System.out.println("\nEncoding \"\":         \n" + formataQueryString(nomes, valores, ""));
			System.out.println("\nEncoding ISO-8859-1: \n" + formataQueryString(nomes, valores, "ISO-8859-1"));
			System.out.println("\nEncoding UTF-8:      \n" + formataQueryString(nomes, valores, "UTF-8"));
			System.out.println("\nEncoding BANANA:     \n" + formataQueryString(nomes, valores, "BANANA"));
		} catch (java.io.UnsupportedEncodingException ex) {
			System.out.println("\nEncoding n�o suportado: " + ex.toString());
		}


		System.out.println("\n\n******************** Testa montaHyperlink ********************");
		System.out.println("Texto: <cadastrar novo usu�rio>");
		try {
			System.out.println("\n" + montaHyperlink
				("/cadastro.jsp", "<cadastrar novo usu�rio>", null, null, null));

			System.out.println("\n" + montaHyperlink
				("/cadastro.jsp", "<cadastrar novo usu�rio>", nomes, valores, "ISO-8859-1"));
		} catch (java.io.UnsupportedEncodingException ex) {
			System.out.println("\nEncoding n�o suportado: " + ex.toString());
		}
	} // main



	/*
		Se s for null, retorna "", caso contr�rio retorna
		o pr�prio s.
	*/
	public static String converteNullParaStr (String s)
	{
		if (s != null) {
			return s;
		} else {
			return "";
		}
	} // converteNullParaStr



	/*
     	Calcula o MD5 de um String.
     	Retorna o digest MD5 de s. Se houver algum erro, retorna um string 
		de 32 espa�os em branco.
		Os caracteres hexadecimais s�o retornados em min�sculas.
     */
    public static String MD5 (String s)
    {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (Exception ex) {
            // 32 espa�os
            return "                                ";
        }

		// Calcula o MD5 como uma matriz de bytes
        md.update(s.getBytes());
        byte[] b = md.digest();

		// Converte para um string de d�gitos hexadecimais
        StringBuilder md5 = new StringBuilder();

        for (int i=0; i < b.length; i++) {
            int n = b[i] & 0x000000FF;
            String hex = Integer.toHexString(n);
            if (hex.length() == 1) md5.append("0");
            md5.append(hex);
        }

        return md5.toString().toLowerCase();
    } // MD5




	/*
     	Calcula o MD5 de um String.
     	Retorna o digest MD5 de s. Se houver algum erro, retorna um string 
		de 32 espa�os em branco.
		Se caixaAlta for true, os caracteres hexadecimais s�o retornados em mai�sculas,
		caso contr�rio, ser�o retornados em min�sculas.
     */
    public static String MD5 (String s, boolean caixaAlta)
	{
		String md5 = MD5(s);
		if (caixaAlta) {
			return md5.toUpperCase();
		} else {
			return md5;
		}
	} // MD5



	/*
     	Converte os caracteres especiais " , & , < e > de 
     	um String nas entidades HTML correspondentes, para inser��o no 
		corpo de um documento HTML.
		s � o String a ser convertido.
     */
    public static String converteEntidadesHTML (String s)
    {
		// null e string vazio
    	if (s == null) return "";
        
        int tamanho = s.length();
        if (tamanho == 0) return "";
        
		// Strings com caracters
        StringBuilder sb = new StringBuilder();

        for (int i=0; i < tamanho; i++) {
            char ch = s.charAt(i);
            
            switch (ch) {
                case '&': sb.append("&amp;"); break;
                case '"': sb.append("&quot;"); break;
                case '<': sb.append("&lt;"); break;
                case '>': sb.append("&gt;"); break;
                default: sb.append(Character.toString(ch));
            }
        }
        
        return sb.toString();
    } // converteEntidadesHTML



	/*
	 	Formata um query string.

		nomes[] e valores[] s�o os nomes e valores dos par�metros,
		respectivamente. Essas duas matrizes DEVEM ter o mesmo
		tamanho.

		encoding � a codifica��o utilizada, como "ISO-8859-1". Esse � o padr�o, caso
		encoding seja null ou "".

		O String retornado n�o inicia com "?".

	*/
	public static String formataQueryString 
		( String[] nomes, String[] valores, String encoding)
		throws java.io.UnsupportedEncodingException
	{
		int ultimoelemento = nomes.length - 1;

		StringBuilder qs = new StringBuilder();
		encoding = converteNullParaStr(encoding);
		if (encoding.length() == 0) encoding = "ISO-8859-1";

		for (int i = 0; i <= ultimoelemento; i++) {
			qs.append(URLEncoder.encode(nomes[i], encoding));
        	qs.append("=");
			qs.append(URLEncoder.encode(valores[i], encoding));
			if (i < ultimoelemento) qs.append("&");
		}

		return qs.toString();
	} // formataQueryString



	public static String montaHyperlink 
		( String URLbase, String texto,
			String[] nomes, String[] valores, String encoding)
		throws java.io.UnsupportedEncodingException
	{
		StringBuilder hl = new StringBuilder("<a href=\"");
		hl.append(URLbase);

		// Temos um query string?
		if (nomes != null) {
			hl.append("?");
			hl.append(formataQueryString(nomes, valores, encoding));
		}

		hl.append("\">") ;
		hl.append(converteEntidadesHTML(texto));
		hl.append("</a>");

		return hl.toString();
	} // montaHyperlink

    public static String RemoveAcentos(String acentos)
	{
                    acentos = acentos.replaceAll("[����]","e");
                    acentos = acentos.replaceAll("[��]","u");
                    acentos = acentos.replaceAll("[��]","i");
                    acentos = acentos.replaceAll("[���]","a");
                    acentos = acentos.replaceAll("�","o");
                    acentos = acentos.replaceAll("�","c");

                    acentos = acentos.replaceAll("[����]","E");
                    acentos = acentos.replaceAll("[��]","U");
                    acentos = acentos.replaceAll("[��]","I");
                    acentos = acentos.replaceAll("[���]","A");
                    acentos = acentos.replaceAll("��","O");
                    acentos = acentos.replaceAll("�","C");
                    return acentos;
            } //RemoveAcentos
        
} // class StringUtl


