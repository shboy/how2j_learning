package com.how2java.action;

public class ShowTime {
    private String currentTime;

    // FIXME: 这个任务没有完成
    // https://how2j.cn/k/struts/struts-jsp/55.html
    // 步骤 9 : 访问路径 /showTime 跳转到页面 showTime.jsp，并且在showTime.jsp中显示当前时间
    public String getCurrentTime() {
        this.setCurrentTime(String.valueOf(System.currentTimeMillis()));
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
