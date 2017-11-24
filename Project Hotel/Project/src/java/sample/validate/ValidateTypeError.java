/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.validate;

import java.io.Serializable;

/**
 *
 * @author CungNguyen
 */
public class ValidateTypeError implements Serializable {

    private String roomId;
    private String descriptionLengthError;
    private String hourPirceNotRightType;
    private String dayPriceNotRightType;


    public ValidateTypeError(String roomId, String descriptionLengthError, String hourPirceNotRightType, String dayPriceNotRightType) {
        this.roomId = roomId;
        this.descriptionLengthError = descriptionLengthError;
        this.hourPirceNotRightType = hourPirceNotRightType;
        this.dayPriceNotRightType = dayPriceNotRightType;
    }

    public ValidateTypeError() {
    }
    public String getRoomId() {
        return roomId;
    }

    public String getDayPriceNotRightType() {
        return dayPriceNotRightType;
    }

    public String getDescriptionLengthError() {
        return descriptionLengthError;
    }

    public String getHourPirceNotRightType() {
        return hourPirceNotRightType;
    }

    public void setDayPriceNotRightType(String dayPriceNotRightType) {
        this.dayPriceNotRightType = dayPriceNotRightType;
    }

    public void setDescriptionLengthError(String descriptionLengthError) {
        this.descriptionLengthError = descriptionLengthError;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setHourPirceNotRightType(String hourPirceNotRightType) {
        this.hourPirceNotRightType = hourPirceNotRightType;
    }

}
