package com.mycompany.testproj.domain;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * <PRE>
 * 페이징 정보를 포함하는 List.
 * Apache CFX Web Service framework에서 java collection 을 상속하는 모든 클래스를 세부정보를 제외하고
 * collection으로만 다루는 제약 사항때문에 java.util.List를 상속하지 않음.
 * 단,  List 인터페이스를 상속하지 않을뿐 메소드 구성은 java.util.List와 동일하게 제공한다.
 * <br />
 * Author : "Yongjae Jang"
 * <br />
 * Date   : 2012. 5. 22. 오후 3:14:03
 * <br />
 * History
 * ------------------------------------------------------
 * 2012. 5. 22. / Yongjae Jang : 신규 개발.
 * 2013. 3. 18. / TaeHun Kim   : Entity클래스를 제네릭형식인 T로 변경
 * </PRE>
 */
public class PageList<T> {

    protected List<T> itemList;
    protected Integer itemCount;
    protected Paging paging;

    public PageList() {
        this.itemList = new ArrayList<T>();
        this.paging   = new Paging();
    }

    public PageList(List<T> itemList, BaseSC param) {
        this.itemList = itemList;
        setItemCount(itemList.size());
        paginate();
        if(param != null) {
            paging.setPerPage(param.getItemPerPage());
            paging.setCurrentPage(param.getCurrentPage());
        }
    }

    public PageList(List<T> itemList, int perPage, int currentPage) {
        this.itemList = itemList;
        setItemCount(itemList.size());
        paginate();
        paging.setPerPage(perPage);
        paging.setCurrentPage(currentPage);
    }

    public PageList(List<T> itemList, int itemCount){
        this.itemList = itemList;
        this.itemCount = itemCount;
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
    }

    public List<T> getItemList() {
        return itemList;
    }

    /**
     * 페이징 정보를 갱신한다.
     *
     * @param search
     */
    public void paginate(BaseSC search) {
        paging = new Paging(itemCount, search);
    }

    /**
     * 페이징 정보를 갱신한다.
     *
     * @param search
     */

    public void paginate() {
        paging = new Paging(itemCount);
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    /**
     * Gets the total count.
     *
     * @return the totalCount
     */
    public Integer getItemCount() {
        return itemCount;
    }

    /**
     * Sets the total count.
     *
     * @param totalCount
     *            the totalCount to set
     */
    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
        this.paging.setItemCount(itemCount);
    }

    /**
     * Adds the.
     *
     * @param e
     *            the e
     *
     * @return true, if adds the
     *
     * @see java.util.List#add(java.lang.Object)
     */
    public boolean add(T e) {
        return itemList.add(e);
    }

    /**
     * Adds the.
     *
     * @param index
     *            the index
     * @param element
     *            the element
     *
     * @see java.util.List#add(int, java.lang.Object)
     */
    public void add(int index, T element) {
        itemList.add(index, element);
    }

    /**
     * Adds the all.
     *
     * @param collection
     *            the c
     *
     * @return true, if adds the all
     *
     * @see java.util.List#addAll(java.util.Collection)
     */
    public boolean addAll(Collection<? extends T> collection) {
        return itemList.addAll(collection);
    }

    /**
     * Adds the all.
     *
     * @param index
     *            the index
     * @param collection
     *            the c
     *
     * @return true, if adds the all
     *
     * @see java.util.List#addAll(int, java.util.Collection)
     */
    public boolean addAll(int index, Collection<? extends T> collection) {
        return itemList.addAll(index, collection);
    }

    /**
     * Clear.
     *
     * @see java.util.List#clear()
     */
    public void clear() {
        itemList.clear();
    }

    /**
     * Contains.
     *
     * @param obj
     *            the o
     *
     * @return true, if contains
     *
     * @see java.util.List#contains(java.lang.Object)
     */
    public boolean contains(Object obj) {
        return itemList.contains(obj);
    }

    /**
     * Contains all.
     *
     * @param collection
     *            the c
     *
     * @return true, if contains all
     *
     * @see java.util.List#containsAll(java.util.Collection)
     */
    public boolean containsAll(Collection<?> collection) {
        return itemList.containsAll(collection);
    }

