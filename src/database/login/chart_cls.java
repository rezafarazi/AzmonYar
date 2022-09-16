package database.login;

import java.sql.*;
import java.util.ArrayList;

public class chart_cls 
{
    
    
    private String URL="jdbc:sqlite:db_az_2.sqlite";
    private String URL2="jdbc:sqlite:db_az_2.sqlite";
    
    
    String rete="";
    
    public String class_chart()
    {
        try
        {
            
            
            Connection con=DriverManager.getConnection(URL);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("SELECT name_class,AVG(avg_cls) FROM class_tb GROUP BY name_class;");
            
            while(rs.next())
            {
                
                String Vall=rs.getString("name_class");
                //add_all_cnt(Vall);
                rete+=Vall+";";
                rete+=rs.getString("AVG(avg_cls)")+";";
    
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
    
    
    
    
    
    
    
    
    
    
    String castum="";
    
    public String castum_chart(String class_name)
    {
        
         try
        {
            
            
            Connection con=DriverManager.getConnection(URL2);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("SELECT * FROM stu_tb INNER JOIN st_tb ON stu_tb.ID_stu=st_tb.ID_stu where cls_ID='"+class_name+"';");
            
            while(rs.next())
            {
                
                String Vall=rs.getString("name");
                //add_all_cnt(Vall);
                castum+=rs.getString("family")+";";
                castum+=rs.getString("num")+";";
    
            }
            
            rs.close();
            
            st.close();
            
            con.close();
            
            
            return castum;
        }
        catch(Exception Error)
        {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }
        return null;
    }
    
    
    
    
    
    
    
    
    
    public ArrayList<String> class_chart_array()
    {
        try
        {
            
            
            ArrayList<String> array=new ArrayList<>();
            
            
            Connection con=DriverManager.getConnection(URL);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("SELECT name_class,AVG(avg_cls) FROM class_tb GROUP BY name_class ORDER BY AVG(avg_cls) DESC;");
            
            while(rs.next())
            {
                
                array.add(rs.getString("name_class"));
                array.add((rs.getString("AVG(avg_cls)")!=null)?rs.getString("AVG(avg_cls)"):"-");
                
    
            }
            
            rs.close();
            
            st.close();
            
            con.close();
            
            
            return array;
        }
        catch(Exception Error)
        {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }
        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    public void add_all_cnt(String Val)
//    {
//        
//        try{
//           Connection con=DriverManager.getConnection(URL);
//                        
//            Statement st=con.createStatement();
//            
//            ResultSet rs=st.executeQuery("SELECT * FROM st_tb Where cls_ID='"+Val+"';");
//            
//            int alll=0;
//            
//            int cnt=0;
//            
//            while(rs.next())
//            {
//                cnt++;
//                alll+=Integer.parseInt(rs.getString("num"));
//            }
//            
//            float avg=0;
//            
//            if(cnt!=0)
//                avg=alll/cnt;
//            
//           
//            
//            rs.close();
//            
//            st.close();
//            
//            con.close();
//        
//            Inset(avg+"", Val,alll+"");
//        
//        }
//        catch(Exception Error)
//        {
//            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
//        }
//        
//    }
//    
//    
//    
//    
//    
//    
//    public void Inset(String avg,String VAL,String n)
//    {
//        try
//        {
//            
//            Connection con=DriverManager.getConnection(URL);
//            
//            Statement st=con.createStatement();
//            
//            String SQL="UPDATE class_tb SET avg_cls="+avg+",stu_count="+n+" WHERE name_class='"+VAL+"';";
//            
//            st.executeUpdate(SQL);
//            
//            st.close();
//            
//            con.close();
//            
//            
//        }
//        catch(Exception Error)
//        {
//            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
//                    
//        }
//        
//        
//    }
    
    
    public String castum_chart_array(String class_name)
    {
        
         try
        {
            
            
            Connection con=DriverManager.getConnection(URL2);
                        
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("SELECT * FROM stu_tb INNER JOIN st_tb ON stu_tb.ID_stu=st_tb.ID_stu where cls_ID='"+class_name+"';");
            
            while(rs.next())
            {
                
                String Vall=rs.getString("name");
                //add_all_cnt(Vall);
                castum+=rs.getString("family")+";";
                castum+=rs.getString("num")+";";
    
            }
            
            rs.close();
            
            st.close();
            
            con.close();
            
            
            return castum;
        }
        catch(Exception Error)
        {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }
        return null;
    }
    
    
}
