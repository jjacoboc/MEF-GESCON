/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.mef.gescon.util;

import java.util.Comparator;

/**
 *
 * @author jjacobo
 */
public class ObjectComparator implements Comparator{

    /** Creates a new instance of ObjectComparator */
    public ObjectComparator() {
    }
    
    @Override
    public int compare(Object p1, Object p2) {
        if((p1==null) && (p2==null)) return 0;
        if((p1==null) && (p2!=null)) return Integer.MIN_VALUE;
        if((p1!=null) && (p2==null)) return Integer.MAX_VALUE;
        Class c1=null,c2=null;
        c1=p1.getClass() ;c2=p2.getClass();
        if(c1.equals(c2))
        {   if (Comparable.class.isAssignableFrom(c1))
                return ((Comparable)p1).compareTo(p2);
            else
                if (c1.isPrimitive())
                {   //if it is a boolean order 
                    if(c1.getClass().toString().equals(Boolean.TYPE.toString()))
                    {   boolean b1=false,b2=false;
                        try{
                            b1=((Boolean)p1);
                            b2=((Boolean)p2);
                        }catch(Exception e){
                            throw new ClassCastException("Datos primitivos no comparables");
                        }
                        return (b1==b2)?0:(b1?-1:1);
                    }
                    else
                        throw new ClassCastException("Datos primitivos no comparables");
                }
                else return p1.toString().compareTo(p2.toString());
        }
        else
        {
            if(!c1.isAssignableFrom(c2) && !c2.isAssignableFrom(c1))
                throw new ClassCastException("Parámetros de clases diferentes");
            else {
                if(Comparable.class.isAssignableFrom(c1) &&
                    Comparable.class.isAssignableFrom(c2))
                try{
                    return ((Comparable)p1).compareTo(p2);
                }catch(Exception e){
                    return p1.toString().compareTo(p2.toString());
                }
                else
                    return p1.toString().compareTo(p2.toString());
            }
        }
    }
}
