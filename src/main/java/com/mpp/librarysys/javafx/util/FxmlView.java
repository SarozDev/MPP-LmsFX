package com.mpp.librarysys.javafx.util;

public enum FxmlView {

    LOGIN_VIEW("/fxml/login-view.fxml"),
    ADMIN_DASH_VIEW("/fxml/user/admin-dash-view.fxml")
    ;

    private String value;

    FxmlView(String s) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }
}
