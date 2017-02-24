package com.store.app.controller;

import com.google.appengine.api.datastore.Entity;
import com.store.app.domain.Item;
import com.store.app.domain.ToDoList;
import com.store.app.service.ToDoListServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    ToDoListServiceInterface toDoListService;

    @Autowired
    public HomeController(ToDoListServiceInterface todoListService){
        this.toDoListService = todoListService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "Home/login";
    }

//    @RequestMapping(value = "/userModel", method = RequestMethod.GET)
//    public ModelAndView userModel() {
//        return new ModelAndView("userModel", "command", new UserModel());
//    }

    /*
    * Returns main page after login
    * */
    @RequestMapping(value = "/loadPage", method = RequestMethod.GET)
    public String loadPage(ModelMap map){
        return "Home/index";
    }

    /*
    * Returns index page after logout
    * */
    @RequestMapping(value = "/loadPage_index", method = RequestMethod.GET)
    public String loadPage_index(ModelMap map){
        return "Home/login";
    }

    /*
    * Fake Mapping to test controller from AngularJS http request
    * */
    @RequestMapping(value = "/getMessages", method = RequestMethod.GET)
    public @ResponseBody ArrayList<ToDoList> getMessages(@ModelAttribute("TodoList") ToDoList tDL){
        return toDoListService.getToDoListArrayEntity(tDL);
    }


    /*
    * Get all the lists that the owner created
    * */
    @RequestMapping(value = "/getMyLists", method = RequestMethod.GET)
    public @ResponseBody ArrayList<ToDoList> getMyLists(@ModelAttribute("TodoList") ToDoList user){
        return toDoListService.getToDoListArrayByEmail(user);
    }

    /*
   * Get all the viewable lists
   * */
    @RequestMapping(value = "/getViewableLists", method = RequestMethod.GET)
    public @ResponseBody ArrayList<ToDoList> getViewableLists(@ModelAttribute("TodoList") ToDoList user){
        return toDoListService.getViewableToDoListArray(user);
    }

    /*
    * Get all the lists that the owner created and all public lists
    * */
    @RequestMapping(value = "/getAllLists", method = RequestMethod.GET)
    public @ResponseBody ArrayList<ToDoList> getAllLists(@ModelAttribute("TodoList") ToDoList user){
        return toDoListService.getToDoListArrayEntity(user);
    }

    /*
    * Get all the items based on the listId
    * */
    @RequestMapping(value = "/getItems", method = RequestMethod.GET)
    public @ResponseBody ArrayList<Entity> getItemsEntity(@ModelAttribute("TodoList") Item item){
        //int listId = list.();
        ArrayList<Entity> ie = toDoListService.getItemByListID(item);
        return ie;
    }

    /*
    *  Add all list
     */
    @RequestMapping(value = "/addAllList", method = RequestMethod.GET)
    public @ResponseBody boolean addAllList(@ModelAttribute("TodoListModel") ArrayList<ToDoList> TDL ){
        return true;//toDoListService.updateAllDoListEntity(listList);
    }



    /*
    * Add a new list returns the list that it saved
    * */
    @RequestMapping(value = "/addList", method = RequestMethod.GET)
    public @ResponseBody ToDoList addList(@ModelAttribute("TodoListModel") ToDoList list){
        return new ToDoList(toDoListService.saveDoListEntity(list.getEmail(), list.getPrivate(), list.getListName(), list.getID()));
    }

    /*
*  Add all item
 */
    @RequestMapping(value = "/addAllItem", method = RequestMethod.POST)
    public @ResponseBody boolean addAllItem(@RequestBody List<Item> itemList){
    //    return toDoListService.updateAllItemEntity(itemList);
        for(Item i : itemList) {
            addItem(i);
        }
        return true;
    }

    @RequestMapping(value = "/updateAllItem", method = RequestMethod.POST)
    public @ResponseBody boolean updateAllItem(@RequestBody List<Item> itemList){
        for(Item i : itemList) {
            updateItem(i);
        }
        return true;
    }


    /*
    * Add a new item, returns entity
    * */
    @RequestMapping(value = "/addItem", method = RequestMethod.GET)
    public @ResponseBody Entity addItem(@ModelAttribute("ItemModel") Item item){
        return toDoListService.saveItemEntity(item.getListId(),item.getCategory(),item.getDescription(),item.getStartDate(),item.getEndDate(),item.getCompleted(),item.getPositionInList(),item.getID());
    }

    /*
    * Update an existing list with new data
    * */
    @RequestMapping(value = "/updateList", method = RequestMethod.GET)
    public @ResponseBody Entity updateList(@ModelAttribute("TodoListModel") ToDoList list){
        //    list = new ToDoList("email", false, "name");
        return toDoListService.updateDoListEntity(list.getEntity());
    }

    /*
    * Update an existing item with new data
    * */
    @RequestMapping(value = "/updateItem", method = RequestMethod.GET)
    public @ResponseBody Entity updateItem(@ModelAttribute("ItemModel") Item item){
        return toDoListService.updateItemEntity(item.getEntity());
    }

    /*
    * Delete an existing list (NOT IN SPECS. COMPLETE LATER)
    * */
    @RequestMapping(value = "/deleteList", method = RequestMethod.GET)
    public @ResponseBody boolean deleteList(@ModelAttribute("TodoListModel") ToDoList list){
        return false;
    }

    /*
    * Delete an existing item
    * */
    @RequestMapping(value = "/deleteItem", method = RequestMethod.GET)
    public @ResponseBody boolean deleteItem(@ModelAttribute("ItemModel") Item item){
        return toDoListService.deleteItemEntity(item.getEntity()) ;
    }

    /*
     Delete all items
     */
    @RequestMapping(value = "/deleteItems", method = RequestMethod.POST)
    public @ResponseBody boolean deleteItems(@RequestBody List<Item> itemList){
        for(Item i : itemList) {
            if(i.getID() != 0) deleteItem(i);
        }
        return true;
    }

}
