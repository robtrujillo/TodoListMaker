package com.store.app.service;

import com.google.appengine.api.datastore.Entity;
import com.store.app.domain.Item;
import com.store.app.domain.ToDoList;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Chuntak on 2/12/2017.
 */
public interface ToDoListServiceInterface {
    public boolean saveItemEntity(final long listId, final String category, final String description,
                                  final Date startDate, final Date endDate, final boolean completed, final int positionsInList, final long id);
    public boolean saveDoListEntity(final String email, final boolean priv, final String listName, final long iD);
    public ArrayList<ToDoList> getToDoListArrayEntity(ToDoList tDL);
    public ArrayList<ToDoList> getToDoListArrayByEmail(ToDoList tDL);
    public ArrayList<Item> getItemByListID(ToDoList tDL);
    public Entity updateDoListEntity(Entity entity);
    public Entity updateItemEntity(Entity entity);
    public boolean deleteItemEntity(Entity entity);

    public boolean updateAllDoListEntity(ArrayList<ToDoList> toDoListList);
    public boolean updateAllItemEntity(ArrayList<Item> itemList);
//    public ToDoList getToDoListEntity(ToDoList tDL);
}
