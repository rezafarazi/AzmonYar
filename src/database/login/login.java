package database.login;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class login 
{
    
    private String URL="jdbc:sqlite:db_az.sqlite";
    
    private String rete="";
    
    public String quary(String sql)
    {
        
        try 
        {
            Path Main_path=Paths.get("db_az.sqlite");
            
            Path Main_path_two=Paths.get("db_az_2.sqlite");
            
            Path Main_path_thr=Paths.get("db_az_3.sqlite");
            
            Path Main_path_for=Paths.get("db_az_4.sqlite");
            
            
            
            File file_two=new File("db_az_2.sqlite");
            
            File file_thr=new File("db_az_3.sqlite");
            
            File file_for=new File("db_az_4.sqlite");
            
            if(file_two.exists())
            {
                file_two.delete();
            }
            
            if(file_thr.exists())
            {
                file_thr.delete();
            }
            
            if(file_for.exists())
            {
                file_for.delete();
            }
            
            
            
            Files.copy(Main_path, Main_path_two);
            
            Files.copy(Main_path, Main_path_thr);
            
            Files.copy(Main_path, Main_path_for);
            
            Connection con=DriverManager.getConnection(URL);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next())
            {
                rete+=rs.getString("name")+";";
                rete+=rs.getString("pass")+";";
                
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
    
    
    
    
    public String quary_tch(String sql)
    {
        
        try 
        {
            
            
            Connection con=DriverManager.getConnection(URL);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next())
            {
                rete+=rs.getString("name")+";";
                rete+=rs.getString("pass")+";";
                rete+=rs.getString("smat")+";";
                rete+=rs.getString("shl")+";";
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
    
    
    
    
    
    
    
    
    
    
    
    
    
}
