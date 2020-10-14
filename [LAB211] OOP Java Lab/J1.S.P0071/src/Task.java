/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Task {
    int id;
    String name;
    int typeID;
    String date;
    String from;
    String to;
    String assignee;
    String expert;

    public Task(){
        id=0;
        name="";
        typeID=1;
        date="";
        from="";
        to="";
        assignee="";
        expert="";
    }
    
    public Task(int id, String name, int typeID, String date, String from, String to, String assignee, String expert) {
        this.id=id;
        this.name = name;
        this.typeID = typeID;
        this.date = date;
        this.from = from;
        this.to = to;
        this.assignee = assignee;
        this.expert = expert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }
    
    
}
