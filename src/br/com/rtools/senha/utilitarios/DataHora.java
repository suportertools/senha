/*
 * DataHora2.java
 *
 * Created on 23 de Agosto de 2007, 10:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.rtools.senha.utilitarios;
import java.util.*;   

/**
 *
 * @author Rogerio
 */
public class DataHora{
        static
        {
             // get the supported ids for GMT-08:00 (Pacific Standard Time)
             String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
             // if no ids were returned, something is wrong. get out.
             //if (ids.length == 0)
             //System.exit(0);
             // begin output
             //System.out.println("Current Time");
             // create a Pacific Standard Time time zone
             SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);
             // set up rules for daylight savings time
             pdt.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
             pdt.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
             // create a GregorianCalendar with the Pacific Daylight time zone
             // and the current date and time
             Calendar calendar = new GregorianCalendar(pdt);
             Date trialTime = new Date();
             calendar.setTime(trialTime);
         }
                
	public static String obtemHoje()
	{

            Calendar c = Calendar.getInstance();
            int c1 = c.get(Calendar.DAY_OF_MONTH);
            int c2 = c.get(Calendar.MONTH); 
            int c3 = c.get(Calendar.YEAR); 
            c2 = c2 + 1;
            String dd   = Integer.toString(c1);
            String mm   = Integer.toString(c2);
            String yyyy = Integer.toString(c3);
            String wdata = "";//dd+"/"+mm+"/"+yyyy;
            if (dd.length()==1)
                dd = "0"+dd;
            if (mm.length()==1)
                mm = "0"+mm;
            return  dd+"/"+mm+"/"+yyyy;
        }
	public static String obtemVencimentos(int meses)
	{
        String dd="";
        String mm="";
        String yyyy="";
        String wdata="";
//        for (int xx=0;xx < meses;xx++)
//        {
            Calendar c = Calendar.getInstance();
            Date hoje = new Date(); 
            hoje.setMonth(meses);
            c.setTime(hoje);
            int c1 = c.get(Calendar.DAY_OF_MONTH);
            int c2 = c.get(Calendar.MONTH); 
            int c3 = c.get(Calendar.YEAR);
            c2 = c2 + 1;
            dd   = Integer.toString(c1);
            mm   = Integer.toString(c2);
            yyyy = Integer.toString(c3);
            wdata = "";//dd+"/"+mm+"/"+yyyy;
            if (dd.length()==1)
                dd = "0"+dd;
            if (mm.length()==1)
                mm = "0"+mm;
//        }
        return  dd+"/"+mm+"/"+yyyy;
     }

    
	public static String obtemDia(String wdata)
	{
            
            GregorianCalendar pdata = new GregorianCalendar(2007,8,22);
            int ipdata = pdata.get(Calendar.DAY_OF_MONTH);
            String spdata   = Integer.toString(ipdata);
            if (spdata.length()==1)
                spdata = "0"+spdata;
            return  spdata;
        }

	public static String obtemMes(String wdata)
	{
            GregorianCalendar pdata = new GregorianCalendar(2007,8,22);
            int ipdata = pdata.get(Calendar.MONTH);
            String spdata   = Integer.toString(ipdata);
            if (spdata.length()==1)
                spdata = "0"+spdata;
            return  spdata;
        }

       	public static String obtemNomeMes(String wdata)
	{
            GregorianCalendar pdata = new GregorianCalendar(2007,8,22);
            int ipdata = pdata.get(Calendar.MONTH);
            String spdata="";
            switch (ipdata ){
		   case 1:
                       spdata="Janeiro";
                       break; 
		   case 2:
                       spdata="Fevereiro";
                       break; 
		   case 3:
                       spdata="Mar�o";
                       break; 
		   case 4:
                       spdata="Abril";
                       break; 
		   case 5:
                       spdata="Maio";
                       break; 
		   case 6:
                       spdata="Junho";
                       break; 
		   case 7:
                       spdata="Julho";
                       break; 
		   case 8:
                       spdata="Agosto";
                       break; 
		   case 9:
                       spdata="Setembro";
                       break; 
		   case 10:
                       spdata="Outubro";
                       break; 
		   case 11:
                       spdata="Novembro";
                       break; 
		   case 12:
                       spdata="Dezembro";
                       break; 
            } //switch
            return  spdata;
        }
        
         public static String obtemAno(String wdata)
	{
            GregorianCalendar pdata = new GregorianCalendar(2007,8,22);
            int ipdata = pdata.get(Calendar.YEAR);
            String spdata   = Integer.toString(ipdata);
            return  spdata;
        }
        

         public static String obtemDiaSemana(String wdata)
	{
            GregorianCalendar pdata = new GregorianCalendar(2007,8,22);
            int ipdata = pdata.get(Calendar.DAY_OF_WEEK_IN_MONTH);
            String spdata   = Integer.toString(ipdata);
            return  spdata;

        }
         public static String obtemNomeSemana(String wdata)
	{
            GregorianCalendar pdata = new GregorianCalendar(2007,8,22);
            int ipdata = pdata.get(Calendar.DAY_OF_WEEK_IN_MONTH);
            String spdata="";
            switch (ipdata ){
		   case 1:
                       spdata="Domingo";
                       break; 
		   case 2:
                       spdata="Segunda";
                       break; 
		   case 3:
                       spdata="Ter�a";
                       break; 
		   case 4:
                       spdata="Quarta";
                       break; 
		   case 5:
                       spdata="Quinta";
                       break; 
		   case 6:
                       spdata="Sexta";
                       break; 
		   case 7:
                       spdata="Sabado";
                       break; 
            } //switch
            return spdata;
         }

         public static String obtemHoras()
	{
            GregorianCalendar pdata = new GregorianCalendar();
            int ipdata = pdata.get(Calendar.HOUR_OF_DAY);
            String spdata   = Integer.toString(ipdata);
            return  spdata;
        }


         public static String obtemMinutos()
	{
            GregorianCalendar pdata = new GregorianCalendar();
            int ipdata = pdata.get(Calendar.MINUTE);
            String spdata   = Integer.toString(ipdata);
            return  spdata;
        }

         public static String obtemSegundos()
	{
            GregorianCalendar pdata = new GregorianCalendar();
            int ipdata = pdata.get(Calendar.SECOND);
            String spdata   = Integer.toString(ipdata);
            return  spdata;
        }
    
         public static String obtemHorasTotal()
	{
            GregorianCalendar pdata = new GregorianCalendar();
            int ipdata1 = pdata.get(Calendar.HOUR);
            int ipdata2 = pdata.get(Calendar.MINUTE);
            int ipdata3 = pdata.get(Calendar.SECOND);
            String spdata1   = Integer.toString(ipdata1);
            String spdata2   = Integer.toString(ipdata2);
            String spdata3   = Integer.toString(ipdata3);
            return  spdata1+":"+spdata2+":"+spdata3;
        }
    
         public static String obtemAmPm()
	{
            GregorianCalendar pdata = new GregorianCalendar();
            int ipdata = pdata.get(Calendar.AM_PM);
            String spdata   = Integer.toString(ipdata);
            if (ipdata > 0)
            {
                spdata="pm";
            }
            else
            {
                spdata="am";
            }
                
                
            return  spdata;
        }
         
}
