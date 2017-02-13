package com.tdlm.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.data.ToDoItem;
import com.data.ToDoList;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Kieran on 2/13/2017.
 */

@Controller
public class ToDoListController {

    //the to do list
    ToDoList list = new ToDoList();

    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the 'To Do List Maker' web app!");
        return "index";
    }

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

        //give the item a unique id before adding it to the list
        item.setId(getUniqueID());

        //add the item to the end of the list
        list.addItem(item);

        //testing prints, get the category of the last item added and print it out
        System.out.println("ID = " + item.getId());

        //send the user to home.jsp
        return "redirect:home";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteItem(@RequestParam("id") int id) {

        //test print
        System.out.println("before delete, size = " + list.size());

        //remove the item with the matching id
        list.deleteItem(id);

        //test print
        System.out.println("after delete, size = " + list.size());

        return "redirect:home";
    }

    private int getUniqueID() {

        //assume the first id is unique
        boolean unique = true;

        //check every possible id starting from 0
        for (int i = 0;i < Integer.MAX_VALUE;i++) {

            //check each element in the list for the id
            for (int k = 0;k < list.size();k++) {

                //if we find the id, it is not unique
                if (list.get(k).getId() == i) {
                    unique = false;
                    break;
                }
            }

            //after we are done searching, if the id is unique, return it
            if (unique == true) {
                return i;
            }

            //if not, we check the next id
            unique = true;
        }

        //return -1 for error
        return -1;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView listTodos() {
        //List<String> list = new ArrayList<String>();
        List<ToDoItem> theList = list.getItems();
        //return back to home.jsp
        ModelAndView model = new ModelAndView("home");
        model.addObject("todos", theList);

        return model;
    }
}