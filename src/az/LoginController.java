/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az;

import com.jfoenix.controls.*;
import database.login.Socket_s.sock_fitch;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author rezafta
 */
public class LoginController implements Initializable {
    
    
    
    
    //password text filde
    @FXML
    private JFXPasswordField pass_text;

    
    
    //username text filde
    @FXML
    private JFXTextField user_text;

    
    
    
    
    //login _btn on clicked
    @FXML
    void login_click(ActionEvent event) 
    {
        
        boolean login=az.login_check.login_chk.login(user_text.getText().toString(),pass_text.getText().toString());
        
        
        if(login)
        {
            try
            {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("Main_Page/Main_Pg.fxml"));
                
                Parent par=(Parent)loader.load();
                
                Stage stage=new Stage();
                
                stage.setScene(new Scene(par));
                
                stage.initStyle(StageStyle.UTILITY);
                
                stage.show();
                
                Stage login_form = (Stage)user_text.getScene().getWindow();
                
                login_form.hide();
                
                
            }
            catch(Exception eventError)
            {
                javax.swing.JOptionPane.showMessageDialog(null, eventError.getMessage().toString());
            }
        }
        else
        {
           
            user_text.clear();
            pass_text.clear();
            
        }
        
        
    }
    
    
    
    
    
    
    
    //exit of program
    @FXML
    void exit_click(ActionEvent event) 
    {
        System.exit(1);
    }
    

    
    
    
    
    ///foucuse code
    @FXML
    void c5db5a(ActionEvent event) {

    }

    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Thread t_qus=new Thread(new Runnable() {
            @Override
            public void run() {
                
                database.login.Socket_s.sock_fitch socket=new sock_fitch();
                
                socket.show_All_cls();
                
            }
        });
        
        t_qus.start();
        
    }    
    
}
