/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.Main_Page;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author rezafta
 */
public class Main_PgController implements Initializable {

    
    
    
    
    
    //onclick on تعریف سوالات جدید
     @FXML
    void n_ques(ActionEvent event) 
    {
        
        try
        {
         
            FXMLLoader loader=new FXMLLoader(getClass().getResource("new_Q/Q_new.fxml"));
            
            Parent par=(Parent)loader.load();
            
            Stage st=new Stage();
            
            st.setScene(new Scene(par));
            
            st.initStyle(StageStyle.UTILITY);
            
            st.initModality(Modality.APPLICATION_MODAL);
            
            st.show();
            
            
        }
        catch(Exception Error)
        {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }


        

    }
    
    
    
    
     @FXML
    void onclick_Nuser(ActionEvent event)throws Exception
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("user_new/new_User.fxml"));
        
        Parent par=(Parent)loader.load();
        
        Stage st=new Stage();
        
        st.setScene(new Scene(par));
        
        st.initModality(Modality.APPLICATION_MODAL);
        
        st.initStyle(StageStyle.UTILITY);
        
        st.show();

    }

    
    
    
    
    @FXML
    void Open_Class_Form(ActionEvent event) 
    {
        
        try
        {
         
            FXMLLoader loader=new FXMLLoader(getClass().getResource("classes_Form/Class_Form.fxml"));
            
            Parent par=(Parent)loader.load();
            
            Stage st=new Stage();
            
            st.setScene(new Scene(par));
            
            st.initStyle(StageStyle.UTILITY);
            
            st.initModality(Modality.APPLICATION_MODAL);
            
            st.show();
            
            
        }
        catch(Exception Error)
        {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }

    }
    
    
    
    
    
    @FXML
    void Edit_Q_btn_Click(ActionEvent event) 
    {
        try 
        {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Edit_Q/Edit_Q_From.fxml"));
            
            Parent par=(Parent)loader.load();
            
            Stage st=new Stage();
            
            st.setScene(new Scene(par));
            
            st.initStyle(StageStyle.UTILITY);
            
            st.initModality(Modality.APPLICATION_MODAL);
            
            st.show();
            
        } 
        catch (Exception e) 
        {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    
    
    
    
    
    
    @FXML
    void View_Questions(ActionEvent event) 
    {

        try 
            {
                FXMLLoader load=new FXMLLoader(getClass().getResource("Alert_Pg/Alert_For_V.fxml"));
                
                Parent par=(Parent)load.load();
                
                Stage st=new Stage();
                
                st.setScene(new Scene(par));
                
                st.initStyle(StageStyle.UTILITY);
                
                st.initModality(Modality.APPLICATION_MODAL);
                
                st.show();
                
            }
            catch (Exception e) 
            {
                javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
            }
        
    }
    
    
    
    
    
     @FXML
    void Chart_btn_on_Click(ActionEvent event) 
    {
        try 
        {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("chart/Chart_Form.fxml"));
            
            Parent par=(Parent)loader.load();
            
            Stage st=new Stage();
            
            st.setScene(new Scene(par));
            
            st.initStyle(StageStyle.UTILITY);
            
            st.initModality(Modality.APPLICATION_MODAL);
            
            st.show();
        } 
        catch (Exception Error) 
        {
            javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }

    }
    
    
    
    
    
    
    
    //on clicked exit btn
     @FXML
    void exit_click(ActionEvent event) 
    {
        System.exit(1);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
