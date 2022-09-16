package database.login;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;


public class Q_View 
{
    
    
    private String URL="jdbc:sqlite:db_az.sqlite";
    
    String rete="";
    
    public String Val(String where)
    {
        
        try 
        {
            
            
            Connection con=DriverManager.getConnection(URL);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("SELECT * FROM question_tb WHERE ID_cls='"+where+"';");
            
            while(rs.next())
            {
                rete+=rs.getString("qus")+";";
                rete+=rs.getString("ans_one")+";";
                rete+=rs.getString("ans_two")+";";
                rete+=rs.getString("ans_thr")+";";
                rete+=rs.getString("ans_for")+";";
                rete+=rs.getString("ans")+";";
            }
            
            
            rs.close();
            
            st.close();
            
            con.close();
            
        }
        catch (Exception e)
        {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
        
        return rete;
        
    }
    
    
    
    
    public void delete(String val)
    {
        try 
        {
            Connection con=DriverManager.getConnection(URL);
            
            Statement st=con.createStatement();
            
            st.execute("DELETE FROM question_tb WHERE qus='"+val+"';");
            
            st.close();
            
            con.close();
            
        } 
        catch (Exception e) 
        {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    
}
