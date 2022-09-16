package database.login.Socket_s;


import Calender_Per.cal;
import database.login.Insert_Q;
import database.login.login;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.DataInputStream;
import java.util.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class sock_fitch 
{
    
    private String URL="jdbc:sqlite:db_az.sqlite";
    
    private String rete="";
    
    String qus_cls="";
    
    
    public void show_All_cls()
    {
        
        try 
        {
            
            Connection con=DriverManager.getConnection(URL);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("SELECT * FROM class_tb WHERE NOT delete_falg is 'true';");
            
            while(rs.next())
            {
                rete+=rs.getString("name_class")+";";
            }
            
            
            rs.close();
            
            st.close();
            
            con.close();
            
        }
        catch (Exception e)
        {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
        SND(rete);
        
        
    }
    
    
    
    
    /***********for student sss*/
    ServerSocket server_soc;
    
    Socket soc;
    
    java.io.DataInputStream datainput;
    
    DataOutputStream dataout;
    
    
    
    
    
    /************for teachers*/
    ServerSocket server_soc_tch;
    
    Socket soc_tch;
    
    java.io.DataInputStream datainput_tch;
    
    DataOutputStream dataout_tch;
    
    
    public Thread thr_res_q;
    
    
    
    
    
    public void SND(String Val)
    {
        final String hh=Val;
        
        Thread thr=new Thread(new Runnable() {
            @Override
            public void run() {
                
                try
                {
                server_soc=new ServerSocket(8084);
            
            while(true)
            {
                
                soc=server_soc.accept();
                
                datainput=new java.io.DataInputStream(soc.getInputStream());
                
                dataout=new DataOutputStream(soc.getOutputStream());
                
                dataout.writeUTF(hh);
                

                Thread thrd=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        
                                    try
                                    {
                                        String strVal=datainput.readUTF();
                                        
                                        System.out.println(strVal);
                                        
                                        qus_cls=strVal;
                                        
                                        qus_Val="";
                                        
                                        sel_ques(strVal);
                                        
                                        
                                        
                                        
                                        String karnameh="";
                                        
                                        karnameh=datainput.readUTF();
                                        
                                        res_karnameh(karnameh);
                                        
                                        
                                        
                                        
                                    }
                                    catch(Exception Error){}
                    }
                });
                
                
                thrd.start();
                
                
            }
            
            
        }
        catch(Exception Error)
        {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }
                
            }
        });
        
        thr.start();
        
        
        
        
        
        /*for teacher*/
        Thread thr_tch=new Thread(new Runnable() {
            @Override
            public void run() {
                
                try 
                {
                    server_soc_tch=new ServerSocket(8910);
                    
                    while (true) 
                    {   
                        soc_tch=server_soc_tch.accept();
                        
                        datainput_tch=new java.io.DataInputStream(soc_tch.getInputStream());
                        
                        dataout_tch=new DataOutputStream(soc_tch.getOutputStream());
                        
                        database.login.login logins=new login();
                        
                        dataout_tch.writeUTF(logins.quary_tch("SELECT * FROM teacher_tb WHERE NOT delete_flag is 'true';"));
                  
                        thrs_thch();
                        
                        
                    }
                    
                } 
                catch (Exception e) 
                {
                    javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
                }
                
                
            }
        });
        
        thr_tch.start();
        
        
        
      
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    String qus_Val="";
    
    
    public String sel_ques(String cls_name)
    {
        try 
        {
            
            Connection con=DriverManager.getConnection(URL);
                        
            Statement st=con.createStatement();
            
            Calender_Per.cal p=new cal();
            
            String dt=p.getIranianYear()+"/"+p.getIranianMonth()+"/"+p.getIranianDay();
            
            String date="";
            
            Calender_Per.cal cal=new cal();
            
            
            
            
            date+=cal.getIranianYear()+"/";
            date+=cal.getIranianMonth()+"/";
            date+=cal.getIranianDay();
            
            
            
            int clock_hor=LocalTime.now().getHour(),clock_min=LocalTime.now().getMinute();
            
            String min="(";
            String []mins=new String[30];
            
            for(int u=-15;u<15;u++)
            {
                if(u>=14)
                    min+=(clock_min+u);
                else
                    min+=(clock_min+u)+",";
                mins[u+15]=u+"";
            }
            
            
            min+=")";
            
            
            System.out.println(min);
            
            String ss="SELECT * FROM question_tb INNER JOIN limQ_tb ON question_tb.ID_Q=limQ_tb.ID WHERE limQ_tb.date='"+date+"' AND limQ_tb.saat_start="+clock_hor+" AND limQ_tb.Daq_lim in "+min+"  AND question_tb.ID_cls='"+qus_cls+"';";
            
            
            ResultSet rs=st.executeQuery(ss);
            
            
            boolean snd=false;
            
            while(rs.next())
            {
                
                String min_y=rs.getString("Daq_lim");
                for(int i=1;i<15;i++)
                {
                    if(min_y.equals(mins[i])||min_y.equals(clock_min))
                        snd=true;
                }
                //if(snd)
                {
                    System.out.println(snd);
                    qus_Val+=rs.getString("ID_Q")+";";
                    qus_Val+=rs.getString("qus")+";";
                    qus_Val+=rs.getString("ans_one")+";";
                    qus_Val+=rs.getString("ans_two")+";";
                    qus_Val+=rs.getString("ans_thr")+";";
                    qus_Val+=rs.getString("ans_for")+";";
                    qus_Val+=rs.getString("ans")+";";
                    qus_Val+=rs.getString("type")+";";
                    qus_Val+=rs.getString("image")+";";
                    qus_Val+=rs.getString("Daq_lim")+";";
                }
            }
            
            
            
            dataout.writeUTF(qus_Val);
            
            
            rs.close();
            
            st.close();
            
            con.close();
            
        }
        catch (Exception e)
        {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return qus_Val;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public String Image_to_string(Blob b)
    {
        try
        {
            String s = "";
            InputStream inStream = b.getBinaryStream();
            InputStreamReader inStreamReader = new InputStreamReader(inStream);
            BufferedReader reader = new BufferedReader(inStreamReader );
            StringBuffer buf = new StringBuffer();
            while((s = reader.readLine())!=null)
            {
                    buf.append(s);
            }
            
            return buf.toString();
            
            
            
        }
        catch(Exception Error)
        {
            System.out.println(Error.getMessage());
        }
        return "";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void Insert_Q_to_db(String Quesss)
    {
     
        String []All=Quesss.split("#");
        
        
        database.login.Insert_Q.bls=true;
        
        for(int a=0;a<All.length;a++)
        {
            
            String []qus=All[a].split(";");
            
            Insert(qus);
            
        }
        
        
        
    }
    
    
    
    
    
    
    
    public void Insert(String []Val)
    {
        try
        {
            
            
        database.login.Insert_Q insert=new Insert_Q();
       
        
        insert.Insert_Question(Val[0], Val[1], Val[2], Val[3], Val[4], Val[5],Val[6] );
        
        insert.end_question(Integer.parseInt(Val[7]), Integer.parseInt(Val[8]), Integer.parseInt(Val[9]), Integer.parseInt(Val[10]), Val[11]);
        
        
        }
        catch(Exception Error)
        {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    
    /* fitch dats                    by clinets*/
    public void res_karnameh(String str)
    {
        
        System.out.println(str);
        
        String []res=str.split(";");
        
        String name=res[0];
        
        String family=res[1];
        
        String tru,fls,non;
        
        tru=fls=non="";
        
        int t,f,n;
        
        t=f=n=0;
        
        
        for(int a=2;a<res.length;a++)
        {
            String []valll=res[a].split(",");
            
            
            if(valll[0].trim().equals("true"))
            {
                tru+=valll[1].toString()+";";
                t++;
            }
            else if(valll[0].trim().equals("false"))
            {
                fls+=valll[1].toString()+";";
                f++;
            }
            else if(valll[0].trim().equals("none"))
            {
                non+=valll[1]+";";
                n++;
            }
            
        }
        
        
        
        String Datees=Datee();
        
        stu(name, family, f, t, n,tru,fls,non,Datees);
        
        
    }
    
    
    
    
    public void stu(String name,String family,int fls,int tru,int non,String tru_Q,String fls_Q,String non_Q,String date)
    {
    
        boolean bl=check_record(name, family);
        
        if(bl)
        {
            Update(name, family, fls, tru, non, tru_Q, fls_Q, non_Q, date);
            //javax.swing.JOptionPane.showMessageDialog(null, "find"+"ID_CLS="+ID_CLS+"and AVG="+AVG+" all COUNT"+all_cnt);
        }
        else
        {
            insert_st(name, family, fls, tru, non, tru_Q, fls_Q, non_Q, date);
            //javax.swing.JOptionPane.showMessageDialog(null, "not find");
        }
        
        updt(drs_name(tru_Q, fls_Q, non_Q), tru, tru);
        
    }
    
    
    
    
    
    
    
    
    
    public void updt(String name,float count,float avg)
    {
        try 
        {
            Connection con=DriverManager.getConnection(URL);
            
            Statement st=con.createStatement();
            
            st.execute("INSERT INTO class_tb(name_class,stu_count,avg_cls) VALUES('"+name+"',"+count+","+avg+");");
            
            st.close();
            
            con.close();
            
            
        } 
        catch (Exception e) 
        {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    
    /* check a Value By db records   */
    
    
    public boolean check_record(String name,String family)
    {
        
        boolean checked=false;
            
        try
        {
            Connection con=DriverManager.getConnection(URL);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("SELECT * FROM stu_tb WHERE name='"+name+"' and family='"+family+"';");
            
            while(rs.next())
            {
                checked=true;
                
                ID_CLS=rs.getString("ID_stu");
                
                all_cnt=rs.getFloat("all_count");
                
                AVG=rs.getFloat("avg");
                
                break;
            }
            
            
            rs.close();
            
            st.close();
            
            con.close();
            
        }
        catch(Exception Error)
        {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }
        return checked;
    }
    
    
    
    
    
    
    
    /* INSERT on if checked not find */
    
    public void insert_st(String name,String family,int fls,int tru,int non,String tru_Q,String fls_Q,String non_Q,String date)
    {
        try 
        {
            
            float all_count=tru-(non+fls);
            
            float avg=all_count/(tru+non+fls);
            
            Connection con=DriverManager.getConnection(URL);
            
            Statement st=con.createStatement();
            
            st.execute("INSERT INTO stu_tb(name,family,avg,all_count) VALUES('"+name+"','"+family+"',"+avg+","+all_count+");");
            
            st.close();
            
            con.close();
            
            Insert_st_tb(name, family, fls, tru, non, tru_Q, fls_Q, non_Q, date);

        } 
        catch (Exception Eror) 
        {
            javax.swing.JOptionPane.showMessageDialog(null, Eror.getMessage());
        }
    }
    
    
    
    
    
    
    /*  update tb stu*/

    public void Update(String name,String family,int fls,int tru,int non,String tru_Q,String fls_Q,String non_Q,String date)
    {
        try 
        {
            float all_count=tru-(non+fls);
            
            float avg=all_count/(tru+fls+non);
            
            all_count=all_count+all_cnt;
            
            Connection con=DriverManager.getConnection(URL);
            
            Statement st=con.createStatement();
            
            String sql="UPDATE stu_tb SET all_count="+all_count+",avg="+avg+",avg_pev="+AVG+" WHERE ID_stu="+ID_CLS+";";
            
            st.execute(sql);
            
            st.close();
            
            con.close();
            
            Insert_st_tb(name, family, fls, tru, non, tru_Q, fls_Q, non_Q, date);
            
        } 
        catch (Exception Eror) 
        {
            javax.swing.JOptionPane.showMessageDialog(null, Eror.getMessage());
        }
    }








    /*** Insert Value to st_tb */
    
    
    public void Insert_st_tb(String name,String family,int fls,int tru,int non,String tru_Q,String fls_Q,String non_Q,String date)
    {
        try 
        {
            
            float all_count=tru-(non+fls);
            
            String CLS_name=drs_name(tru_Q, fls_Q, non_Q);
            
            Connection con=DriverManager.getConnection(URL);
            
            Statement st=con.createStatement();
            
            String sql="INSERT INTO st_tb(ID_stu,all_count,tru_cnt,fls_cnt,non_cnt,fls_id,tru_id,non_id,num,drs_name,date,cls_ID,ID_s) VALUES";

            if(ID_CLS.trim()!="")
                
                sql+="("+ID_CLS+","+all_count+","+tru+","+fls+","+non+",'"+fls_Q+"','"+tru_Q+"','"+non_Q+"',"+tru+",'"+CLS_name+"','"+date+"','"+CLS_name+"',100);";
            
            else
                
                sql+="("+aznum(tru_Q, fls_Q, non_Q)+","+all_count+","+tru+","+fls+","+non+",'"+fls_Q+"','"+tru_Q+"','"+non_Q+"',"+tru+",'"+CLS_name+"','"+date+"','"+CLS_name+"',100);";
                
            
            
            st.execute(sql);
            
            st.close();
            
            con.close();
            
            
        } 
        catch (Exception Eror) 
        {
            javax.swing.JOptionPane.showMessageDialog(null, Eror.getMessage());
        }
    }




    
    int a=1;
    public int aznum(String tru_q,String fls_q,String non_q)
    {
        try{
        File file=new File("conf.strs");
        BufferedReader read=new BufferedReader(new FileReader(file));
        
            int Vals=read.read();
            
            
            Vals++;
            
            a=Vals;
            
            BufferedWriter bwf=new BufferedWriter(new FileWriter(file));
            
            bwf.write(Vals);
            
            bwf.close();
        
        }catch(Exception Error)
        {
            try {
                
            File file=new File("conf.strs");
            
            BufferedWriter bwf=new BufferedWriter(new FileWriter(file));
            
            a++;
            
            bwf.write(a);
            
            bwf.close();
            } catch (Exception e) {
            }
        }
        return a;
    }
    
    
    
    
    
    
    String ID_CLS="";
    
    float all_cnt=0;
    
    float AVG=0;
    
    
   
    
    
    
    public String drs_name(String tru,String fls,String non)
    {
        String nm="";
            
        try
        {
            Connection con=DriverManager.getConnection(URL);
                        
            Statement st=con.createStatement();
            
            String nmm;
            
            if(tru!="")
            {
                String []t=tru.split(";");
                nmm=t[0];
            }
            else if(fls!="")
            {
                String []t=fls.split(";");
                nmm=t[0];
            }
            else
            {
                String []t=non.split(";");
                nmm=t[0];
            }
            
            
            ResultSet rs=st.executeQuery("SELECT * FROM question_tb WHERE ID_Q="+nmm+";");
            
            
            while(rs.next())
            {
                nm=rs.getString("ID_cls");
                break;
            }
            
            
            rs.close();
            
            st.close();
            
            con.close();
            
        }
        catch(Exception Error)
        {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }
        
        return nm;
    }
    
    
    
    
    
    

    public String Datee()
    {
        Calender_Per.cal clae=new cal();
        
        String date=clae.getIranianYear()+"/"+clae.getIranianMonth()+"/"+clae.getIranianDay()+"";
        
        
        
        return date;
    }
    
    
    
    
    
    
    
    
    
    
    
    public String retun_ditalis(String Valuee)
    {
        try
        {
            Connection con=DriverManager.getConnection(URL);
            
            Statement st=con.createStatement();
            
            st.executeQuery("SELECT * FROM stu_tb,st_tb WHERE cls_ID='"+Valuee+"';");
            
            ResultSet rs=st.getResultSet();
            
            String ret="";
            
            while(rs.next())
            {
                ret+=rs.getString("name")+";";
                ret+=rs.getString("family")+";";
                ret+=rs.getString("num")+";";
            }
            
            rs.close();
            
            st.close();
            
            con.close();
            
            return ret;
            
        }
        catch(Exception Error)
        {}
        return "";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public String snd_ditales(String class_name)
    {
        String ret="";
        try
        {
            
            Connection con=DriverManager.getConnection(URL);
            
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("SELECT * FROM st_tb INNER JOIN stu_tb ON st_tb.ID_stu=stu_tb.ID_stu;");
            
            int sst=1;
                
            int counter=1;
            
            int col=rs.getMetaData().getColumnCount();
            
            while(rs.next())
            {
                
                for(counter=1;counter<=(col);counter++)
                {
                    ret+=rs.getString(counter)+"#";
                }
                
            }
            
            st.close();
            
            rs.close();
            
            con.close();
            
        } 
        catch (Exception e) 
        {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return ret;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void thrs_thch()
    {
        
        Thread thr=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    
                                    String Value=datainput_tch.readUTF();
                                    //javax.swing.JOptionPane.showMessageDialog(null,Value);
                                    
                                    if(Value.equals("cls_names"))
                                    {
                                         try
                                        {
                                            database.login.Insert_Q cls_combo=new Insert_Q();
                                
                                            dataout_tch.writeUTF(cls_combo.fill_combo().toString());
                                
                                            
                                        }
                                        catch(Exception Error)
                                        {
                                            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                        }
                                        
                                    }
                                    if(Value.equals("dit"))
                                    {
                                        String Class_name=datainput_tch.readUTF();
                                        
                                        dataout_tch.writeUTF(snd_ditales(Class_name));
                                    
                                    }
                                    if(Value.equals("qus"))
                                    {
                                        Insert_Q_to_db(datainput_tch.readUTF());
                                    }
                                    if(Value.equals("Message"))
                                    {
                                        show_message(datainput_tch.readUTF());
                                    }
                                    if(Value.equals("clk_edt"))                            
                                    {
                                        dataout_tch.writeUTF(snd_questions_eidt());
                                    }
                                    
                                    if(Value.equals("Q_db"))
                                    {
                                        receve_db_by_teacher();
                                    }
                                    
                                    
                                }catch(Exception Error)
                                {
                                    //javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                }
                                
                                
                                
                                
                                
                                               Thread thr2=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    
                                    String Value=datainput_tch.readUTF();
                                    //javax.swing.JOptionPane.showMessageDialog(null,Value);
                                    
                                    if(Value.equals("cls_names"))
                                    {
                                         try
                                        {
                                            database.login.Insert_Q cls_combo=new Insert_Q();
                                
                                            dataout_tch.writeUTF(cls_combo.fill_combo().toString());
                                
                                            
                                        }
                                        catch(Exception Error)
                                        {
                                            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                        }
                                        
                                    }
                                    if(Value.equals("dit"))
                                    {
                                        String Class_name=datainput_tch.readUTF();
                                        
                                        dataout_tch.writeUTF(snd_ditales(Class_name));
                                    
                                    }
                                    if(Value.equals("qus"))
                                    {
                                        Insert_Q_to_db(datainput_tch.readUTF());
                                    }
                                    if(Value.equals("Message"))
                                    {
                                        show_message(datainput_tch.readUTF());
                                    }
                                    if(Value.equals("clk_edt"))                            
                                    {
                                        dataout_tch.writeUTF(snd_questions_eidt());
                                    }
                                    
                                    if(Value.equals("Q_db"))
                                    {
                                        receve_db_by_teacher();
                                    }
                                
                                    
                                    
                                }catch(Exception Error)
                                {
                                    //javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                }
                                
                                
                                
                                
                                
                                               Thread thr3=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    
                                    String Value=datainput_tch.readUTF();
                                    //javax.swing.JOptionPane.showMessageDialog(null,Value);
                                    
                                    if(Value.equals("cls_names"))
                                    {
                                         try
                                        {
                                            database.login.Insert_Q cls_combo=new Insert_Q();
                                
                                            dataout_tch.writeUTF(cls_combo.fill_combo().toString());
                                
                                            
                                        }
                                        catch(Exception Error)
                                        {
                                            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                        }
                                        
                                    }
                                    if(Value.equals("dit"))
                                    {
                                        String Class_name=datainput_tch.readUTF();
                                        
                                        dataout_tch.writeUTF(snd_ditales(Class_name));
                                    
                                    }
                                    if(Value.equals("qus"))
                                    {
                                        Insert_Q_to_db(datainput_tch.readUTF());
                                    }
                                    if(Value.equals("Message"))
                                    {
                                        show_message(datainput_tch.readUTF());
                                    }
                                    
                                    if(Value.equals("Q_db"))
                                    {
                                        receve_db_by_teacher();
                                    }
                            
                                }catch(Exception Error)
                                {
                                    //javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                }
                        
                                
                                
                                
                                
                                
                                 Thread thr4=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    
                                    String Value=datainput_tch.readUTF();
                                    //javax.swing.JOptionPane.showMessageDialog(null,Value);
                                    
                                    if(Value.equals("cls_names"))
                                    {
                                         try
                                        {
                                            database.login.Insert_Q cls_combo=new Insert_Q();
                                
                                            dataout_tch.writeUTF(cls_combo.fill_combo().toString());
                                
                                            
                                        }
                                        catch(Exception Error)
                                        {
                                            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                        }
                                        
                                    }
                                    if(Value.equals("dit"))
                                    {
                                        String Class_name=datainput_tch.readUTF();
                                        
                                        dataout_tch.writeUTF(snd_ditales(Class_name));
                                    
                                    }
                                    if(Value.equals("qus"))
                                    {
                                        Insert_Q_to_db(datainput_tch.readUTF());
                                    }
                                    
                                    if(Value.equals("Q_db"))
                                    {
                                        receve_db_by_teacher();
                                    }
                                
                                    
                                    
                                }catch(Exception Error)
                                {
                                    //javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                }
                                
                                
                                
                                
                                
                                
                                 Thread thr5=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    
                                    String Value=datainput_tch.readUTF();
                                    //javax.swing.JOptionPane.showMessageDialog(null,Value);
                                    
                                    if(Value.equals("cls_names"))
                                    {
                                         try
                                        {
                                            database.login.Insert_Q cls_combo=new Insert_Q();
                                
                                            dataout_tch.writeUTF(cls_combo.fill_combo().toString());
                                
                                            
                                        }
                                        catch(Exception Error)
                                        {
                                            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                        }
                                        
                                    }
                                    if(Value.equals("dit"))
                                    {
                                        String Class_name=datainput_tch.readUTF();
                                        
                                        dataout_tch.writeUTF(snd_ditales(Class_name));
                                    
                                    }
                                    if(Value.equals("qus"))
                                    {
                                        Insert_Q_to_db(datainput_tch.readUTF());
                                    }
                                    
                                    if(Value.equals("Q_db"))
                                    {
                                        receve_db_by_teacher();
                                    }
                                    
                                
                                    
                                    
                                }catch(Exception Error)
                                {
                                    //javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                }
                                
                                
                                
                                
                                
                                 Thread thr6=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    
                                    String Value=datainput_tch.readUTF();
                                    //javax.swing.JOptionPane.showMessageDialog(null,Value);
                                    
                                    if(Value.equals("cls_names"))
                                    {
                                         try
                                        {
                                            database.login.Insert_Q cls_combo=new Insert_Q();
                                
                                            dataout_tch.writeUTF(cls_combo.fill_combo().toString());
                                
                                            
                                        }
                                        catch(Exception Error)
                                        {
                                            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                        }
                                        
                                    }
                                    if(Value.equals("dit"))
                                    {
                                        String Class_name=datainput_tch.readUTF();
                                        
                                        dataout_tch.writeUTF(snd_ditales(Class_name));
                                    
                                    }
                                    if(Value.equals("qus"))
                                    {
                                        Insert_Q_to_db(datainput_tch.readUTF());
                                    }
                                    
                                    if(Value.equals("Q_db"))
                                    {
                                        receve_db_by_teacher();
                                    }
                                    
                                
                                    
                                    
                                }catch(Exception Error)
                                {
                                    //javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                }
                                
                                
                                
                                
                                
                                
                                 Thread thr7=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    
                                    String Value=datainput_tch.readUTF();
                                    //javax.swing.JOptionPane.showMessageDialog(null,Value);
                                    
                                    if(Value.equals("cls_names"))
                                    {
                                         try
                                        {
                                            database.login.Insert_Q cls_combo=new Insert_Q();
                                
                                            dataout_tch.writeUTF(cls_combo.fill_combo().toString());
                                
                                            
                                        }
                                        catch(Exception Error)
                                        {
                                            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                        }
                                        
                                    }
                                    if(Value.equals("dit"))
                                    {
                                        String Class_name=datainput_tch.readUTF();
                                        
                                        dataout_tch.writeUTF(snd_ditales(Class_name));
                                    
                                    }
                                    if(Value.equals("qus"))
                                    {
                                        Insert_Q_to_db(datainput_tch.readUTF());
                                    }
                                    
                                    if(Value.equals("Q_db"))
                                    {
                                        receve_db_by_teacher();
                                    }
                                    
                                
                                    
                                    
                                }catch(Exception Error)
                                {
                                    //javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                }
                                
                                
                                
                                
                                
                                
                                
                                 Thread thr8=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    
                                    String Value=datainput_tch.readUTF();
                                    //javax.swing.JOptionPane.showMessageDialog(null,Value);
                                    
                                    if(Value.equals("cls_names"))
                                    {
                                         try
                                        {
                                            database.login.Insert_Q cls_combo=new Insert_Q();
                                
                                            dataout_tch.writeUTF(cls_combo.fill_combo().toString());
                                
                                            
                                        }
                                        catch(Exception Error)
                                        {
                                            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                        }
                                        
                                    }
                                    if(Value.equals("dit"))
                                    {
                                        String Class_name=datainput_tch.readUTF();
                                        
                                        dataout_tch.writeUTF(snd_ditales(Class_name));
                                    
                                    }
                                    if(Value.equals("qus"))
                                    {
                                        Insert_Q_to_db(datainput_tch.readUTF());
                                    }
                                    
                                    if(Value.equals("Q_db"))
                                    {
                                        receve_db_by_teacher();
                                    }
                                    
                                
                                    
                                    
                                }catch(Exception Error)
                                {
                                   // javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                }
                                
                                
                                
                                
                                for(int a=0;a<7;a++)
                                {
                                     Thread thr9=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    
                                    String Value=datainput_tch.readUTF();
                                    //javax.swing.JOptionPane.showMessageDialog(null,Value);
                                    
                                    if(Value.equals("cls_names"))
                                    {
                                         try
                                        {
                                            database.login.Insert_Q cls_combo=new Insert_Q();
                                
                                            dataout_tch.writeUTF(cls_combo.fill_combo().toString());
                                
                                            
                                        }
                                        catch(Exception Error)
                                        {
                                            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                        }
                                        
                                    }
                                    if(Value.equals("dit"))
                                    {
                                        String Class_name=datainput_tch.readUTF();
                                        
                                        dataout_tch.writeUTF(snd_ditales(Class_name));
                                    
                                    }
                                    if(Value.equals("qus"))
                                    {
                                        Insert_Q_to_db(datainput_tch.readUTF());
                                    }
                                    
                                    
                                    if(Value.equals("Q_db"))
                                    {
                                        receve_db_by_teacher();
                                    }
                                    
                                
                                    
                                    
                                }catch(Exception Error)
                                {
                                    //javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
                                }
                            }
                        });
                        
                                                thr9.start();
                        
                                }
                                
                                
                                
                            }
                        });
                        
                                                thr8.start();
                        
                                
                                
                                
                                
                                
                                
                                
                            }
                        });
                        
                                                thr7.start();
                        
                                
                                
                                
                                
                                
                            }
                        });
                        
                                                thr6.start();
                        
                                
                                
                                
                                
                                
                                
                            }
                        });
                        
                                                thr5.start();
                        
                                
                                
                                
                                
                                
                                
                                
                                
                            }
                        });
                        
                                                thr4.start();
                        
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                            }
                        });
                        
                                                thr3.start();
                        
                                
                                
                            }
                        });
                        
                                                thr2.start();
                        
         
                                
                                
                            }
                        });
                        
                        thr.start();
    }
    
    
    
    
    
    
    
    
    
    
    
    public String snd_questions_eidt()
    {
        
        String ret_Val="";
        
        try
        {
            
            Connection con=DriverManager.getConnection(URL);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("SELECT * FROM question_tb WHERE ID_cls!='';");
            
            while(rs.next())
            {
                
                ret_Val+=rs.getString("ID_Q")+"#";
                ret_Val+=rs.getString("qus")+"#";
                ret_Val+=rs.getString("ans_one")+"#";
                ret_Val+=rs.getString("ans_two")+"#";
                ret_Val+=rs.getString("ans_thr")+"#";
                ret_Val+=rs.getString("ans_for")+"#";
                ret_Val+=rs.getString("ans")+"#";
                ret_Val+=rs.getString("ID_cls")+"#";
                ret_Val+="^";
                
                
            }
            
            
            rs.close();
            
            st.close();
            
            con.close();
            
            
            
        }
        catch(Exception Error)
        {
            
        }
        return ret_Val;
    }
    
    
    
    
    
    
    
    
    
    
    public void receve_db_by_teacher()
    {
        try
        {
            database.login.Insert_Q db=new Insert_Q();
            
            FileOutputStream my_db=new FileOutputStream(new File("db_tech.sqlite"));
            
            byte [] recive_file=new byte[99999];
            
            datainput_tch.read(recive_file);
            
            my_db.write(recive_file);
            
            my_db.close();
            
            db.insert_from_resived_db();
            
            
        }
        catch(Exception Error)
        {
            System.out.println("Error");
        }
        
        
    }
    
    
    
    
    
    
    
    
    
    
    public void show_message(String Val)
    {
        try
        {
         
            
            javax.swing.JOptionPane.showMessageDialog(null, Val);
            
            
            
            //al.show();
            
            
               
//            database.login.Socket_s.Message_From.Message_VAL=Val;
//         
//            database.login.Socket_s.Message_From st=new database.login.Socket_s.Message_From();
//            
//            st.show();
        }
        catch(Exception Error)
        {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }
    }
    
    
    
    
    
    
}
