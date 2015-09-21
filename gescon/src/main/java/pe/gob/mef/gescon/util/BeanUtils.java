/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.mef.gescon.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jjacobo
 */
public class BeanUtils {

    /** Creates a new instance of BeanUtil */
    public BeanUtils() {
    }
    
    public static int setProperties(Object o,String[] attributes,Object[] values)
    {   
        //Compare the size of arrays
        if (attributes.length!=values.length) return 0;
        int counter=0;
        for (int i=0;i<attributes.length;i++)
        {
            try{  
                BeanUtils.setProperty(o,attributes[i],values[i]);
                counter++;
            }catch(NoSuchMethodException ignore){} catch (IllegalAccessException ignore) {    
            } catch (InvocationTargetException ignore) {
            }    
        }
        return counter;
    }
    
    public static void setProperty(Object o,String attribute, Object value)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Class objectClass=o.getClass(),valueClass=value.getClass();
        String setterMethodName="set"+String.valueOf(attribute.charAt(0)).toUpperCase()+
            attribute.substring(1);        
        Method setterMethod=null;
        do{ Class[] parameterTypes={ valueClass };
            try{
                setterMethod=objectClass.getMethod(setterMethodName,parameterTypes);
                break;
            }catch(NoSuchMethodException e){/*Didnt find the method, then continue*/} catch (SecurityException e) {
                /*Didnt find the method, then continue*/
            }
        }while ((valueClass=valueClass.getSuperclass())!=null);
        //if value is of a primitive Type try to get the TYPE class
        if (setterMethod==null){
            try{
                Field at=value.getClass().getField("TYPE");
                Class parameterTypes[]=new Class[1];
                parameterTypes[0]=(Class)at.get(value);
                setterMethod=objectClass.getMethod(setterMethodName, parameterTypes);
            }catch(NoSuchFieldException nsfe){
                System.out.println("No such field");
            }catch(ClassCastException cce){
                System.out.println("field TYPE is not of type CLASS");                
            }catch(NoSuchMethodException nsme){}
        }
        if (setterMethod!=null){
            Object[] args={ value };
            setterMethod.invoke(o, args);
        }else throw new NoSuchMethodException("La clase (bean) "+o.getClass()+
            "no contiene el atributo " + attribute);        
    }
    
     public static Object getProperty(Object o,String attribute)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Class objectClass=o.getClass();
        String getterMethodName="get"+String.valueOf(attribute.charAt(0)).toUpperCase()+
            attribute.substring(1);
        Method getterMethod=null;
        try{
            getterMethod=objectClass.getMethod(getterMethodName, (Class) null);
        }catch(NoSuchMethodException e)
        {   //in case its a boolean attribute
            getterMethodName="is"+String.valueOf(attribute.charAt(0)).toUpperCase()+
                attribute.substring(1);
            try{
                getterMethod=objectClass.getMethod(getterMethodName, (Class) null);
            }catch(NoSuchMethodException nsme)
            {
                throw new NoSuchMethodException("La clase (bean) "+o.getClass()+
                    " no contiene el atributo " + attribute);  
            }
        }
        return getterMethod.invoke(o, (Object) null);
    }
     
    public static HashMap separateByAttribute(Collection beanList,String attributeName)
        throws InstantiationException, IllegalAccessException
    {
        Object obj=null,attribute=null;
        Class collectionClass=beanList.getClass();
        Collection subColl=null;
        HashMap result=new HashMap();
        Iterator it=beanList.iterator();
        while(it.hasNext())
        {
            obj=it.next();
            if (obj!=null)
            {
                try{
                    attribute=getProperty(obj,attributeName);
                    if (attribute==null) continue;
                }catch (NoSuchMethodException e)
                {continue;} catch (IllegalAccessException e) {
                    continue;
                } catch (InvocationTargetException e) {
                    continue;
                }
                subColl=(Collection)result.get(attribute);
                if (subColl==null){ 
                    subColl=(Collection)collectionClass.newInstance();
                    result.put(attribute,subColl);
                }
                subColl.add(obj);
            }
        } 
        return result;
    }
    
    public static void orderListByAttribute(List beanList,String attributeName)
        throws InstantiationException, IllegalAccessException
    {
        Comparator c=new BeanAttributeComparator(attributeName);
        Collections.sort(beanList,c);
    }
    
    public static void orderListByValueAttribute(List beanList, List keyAtributeList,
        String keyName, String valueName,String beanKeyName)
    {
        Comparator c=new ReplaceKeyByValueBeanComparator(keyAtributeList,
            keyName, valueName,beanKeyName);
        Collections.sort(beanList,c);
    }

}
