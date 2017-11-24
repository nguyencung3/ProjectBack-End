/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.room;

import java.io.Serializable;

/**
 *
 * @author CungNguyen
 */
public class RoomDTO implements Serializable {

    private String roomId;
    private String reason;
    private String description;
    private float hourPrice;
    private float dayPrice;

    public RoomDTO() {
    }

    public RoomDTO(String roomId, String description, float hourPrice, float dayPrice) {
        this.roomId = roomId;
        this.description = description;
        this.hourPrice = hourPrice;
        this.dayPrice = dayPrice;
    }

    public RoomDTO(String roomId, String reason) {
        this.roomId = roomId;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public float getDayPrice() {
        return dayPrice;
    }

    public String getDescription() {
        return description;
    }

    public float getHourPrice() {
        return hourPrice;
    }

    public void setDayPrice(float dayPrice) {
        this.dayPrice = dayPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHourPrice(float hourPrice) {
        this.hourPrice = hourPrice;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

}
