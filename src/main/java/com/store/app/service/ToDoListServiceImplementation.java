package com.store.app.service;

import com.google.appengine.api.datastore.*;
import com.store.app.domain.Item;
import com.store.app.domain.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

import static com.store.app.domain.Item.*;
import static com.store.app.domain.ToDoList.*;

/**
 * Created by Chuntak on 2/12/2017.
 */
@Service
public class ToDoListServiceImplementation implements ToDoListServiceInterface {
    DatastoreService datastoreService;
    @Autowired
    public ToDoListServiceImplementation(DatastoreServiceFactoryInterface datastoreServiceFactory) {
        this.datastoreService = datastoreServiceFactory.getDatastoreService();
    }


    public boolean saveItemEntity(final long listId, final String category, final String description,
                                  final Date startDate, final Date endDate, final boolean completed, final int positionInList, final long id) {
        Item it = new Item(listId, category, description, startDate, endDate, completed, positionInList, id);
        Entity itEntity = it.getEntity();
        return SaveTransactions(itEntity);
    }

    public boolean saveDoListEntity(final String email, final boolean priv, final String listName, final long id){
        ToDoList tdl = new ToDoList(email, priv, listName, id);
        Entity toDoEntity = tdl.getEntity();
        return SaveTransactions(toDoEntity);
    }

    public Entity updateDoListEntity(Entity entity) {
       // ToDoList tdl = new ToDoList( (String) entity.getProperty(EMAIL),(Boolean) entity.getProperty(PRIVATE),(String) entity.getProperty(LIST_NAME));
        Entity ent = getEntityByID(entity, TO_DO_LIST_ENTITY);
        if(ent != null) {
            if (entity.hasProperty(EMAIL)) ent.setProperty(EMAIL, entity.getProperty(EMAIL));
            if (entity.hasProperty(PRIVATE)) ent.setProperty(PRIVATE, entity.getProperty(PRIVATE));
            if (entity.hasProperty(LIST_NAME)) ent.setProperty(LIST_NAME, entity.getProperty(LIST_NAME));
            SaveTransactions(ent);
        }
        return ent;
    }

    public Entity updateItemEntity(Entity entity){
        Entity ent = getEntityByID(entity, ITEM_ENTITY);
        if(ent != null) {
            if (entity.hasProperty(LIST_ID)) ent.setProperty(LIST_ID, entity.getProperty(LIST_ID));
            if (entity.hasProperty(CATEGORY)) ent.setProperty(CATEGORY, entity.getProperty(CATEGORY));
            if (entity.hasProperty(DESCRIPTION)) ent.setProperty(DESCRIPTION, entity.getProperty(DESCRIPTION));
            if (entity.hasProperty(START_DATE)) ent.setProperty(START_DATE, entity.getProperty(START_DATE));
            if (entity.hasProperty(END_DATE)) ent.setProperty(END_DATE, entity.getProperty(END_DATE));
            if (entity.hasProperty(COMPLETED)) ent.setProperty(COMPLETED, entity.getProperty(COMPLETED));
            if (entity.hasProperty(POSITION_IN_LIST)) ent.setProperty(POSITION_IN_LIST, entity.getProperty(POSITION_IN_LIST));
            SaveTransactions(ent);
        }
        return ent;
    }

    private Entity getEntityByID(Entity entity, String kind){
        Query query = new Query(kind);
        Entity ent = null;
        for (Entity e : datastoreService.prepare(query).asIterable()){
            if(((Long)e.getProperty(ID)).equals((Long)entity.getProperty(ID))){
                ent = e;
                break;
            }
        }
        return ent;
    }

    private boolean SaveTransactions(Entity entity){
        saveTransactions(entity);
        entity.setProperty(ID,entity.getKey().getId());
        return saveTransactions(entity);
    }

