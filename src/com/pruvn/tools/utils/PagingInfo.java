/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pruvn.tools.utils;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Owner
 */
public class PagingInfo
{
    private int totalItems;
    private int itemsPerPage = 10;
    private List<Integer> listOfItemsPerPage;

    private int currentPage = 1;

    public PagingInfo()
    {
       this.listOfItemsPerPage = new ArrayList<Integer>();
       this.listOfItemsPerPage.add(10);
       this.listOfItemsPerPage.add(20);
       this.listOfItemsPerPage.add(50);
       this.listOfItemsPerPage.add(100);
    }

    public int getTotalPages()
    {
         return (int)Math.ceil((float)getTotalItems() / getItemsPerPage());
    }

    /**
     * @return the TotalItems
     */
    public int getTotalItems() {
        return totalItems;
    }

    /**
     * @param TotalItems the TotalItems to set
     */
    public void setTotalItems(int TotalItems) {
        this.totalItems = TotalItems;
    }

    /**
     * @return the ItemsPerPage
     */
    public int getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * @return the CurrentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param ItemsPerPage the ItemsPerPage to set
     */
    public void setItemsPerPage(int ItemsPerPage) {
        this.itemsPerPage = ItemsPerPage;
    }

    /**
     * @param CurrentPage the CurrentPage to set
     */
    public void setCurrentPage(int CurrentPage) {
        this.currentPage = CurrentPage;
    }

    /**
     * @return the listOfItemsPerPage
     */
    public List<Integer> getListOfItemsPerPage() {
        //ArrayList<Integer> a = (ArrayList<Integer>) listOfItemsPerPage;
       // List<Integer> list = (List<Integer>) a.clone();
       // list.remove(this.itemsPerPage);
        return listOfItemsPerPage;
    }

    /**
     * @param listOfItemsPerPage the listOfItemsPerPage to set
     */
    public void setListOfItemsPerPage(List<Integer> listOfItemsPerPage) {
        this.listOfItemsPerPage = listOfItemsPerPage;
    }
}
