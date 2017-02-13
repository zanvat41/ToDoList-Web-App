package com.data;

import java.util.ArrayList;

/**
 * ToDoList is a java object representing a to do list with a number of tasks
 *
 * @author Tyler Thompson
 */
public class ToDoList {

    //parameters
    String name;
    String owner;
    ArrayList<ToDoItem> items;

    //default constructor
    public ToDoList() {

        name = "";
        owner = "";
        items = new ArrayList<>();
    }

    //accessor methods
    public String getName() {
        return name;
    }
    public String getOwner() {
        return owner;
    }
    public ArrayList<ToDoItem> getItems() {
        return items;
    }

    //mutator methods
    public void setName(String name) {
        this.name = name;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public void setItems(ArrayList<ToDoItem> items) {
        this.items = items;
    }

    //add an item to the list
    public void addItem(ToDoItem item) {
        items.add(item);

    }

    //move an item up in the list
    public void moveItemUp(ToDoItem item) {

        //check to see if we can move the item up
        int index = items.indexOf(item);
        if (index > 0) {

            //swap the items
            ToDoItem temp = items.get(index-1);
            items.set(index-1, item);
            items.set(index, temp);
        }
    }

    //move an item down in the list
    public void moveItemDown(ToDoItem item) {

        //check to see if we can move the item down
        int index = items.indexOf(item);
        if (index < (items.size() - 1)) {

            //swap the items
            ToDoItem temp = items.get(index+1);
            items.set(index+1, item);
            items.set(index, temp);
        }
    }

    //remove an item from a list
    public boolean deleteItem(int id) {

        //iterate through all items in the list, searching for a matching id
        for (int i = 0;i < items.size();i++) {

            //if we find a matching item, remove it and return true
            if (items.get(i).getId() == id) {
                items.remove(i);
                return true;
            }
        }

        //otherwise return false
        return false;
    }

    //reset the list to a default list
    public void reset() {
        name = "";
        owner = "";
        items.clear();
    }

    public int size() {
        return items.size();
    }

    public ToDoItem get(int index) {
        return items.get(index);
    }
}
