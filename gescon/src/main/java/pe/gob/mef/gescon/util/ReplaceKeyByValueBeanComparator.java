/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.mef.gescon.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jjacobo
 */
public class ReplaceKeyByValueBeanComparator implements Comparator{

    private HashMap values;
    private String keyName=null;
    private String valueName=null;
    private String beanKeyName=null;
    private ObjectComparator objectComparator=null;
    
    /** Creates a new instance of ReplaceKeyByValueBeanComparator
     * @param list
     * @param keyName
     * @param valueName
     * @param beanKeyName */
    public ReplaceKeyByValueBeanComparator(List list,String keyName,String valueName,
        String beanKeyName)throws IllegalArgumentException
    {
        values=new HashMap();
        Object obj=null;
        this.keyName=keyName;
        this.valueName=valueName;
        this.beanKeyName=beanKeyName;
        if(list.isEmpty()) throw new IllegalArgumentException("Lista vacia");
        Iterator it= list.iterator();
        while(it.hasNext())
        {
            obj=it.next();
            Object key,value;
            try{
                key=BeanUtils.getProperty(obj, keyName);
            }catch(NoSuchMethodException e)
            {
                throw new IllegalArgumentException("No existe el atributo para la llave en el bean");
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("No existe el atributo para la llave en el bean");
            } catch (InvocationTargetException e) {
                throw new IllegalArgumentException("No existe el atributo para la llave en el bean");
            }
            try{
                value=BeanUtils.getProperty(obj, valueName);
            }catch(NoSuchMethodException e)
            {
                throw new IllegalArgumentException("No existe el atributo para el valor en el bean");
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("No existe el atributo para el valor en el bean");
            } catch (InvocationTargetException e) {
                throw new IllegalArgumentException("No existe el atributo para el valor en el bean");
            }
            obj=values.put(key, value);
            if(obj!=null) throw new IllegalArgumentException("Llave repetida "+key);
        }
        
        objectComparator=new ObjectComparator();
    }
    
    @Override
    public int compare(Object o1, Object o2) {
        try{
            Object value1,value2;
            value1=values.get(BeanUtils.getProperty(o1, beanKeyName));
            value2=values.get(BeanUtils.getProperty(o2, beanKeyName));
            System.out.println("Value1 :"+value1+",value2 :"+value2);
            if(value1.equals(value2)) return 0;
            else return objectComparator.compare(value1,value2);
        }catch(NoSuchMethodException e)
        {
            return 0;
        } catch (IllegalAccessException e) {
            return 0;
        } catch (InvocationTargetException e) {
            return 0;
        }
    }

}
