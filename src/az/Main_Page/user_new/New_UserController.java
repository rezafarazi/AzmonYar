package az.Main_Page.user_new;

import com.jfoenix.controls.*;
import database.login.Class_db;
import database.login.User_db_class;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author rezafta
 */
public class New_UserController implements Initializable {

    
    
    private TableView<?> tb_view;
    
      @FXML
    private JFXTextField User_Text;

    @FXML
    private JFXTextField Pass_Text;
    @FXML
    private JFXToggleButton smt;
    @FXML
    private JFXComboBox<String> combo_all_cls;

    void c5db5a(ActionEvent event) {

    }

    
    
    

    @FXML
    void Insert_User(ActionEvent event) {

        database.login.User_db_class Users_tb=new User_db_class();
        
        Users_tb.Insert_User(User_Text.getText().toString(),Pass_Text.getText().toString(),smt.getText().trim().toString(),combo_all_cls.getValue().trim().toString() );
        
        User_Text.clear();
        
        Pass_Text.clear();
        
        combo_all_cls.setValue("");
        
    }
    
    
   
    
    
    
    @FXML
    void Edit_User(ActionEvent event) 
    {
        
        User_Text.setDisable(true);
        Pass_Text.setDisable(true);
        user_pass_checked();
        
    }
    
    
    
    
    public void edit_btn_clicked()
    {
        try 
        {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("edit/edit_win.fxml"));
        
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
    
    
    
    
    @FXML
    private StackPane dialog_pane;

    
    
    public boolean user_pass_checked()
    {
        User_Text.setDisable(false);
        Pass_Text.setDisable(false);
        
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
        
        final JFXDialog dialog=new JFXDialog(dialog_pane,layout_dialog , JFXDialog.DialogTransition.TOP);
        
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
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        refresh();
        
    }    
    
    
    
    
    public void refresh()
    {
        try
        {
        
            
            database.login.Class_db db=new Class_db();
            
            ObservableList<String> cls_names=javafx.collections.FXCollections.observableArrayList();
            
            ArrayList<String> str=db.quary_lst("SELECT * FROM class_tb");
            
            for(int a=0;a<str.size();a+=4)
            {
                combo_all_cls.getItems().add(str.get(a+1));
            }
            
            
            
        
        }
        catch(Exception Error)
        {
            //javax.swing.JOptionPane.showMessageDialog(null, Error.getMessage());
        }
    }

    
    
    @FXML
    private void smt_chng(ActionEvent event) 
    {
        if(smt.getText().trim().equals("مدیر"))
        {
            smt.setText("معلم");
        }
        else
        {
            smt.setText("مدیر");
        }
    }


    
}
