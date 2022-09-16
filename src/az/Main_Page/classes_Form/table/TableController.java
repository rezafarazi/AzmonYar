package az.Main_Page.classes_Form.table;

import az.Main_Page.user_new.edit.Edit_winController;
import com.jfoenix.controls.JFXTextField;
import database.login.Class_db;
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

/**
 * FXML Controller class
 *
 * @author rezafta
 */
public class TableController implements Initializable {

    
    javafx.collections.ObservableList<tbl_cls> lst=javafx.collections.FXCollections.observableArrayList();
    
    
    @FXML
    private TableView<tbl_cls> table_view;
    @FXML
    private TableColumn<tbl_cls, String> ID_col;
    @FXML
    private TableColumn<tbl_cls, String> Name_Col;
    @FXML
    private TableColumn<tbl_cls, String> Count_Col;
    @FXML
    private TableColumn<tbl_cls, String> Avg_Col;
    @FXML
    private JFXTextField class_room_name_edit;
    @FXML
    private JFXTextField class_room_count_edit;
    @FXML
    private JFXTextField class_room_name_serch;

    
    


    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        fildes();
        data("SELECT * FROM class_tb WHERE NOT delete_falg is 'true';");
        
        table_view.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
                Edit_id=table_view.getSelectionModel().getSelectedItem().getId();
                
                class_room_name_edit.setText(table_view.getSelectionModel().getSelectedItem().getName());
                class_room_count_edit.setText(table_view.getSelectionModel().getSelectedItem().getCount());
                
            }
        });
        
    }    
    
    
    
    public void fildes()
    {
        ID_col.setCellValueFactory(new PropertyValueFactory<tbl_cls,String>("id"));
        Name_Col.setCellValueFactory(new PropertyValueFactory<tbl_cls,String>("name"));
        Count_Col.setCellValueFactory(new PropertyValueFactory<tbl_cls,String>("count"));
        Avg_Col.setCellValueFactory(new PropertyValueFactory<tbl_cls,String>("avg"));
    }
    
    
    
    
    public void data(String quary)
    {
        lst.remove(lst);
        
        fill_data(quary);
        
        table_view.getItems().addAll(lst);
        
    }
    
    
    
    
    
    public void fill_data(String quary)
    {
        database.login.Class_db db=new Class_db();
        
        ArrayList<String> lst_data=db.quary_lst(quary);
        
        
        for(int a=0;a<lst_data.size();a+=4)
        {
            lst.addAll(new tbl_cls(lst_data.get(a).toString(), lst_data.get(a+1).toString(), lst_data.get(a+2).toString(), lst_data.get(a+3).toString()));
        }
        
        
        
        
    }

    
    
    
    
    String Edit_id="";
    
    
    @FXML
    private void edit_btn_click(ActionEvent event) 
    {
        database.login.Class_db.Edit(class_room_name_edit.getText().trim(), Integer.parseInt(class_room_count_edit.getText().trim().toString()), Edit_id);
        lst.clear();
        table_view.getItems().clear();
        data("SELECT * FROM class_tb WHERE NOT delete_falg is 'true';");
    }

    
    
    
    
    
    @FXML
    private void serach_btn_click(ActionEvent event) 
    {
        
        lst.clear();
        
        table_view.getItems().clear();
        
        String serach_key=class_room_name_serch.getText().trim().toString();
        
        if(!serach_key.trim().equals(""))
            data("SELECT * FROM class_tb WHERE name_class like('%"+serach_key+"%') AND NOT delete_falg is 'true';;");
        else
            data("SELECT * FROM class_tb WHERE NOT delete_falg is 'true';");
    }

    
    
    
    
    @FXML
    private void delete_btn_click(ActionEvent event) 
    {
        database.login.Class_db.Delete_Flag(Edit_id);
        lst.clear();
        table_view.getItems().clear();
        data("SELECT * FROM class_tb WHERE NOT delete_falg is 'true';");
        class_room_count_edit.clear();
        class_room_name_edit.clear();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static class tbl_cls
    {
        final private SimpleStringProperty id,name,count,avg;
        
        public tbl_cls(String id,String name,String count,String avg)
        {
            this.id=new SimpleStringProperty(id);
            this.name=new SimpleStringProperty(name);
            this.count=new SimpleStringProperty(count);
            this.avg=new SimpleStringProperty(avg);
        }

        public String getId() {
            return id.get();
        }

        public String getName() {
            return name.get();
        }

        public String getCount() {
            return count.get();
        }

        public String getAvg() {
            return avg.get();
        }
        
        
        
        
    }
   
    
    
    
}
