package com.mpp.librarysys.lms.util;

import com.mpp.librarysys.lms.entities.User;
import org.springframework.beans.BeanUtils;

public class AppUtil {

    private static User authenticatedUser = null;

    public static void setAuthenticatedUser(User user) {
        User userClone = new User();
        BeanUtils.copyProperties(user, userClone);
        authenticatedUser = userClone;
    }

    public static User getAuthenticatedUser() {
        return authenticatedUser;
    }

}
