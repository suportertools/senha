/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import br.com.rtools.senha.DBpostgreSQL;
import br.com.rtools.senha.ConexaoPostgreSQL;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import br.com.rtools.senha.utilitarios.DataHora;
/**
 *
 * @author RTools
 */
public class Main {
               public static FileReader reader;
               public static BufferedReader buffReader;
               public static DBpostgreSQL conecta  = new DBpostgreSQL();

                String wqry=
                "select "+
                "func_nullString(nr_senha||'') as senha, "+
                "nr_mesa||''  as mesa, "+
                "ds_hora_chamada as hora_chamada "+
                "from hom_senha  "+
                "where nr_mesa > 0 and ds_hora_chamada is not null and  "+
                "to_char(dt_data, 'DD/MM/YYYY') = to_char(now(), 'DD/MM/YYYY') "+
//                "order by id desc  "+
                "order by nr_ordem desc  "+
                "limit 4 ";
    public static ResultSet rsx; //Abre
    public JPanel pane;

    public  static Sons som;

    public  static JLabel titulo1  = new JLabel("S I N E C O L");
    public  static JLabel titulo21 = new JLabel("");
    
    
    public  static JLabel senha1 = new JLabel("");
    public  static JLabel senha2 = new JLabel("");
    public  static JLabel senha3 = new JLabel("");
    public  static JLabel senha4 = new JLabel("");

    public  static JLabel mesa1  = new JLabel("");
    public  static JLabel mesa2 = new JLabel("");
    public  static JLabel mesa3 = new JLabel("");
    public  static JLabel mesa4 = new JLabel("");

    public  static String wsenha1="";
    public  static String wsenha2="";
    public  static String wsenha3="";
    public  static String wsenha4="";

    public  static String whora1="";
    public  static String whora2="";
    public  static String whora3="";
    public  static String whora4="";

    public  static int wfilial=0;


/*
 *
    public  static JLabel senha2 = new JLabel("2");
    public  static JLabel senha3 = new JLabel("3");
    public  static JLabel senha4 = new JLabel("4");
    public  static JLabel mesa2 = new JLabel("2");
    public  static JLabel mesa3 = new JLabel("3");
    public  static JLabel mesa4 = new JLabel("4");
*/

    public static void main(String[] args) throws IOException {
            //Toolkit.getDefaultToolkit().beep();
            wfilial = filial();
            if (conecta.DriverConexao()==null)
            {
                JOptionPane.showMessageDialog(null,"Sem Conexão com o Banco de Dados !!");
                System.exit(0);
            }
            if (wfilial<1)
            {
                JOptionPane.showMessageDialog(null,"Definir Filial em c:\\projetos\\DB\\filial.txt !!");
                System.exit(0);
            }
            inicio a = new inicio();
            Thread t1 = new Thread(a, "Thread 1");

            t1.start();
            loop b = new loop();
            Thread t2 = new Thread(b, "Thread 2");
            t2.start();

    }
    
