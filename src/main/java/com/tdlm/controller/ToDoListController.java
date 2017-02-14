package com.tdlm.controller;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.data.ToDoItem;
import com.data.ToDoList;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;

/**
 * Created by Kieran on 2/13/2017.
 */

@Controller
public class ToDoListController extends HttpServlet {

    //the to do list
    ToDoList list = new ToDoList();
    String listName = "";
    boolean pub = false;

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

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String updateItem(@RequestParam("id") int id, Model model) {

        ToDoItem itemToUpdate = list.getById(id);

        model.addAttribute("itemToUpdate", itemToUpdate);

        return "update-todo";
    }

    //method that is used when the user completes the form to add the item to the list
    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String completeUpdateItemForm(@ModelAttribute("itemToUpdate") ToDoItem item) {

        //add the item to the end of the list
        list.updateItem(item);

        //testing prints, get the category of the last item added and print it out
        System.out.println("ID = " + item.getId());

        //send the user to home.jsp
        return "redirect:home";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteItem(@RequestParam("id") int id) {

        //test print
        System.out.println("before delete, size = " + list.size());

        ToDoItem item = list.getById(id);

        //remove the item with the matching id
        list.deleteItem(id);

        //test print
        System.out.println("after delete, size = " + list.size());

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query("ToDoItem");
        FilterPredicate filter1 = new FilterPredicate("listname", FilterOperator.EQUAL, list.getName());
        FilterPredicate filter2 = new FilterPredicate("owner", FilterOperator.EQUAL, list.getOwner());
        FilterPredicate filter3 = new FilterPredicate("id", FilterOperator.EQUAL, item.getId());
        List list = new ArrayList();
        list.add(filter1);
        list.add(filter2);
        list.add(filter3);
        CompositeFilter filter = new CompositeFilter(Query.CompositeFilterOperator.AND, list);
        query.setFilter(filter);
        PreparedQuery pq = datastore.prepare(query);

        Entity eItem = pq.asSingleEntity();
        if (eItem == null) {
            System.out.println("Item not in datastore");
        }
        else {
            datastore.delete(eItem.getKey());
            System.out.println("Deleted the item from the datastore.");
        }

        return "redirect:home";
    }

    @RequestMapping(value = "/up-todo", method = RequestMethod.GET)
    public String itemUp(@RequestParam("id") int id) {

        list.moveItemUp(id);

        return "redirect:home";
    }

    @RequestMapping(value = "/down-todo", method = RequestMethod.GET)
    public String itemDown(@RequestParam("id") int id) {

        list.moveItemDown(id);

        return "redirect:home";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createNew() {

        list.reset();

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

    @RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listTodos() {
        //List<String> list = new ArrayList<String>();
        List<ToDoItem> theList = list.getItems();
        //return back to home.jsp
        ModelAndView model = new ModelAndView("home");
        model.addObject("todos", theList);
        model.addObject("listName", list.getName());

        String isPub = "";
        if(pub)
            isPub = "checked";
        model.addObject("isPub", isPub);

        return model;
    }

    @RequestMapping(value = "/setname", method = RequestMethod.GET)
    public String setName(@RequestParam("nameoflist") String name) {
        System.out.println("NAME: " + name);
        list.setName(name);

        return "redirect:home";
    }

    @RequestMapping(value = "/setpub", method = RequestMethod.GET)
    public String setName(@RequestParam("pub") boolean isPub) {
        pub = isPub;

        return "redirect:home";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String saveList(@RequestParam("name") String name, @RequestParam("owner") String owner,
                           @RequestParam("pub") boolean pub) {
        System.out.println("name: " + name + "; owner: " + owner);

        list.setName(name);
        list.setOwner(owner);

        //first save the actual list -- delete the list from the datastore if it's there and re-save it
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Query query = new Query("ToDoList");
        FilterPredicate filter1 = new FilterPredicate("name", FilterOperator.EQUAL, name);
        FilterPredicate filter2 = new FilterPredicate("owner", FilterOperator.EQUAL, owner);
        List elist = new ArrayList();
        elist.add(filter1);
        elist.add(filter2);
        CompositeFilter filter = new CompositeFilter(Query.CompositeFilterOperator.AND, elist);
        query.setFilter(filter);
        PreparedQuery pq = datastore.prepare(query);

        Entity tdList = pq.asSingleEntity();

        if (tdList == null) {
            System.out.println("List is not in dictionary.");
        }
        else {
            datastore.delete(tdList.getKey());
            System.out.println("List deleted.");
        }

        //now save the list
        Entity todolist = new Entity("ToDoList");

        todolist.setProperty("name", name);
        todolist.setProperty("owner", owner);
        todolist.setProperty("pub", pub);

        datastore.put(todolist);

        //and save the items
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSaved())
                continue;

            Entity todoitem = new Entity("ToDoItem");
            todoitem.setProperty("id", Integer.toString(list.get(i).getId()));
            todoitem.setProperty("listname", name);
            todoitem.setProperty("owner", owner);
            todoitem.setProperty("category", list.get(i).getCategory());
            todoitem.setProperty("description", list.get(i).getDescription());
            todoitem.setProperty("startDate", list.get(i).getStartDate());
            todoitem.setProperty("endDate", list.get(i).getEndDate());
            todoitem.setProperty("completed", list.get(i).getCompleted());

            datastore.put(todoitem);
            list.get(i).setSaved(true);
        }

        return "redirect:home";
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public ModelAndView loadList(@RequestParam("owner") String owner) {
        List<ToDoList> resList = new ArrayList<>();

        //fetch the datastore for each list
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Query query = new Query("ToDoList");
        FilterPredicate filter1 = new FilterPredicate("pub", FilterOperator.EQUAL, true);
        FilterPredicate filter2 = new FilterPredicate("owner", FilterOperator.EQUAL, owner);
        List elist = new ArrayList();
        elist.add(filter1);
        elist.add(filter2);
        CompositeFilter filter = new CompositeFilter(Query.CompositeFilterOperator.OR, elist);
        query.setFilter(filter);
        PreparedQuery pq = datastore.prepare(query);

        //for each list found, create a new list object and put it in the result list
        for (Entity lists : pq.asIterable()) {
            String name = (String)lists.getProperty("name");
            String owner1 = (String)lists.getProperty("owner");
            System.out.print("NAME: " + name);
            System.out.print(" OWNER: " + owner + "\n");

            ToDoList tempList = new ToDoList();
            tempList.setName(name);
            tempList.setOwner(owner1);

            resList.add(tempList);
        }

        ModelAndView model = new ModelAndView("load");
        model.addObject("lists", resList);

        return model;
    }

    @RequestMapping(value = "/load-list", method = RequestMethod.GET)
    public String loadListFromDB(@RequestParam("name") String name, @RequestParam("owner") String owner) {

        list.reset();

        //fetch the datastore for each list
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Query query = new Query("ToDoItem");
        System.out.println("NAME " + name);
        System.out.println("OWNER " + owner);
        FilterPredicate filter1 = new FilterPredicate("listname", FilterOperator.EQUAL, name);
        FilterPredicate filter2 = new FilterPredicate("owner", FilterOperator.EQUAL, owner);
        List elist = new ArrayList();
        elist.add(filter1);
        elist.add(filter2);
        CompositeFilter filter = new CompositeFilter(Query.CompositeFilterOperator.AND, elist);
        query.setFilter(filter);
        PreparedQuery pq = datastore.prepare(query);

        for (Entity lists : pq.asIterable()) {
            int id = Integer.parseInt((String)lists.getProperty("id"));
            String category = (String)lists.getProperty("category");
            System.out.println("HELLO");
            String description = (String)lists.getProperty("description");
            Date startDate = (Date)lists.getProperty("startDate");
            Date endDate = (Date)lists.getProperty("endDate");
            boolean completed = (boolean)lists.getProperty("completed");

            ToDoItem tempItem = new ToDoItem();
            tempItem.setId(id);
            tempItem.setCategory(category);
            tempItem.setDescription(description);
            tempItem.setStartDate(startDate);
            tempItem.setEndDate(endDate);
            tempItem.setCompleted(completed);
            tempItem.setSaved(false);

            if(list.getById(id) == null)
                list.addItem(tempItem);
        }

        System.out.println(Integer.toString(list.size()));

        return "redirect:home";
    }
}