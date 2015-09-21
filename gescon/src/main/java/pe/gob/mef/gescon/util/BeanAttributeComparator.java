/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.mef.gescon.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 *
 * @author jjacobo
 */
public class BeanAttributeComparator implements Comparator{

    private String attributeName=null;
    private ObjectComparator objectComparator=null;
    
    public BeanAttributeComparator(String attributeName)
        throws IllegalArgumentException
    {   this.attributeName=attributeName.trim();
        if( (this.attributeName.length()==0 )||
            (!Pattern.matches("^[a-zA-Z_]\\w*$",this.attributeName) ))
            throw new IllegalArgumentException("incorrect attributeName");    
        objectComparator=new ObjectComparator();
    }
    
    @Override
    public int compare(Object o1, Object o2) throws ClassCastException
    {
        Object p1=null,p2=null;
        try{
            p1=BeanUtils.getProperty(o1,attributeName);
        }catch(NoSuchMethodException ignore1){} catch (IllegalAccessException ignore1) {
        } catch (InvocationTargetException ignore1) {
        }
        try{
            p2=BeanUtils.getProperty(o2,attributeName);
        }catch(NoSuchMethodException ignore2){} catch (IllegalAccessException ignore2) {
        } catch (InvocationTargetException ignore2) {
        }
        return objectComparator.compare(p1,p2);
    }
}
