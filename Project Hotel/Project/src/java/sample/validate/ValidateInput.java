/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.validate;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author CungNguyen
 */
public class ValidateInput implements Serializable {

    private ValidateTypeError errors;
    private Map<String, String> listReport;

    public ValidateTypeError getErrors() {
        return errors;
    }

    public Map<String, String> getListReport() {
        return listReport;
    }
   
    public boolean checkDateInput(String txtDate) throws ParseException {
        boolean flag = false;
        if (!txtDate.matches("\\d{4}+-\\d{2}+-\\d{2}")) {
            flag = true;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(txtDate);
            if (!txtDate.equals(formatter.format(date))) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean checkUpdateByManagerInput(String description, String hourPrice, String dayPrice) {
        float hourPriceFloat;
        float dayPriceFloat;
        boolean error = false;
        if (this.errors == null) {
            this.errors = new ValidateTypeError();
        }
        if (description.trim().length() < 3 || description.trim().length() > 250) {
            error = true;
            this.errors.setDescriptionLengthError("Please Input Description (3-250)chars");
        }
        try {
            hourPriceFloat = Float.parseFloat(hourPrice);
            if (hourPriceFloat < 0) {
                this.errors.setHourPirceNotRightType("Please Input Number > 0");
                error = true;
            }
        } catch (NumberFormatException e) {
            error = true;
            this.errors.setHourPirceNotRightType("Please Input Number Not String Or Empty");
        }
        try {
            dayPriceFloat = Float.parseFloat(dayPrice);
            if (dayPriceFloat < 0) {
                this.errors.setDayPriceNotRightType("Please Input Number > 0");
                error = true;
            }
        } catch (NumberFormatException e) {
            error = true;
            this.errors.setDayPriceNotRightType("Please Input Number Not String Or Empty");
        }
        return error;
    }

    public boolean checkReport(String reportInfo, String roomId) {
        if (this.errors == null) {
            this.errors = new ValidateTypeError();
        }
        if (this.listReport == null) {
            this.listReport = new HashMap<String, String>();
        }
        boolean error = false;
        if (reportInfo.trim().length() < 3 || reportInfo.trim().length() > 250) {
            error = true;
            this.errors.setRoomId(roomId);
            this.listReport.put("ERROR" + roomId, "Please Input Report (3-250)chars");
        } else {
            this.listReport.put(roomId, reportInfo);
        }
        return error;
    }

}
