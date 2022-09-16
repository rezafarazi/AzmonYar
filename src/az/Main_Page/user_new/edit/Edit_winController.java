package az.Main_Page.user_new.edit;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.login.User_db_class;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javax.swing.JButton;

/**
 * FXML Controller class
 *
 * @author rezafta
 */
public class Edit_winController implements Initializable 
{

    
    ObservableList<tbl_view> tbl_lst=FXCollections.observableArrayList();
    
    
    @FXML
    private TableView<tbl_view> table_view;
    @FXML
    private TableColumn<tbl_view,String> ID_col;
    @FXML
    private TableColumn<tbl_view,String> usr_name_col;
    @FXML
    private TableColumn<tbl_view,String> usr_pass_col;
    @FXML
    private JFXPasswordField user_pass_filde;
    @FXML
    private JFXTextField user_name_filde;
    @FXML
    private StackPane stack_pane;
    @FXML
    private TableColumn<tbl_view,String> smt_name_col;
    @FXML
    private TableColumn<tbl_view,String> cls_name_col;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        fileds();
        fill();
    }   
    
    
    
    
    public void fill()
    {
        tbl_lst.removeAll(tbl_lst);
        
        fill_by_db();
        
        table_view.getItems().addAll(tbl_lst);
        
    }
    
    
    
    public void fileds()
    {        
        ID_col.setCellValueFactory(new PropertyValueFactory<tbl_view,String>("id"));
        usr_name_col.setCellValueFactory(new PropertyValueFactory<tbl_view,String>("name"));
        usr_pass_col.setCellValueFactory(new PropertyValueFactory<tbl_view,String>("pass"));
        smt_name_col.setCellValueFactory(new PropertyValueFactory<tbl_view,String>("smt"));
        cls_name_col.setCellValueFactory(new PropertyValueFactory<tbl_view,String>("shl"));
        
        
        table_view.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
                ID=table_view.getSelectionModel().getSelectedItem().getId();
                
                
            }
        
        });
        
    }
    
    
    
    
    
    public void fill_by_db()
    {
        database.login.User_db_class db=new User_db_class();
        
        ArrayList<String> lst=db.table_view();
        
        for(int a=0;a<lst.size();a+=5)
            tbl_lst.addAll(new tbl_view(lst.get(a), lst.get(a+1), lst.get(a+2),lst.get(a+3),lst.get(a+4)));
    
    }

    
    
    
    String ID="";
    
    @FXML
    private void delete_btn_click(ActionEvent event) 
    {
        
        database.login.User_db_class db=new User_db_class();
        
        db.delete_user(ID);
        
        table_view.getItems().clear();
        
        fill_by_db();
        
        fill();
        
        ID="";
        
        
    }

    
    
    @FXML
    private void edit_btn_click(ActionEvent event) 
    {
    
        database.login.User_db_class db=new User_db_class();
        
        db.Update_User(user_name_filde.getText().toString(), user_pass_filde.getText().toString(), Integer.parseInt(ID));
    
        table_view.getItems().clear();
        
        fill_by_db();
        
        fill();
        
        ID="";
        
        
    }

    
    
    
    
    public void message_select()
    {
        JFXDialogLayout ly=new JFXDialogLayout();
        
        ly.setHeading(new Text("خطا"));
        
        ly.setBody(new Text("لطفا یک گزینه از جدول را انتخاب کنید سپس عملیات را انجام دهید"));
        
        JFXButton btn=new JFXButton("تایید");
        
        ly.setActions(ly);
        
        JFXDialog dia=new JFXDialog( stack_pane,ly, JFXDialog.DialogTransition.TOP);
        
        dia.show();
       
        
    }
    
    
    
    
    
    
    
    public static class tbl_view
    {
        SimpleStringProperty id;
        SimpleStringProperty name;
        SimpleStringProperty pass;
        SimpleStringProperty smt;
        SimpleStringProperty shl;
        
        public tbl_view(String id,String name,String pass,String smt,String shl)
        {
            this.id=new SimpleStringProperty(id);
            this.name=new SimpleStringProperty(name);
            this.pass=new SimpleStringProperty(pass);
            this.smt=new SimpleStringProperty(smt);
            this.shl=new SimpleStringProperty(shl);
            
        }

        public String getId() {
            return id.get();
        }

        public String getName() {
            return name.get();
        }

        public String getPass() {
            return pass.get();
        }

        public String getSmt() {
            return smt.get();
        }

        public String getShl() {
            return shl.get();
        }
        
        
    }
    
    
    
}
