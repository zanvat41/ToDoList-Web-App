package com.tdlm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.data.ToDoItem;
import com.data.ToDoList;

/**
 * Created by Kieran on 2/13/2017.
 */

@Controller
public class ToDoListController {
    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the 'To Do List Maker' web app!");
        return "index";
    }

    //the to do list
    ToDoList list = new ToDoList();

    //method that is called when a get request for the addItem.jsp form arrives
    @RequestMapping(value = "/addItem", method = RequestMethod.GET)
    public String viewAddItemForm(Model model) {
        System.out.println("SPRING GOT THE REQUEST");

        //create a new to do item to hold the form info from the user when they complete it, then add it to the model
        //this associates the form data with the item
        model.addAttribute("addItem", new ToDoItem());

        //send the user to the addItem.jsp form
        return "addItem";

    }

    //method that is used when the user completes the form to add the item to the list
    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public String completeAddItemForm(@ModelAttribute("addItem") ToDoItem item) {

        //add the item to the end of the list
        list.addItem(item);

        //testing prints, get the category of the last item added and print it out
        System.out.println(list.getItems().get(list.getItems().size() - 1).getCategory());

        //send the user to home.jsp
        return "home";
    }
}
