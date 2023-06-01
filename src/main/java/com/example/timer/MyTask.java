package com.example.timer;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

class MyTask extends Service<Void> {
    private int starttime = -1;
    public void setStartNumber(int starttime) {
        this.starttime = starttime;
    }
    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (starttime>0){
                    starttime --;
                    updateMessage(String.valueOf(starttime));
                    Thread.sleep(1000);
                }
                updateMessage("finish");
                return null;
            }
        };
    }
}