    /**
     * Equals.
     *
     * @param obj
     *            the o
     *
     * @return true, if equals
     *
     * @see java.util.List#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        return itemList.equals(obj);
    }

    /**
     * Gets the.
     *
     * @param index
     *            the index
     *
     * @return the E
     *
     * @see java.util.List#get(int)
     */
    public T get(int index) {
        return itemList.get(index);
    }

    /**
     * Hash code.
     *
     * @return the int
     *
     * @see java.util.List#hashCode()
     */
    public int hashCode() {
        return itemList.hashCode();
    }

    /**
     * Index of.
     *
     * @param obj
     *            the o
     *
     * @return the int
     *
     * @see java.util.List#indexOf(java.lang.Object)
     */
    public int indexOf(Object obj) {
        return itemList.indexOf(obj);
    }

    /**
     * Checks if is empty.
     *
     * @return true, if checks if is empty
     *
     * @see java.util.List#isEmpty()
     */
    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    /**
     * Iterator.
     *
     * @return the iterator< e>
     *
     * @see java.util.List#iterator()
     */
    public Iterator<T> iterator() {
        return itemList.iterator();
    }

    /**
     * Last index of.
     *
     * @param obj
     *            the o
     *
     * @return the int
     *
     * @see java.util.List#lastIndexOf(java.lang.Object)
     */
    public int lastIndexOf(Object obj) {
        return itemList.lastIndexOf(obj);
    }

    /**
     * List iterator.
     *
     * @return the list iterator< e>
     *
     * @see java.util.List#listIterator()
     */
    public ListIterator<T> listIterator() {
        return itemList.listIterator();
    }

    /**
     * List iterator.
     *
     * @param index
     *            the index
     *
     * @return the list iterator< e>
     *
     * @see java.util.List#listIterator(int)
     */
    public ListIterator<T> listIterator(int index) {
        return itemList.listIterator(index);
    }

    /**
     * Removes the.
     *
     * @param index
     *            the index
     *
     * @return the E
     *
     * @see java.util.List#remove(int)
     */
    public T remove(int index) {
        return itemList.remove(index);
    }

    /**
     * Removes the.
     *
     * @param obj
     *            the o
     *
     * @return true, if removes the
     *
     * @see java.util.List#remove(java.lang.Object)
     */
    public boolean remove(Object obj) {
        return itemList.remove(obj);
    }

    /**
     * Removes the all.
     *
     * @param collection
     *            the c
     *
     * @return true, if removes the all
     *
     * @see java.util.List#removeAll(java.util.Collection)
     */
    public boolean removeAll(Collection<?> collection) {
        return itemList.removeAll(collection);
    }

    /**
     * Retain all.
     *
     * @param collection
     *            the c
     *
     * @return true, if retain all
     *
     * @see java.util.List#retainAll(java.util.Collection)
     */
    public boolean retainAll(Collection<?> collection) {
        return itemList.retainAll(collection);
    }

    /**
     * Sets the.
     *
     * @param index
     *            the index
     * @param element
     *            the element
     *
     * @return the E
     *
     * @see java.util.List#set(int, java.lang.Object)
     */
    public T set(int index, T element) {
        return itemList.set(index, element);
    }

    /**
     * Size.
     *
     * @return the int
     *
     * @see java.util.List#size()
     */
    public int size() {
        return itemList.size();
    }

    /**
     * Sub list.
     *
     * @param fromIndex
     *            the from index
     * @param toIndex
     *            the to index
     *
     * @return the list< e>
     *
     * @see java.util.List#subList(int, int)
     */
    public List<T> subList(int fromIndex, int toIndex) {
        return itemList.subList(fromIndex, toIndex);
    }

    /**
     * To array.
     *
     * @return the object[]
     *
     * @see java.util.List#toArray()
     */
    public Object[] toArray() {
        return itemList.toArray();
    }

    /**
     * To array.
     *
     * @param a
     *            the a
     *
     * @return the t[]
     *
     * @see java.util.List#toArray(Type[])
     */
    public <Type> Type[] toArray(Type[] a) {
        return itemList.toArray(a);
    }

    /**
     * PageList to List
     *
     * @return
     */
    public List<T> asList() {
        return itemList;
    }

}
