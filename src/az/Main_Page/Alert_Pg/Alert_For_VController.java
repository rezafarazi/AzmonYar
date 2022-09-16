/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.Main_Page.Alert_Pg;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import database.login.Edit_Q_db_cls;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
public class Alert_For_VController implements Initializable {

    
    
    
    
    @FXML
    private JFXComboBox<String> combo;

    
    public static String Com_Val="";
    
    @FXML
    void ok_btn(ActionEvent event) 
    {

        if(combo.getValue().toString()!="")
        {
            Com_Val=combo.getValue().toString();
            
            try 
            {
                FXMLLoader load=new FXMLLoader(getClass().getResource("View_Q/View_Q.fxml"));
                
                Parent par=(Parent)load.load();
                
                Stage st=new Stage();
                
                st.setScene(new Scene(par));
                
                st.initStyle(StageStyle.UTILITY);
                
                st.initModality(Modality.APPLICATION_MODAL);
                
                st.show();
                
                
                Stage stage=(Stage)combo.getScene().getWindow();
                
                stage.hide();
                
            }
            catch (Exception e) 
            {
                javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
            }
            
        }
        
    }
    
    
    
    
     public void fill_combo()
    {
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                
                
                database.login.Edit_Q_db_cls combo_cls=new Edit_Q_db_cls();
                
                ObservableList<String> str=combo_cls.fill_combo_cla_combo();
                
                combo.setItems(str);
            }
        });
        
        t.start();
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        fill_combo();
        
    }    
    
}
