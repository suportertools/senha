/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.senha;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Rogerio
 */
public class DBpostgreSQL {

  ConexaoPostgreSQL conexao = new ConexaoPostgreSQL();

  public DBpostgreSQL()
  {
    super();
  }

  //insere os dados de acordo com a query
  public int inserir(String query)
  {

    int retorno = 0;

    try
    {
      retorno = conexao.stm.executeUpdate(query);
      retorno = 1;
    }
    catch (SQLException e)
    {
      try
      {
        conexao.con.rollback();
      }
      catch (SQLException e1)
      {
        System.out
            .println("Erro -> DBpostgreSQL.inserir(): " + e.getMessage());
        e1.printStackTrace();
      }
      System.out
          .println("DBpostgreSQL.inserir() -> Erro: " + e.getMessage());
    }

    return retorno;
  }

  //altera os dados de acordo com a query
  public int alterar(String query)
  {

    int retorno = 0;

    try
    {
      retorno = conexao.stm.executeUpdate(query);
      retorno = 1;
    }
    catch (SQLException e)
    {
      try
      {
        conexao.con.rollback();
      }
      catch (SQLException e1)
      {
        System.out
            .println("Erro -> DBpostgreSQL.alterar(): " + e.getMessage());
        e1.printStackTrace();
      }
      System.out
          .println("DBpostgreSQL.alterar() -> Erro: " + e.getMessage());
    }

    return retorno;

  }

  //exclui os dados de acordo com a query
  public int excluir(String query)
  {

    int retorno = 0;

    try
    {
      retorno = conexao.stm.executeUpdate(query);
      retorno = 1;
    }
    catch (SQLException e)
    {
      try
      {
        conexao.con.rollback();
      }
      catch (SQLException e1)
      {
        System.out
            .println("Erro -> DBpostgreSQL.inserir(): " + e.getMessage());
        e1.printStackTrace();
      }
      System.out
          .println("DBpostgreSQL.excluir() -> Erro: " + e.getMessage());
    }

    return retorno;
  }

  //consulta os dados de acordo com a query
  public ResultSet consultar(String query)
  {

    ResultSet rs = null;

    try
    {
      rs = conexao.stm.executeQuery(query);
    }
    catch (SQLException e)
    {
      System.out.println("DBpostgreSQL.consultar() -> Erro: "
          + e.getMessage());
    }

    return rs;
  }

  public void fecharConexao()
  {

    try
    {
      conexao.con.close();
    }
    catch (SQLException e)
    {
      System.out.println("DBpostgreSQL.fecharConexao() -> Erro: "
          + e.getMessage());
    }

  }

  public String DriverConexao()
  {
      return conexao.getConexaoDSN();
  }
}
