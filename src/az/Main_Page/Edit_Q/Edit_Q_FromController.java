/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.Main_Page.Edit_Q;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.*;
import database.login.Edit_Q_db_cls;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author rezafta
 */
public class Edit_Q_FromController implements Initializable {

    
    
    
       @FXML
    private JFXTextField text_for_edit;

    @FXML
    private JFXRadioButton radio_for_edit;

    @FXML
    private JFXRadioButton radio_thr_edit;

    @FXML
    private JFXTextField text_thr_edit;

    @FXML
    private JFXComboBox<String> combo_box_Edit;

    @FXML
    private JFXTextField text_two_edit;

    @FXML
    private ToggleGroup sel;

    @FXML
    private JFXRadioButton radio_two_edit;

    @FXML
    private JFXRadioButton radio_one_edit;

    @FXML
    private JFXTextField text_one_edit;

    @FXML
    private JFXComboBox<String> cla_combo;
    
    
    @FXML
    void edit_btn_click(ActionEvent event) 
    {

        if(check_fill())
        {
            Edite();
            
        }
        
    }
    
    
    
    public void Edite()
    {
        database.login.Edit_Q_db_cls edit=new Edit_Q_db_cls();
        String t="";
        if(radio_one_edit.isSelected())
        {
            t="1";
        }
        
        if(radio_two_edit.isSelected())
        {
            t="2";
        }
        
        if(radio_thr_edit.isSelected())
        {
            t="3";
        }
        
        if(radio_for_edit.isSelected())
        {
            t="4";
        }
        
        edit.Update(combo_box_Edit.getValue().toString().trim(), text_one_edit.getText().toString().trim(), text_two_edit.getText().toString().trim(), text_thr_edit.getText().toString().trim(), text_for_edit.getText().toString().trim(), t);
        
        clear();
        
    }
    
    
    
    public void clear()
    {
        text_one_edit.setText("");
        text_two_edit.setText("");
        text_thr_edit.setText("");
        text_for_edit.setText("");
        radio_one_edit.setSelected(false);
        radio_two_edit.setSelected(false);
        radio_thr_edit.setSelected(false);
        radio_for_edit.setSelected(false);
        combo_box_Edit.setValue("");
    }
    
    
    
    public boolean check_fill()
    {
        
        com.jfoenix.controls.JFXTextField []txt={text_one_edit,text_two_edit,text_thr_edit,text_for_edit};
        
        boolean chk=true;
        
        if(combo_box_Edit.getValue().toString().trim()==""||combo_box_Edit.getValue().toString().trim()==null)
        {
            chk=false;
        }
        
        
        for(int a=0;a<txt.length;a++)
        {
            if(txt[a].getText().trim()==null||txt[a].getText().trim()=="")
            {
                chk=false;
            }
        }
        
        if(!radio_one_edit.isSelected()&&!radio_two_edit.isSelected()&&!radio_thr_edit.isSelected()&&!radio_for_edit.isSelected())
        {
            chk=false;
        }   
        
        return chk;
    }
    
    
    
    
    @FXML
    void chn(ActionEvent event) 
    {

        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                
                for(int a=0;a<=Array.length;a+=6)
                {
                    
                    if(combo_box_Edit.getValue().equals(Array[a]))
                    {
                        text_one_edit.setText(Array[a+1]);
                        text_two_edit.setText(Array[a+2]);
                        text_thr_edit.setText(Array[a+3]);
                        text_for_edit.setText(Array[a+4]);
                        
                        
                        if(Array[a+5].trim()=="1")
                            radio_one_edit.setSelected(true);
                        else if(Array[a+5].trim()=="2")
                            radio_two_edit.setSelected(true);
                        else if(Array[a+5].trim()=="3")
                            radio_thr_edit.setSelected(true);
                        else if(Array[a+5].trim()=="4")
                            radio_for_edit.setSelected(true);
                        
                        
                    }
                }
                
            }
        });
        
        t.start();
        
        
    }
    
    
    
    
    
    
    
    String []Array;
    
    
    
    public void fill_combo()
    {
        database.login.Edit_Q_db_cls o=new Edit_Q_db_cls();
        
        String []str=o.fill_combo_edit("SELECT * FROM question_tb;").split(";");
        
        Array=new String[str.length];
        
        ObservableList<String> value=FXCollections.observableArrayList();
        
        for(int a=0;a<str.length;a++)
        {
            Array[a]=str[a];    
        }
        
        for(int a=0;a<str.length;a+=6)
        {
            
            value.add(str[a].toString());
        }
        
        combo_box_Edit.setItems(value);
        
        ObservableList<String> val=o.fill_combo_cla_combo();
        
        cla_combo.setItems(val);
        
        
        
    }
    
    
    
    
    
    
    
    
    @FXML
    void class_action_event_combo(ActionEvent event) 
    {
        Thread t=new Thread(new Runnable() 
        {
            @Override
            public void run() 
            {
                
                database.login.Edit_Q_db_cls o=new Edit_Q_db_cls();
        
                String []str=o.fill_combo_edit("SELECT * FROM question_tb WHERE ID_cls='"+cla_combo.getValue().toString()+"';").split(";");
        
                ObservableList<String> value=FXCollections.observableArrayList();
        
        
                for(int a=0;a<str.length;a+=6)
                {
                    value.add(str[a].toString());
                }
        
                combo_box_Edit.setItems(value);
                
            }
        });
        
        
        t.start();
        

    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fill_combo();
    }    
    
}
