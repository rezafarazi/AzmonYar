package az.login_check;

import database.login.login;
import javafx.fxml.FXMLLoader;


public class login_chk 
{
    
    
    public static String name;

    
    public static boolean login(String username,String Password)
    {
        
        
        database.login.login cls=new login();
        
        //"SELECT * FROM teacher_tb where name='"+username.trim()+"' and pass='"+Password.trim()+"';"
        
        //INSERT INTO teacher_tb(name,pass) VALUES('erza','4321');
        
        String array=cls.quary("SELECT * FROM teacher_tb where name='"+username.trim()+"' and pass='"+Password.trim()+"' AND NOT delete_flag is 'true' AND smat='mgmt' ;");
        
        String []colnums=array.split(";");
        
        
        if(colnums.length!=1)
        {
            name=username.toString().trim();
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    
}
