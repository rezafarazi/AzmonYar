package database.login;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Edit_Q_db_cls 
{
    
    private String URL="jdbc:sqlite:db_az.sqlite";
    
    private String rete="";
    private String a;
    
    public String fill_combo_edit(String cls_name)
    {
        
        try 
        {
            String strval="";
            
            Connection con=DriverManager.getConnection(URL);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery(cls_name);
            
            while(rs.next())
            {
                
                strval+=rs.getString("qus")+";";
                strval+=rs.getString("ans_one")+";";
                strval+=rs.getString("ans_two")+";";
                strval+=rs.getString("ans_thr")+";";
                strval+=rs.getString("ans_for")+";";
                strval+=rs.getString("ans")+";";
                
            }
            
            
            rs.close();
            
            st.close();
            
            con.close();
            
            return strval;
            
        }
        catch (Exception e)
        {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
        
        return null;
        
    }
    
    
    
    
    
    public ObservableList<String> fill_combo_cla_combo()
    {
        
        try 
        {
            Connection con=DriverManager.getConnection(URL);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("SELECT * FROM class_tb;");
            
            ObservableList<String> val=FXCollections.observableArrayList();
            
            while(rs.next())
            {
                
                val.add(rs.getString("name_class"));
                
            }
            
            
            rs.close();
            
            st.close();
            
            con.close();
            
            return val;
            
        }
        catch (Exception e)
        {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
        
        return null;
        
    }
    
    
    
    public void Update(String Q,String j1,String j2,String j3,String j4,String an)
    {
        
        try 
        {
            Connection con=DriverManager.getConnection(URL);
            Statement st=con.createStatement();
            String sql="UPDATE question_tb SET ans_one='"+j1+"',ans_two='"+j2+"',ans_thr='"+j3+"',ans_for='"+j4+"',ans='"+an+"' WHERE qus='"+Q+"';";
            st.execute(sql);
            
            st.close();
            con.close();
        } 
        catch (Exception e) 
        {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
    
    
}
