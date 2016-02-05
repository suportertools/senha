
package br.com.senha;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoPostgreSQL{
   private String ConexaoDSN;
   public Connection con = null;
   public Statement stm = null;
   public FileReader reader;
   BufferedReader buffReader;
//    private final String ConexaoPost;

   public ConexaoPostgreSQL()  {
        try {
            //Carrega Arquivo txt
            reader = new FileReader("c:/Projetos/DB/regPostgreSQL.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConexaoPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
          buffReader = new BufferedReader(reader);
          //------------------------------------------------
          String ip="";
          String usuario="";
          String senha="";
          String db="";
          int tam;

          String linha;
        try {
            while ((linha = buffReader.readLine()) != null) {
                tam = linha.length();
                if (linha.substring(0, 3).equals("IP:")) {
                    ip = linha.substring(3,tam);
                }
                if (linha.substring(0, 8).equals("USUARIO:")) {
                    usuario = linha.substring(8, tam);
                }
                if (linha.substring(0, 6).equals("SENHA:")) {
                    senha = linha.substring(6, tam);
                }
                if (linha.substring(0, 3).equals("DB:")) {
                    db = linha.substring(3, tam);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ConexaoPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(ConexaoPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
//--------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
      ConexaoDSN   =  "jdbc:postgresql://"+ip+"/"+db;
      try {
           Class.forName("org.postgresql.Driver");  //Post
           con = DriverManager.getConnection(ConexaoDSN,usuario,senha);
  //         stm = con.createStatement();
           stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);


      } catch (Exception e) {
         ConexaoDSN=null;
         System.out.println("N�o foi poss�vel conectar ao banco: " + e.getMessage());
      }
   }
    public String getConexaoDSN(){
        return ConexaoDSN;
     }
}
