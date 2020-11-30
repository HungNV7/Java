/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

/**
 *
 * @author ASUS
 */
public class FeedbackDTO {
    private String id, feedback, room, userName;
    private int star;

    public FeedbackDTO() {
    }

    public FeedbackDTO(String id, String feedback, String room, int star) {
        this.id = id;
        this.feedback = feedback;
        this.room = room;
        this.star = star;
    }

    public FeedbackDTO(String id, String feedback, String room, String userName, int star) {
        this.id = id;
        this.feedback = feedback;
        this.room = room;
        this.userName = userName;
        this.star = star;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }   
}
