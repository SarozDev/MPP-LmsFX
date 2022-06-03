package com.mpp.librarysys.javafx.constants;

public final class FxmlEnums {

    public enum FxmlView {
        LOGIN_VIEW("/fxml/login-view.fxml"),
        ADMIN_DASH_VIEW("/fxml/user/admin-dash-view.fxml"),

        GENERIC_ADMIN_VIEW("/fxml/user/generic-admin-view.fxml");


        private String value;

        FxmlView(String s) {
            this.value = s;
        }

        public String getValue() {
            return value;
        }
    }


    public enum FxmlContainer {
        TABLE_COMPONENT("/fxml/component/table-component.fxml");

        private String value;

        FxmlContainer(String s) {
            this.value = s;
        }

        public String getValue() {
            return value;
        }
    }
}
