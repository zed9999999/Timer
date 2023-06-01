package com.example.timer;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class StartController {
    private final MyTask myTask = new MyTask();
    @FXML
    private Label Timertext;
    @FXML
    private Label Isrunning;
    @FXML
    private TextField TimeHtext;
    @FXML
    private TextField TimeMtext;
    @FXML
    private TextField TimeStext;
    @FXML
    private Button startButton;
    @FXML
    private Button ZeroButton;
    @FXML
    protected void onStartButtonClick() throws InterruptedException {
        // 判断开始or复位
        if(startButton.getText().equals("开始")&&Isrunning.getText().equals("0")){
            int hour=0,minute=0,second=0;
            try{
                hour = Integer.parseInt(TimeHtext.getText());
                if(hour<0){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("小时数据格式输入错误");
                    alert.show();
                    clear();
                    return ;
                }
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("小时数据格式输入错误");
                alert.show();
                clear();
                return ;
            }
            try{
                minute = Integer.parseInt(TimeMtext.getText());
                if(minute<0 || minute>60){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("分钟数据格式输入错误");
                    alert.show();
                    clear();
                    return ;
                }
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("分钟数据格式输入错误");
                alert.show();
                clear();
                return ;
            }
            try{
                second = Integer.parseInt(TimeStext.getText());
                if(second<0 || second>60){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("秒数据格式输入错误");
                    alert.show();
                    clear();
                    return ;
                }
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("秒数据格式输入错误");
                alert.show();
                clear();
                return ;
            }
            String s="";
            if(hour<10)
                s = s+"0"+ hour +":";
            else
                s = s+ hour +":";
            if(minute<10)
                s = s+"0"+ minute +":";
            else
                s = s+ minute +":";
            if(second<10)
                s = s+"0"+ second;
            else
                s = s+ second;
            Timertext.setText(s);
            TimeHtext.setText(String.valueOf(hour));
            TimeMtext.setText(String.valueOf(minute));
            TimeStext.setText(String.valueOf(second));
            TimeHtext.setEditable(false);
            TimeMtext.setEditable(false);
            TimeStext.setEditable(false);
            startButton.setText("暂停");
            Isrunning.setText("1");
            Integer time = hour*3600+minute*60+second;
            myTask.setStartNumber(time);
            myTask.restart();
            myTask.messageProperty().addListener((obs, oldMsg, newMsg) -> {
                if(!newMsg.equals("finish")){
                    Integer nowtime = 0;
                    try{
                        nowtime = Integer.parseInt(newMsg);

                    }catch(Exception ignored){}
                    int nowhour,nowminute,nowsecond;
                    nowhour = nowtime/3600;
                    nowminute = nowtime%3600/60;
                    nowsecond = nowtime%3600%60;
                    String ss="";
                    if(nowhour<10)
                        ss = ss+"0"+ nowhour +":";
                    else
                        ss = ss+ nowhour +":";
                    if(nowminute<10)
                        ss= ss+"0"+ nowminute +":";
                    else
                        ss = ss+ nowminute +":";
                    if(nowsecond<10)
                        ss = ss+"0"+ nowsecond;
                    else
                        ss = ss+ nowsecond;
                    Timertext.setText(ss);

                }
                else{
                    finish();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("计时结束");
                    alert.show();
                }
            });
        }
        else if(startButton.getText().equals("继续")&&Isrunning.getText().equals("1")){
            myTask.setStartNumber(Integer.parseInt(myTask.getMessage()));
            myTask.restart();
            startButton.setText("暂停");
            ZeroButton.setVisible(false );
        }
        else if(startButton.getText().equals("暂停")){
            startButton.setText("继续");
            myTask.getMessage();
            myTask.cancel();
            ZeroButton.setVisible(true);
        }
    }
    @FXML
    protected void onZeroButtonClick() throws InterruptedException{
        myTask.cancel();
        finish();
        Timertext.setText("00:00:00");
    }
    protected void clear(){
        TimeHtext.setText("0");
        TimeMtext.setText("0");
        TimeStext.setText("0");
    }
    protected void finish(){
        TimeHtext.setText("0");
        TimeMtext.setText("0");
        TimeStext.setText("0");
        TimeHtext.setEditable(true);
        TimeMtext.setEditable(true);
        TimeStext.setEditable(true);
        startButton.setText("开始");
        ZeroButton.setVisible(false);
        Isrunning.setText("0");
    }

}