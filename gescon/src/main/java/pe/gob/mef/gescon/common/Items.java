/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.common;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;
import org.apache.commons.beanutils.BeanUtils;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Jonatan Jacobo
 * @param <T>
 */
public class Items<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<T> sourceList;
    private List<SelectItem> selectItems;
    private final SelectItem firstItem;
    private String label;
    private String value;
    private boolean effectIfJustSingleResult;
    public static final Integer NULL_VALUE = -1;
    public static final String FIRST_ITEM_SELECT = "Seleccione";
    public static final String FIRST_ITEM_ALL = "Todos";
    public static final String FIRST_ITEM_ANY = "Cualquiera";
    public static final String FIRST_ITEM_MARKS = "-------";
    public static final String FIRST_ITEM_BLANK = "";

    /**
     * Metodo Items este metodo sirve para que sea empleado en las listas desplegables
     *
     * @param list de tipo List
     * @param firstItem de tipo String
     * @param firstItemDisabled de tipo boolean
     * @param id de tipo String
     * @param descripcion de tipo String
     */
    public Items(List<T> list, String firstItem, boolean firstItemDisabled, String id, String descripcion) {
        this.sourceList = list;
        this.firstItem = getFirstItem(firstItem, firstItemDisabled);
        this.value = id;
        this.label = descripcion;
    }

    /**
     * Metodo Items
     * @param list de tipo List
     * @param firstItem de tipo String
     * @param id de tipo String
     * @param descripcion de tipo String
     */
    public Items(List<T> list, String firstItem, String id, String descripcion) {
        this.sourceList = list;
        this.firstItem = getFirstItem(firstItem, false);
        this.value = id;
        this.label = descripcion;
    }

    /**
     * Metodo Items
     * @param list de tipo List
     * @param firstItem de tipo String
     * @param id de tipo String
     * @param descripcion de tipo String
     * @param effectIfJustSingleResult de tipo boolean
     */
    public Items(List<T> list, String firstItem, String id, String descripcion, boolean effectIfJustSingleResult) {
        this.sourceList = list;
        this.firstItem = getFirstItem(firstItem, false);
        this.value = id;
        this.label = descripcion;
        this.effectIfJustSingleResult = effectIfJustSingleResult;
    }

    /**
     * Metodo Items
     * @param list de tipo List
     * @param id de tipo String
     * @param descripcion de tipo String
     */
    public Items(List<T> list, String id, String descripcion) {
        this.sourceList = list;
        this.firstItem = null;
        this.value = id;
        this.label = descripcion;
    }

    /**
     * Metodo getItems
     * @return selectItems
     */
    public List<SelectItem> getItems() {
        if (selectItems == null) {
            selectItems = getItems(sourceList);
            sourceList = null;
        }
        return selectItems;
    }

    /**
     * Metodo getItemsNoFirst
     * @return ret de tipo List
     */
    public List<SelectItem> getItemsNoFirst() {
        List<SelectItem> ret;
        if (firstItem == null || getItems().isEmpty()) {
            ret = getItems();
        } else {
            ret = getItems().subList(1, getItems().size());
        }
        return ret;
    }

    /**
     * Metodo remove
     * @param value de tipo Object
     */
    public void remove(Object value) {
        if (value == null) {
            return;
        }
        Iterator iter = getItems().iterator();
        while (iter.hasNext()) {
            SelectItem item = (SelectItem) iter.next();
            if (value.toString().equals(item.getValue())) {
                iter.remove();
            }
        }
        if (getItems().size() == 1 && firstItem != null) {
            getItems().clear();
        }
    }

    /**
     * Metodo add
     * @param obj de tipo Object
     */
    public void add(Object obj) {
        if (obj == null) {
            return;
        }
        if (getItems().isEmpty() && firstItem != null) {
            getItems().add(firstItem);
        }
        getItems().add(getSelectItem(obj));
    }

    /**
     * Metodo getValue
     * @param i de tipo Int
     * @return Items
     */
    public String getValue(int i) {
        return getItems().get(i).getValue().toString();
    }

    /**
     * Metodo toString
     * @return ret de tipo  String
     */
    @Override
    public String toString() {
        String ret = "";
        for (SelectItem item : getItems()) {
            ret = ret + "value: " + item.getValue() + "; label: " + item.getLabel() + "/n";
        }
        return ret;
    }

    /**
     * Metodo getItems
     * @param list de tipo List
     * @return items
     */
    private List<SelectItem> getItems(List list) {
        List<SelectItem> items = new ArrayList<SelectItem>();
        if (list != null && !list.isEmpty()) {
            if (firstItem != null && (!effectIfJustSingleResult || list.size() > 1)) {
                items.add(firstItem);
            }
            for (Object list1 : list) {
                if (list1 != null) {
                    SelectItem item = getSelectItem(list1);
                    if (item != null && StringUtils.isNotBlank(item.getLabel())) {
                        items.add(item);
                    }
                }
            }

        }else{
            if (firstItem != null) {
                items.add(firstItem);
            }
        }

        return items;
    }

    /**
     * Metodo getSelectItem
     * @param obj
     * @return item
     */
    protected SelectItem getSelectItem(Object obj) {
        SelectItem item = null;
        //Se verificara que los objetos de la lista sean estructuras propias del sistema y no de java lang
        if (obj.getClass().getName().contains("java.lang")) {
            item = new SelectItem(obj, obj.toString());
        } else {
            try {
                item = new SelectItem(BeanUtils.getProperty(obj, this.value), BeanUtils.getProperty(obj, this.label));
//item = new SelectItem(Integer.parseInt(BeanUtils.getProperty(obj, this.value)),BeanUtils.getProperty(obj, this.label));
            } catch (IllegalAccessException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return item;
    }

    /**
     * Metodo getFirstItem
     * @param firstItemString
     * @param disabled
     * @return
     */
    private SelectItem getFirstItem(String firstItemString, boolean disabled) {
        return firstItemString == null ? null : new SelectItem(NULL_VALUE, firstItemString, null,
                disabled);
    }

    /**
     * Returns the number of items, not counting the optional first items if it is present.
     * @return number of items in the list
     */
    public int size() {
        int size = getItems().size();
        if (firstItem != null && size > 0) {
            size--;
        }
        return size;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isEffectIfJustSingleResult() {
        return effectIfJustSingleResult;
    }

    public void setEffectIfJustSingleResult(boolean effectIfJustSingleResult) {
        this.effectIfJustSingleResult = effectIfJustSingleResult;
    }
}