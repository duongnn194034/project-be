package com.example.rental.model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Notification implements Serializable {
    private int newNotify = 0;
    private List<String> notifyList = new ArrayList<>();

    public int getNewNotify() {
        return newNotify;
    }

    public void setNewNotify(int newNotify) {
        this.newNotify = newNotify;
    }

    public void addNewNotify() {
        this.newNotify++;
    }

    public void addNotify(String notify) {
        notifyList.add(notify);
    }

    public void deleteNotify(String notify) {
        notifyList.remove(notify);
    }

    public int getSize() {
        return notifyList.size();
    }

    public List<String> getNotifyList() {
        return notifyList;
    }

    public void setNotifyList(List<String> notifyList) {
        this.notifyList = notifyList;
    }
}