    private boolean saveTransactions(Entity entity){
        boolean saved = false;
        Transaction transaction = datastoreService.beginTransaction();
        try {
            // save entity in dataStore
            Key key = datastoreService.put(transaction, entity);
            transaction.commit();
            saved = true;
        } catch(Exception e) {
            saved = false;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
                saved = false;
            }
        }
        return saved;
    }

    @Override
    public ArrayList<ToDoList> getToDoListArrayEntity(ToDoList tDL) {
        ArrayList<ToDoList> tDLArray = new ArrayList<ToDoList>();
        Query query = new Query(TO_DO_LIST_ENTITY);
        for (Entity entity : datastoreService.prepare(query).asIterable()) {
            ToDoList model = new ToDoList(entity);
            tDLArray.add(model);
        }
        return tDLArray;
    }

    public ArrayList<ToDoList> getToDoListArrayByEmail(ToDoList tDL) {
        ArrayList<ToDoList> filteredList = new ArrayList<ToDoList>();
        for(ToDoList tdl : getToDoListArrayEntity(tDL)){
            if(tdl.getEmail().equals(tDL.getEmail())){
                filteredList.add(tdl);
            }
        }
        return filteredList;
    }

    public ArrayList<ToDoList> getViewableToDoListArray(ToDoList tDL) {
        ArrayList<ToDoList> filteredList = new ArrayList<ToDoList>();
        for(ToDoList tdl : getToDoListArrayEntity(tDL)){
            if(tdl.getEmail().equals(tDL.getEmail())){
                filteredList.add(tdl);
            } else if (tdl.getPrivate() == false){
                filteredList.add(tdl);
            }
        }
        return filteredList;
    }

    public ArrayList<Item> getItemByListID(ToDoList tDL) {
        ArrayList<Item> itemArray = new ArrayList<Item>();
        Query query = new Query(ITEM_ENTITY);
        for(Entity entity : datastoreService.prepare(query).asIterable()) {
            if(((Long)entity.getProperty(LIST_ID)).equals(tDL.getID())){
                Item model = new Item(entity);
                itemArray.add(model);
            }
        }
     //   itemArray.add(new Item());
        return itemArray;
    }

    public boolean deleteItemEntity(Entity entity) {
        Entity ent = getEntityByID(entity, ITEM_ENTITY);
        boolean deleted = false;
        Transaction transaction = datastoreService.beginTransaction();
        try {
            // save entity in dataStore
            datastoreService.delete(ent.getKey());
            transaction.commit();
            deleted = true;
        } catch(Exception e) {
            deleted = false;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
                deleted = false;
            }
        }
        return deleted;
    }

    private boolean entityExist(Entity entity){
        Query query = new Query(entity.getKey().getKind());
        for (Entity e : datastoreService.prepare(query).asIterable()) {
            if(((Long)e.getProperty(ID)).equals((Long)entity.getProperty(ID)))
                return true;
        }
        return false;
    }

    public boolean updateAllDoListEntity(ArrayList<ToDoList> toDoListList){
        for(ToDoList tdl : toDoListList){
            if(entityExist(tdl.getEntity())){
                updateDoListEntity(tdl.getEntity());
            } else{
                saveDoListEntity(tdl.getEmail(),tdl.getPrivate(),tdl.getListName(), tdl.getID());
            }
        }
        return true;
    }

    public boolean updateAllItemEntity(ArrayList<Item> itemList){
        for(Item iL : itemList){
            if(entityExist(iL.getEntity())){
                updateItemEntity(iL.getEntity());
            } else {
                saveItemEntity(iL.getListId(),iL.getCategory(),iL.getDescription(),iL.getStartDate(),iL.getEndDate(),iL.getCompleted(), iL.getPositionInList(), iL.getID());
            }
        }
        return true;
    }

//    @Override
//    public ToDoList getToDoListEntity(ToDoList tDL) {
//        Query query = new Query(TO_DO_LIST_ENTITY);
//        Query.Filter keyFilter =
//                new Query.FilterPredicate(Entity.KEY_RESERVED_PROPERTY, Query.FilterOperator.EQUAL, tDL.getEntity().getKey());
//        query.setFilter(keyFilter);
//        ToDoList model = new ToDoList(datastoreService.prepare(query).asSingleEntity());
//        return model;
//    }


}
