package az.Main_Page.new_Q;

import Calender_Per.cal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.*;
import database.login.Insert_Q;
import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author rezafta
 */
public class Q_newController implements Initializable {

    
    
    
    @FXML
    private JFXTextField ans_two;

    @FXML
    private JFXTextField ans_for;

    @FXML
    private JFXTextField ques;

    @FXML
    private JFXComboBox<String> sel_class_com;

    @FXML
    private JFXTextField time_q_start;

    @FXML
    private JFXRadioButton j_thr;

    private JFXTextField ID_Q;

    @FXML
    private JFXTextField time_q;

    @FXML
    private JFXRadioButton j_one;

    @FXML
    private JFXRadioButton j_two;

    @FXML
    private JFXTextField ans_thr;

    @FXML
    private JFXRadioButton j_for;

    @FXML
    private JFXTextField daq_q_start;

    @FXML
    private ToggleGroup selected_radio_btn;

    @FXML
    private JFXTextField ans_one;
    
    @FXML
    private JFXTextField date_ques;
    
    @FXML
    private JFXToggleButton toggle_btn;
    @FXML
    private JFXButton img_btn;
    
    @FXML
    private JFXDatePicker start_qize;
    
    
    
    
    
    
    //insert function
    public void insert()
    {
        
        
        com.jfoenix.controls.JFXRadioButton []radio={j_one,j_two,j_thr,j_for};
        
        int a;
        
        for(a=0;a<4;a++)
        {
            if(radio[a].isSelected())
            {
                break;
            }
        }
        
        //String combo=sel_class_com.getValue().toString();
        
        database.login.Insert_Q insert=new Insert_Q();
        
        if(!img_btn.getText().trim().toString().equals("عکس"))
        {
            insert.Insert_Question_new_function_img(ques.getText().toString(), ans_one.getText().toString(), ans_two.getText().toString(), ans_thr.getText().toString(), ans_for.getText().toString(), (a+1)+"", sel_class_com.getValue().toString(),toggle_btn.getText().trim().toString(),img_btn.getText().trim().toString());
            System.out.println("Image");
        }
        else
        {
            insert.Insert_Question_new_function(ques.getText().toString(), ans_one.getText().toString(), ans_two.getText().toString(), ans_thr.getText().toString(), ans_for.getText().toString(), (a+1)+"", sel_class_com.getValue().toString(),toggle_btn.getText().trim().toString());
            System.out.println("NOT Image");
        }
        
        int class_ID;int lim_time;int saat_start;int Daq_lim;String date;
        
        class_ID=0;
        
        lim_time=Integer.parseInt(time_q.getText().toString());
        
        LocalTime time=start_qize.getTime();
        
        saat_start=time.getHour();
        
        Daq_lim=time.getMinute();
        
        date=date_ques.getText().toString();
        
        insert.end_question_new_function(class_ID, lim_time, saat_start, Daq_lim, date);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //end btn
    public void end()
    {
        insert();
        
        database.login.Insert_Q cls=new Insert_Q();
        
        //cls.end_question(startlim, endlim, starttime, time, date);
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    //onclick on done btn
    @FXML
    void done_btn(ActionEvent event)
    {
        
        
        
        if(cleck_fill())
        {
            insert();
            claer_filde();
            Stage st=(Stage)ID_Q.getScene().getWindow();
            st.hide();
        }
        else
        {
            javax.swing.JOptionPane.showMessageDialog(null, "لطفا از پر بودن فیلد ها مطمئن شوید");
        }
        
    }
    
    
    
    
    
    
    
    
    
    //کلیک روی دکمه جدید
    @FXML
    void on_new(ActionEvent event) {

        
        
        if(cleck_fill())
        {
            insert();
            claer_filde();
        }
        else
        {
            javax.swing.JOptionPane.showMessageDialog(null, "از پر بودن فیلد های اطمینان پیدا کنید");
        }
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //تابع چک کردن خالی نبودن فیلد ها
    public boolean cleck_fill()
    {
        boolean val=true;
        com.jfoenix.controls.JFXTextField []text={ques,ans_one,ans_two,ans_thr,ans_for,time_q,date_ques};
        
        
        for(int a=0;a<text.length;a++)
        {
            if(text[a].getText().toString().trim()=="")
            {
                val=false;
            }
            else continue;
            
        }
        
        
        if(j_one.isSelected()==false||j_two.isSelected()==false||j_thr.isSelected()==false||j_for.isSelected()==false)
        {
            val=false;
        }
        
        if(!start_qize.isShowTime())
        {
            val=false;
        }
        
        
        
        return true;
    }
    
    
    
    
    
    
    
    
    public void claer_filde()
    {
        com.jfoenix.controls.JFXTextField []text={ans_one,ans_two,ans_thr,ans_for,ques};
        
        for(int a=0;a<text.length;a++)
            text[a].clear();
        
        j_one.setSelected(false);
        j_two.setSelected(false);
        j_thr.setSelected(false);
        j_for.setSelected(false);
        
        img_btn.setText("عکس");
        
        
    }
    
    
    
    
    
    
    
    
    
    @FXML
    void toggle_btn_click(ActionEvent event) 
    {
        JFXToggleButton togle_btn=(JFXToggleButton)event.getSource();
        
        if(togle_btn.getText().trim().toString().equals("چهارگزینه ای"))
        {
            togle_btn.setText("تشریحی");
        }
        else
        {
            togle_btn.setText("چهارگزینه ای");
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        selector();
        show_date_to_day();
        
    }    

    
    
    
    
    
    public void show_date_to_day()
    {
        Calender_Per.cal date=new cal();
        date_ques.setText(date.getIranianYear()+"/"+date.getIranianMonth()+"/"+date.getIranianDay());
    }
    
    
    

    
    public void selector()
    {
   
        database.login.Insert_Q p=new Insert_Q();
        ObservableList<String> str=p.fill_combo_box();
        sel_class_com.setItems(str);
    }

    
    
    
    
    
    
    @FXML
    private void img_btn_click(ActionEvent event) 
    {
        javax.swing.JFileChooser file_choser=new JFileChooser();
        
        FileFilter filter = new FileNameExtensionFilter(".PNG","PNG");
        
        file_choser.setFileFilter(filter);
        
        int count_val=file_choser.showOpenDialog(file_choser);
        
        if(count_val==javax.swing.JFileChooser.APPROVE_OPTION)
        {
            img_btn.setText(file_choser.getSelectedFile().toString().trim());
        }
        
    }
    
   

    
    
    
}

