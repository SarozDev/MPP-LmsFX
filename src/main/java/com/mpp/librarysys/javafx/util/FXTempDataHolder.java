package com.mpp.librarysys.javafx.util;

public final class FXTempDataHolder {

    private Object data;

    private final static FXTempDataHolder INSTANCE = new FXTempDataHolder();

    private FXTempDataHolder() {
    }

    public static FXTempDataHolder getInstance() {
        return INSTANCE;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}