/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.Main_Page.classes_Form;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import database.login.Class_db;
import database.login.User_db_class;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author rezafta
 */
public class Class_FormController implements Initializable {

    
    @FXML
    private JFXTextField cls_name;

    @FXML
    private JFXTextField stu_cnt;
    @FXML
    private StackPane stack_pane;



    @FXML
    void edit_fun(ActionEvent event) 
    {

        stu_cnt.setDisable(true);
        cls_name.setDisable(true);
        
        user_pass_checked();
        
    }

    
    
    
    @FXML
    void insert_fun(ActionEvent event)
    {

        database.login.Class_db db=new Class_db();
        
        db.Insert_Class(cls_name.getText().toString(), Integer.parseInt(stu_cnt.getText().toString()));
        
        clear();
    }

    
    
    
    @FXML
    void delete_fun(ActionEvent event) 
    {
        database.login.Class_db db=new Class_db();
        
        db.Delete();
        
        clear();
    }
    
    
    
    
    
    
    
    
    
    public void clear()
    {
        stu_cnt.setText("");
        cls_name.setText("");
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
    
    
    
    
    
    
    public void edit_btn_clicked()
    {
        try 
        {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("table/table.fxml"));
        
            Parent Par=(Parent)loader.load();
            
            Stage stage=new Stage();
            
            stage.setScene(new Scene(Par));
            
            stage.initStyle(StageStyle.UTILITY);
            
            stage.initModality(Modality.APPLICATION_MODAL);
            
            stage.show();
            
        } 
        catch (Exception e) 
        {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
    }
    
    
    public boolean user_pass_checked()
    {
        cls_name.setDisable(false);
        stu_cnt.setDisable(false);
        
        JFXDialogLayout layout_dialog=new JFXDialogLayout();
        
        layout_dialog.setHeading(new Text("ورود به بخش کاربری"));
        
        final TextField user_name=new TextField();
        
        user_name.setPromptText("نام کاربری");
        
        final PasswordField user_pass=new PasswordField();
        
        user_pass.setPromptText("رمز عبور");
        
        user_name.setAlignment(Pos.CENTER_RIGHT);
        user_pass.setAlignment(Pos.CENTER_RIGHT);
        
        Region line=new Region();
        
        line.setStyle("-fx-padding: 10  ;");
        
        
        VBox vb=new VBox(user_name,line,user_pass);
        
        layout_dialog.setBody(vb);
        
        JFXButton Enter_btn=new JFXButton("ورود");
        JFXButton Exit_btn=new JFXButton("خروج");
        
        layout_dialog.setActions(Enter_btn,Exit_btn);
        
        final JFXDialog dialog=new JFXDialog(stack_pane,layout_dialog , JFXDialog.DialogTransition.TOP);
        
        dialog.show();
        
        Enter_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                boolean login=az.login_check.login_chk.login(user_name.getText().toString(),user_pass.getText().toString());
        
                if(login)
                {
                    edit_btn_clicked();
                    dialog.close();
                }
                
            }
        });
        
        Exit_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              dialog.close();
            }
        });
        
    
        
        return login;
    }
    
    boolean login=false;
    
    
    
    
    
    
    
}
