/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.Main_Page.Alert_Pg.View_Q;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.jfoenix.controls.*;
import database.login.Q_View;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author rezafta
 */
public class View_QController implements Initializable {

    
    
    @FXML
    private Text text_ans_one;

    @FXML
    private JFXRadioButton radio_one;

    @FXML
    private Text text_ans_for;

    @FXML
    private JFXRadioButton radio_for;

    @FXML
    private Text text_Q;

    @FXML
    private Text text_ans_thr;

    @FXML
    private JFXRadioButton radio_two;

    @FXML
    private Text text_ans_two;

    @FXML
    private JFXRadioButton radio_thr;

    
    
    
    
    
    //fnctions
    
    @FXML
    void next_click(ActionEvent event) 
    {
        
            sett(flag);
            
        if(flag+6<Val.length)
        {
            flag+=6;
        }
    }

    
    
    @FXML
    void delete_click(ActionEvent event) 
    {

        database.login.Q_View varr=new Q_View();
        
        varr.delete(text_Q.getText().toString());
        
        
        flag=0;
        fill_Array();
        
        sett(flag);
    }

    
    
    @FXML
    void priv_click(ActionEvent event) 
    {
        if(flag-12>=0)
        {
            flag-=12;
        }
            sett(flag);
            flag+=6;
        
    }
    
    
    String []Val;
    
    int flag=0;
    
    
    public void fill_Array()
    {
        database.login.Q_View varr=new Q_View();
        
        Val=varr.Val(az.Main_Page.Alert_Pg.Alert_For_VController.Com_Val.toString()).split(";");
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        fill_Array();
        
        sett(flag);
        
        flag+=6;
        
    }


    
    public void sett(int a)
    {
        text_Q.setText(Val[a].toString());
        text_ans_one.setText(Val[a+1].toString());
        text_ans_two.setText(Val[a+2].toString());
        text_ans_thr.setText(Val[a+3].toString());
        text_ans_for.setText(Val[a+4].toString());
        if(Val[a+5]=="1")
        {
            radio_one.setSelected(true);
        }
        if(Val[a+5]=="2")
        {
            radio_two.setSelected(true);
        }
        if(Val[a+5]=="3")
        {
            radio_thr.setSelected(true);
        }
        if(Val[a+5]=="4")
        {
            radio_for.setSelected(true);
        }
        
    }
    
    
}
