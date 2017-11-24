/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_User;

import java.io.Serializable;

/**
 *
 * @author CungNguyen
 */
public class Tbl_UserTypeError implements Serializable {

    private String confirmNotMatch;
    private String usernameIsExisted;
    private String userIDerror;
    private String fullnameLengthError;
    private String passwordNotRightTypeError;
    private String roleNotRightTypeError;
    private String userNameLengthError;

    public String getUserNameLengthError() {
        return userNameLengthError;
    }

    public void setUserNameLengthError(String userNameLengthError) {
        this.userNameLengthError = userNameLengthError;
    }

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }

    public String getUserIDerror() {
        return userIDerror;
    }

    public void setUserIDerror(String userIDerror) {
        this.userIDerror = userIDerror;
    }

    public String getFullnameLengthError() {
        return fullnameLengthError;
    }

    public void setFullnameLengthError(String fullnameLengthError) {
        this.fullnameLengthError = fullnameLengthError;
    }

    public String getPasswordNotRightTypeError() {
        return passwordNotRightTypeError;
    }

    public void setPasswordNotRightTypeError(String passwordNotRightTypeError) {
        this.passwordNotRightTypeError = passwordNotRightTypeError;
    }

    public String getRoleNotRightTypeError() {
        return roleNotRightTypeError;
    }

    public void setRoleNotRightTypeError(String roleNotRightTypeError) {
        this.roleNotRightTypeError = roleNotRightTypeError;
    }

}
