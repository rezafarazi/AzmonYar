package az.Main_Page.chart;

import com.jfoenix.controls.JFXComboBox;
import database.login.Insert_Q;
import database.login.chart_cls;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;


public class Chart_FormController implements Initializable {

    
    
    
    @FXML
    private JFXComboBox<String> combo_bx;

    database.login.chart_cls k;
    
    
    Object [] cls_lst;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    
        k=new chart_cls();
        
        String varstr=k.class_chart();
        
        cls_lst=k.class_chart_array().toArray();
        
        
        for(int a=0;a<cls_lst.length;a++)
            System.out.println(cls_lst[a]);
        
        
        
    }

    

    @FXML
    private AnchorPane pn;
    
    @FXML
    private BarChart<String, Float> chart_control;


    
    public String ss;
    
    public void show_char(String str)
    {
        
        
        
                chart_control.getData().clear();
                
                
                for(int a=0;a<cls_lst.length;a+=2)
                {
                    if(cls_lst[a+1].toString().equals("-"))
                    {
                        continue;
                    }
                    
                    XYChart.Series st=new XYChart.Series();
                
                    st.getData().add(new XYChart.Data<>(cls_lst[a].toString(),Float.parseFloat(cls_lst[a+1].toString())));
                    
                    chart_control.getData().addAll(st);
                    
                }
                
                
                
    }

    
    
     @FXML
    public void on_click_class_btn(ActionEvent event) 
    {
        show_char(ss);
            
    }
    
    
    
    
    @FXML
    void st(ActionEvent event) 
    {
        pn.setVisible(true);
        
        database.login.Insert_Q db=new Insert_Q();
        
        combo_bx.setItems(db.fill_combo_box());
        
        
    }
    
    
    
    
    @FXML
    void done_btn(ActionEvent event) 
    {
        cast_cls(combo_bx.getValue().toString());

    }
    
    
    
    
    public void cast_cls(String Val)
    {
        
        
        
        String Vals=k.castum_chart(Val);
        
        String []Array_str=Vals.split(";");
                
                
                
                for(int a=0;a<Array_str.length;a+=2)
                {
                    
                    XYChart.Series st=new XYChart.Series();
                
                    st.getData().add(new XYChart.Data<>(Array_str[a],Float.parseFloat(Array_str[a+1])));
                    
                    chart_control.getData().addAll(st);
                
                }
        
                
    }
    
    
    
    
    
    
    @FXML
    void clear_chart(ActionEvent event) 
    {
        chart_control.getData().clear();
    }
    
    
    
    
}
