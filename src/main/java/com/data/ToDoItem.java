package com.data;

import java.util.Date;

/**
 * ToDoItem is a java object that represents a task in a to do list
 * @author Tyler Thompson
 */
public class ToDoItem {

    //parameters
    int id;
    String category;
    String description;
    Date startDate;
    Date endDate;
    boolean completed;
    boolean saved;

    //default constructor
    public ToDoItem() {
        id = 0;
        category = "";
        description = "";
        startDate = new Date();
        endDate = new Date();
        completed = false;
        saved = false;
    }

    //detailed constructor
    public ToDoItem(String category, String description, Date startDate, Date endDate, boolean completed) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.completed = completed;
        saved = false;
    }

    //accessor methods
    public int getId() {
        return id;
    }
    public String getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }
    public Date getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public boolean getCompleted() {
        return completed;
    }
    public boolean getSaved() {
        return saved;
    }

    //mutator methods
    public void setId(int id) {
        this.id = id;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    //reset method
    public void reset() {
        category = "";
        description = "";
        startDate = new Date();
        endDate = new Date();
        completed = false;
        saved = false;
    }
}
