package database.login;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Insert_Q 
{
 
     private String URL="jdbc:sqlite:db_az.sqlite";
     
     
     public void Insert_Question(String question,String ans_one,String ans_two,String ans_thr,String ans_for,String ans,String cls)
     {
         
         try
         {
             Connection con=DriverManager.getConnection(URL);
             
             Statement st=con.createStatement();
             
             st.execute("INSERT INTO question_tb(qus,ans_one,ans_two,ans_thr,ans_for,ans,ID_cls,ID_lin) VALUES('"+question+"','"+ans_one+"','"+ans_two+"','"+ans_thr+"','"+ans_for+"','"+ans+"','"+cls+"',"+String_Val+");");
             
             st.close();
            
             con.close();
             
             
         }
         catch(Exception Error)
         {
             javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage().toString());
         }
         
     }
    
     
     
     int a=0;
     
     public static String String_Val;
     
     
     public static boolean bls=false;
     
     
     public Insert_Q()
     {
         
         if(bls)
         {
         try
         {
             
             File file=new File("id.config");
             
             
             boolean f=file.createNewFile();
             
             
             if(f)
             {
             
                 String_Val="1";
                 
                 BufferedWriter bfw=new BufferedWriter(new FileWriter(file));
                 
                 bfw.write(String_Val);
                 
                 bfw.close();
                 
             }
             else
             {
                 BufferedReader bfr=new BufferedReader(new  FileReader(file));
                 
                 String Valll=bfr.readLine();
                 
                 int i=Integer.parseInt(Valll);
                 
                 i++;
                 
                 String_Val=i+"";
                 
                 BufferedWriter bfw=new BufferedWriter(new FileWriter(file));
                 
                 bfw.write(i+"");
                 
                 bfw.close();
                 
                 bfr.close();
                 
             }
             
             bls=false;
             
         }
         catch(Exception Error)
         {
             
         }
         }
         
     }
     
     
     
     
     
     public void end_question(int class_ID,int lim_time,int saat_start,int Daq_lim,String date)
     {
         try
         {
             Connection con=DriverManager.getConnection(URL);
             
             Statement st=con.createStatement();
             
             st.execute("INSERT INTO limQ_tb(class_ID,lim_time,saat_start,Daq_lim,date,ID_Q) VALUES("+class_ID+","+lim_time+","+saat_start+","+Daq_lim+",'"+date+"',"+String_Val+");");
             
             st.close();
            
             con.close();
             
             
         }
         catch(Exception Error)
         {
             javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage().toString());
         }
     }
    
    
     
     
     
     public void end_question_new_function(int class_ID,int lim_time,int saat_start,int Daq_lim,String date)
     {
         try
         {
             Connection con=DriverManager.getConnection(URL);
             
             Statement st=con.createStatement();
             
             st.execute("INSERT INTO limQ_tb(class_ID,lim_time,saat_start,Daq_lim,date) VALUES("+class_ID+","+lim_time+","+saat_start+","+Daq_lim+",'"+date+"');");
             
             st.close();
            
             con.close();
             
             
         }
         catch(Exception Error)
         {
             javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage().toString());
         }
     }
    
     
     
     
     
   
     
     public ObservableList<String> fill_combo_box()
     {
         
         ObservableList<String> lst=FXCollections.observableArrayList();
         
         try
         {
            
             fill_combo();
             
             String []Value=rete.split(";");
            
             for(int a=0;a<Value.length;a++)
             {
                 lst.add(Value[a]);
             }
             
             return lst;
             
         }
         catch(Exception Error)
         {
             javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
         }
         
         
         return lst;
     }
     
     
     String rete="";
     
     public String fill_combo()
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
            
            return rete;
        }
        catch(Exception Error)
        {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }
        return null;
    }
     
     
    
    public void Insert_Question_new_function(String question,String ans_one,String ans_two,String ans_thr,String ans_for,String ans,String cls,String type)
    {
         
         try
         {
             Connection con=DriverManager.getConnection(URL);
             
             Statement st=con.createStatement();
             
             st.execute("INSERT INTO question_tb(qus,ans_one,ans_two,ans_thr,ans_for,ans,ID_cls,type) VALUES('"+question+"','"+ans_one+"','"+ans_two+"','"+ans_thr+"','"+ans_for+"','"+ans+"','"+cls+"','"+type+"');");
             
             st.close();
            
             con.close();
             
             
         }
         catch(Exception Error)
         {
             javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage().toString());
         }
         
     } 
    
    
    
    
    
    public void Insert_Question_new_function_img(String question,String ans_one,String ans_two,String ans_thr,String ans_for,String ans,String cls,String type,String imgaddress)
    {
         
         try
         {
             Connection con=DriverManager.getConnection(URL);
             
             PreparedStatement st=con.prepareStatement("INSERT INTO question_tb(qus,ans_one,ans_two,ans_thr,ans_for,ans,ID_cls,type,image) VALUES(?,?,?,?,?,?,?,?,?);");
             
             //st.execute("INSERT INTO question_tb(qus,ans_one,ans_two,ans_thr,ans_for,ans,ID_cls,type,image) VALUES('"+question+"','"+ans_one+"','"+ans_two+"','"+ans_thr+"','"+ans_for+"','"+ans+"','"+cls+"','"+type+"');");
             
             st.setString(1, question);
             
             st.setString(2, ans_one);
             st.setString(3, ans_two);
             st.setString(4, ans_thr);
             st.setString(5, ans_for);
             st.setString(6, ans);
             st.setString(7, cls);
             st.setString(8, type);
             st.setBlob(9, new FileInputStream(new File(imgaddress)));
             
             
             st.executeUpdate();
             
             
             st.close();
            
             con.close();
             
             
         }
         catch(Exception Error)
         {
             javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage().toString());
         }
         
     } 
    
    
    
    
    
    
    
    
    
    
    
    public void insert_from_resived_db()
    {
        try 
        {
            
            
            Connection conn = DriverManager.getConnection("jdbc:sqlite:db_tech.sqlite");
            
            Statement st = conn.createStatement();
    
            ResultSet rs = st.executeQuery("SELECT * FROM question_tb");
            
            
            
            ArrayList<String> val=new ArrayList<>();
            ArrayList<Blob>img=new ArrayList<>();
    
            while(rs.next())
            {
    
                
                val.add(rs.getString("qus"));
                val.add(rs.getString("ans_one"));
                val.add(rs.getString("ans_two"));
                val.add(rs.getString("ans_thr"));
                val.add(rs.getString("ans_for"));
                val.add(rs.getString("ans"));
                val.add(rs.getString("ID_cls"));
                val.add(rs.getString("type"));
                val.add(rs.getString("image"));
                
                
            }
            
            
            
            for(int a=0;a<val.size();a+=8)
            {
                
                Connection con2=DriverManager.getConnection(URL);
            
                PreparedStatement st2=con2.prepareStatement("INSERT INTO question_tb (qus,ans_one,ans_two,ans_thr,ans_for,ans,ID_cls,type,image) VALUES(?,?,?,?,?,?,?,?,?);");

                st2.setString(1,val.get(a) );
                
                st2.setString(2,val.get(a+1) );
                
                st2.setString(3,val.get(a+2) );
                st2.setString(4,val.get(a+3) );
                st2.setString(5,val.get(a+4) );
                st2.setString(6,val.get(a+5) );
                st2.setString(7,val.get(a+6) );
                st2.setString(8,val.get(a+7) );
                st2.setString(9,val.get(a+8) );
                
                
                st2.executeUpdate();
                
                st2.close();
            
                con2.close();
            
            }
            rs.close();
            
            st.close();
            
            conn.close();
            
            insert_from_resived_db_part_2();
            
        } 
        catch (Exception Error) 
        {
            System.out.println(Error.getMessage());
        }
    }
    
    
    
    
    
    public void insert_from_resived_db_part_2()
    {
        try
        {
        
            
        Connection conn = DriverManager.getConnection("jdbc:sqlite:db_tech.sqlite");
        
        Statement st = conn.createStatement();
    
        ResultSet rs = st.executeQuery("SELECT * FROM limQ_tb");
        
        ArrayList<String> vals=new ArrayList<>();
        
        while(rs.next())
        {
            vals.add(rs.getString("class_ID"));
            vals.add(rs.getString("lim_time"));
            vals.add(rs.getString("saat_start"));
            vals.add(rs.getString("Daq_lim"));
            vals.add(rs.getString("date"));
        }
        
        rs.close();
        
        st.close();
        
        conn.close();
        
        
        for(int a=0;a<vals.size();a+=5)
        {
            
            Connection con3=DriverManager.getConnection(URL);
        
            Statement st3=con3.createStatement();
            st3.execute("INSERT INTO limQ_tb(class_ID,lim_time,saat_start,Daq_lim,date) VALUES("+vals.get(a)+","+vals.get(a+1)+","+vals.get(a+2)+","+vals.get(a+3)+",'"+vals.get(a+4)+"');");
        
        
            st3.close();
        
            con3.close();
        }
        
        }catch(Exception Error)
        {
            System.out.println("Error2");
        }
        
    }
    
    
    
}