    public static class inicio  implements Runnable{
        public void run() {
            JFrame frame = new JFrame();
            Dimension resolucao = Toolkit.getDefaultToolkit().getScreenSize();
            int width = resolucao.width , height = resolucao.height;
            
            frame.setSize(width, height); //dimensão Horizontal/Vertical
//            frame.setLocation(0, 0);
            frame.setLocation(-1300, 0);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Fecha todo o projeto

            JPanel painelChamaSenha = new JPanel(new FlowLayout());
            painelChamaSenha.setLayout(null);//TEM Q SER NULL PARA APLICAR setBounds(new Rectangle(...

            frame.getContentPane().setLayout (new BorderLayout());

            ImageIcon icon = new ImageIcon("C:/Projetos/imagens/senha.png");
            icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

            JLabel label = new JLabel(icon);

            painelChamaSenha.setBounds      (  0, 0,width,height-25);//COLUNA,LINHA,LARG,ALT
            label.setBounds                 (  0, 0,width,height-25);//COLUNA,LINHA,LARG,ALT
            titulo1.setBounds      (  300, -5,1000,100);//COLUNA,LINHA,LARG,ALT
            titulo1.setFont(new Font("", Font.BOLD, 100));
            titulo1.setHorizontalAlignment(JTextField.CENTER);
            titulo1.setForeground(Color.BLACK);


            mesa1.setForeground(Color.WHITE);
            mesa1.setFont(new Font("", Font.BOLD, 150));
            mesa1.setHorizontalAlignment(JTextField.CENTER);

            senha1.setForeground(Color.WHITE);
            senha1.setFont(new Font("", Font.BOLD, 150));
            senha1.setHorizontalAlignment(JTextField.CENTER);


            senha2.setForeground(Color.WHITE);
            senha3.setForeground(Color.WHITE);
            senha4.setForeground(Color.WHITE);

            mesa2.setForeground(Color.WHITE);
            mesa3.setForeground(Color.WHITE);
            mesa4.setForeground(Color.WHITE);
///1280 x 800

/*
            senha2.setBounds      (  800, 660,300,60);//COLUNA,LINHA,LARG,ALT
            senha3.setBounds      (  410, 660,300,60);//COLUNA,LINHA,LARG,ALT
            senha4.setBounds      (   60, 660,300,60);//COLUNA,LINHA,LARG,ALT

            mesa2.setBounds       (  940, 660,300,60);//COLUNA,LINHA,LARG,ALT
            mesa3.setBounds       (  570, 660,300,60);//COLUNA,LINHA,LARG,ALT
            mesa4.setBounds       (  200, 660,300,60);//COLUNA,LINHA,LARG,ALT
*/

///1024 x 768  -- TELEVISÃO
/*            
            senha1.setBounds      (  130, 290,300,150);//COLUNA,LINHA,LARG,ALT
            mesa1.setBounds       (  610, 290,300,150);//COLUNA,LINHA,LARG,ALT


            senha4.setBounds      (   10, 630,300,60);//COLUNA,LINHA,LARG,ALT
            senha3.setBounds      (  310, 630,300,60);//COLUNA,LINHA,LARG,ALT
            senha2.setBounds      (  600, 630,300,60);//COLUNA,LINHA,LARG,ALT

            mesa4.setBounds       (  130, 630,300,60);//COLUNA,LINHA,LARG,ALT
            mesa3.setBounds       (  430, 630,300,60);//COLUNA,LINHA,LARG,ALT
            mesa2.setBounds       (  720, 630,300,60);//COLUNA,LINHA,LARG,ALT
*/

/// MONITOR            
            senha1.setBounds      (  280, 340,300,150);//COLUNA,LINHA,LARG,ALT
            mesa1.setBounds       (  1020, 340,300,150);//COLUNA,LINHA,LARG,ALT


            senha4.setBounds      (   90, 750,300,60);//COLUNA,LINHA,LARG,ALT
            mesa4.setBounds       (  290, 750,300,60);//COLUNA,LINHA,LARG,ALT

            senha3.setBounds      (  560, 750,300,60);//COLUNA,LINHA,LARG,ALT
            mesa3.setBounds       (  755, 750,300,60);//COLUNA,LINHA,LARG,ALT

            senha2.setBounds      (  1030,  750,300,60);//COLUNA,LINHA,LARG,ALT OK
            mesa2.setBounds       (  1225, 750,300,60);//COLUNA,LINHA,LARG,ALT OK

            
            
            
            senha2.setFont(new Font("", Font.BOLD, 60));
            senha3.setFont(new Font("", Font.BOLD, 60));
            senha4.setFont(new Font("", Font.BOLD, 60));


            mesa2.setFont(new Font("", Font.BOLD, 60));
            mesa3.setFont(new Font("", Font.BOLD, 60));
            mesa4.setFont(new Font("", Font.BOLD, 60));


            senha1.setHorizontalAlignment(JTextField.CENTER);
            senha2.setHorizontalAlignment(JTextField.CENTER);
            senha3.setHorizontalAlignment(JTextField.CENTER);
            senha4.setHorizontalAlignment(JTextField.CENTER);

            mesa1.setHorizontalAlignment(JTextField.CENTER);
            mesa2.setHorizontalAlignment(JTextField.CENTER);
            mesa3.setHorizontalAlignment(JTextField.CENTER);
            mesa4.setHorizontalAlignment(JTextField.CENTER);
/*
            senha1.setSize(width, height); //dimensão Horizontal/Vertical
            senha2.setSize(width, height); //dimensão Horizontal/Vertical
            senha3.setSize(width, height); //dimensão Horizontal/Vertical



 senha4.setSize(width, height); //dimensão Horizontal/Vertical
            mesa1.setSize(width, height); //dimensão Horizontal/Vertical
*/

            frame.add(painelChamaSenha);

            painelChamaSenha.add(titulo1);
 
            painelChamaSenha.add(mesa1);
            painelChamaSenha.add(mesa2);
            painelChamaSenha.add(mesa3);
            painelChamaSenha.add(mesa4);

            painelChamaSenha.add(senha1);
            painelChamaSenha.add(senha2);
            painelChamaSenha.add(senha3);
            painelChamaSenha.add(senha4);

            painelChamaSenha.add(label);

            
//            frame.add(label, BorderLayout.BEFORE_FIRST_LINE);
            frame.setVisible(true);
         }
    }

