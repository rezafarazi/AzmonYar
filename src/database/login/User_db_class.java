package database.login;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class User_db_class 
{
    
    
    private String URL="jdbc:sqlite:db_az.sqlite";
     
     
     public void Insert_User(String user,String password,String smt,String shl)
     {
         
         try
         {
             Connection con=DriverManager.getConnection(URL);
             
             Statement st=con.createStatement();
             
             st.execute("INSERT INTO teacher_tb(name,pass,smat,shl) VALUES('"+user+"','"+password+"','"+smt+"','"+shl+"');");
             
             st.close();
            
             con.close();
             
             
         }
         catch(Exception Error)
         {
             javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage().toString());
         }
         
     }
     
     
     
     
     
     
     
     
     
     public void Delete_User(String user,String password)
     {
         
         try
         {
             Connection con=DriverManager.getConnection(URL);
             
             Statement st=con.createStatement();
             
             st.execute("DELETE FROM teacher_tb WHERE name='"+user+"' and pass='"+password+"';");
             
             st.close();
            
             con.close();
             
             
         }
         catch(Exception Error)
         {
             javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage().toString());
         }
         
     }
     
    
     
     
     
     
     
     
     
     
     public void Update_User(String user,String password,int id)
     {
         
         try
         {
             Connection con=DriverManager.getConnection(URL);
             
             Statement st=con.createStatement();
             
             st.execute("UPDATE teacher_tb SET name='"+user+"',pass='"+password+"' WHERE ID_tech="+id+";");
             
             st.close();
            
             con.close();
             
             
         }
         catch(Exception Error)
         {
             javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage().toString());
         }
         
     }
     
     
     
     
     
     
     
     
     
     
     int find_id(String User,String Pass)throws Exception
     {
        
         Connection con=DriverManager.getConnection(URL);
         
         Statement st=con.createStatement();
         
         st.executeQuery("SELECT * FROM teacher_tb");
         
         ResultSet rs=st.getResultSet();
         
         String id_str;
         
         int idd;
                 
         while(rs.next())
         {
         
             if(rs.getString("name")==User&&rs.getString("pass")==Pass)
             {
                 
                 idd=Integer.parseInt(rs.getString("ID_tech"));
                 
                 rs.close();
                 
                 st.close();
                 
                 con.close();
                 
                 return idd;
                 
             }
         }
         
         
         rs.close();
                 
         st.close();
                 
         con.close();
         
        return 0; 
     }
     
     
     
     
     
     
     
     
     
     
     public ObservableList quary(String sql)
    {
        try 
        {
            
//            Connection con=DriverManager.getConnection(URL);
//            Statement st=con.createStatement();
//            st.executeQuery(sql);
//            ResultSet rs=st.getResultSet();
//            ResultSetMetaData rsmd=rs.getMetaData();
//            int col=rsmd.getColumnCount();
//            
//            ObservableList data=FXCollections.observableArrayList();
//            
//            while(rs.next())
//            {
//                
//                ObservableList row=FXCollections.observableArrayList();
//                
//                for(int a=0;a<col;a++)
//                {
//                    row.add(rs.getString(a+1));
//                    javax.swing.JOptionPane.showMessageDialog(null, rs.getString(a+1));
//                }
//                
//                data.add(row);
//                
//                
//            }
//            
//            
//            
//            
//            
//            rs.close();
//            
//            st.close();
//            
//            con.close();
//            
            
            
            
            
            
            
            
            
            
            return null;
            
        }
        catch (Exception e) 
        {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
        
        return null;
        
    }
     
     
     
     
     
     
     
     
     public DefaultTableModel quary_show()
     {
         try
         {
             
             Connection con=DriverManager.getConnection(URL);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("SELECT * FROM teacher_tb;");
            
            DefaultTableModel dt=new DefaultTableModel();
            
            ResultSetMetaData rsmd=rs.getMetaData();
            
            int col=rsmd.getColumnCount();
            
            dt.addColumn("شماره کاربری");
            dt.addColumn("نام کاربری");
            dt.addColumn("رمز عبور");
            
            while(rs.next())
            {
                Object []data=new Object[col];
                
                for(int a=0;a<col;a++)
                {
                    data[a]=rs.getString(a+1);
                }
                
                dt.addRow(data);
            }
            
            
            rs.close();
            
            st.close();
            
            con.close();
        
            return dt;
         }
         catch(Exception Error)
         {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
         }
         
         return null;
     }
     
     
     
     
     
     
     
     public ArrayList<String> table_view()
     {
         
         try
         {
            ArrayList<String> lst=new ArrayList<>();
             
            Connection con=DriverManager.getConnection(URL);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("SELECT * FROM teacher_tb WHERE NOT delete_flag IS 'true';");
            
            ResultSetMetaData rsmd=rs.getMetaData();
            
            int col=rsmd.getColumnCount();
            
            
            while(rs.next())
            {
                
                lst.add(rs.getString("ID_tech"));
                
                lst.add(rs.getString("name"));
                
                lst.add(rs.getString("pass"));
                
                lst.add(rs.getString("smat"));
                
                lst.add(rs.getString("shl"));
                
            }
            
            
            rs.close();
            
            st.close();
            
            con.close();
        
            return lst;
         }
         catch(Exception Error)
         {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
         }
         return null;
         
     }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     public void delete_user(String ID)
     {
         try 
         {
             Connection con=DriverManager.getConnection(URL);
             
             Statement st=con.createStatement();
             
             st.execute("UPDATE teacher_tb SET delete_flag='true' WHERE ID_tech="+ID+";");
             
             st.close();
             
             con.close();
         } 
         catch (Exception e) 
         {
             javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
         }
     }
     
     
     
     
     
     public void retext_user(String ID,String Name,String Password)
     {
         try 
         {
             Connection con=DriverManager.getConnection(URL);
             
             Statement st=con.createStatement();
             
             st.execute("UPDATE teacher_tb SET name="+Name+",pass="+Password+" WHERE ID_tech="+ID+";");
             
             st.close();
             
             con.close();
         } 
         catch (Exception e) 
         {
             javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
         }
     }
     
     
     
     
    
}
