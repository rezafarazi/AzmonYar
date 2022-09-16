package database.login;



import java.sql.*;
import java.text.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import sun.util.calendar.BaseCalendar;


public class Class_db 
{
    
    
    private String URL="jdbc:sqlite:db_az.sqlite";

    private static String URLl="jdbc:sqlite:db_az.sqlite";
    
    
    public void Insert_Class(String class_name,int stu_Count)
    {
        try
        {
            Connection con=DriverManager.getConnection(URL);
            
            Statement st=con.createStatement();
            
            st.execute("Insert into class_tb (name_class,stu_count) Values('"+class_name+"',"+stu_Count+");");
            
            st.close();
            
            con.close();
            
                    
        }
        catch(Exception Error)
        {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }
        
    }
    
    
    
    public void Delete()
    {
        try
        {
            Connection con;
        Statement st ;
        
            
        
            GregorianCalendar cal=new GregorianCalendar();
        
            
            String dt=cal.get(Calendar.YEAR)+"_"+cal.get(Calendar.MONTH)+"_"+cal.get(Calendar.DAY_OF_MONTH);
            
            //javax.swing.JOptionPane.showMessageDialog(null, dt);
            
            con=DriverManager.getConnection(URL);
            
            st=con.createStatement();
            
            st.execute("create table bakup_"+dt+"(id_class Integer,name_class nvarchar(50),stu_count Integer,avg_cls float,ID_st_one Integer,ID_st_two Integer,ID_st_thr Integer);");
            
            st.execute("Insert into bakup_"+dt+" Select * from class_tb;");
            
            
            st.execute("Delete from class_tb;");
            
            st.close();
            
            con.close();
            

        }
        catch(Exception Error)
        {
            
        }
        
        
        
        
        
           
        
    }
    
    
    
    
    public static void Delete_one(String Val)
    {
        try
        {
            Connection con;
            Statement st ;
        
            
            con=DriverManager.getConnection(URLl);
            
            st=con.createStatement();
            
            st.execute("Insert into delete_cls_tb Select * from class_tb WHERE ID_class='"+Val+"';");
            
            
            st.execute("Delete from class_tb WHERE ID_class="+Val+";");
            
            st.close();
            
            con.close();
            

        }
        catch(Exception Error)
        {
            
        }
        
        
        
        
    }
    
    
    
    
    public static void Edit(String class_name,int stu_Count,String ID)
    {
        try
        {
            Connection con=DriverManager.getConnection(URLl);
            
            Statement st=con.createStatement();
            
            st.execute("Update class_tb set stu_count="+stu_Count+",name_class='"+class_name+"' where ID_class='"+ID+"';");
            
            st.close();
            
            con.close();
            
                    
        }
        catch(Exception Error)
        {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }
        
    }
    
    
    
    
    
    
    public static void Delete_Flag(String ID)
    {
        try
        {
            Connection con=DriverManager.getConnection(URLl);
            
            Statement st=con.createStatement();
            
            st.execute("Update class_tb set delete_falg='true' where ID_class='"+ID+"';");
            
            st.close();
            
            con.close();
            
                    
        }
        catch(Exception Error)
        {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }
        
    }
    
    
    
    
    
    
    
    
    public DefaultTableModel quary()
    {
        try
        {
            Connection con=DriverManager.getConnection(URLl);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("SELECT ID_class,name_class,stu_count,avg_cls FROM class_tb;");
            
            DefaultTableModel dt=new DefaultTableModel();
            
            ResultSetMetaData rsmd=rs.getMetaData();
            
            int col=rsmd.getColumnCount();
            
            dt.addColumn("شماره کلاس");
            dt.addColumn("نام کلاس");
            dt.addColumn("تعداد دانش آموزان");
            dt.addColumn("معدل کلاس");
            
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
            
        }
        return null;
    }
    
    
    
    
    
    
    public ArrayList<String> quary_lst(String quary)
    {
        try
        {
            Connection con=DriverManager.getConnection(URLl);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery(quary);
            
            DefaultTableModel dt=new DefaultTableModel();
            
            ResultSetMetaData rsmd=rs.getMetaData();
            
            int col=rsmd.getColumnCount();
            
            ArrayList<String> lst=new ArrayList<>();
            
            while(rs.next())
            {
                lst.add(rs.getString(1));
                lst.add(rs.getString(2));
                lst.add(rs.getString(3));
                
                String avg=rs.getString(4);
                
                if(avg!=null)
                {
                    lst.add(avg);
                }
                else
                {
                    lst.add("-");
                }
                
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
    
    
    
}