    public static class loop  implements Runnable{
        public void run() {

/*
                whora1="";
                whora2="";
                whora3="";
                whora4="";
                wsenha1="";
                wsenha2="";
                wsenha3="";
                wsenha4="";
*/

                mesa1.setText("");
                mesa2.setText("");
                mesa3.setText("");
                mesa4.setText("");

                senha1.setText("");
                senha2.setText("");
                senha3.setText("");
                senha4.setText("");

                String wsenha="";
                String whora="";
                String wmesa="";

               String wqry=
                "select "+
                "func_nullString(nr_senha||'') as senha, "+
                "nr_mesa||''  as mesa, "+
                "ds_hora_chamada as hora_chamada "+
                "from hom_senha  "+
                "where id_filial="+wfilial+" and "+
                "nr_mesa > 0 and ds_hora_chamada is not null and  "+
                "to_char(dt_data, 'DD/MM/YYYY') = to_char(now(), 'DD/MM/YYYY') "+
                "order by nr_ordem desc  "+
//                "order by id desc  "+
                "limit 4 ";
//            public static ResultSet rsx;

             while (true)
            {
//                JOptionPane.showMessageDialog(null,"Entra no Loop");
//                conecta =  new PersistenceDAO();
                rsx = conecta.consultar(wqry);
                int apita = 1;
                int conta = 0;
                try {
                    while (rsx != null && rsx.next()) {
//                        JOptionPane.showMessageDialog(null,"Entra no While rsx");

                        conta++;
                        wsenha=rsx.getString("senha");
                        whora =rsx.getString("hora_chamada");
                        wmesa =rsx.getString("mesa");
                        
                        if (conta==1)
                        {
                           if (senha1.getText().equals(wsenha) && whora1.equals(whora))
                                apita=0;
                           else
                           {
                               senha1.setText(wsenha);
                               mesa1.setText(wmesa);
                               whora1=whora;
                            }
                        }

                        if (apita==1 && conta==2)
                        {
                            if ((senha2.getText().equals(wsenha) && whora2.equals(whora)))
                                    apita=0;
                            else
                            {
                                   senha2.setText(wsenha);
                                   mesa2.setText(wmesa);
                                   whora2=whora;
                            }
                        }

                        if (apita==1 && conta==3)
                        {
                            if ((senha3.getText().equals(wsenha) && whora3.equals(whora)))
                                    apita=0;
                            else
                            {
                                   senha3.setText(wsenha);
                                   mesa3.setText(wmesa);
                                   whora3=whora;
                            }
                        }                        

                        if (apita==1 && conta==4)
                        {
                             if ((senha4.getText().equals(wsenha) && whora4.equals(whora)))
                                 apita=0;
                             else
                             {
                               senha4.setText(wsenha);
                               mesa4.setText(wmesa);
                               whora4=whora;
                             }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
//               conecta.fecharConexao();
                 if (conta==0)
                 {

                    apita = 0;

                    mesa1.setText("");
                    mesa2.setText("");
                    mesa3.setText("");
                    mesa4.setText("");

                    senha1.setText("");
                    senha2.setText("");
                    senha3.setText("");
                    senha4.setText("");
                 }
                 if (conta==1)
                 {
                    mesa2.setText("");
                    mesa3.setText("");
                    mesa4.setText("");

                    senha2.setText("");
                    senha3.setText("");
                    senha4.setText("");
                 }
                 if (conta==2)
                 {
                    mesa3.setText("");
                    mesa4.setText("");

                    senha3.setText("");
                    senha4.setText("");
                 }
                 if (conta==3)
                 {
                    mesa4.setText("");
                    senha4.setText("");
                 }
                if (apita==1)
                {
                                try {


//                                    JOptionPane.showMessageDialog(null,"Entra Som");
                                    som = new Sons();
                                    som.play();
//                                    JOptionPane.showMessageDialog(null,"Sai do Som");
//                                    JOptionPane.showMessageDialog(null,"Entra no Pause");
                                    Thread.sleep(2500);
//                                    JOptionPane.showMessageDialog(null,"Sai do Pause");
                                 //   JOptionPane.showMessageDialog(null,"Nova Senha !!");
                                    } catch (Exception ex) {

                                }
                 }
            }
        }
    }
    
    public static int filial()
    {
        boolean file_existe=true;
        String filial="0";
        try {
            //Carrega Arquivo txt
            reader = new FileReader("c:/Projetos/DB/filial.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConexaoPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
            file_existe=false;
        }
        if (file_existe)
        {
           buffReader = new BufferedReader(reader);
           //------------------------------------------------
           String linha;
           try {
              while ((linha = buffReader.readLine()) != null) {
                  filial = linha.substring(0,1);
              }
              } catch (IOException ex) {
                 Logger.getLogger(ConexaoPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
              }
            try {
              reader.close();
           } catch (IOException ex) {
              Logger.getLogger(ConexaoPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        int ifilial=Integer.parseInt(filial);
        return ifilial;
    }
}
