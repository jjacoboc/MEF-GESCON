/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.mef.gescon.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author jjacobo
 */
public class StringUtil {

    private static final Log log = LogFactory.getLog(StringUtil.class);
    public static final String EMPTY_STRING="";
    public static final String ZERO_STRING="0";
    public static final String NEXT_LINE="\n";
    
    public StringUtil() {
    }

    /**
     * Evalúa si la cadena dada es nula o vacía.
     * @param cadena Cadena a evaluar.
     * @return Verdadero o Falso.
     */
    public static boolean esVacio(String cadena){
        return((cadena==null)||(cadena.trim().length()==0) );
    }

    /**
     * Devuelve una cadena vacía si la cadena recibida es nula,
     * sino devuelve la misma cadena recibida.
     * @param cadena Cadena a evaluar.
     * @return cadena
     */
    public static String nullAsEmptyString(String cadena){
        if(cadena==null) return EMPTY_STRING;
        return cadena;
    }

    /**
     * Devuelve nulo si la cadena recibida es vacía,
     * sino devuelve la misma cadena recibida.
     * @param cadena Cadena a evaluar.
     * @return cadena
     */
    public static String emptyStringAsNull(String cadena){
        if("".equals(cadena)) return null;
        return cadena;
    }
    
    /**
     * Si la cadena pasada es vacia (devuelve true para la funcion esVacio) devuelve una cadena numérica
     * que representa un cero entero :"0". Si no, devuelve la misma cadena
     * @param cadena
     * @return 
     */
    public static String emptyAsZero(String cadena){
        if(esVacio(cadena)) return ZERO_STRING;
        else return cadena;
    }

    /**
     * Invierte el formato de la fecha de dd/mm/yyyy a yyyy/mm/dd o viceversa.
     * @param date Fecha a invertir.
     * @return La fecha invertida.
     */
    public static String invertirFecha(String date){
        
        if(null != date && !"".equals(date)){
            String c = Character.toString(date.charAt(2));
            String newDate;
            if("/".equals(c) || "-".equals(c) || ".".equals(c)){ //formato dd/mm/aaaa
                String day = date.substring(0,2);
                String month = date.substring(3,5);
                String year = date.substring(6,10);
                newDate = year+"/"+month+"/"+day;
            }else{ //formato aaaa/mm/dd
                String day = date.substring(8,10);
                String month = date.substring(5,7);
                String year = date.substring(0,4);
                newDate = day+"/"+month+"/"+year;
            }
            return newDate;
        }else
            return date;
    }

    /**
     * Devuelve el número entero como cadena.
     * @param n
     * @return cad Número entero como cadena.
     */
    public static String integerAsString(long n){
        String cad;
        if(n > 9)
            cad = ""+n;
        else
            cad = "0"+n;
        return cad;
    }
    
    /**
     * Devuelve el número del día del mes como cadena.
     * @param d Número de día del mes.
     * @return cad Número de día del mes.
     */
    public static String getDia(int d){		
        String cad;
        if(d/10 > 0)
            cad = ""+d;
        else
            cad = "0"+d;
        return cad;
    }

    /**
     * Devuelve el día de la semana según el número dado.
     * @param value Número de día de la semana.
     * @return cad Nombre del día de la semana.
     */
    public static String getDiaSemana(int value){      
        String cad = null;
        switch(value){
            case 1 : cad = "Domingo"; break;
            case 2 : cad = "Lunes"; break;
            case 3 : cad = "Martes"; break;
            case 4 : cad = "Miércoles"; break;
            case 5 : cad = "Jueves"; break;
            case 6 : cad = "Viernes"; break;
            case 7 : cad = "Sábado"; break; 
        }
        return cad;
    }

    /**
     * Devuelve el nombre del mes del año según el número dado.
     * @param m Número del mes del año.
     * @return cad Nombre del mes del año.
     */
    public static String getMes(int m){
        String cad = null;
	switch (m) {
            case 0 : cad = "Enero"; break;
            case 1 : cad = "Febrero"; break;
            case 2 : cad = "Marzo"; break;
            case 3 : cad = "Abril"; break;
            case 4 : cad = "Mayo"; break;
            case 5 : cad = "Junio"; break;
            case 6 : cad = "Julio"; break;
            case 7 : cad = "Agosto"; break;
            case 8 : cad = "Septiembre"; break;
            case 9 : cad = "Octubre"; break;
            case 10 : cad = "Noviembre"; break;
            case 11 : cad = "Diciembre"; break;
	}
	return cad;		
    }

    /**
     * Devuelve el número del mes del año como cadena.
     * @param m Número de mes.
     * @return cad Número de mes.
     */
    public static String getMes2(int m){		
        String cad;
        m++;
        if(m/10 > 0)
            cad = ""+m;
        else
            cad = "0"+m;
        return cad;
    }
    
    /**
     * Devuelve la cantidad de meses comprendidos entre la Fecha de Inicio y la Fecha de Fin inclusive.
     * @param fecha1
     * @param fecha2
     * @return int meses: Cantidad de meses.
     */
    public static int getMesesEntre(String fecha1, String fecha2){
        int m1;
        int a1;
        int m2;
        int a2;
        int meses;
        if(fecha1!=null && !"".equals(fecha1)){
            m1 = Integer.parseInt(fecha1.substring(3,5));
            a1 = Integer.parseInt(fecha1.substring(6,10));
        }else{
            m1 = 0;
            a1 = 0;
        }
        if(fecha2!=null && !"".equals(fecha2)){
            m2 = Integer.parseInt(fecha2.substring(3,5));
            a2 = Integer.parseInt(fecha2.substring(6,10));
        }else{
            m2 = 0;
            a2 = 0;
        }
        if(a1==0 && a2==0 && m1==0 && m2==0){
            meses = 0;
        }else{
            meses = (a2*12 + m2) - (a1*12 + m1);
        }
        return meses;
    }

    /**
     * Devuelve la fecha como tipo de dato Date.
     * @param s Fecha como cadena (dd/mm/yyyy)
     * @return Fecha recibida como tipo date.
     */
    public static Date fecha(String s) {
        int year = 0;
        int month = 0;
        int day = 0;
        int firstSlash;
        int secondSlash;
        try{
            if (s == null) throw new java.lang.IllegalArgumentException();

            firstSlash = s.indexOf('/');
            secondSlash = s.indexOf('/', firstSlash+1);
            if ((firstSlash > 0) & (secondSlash > 0) & (secondSlash < s.length()-1)) {
                day = Integer.parseInt(s.substring(0, firstSlash));
                month = Integer.parseInt(s.substring(firstSlash+1, secondSlash))-1;
                year = Integer.parseInt(s.substring(secondSlash+1)) - 1900;
            } else {
                throw new java.lang.IllegalArgumentException();
            }
        }catch(IllegalArgumentException e){
            log.error(e.getMessage());
        }
	return new Date(year, month, day);
    }

    /**
     * Valida si el email dado tiene el formato correcto.
     * @param correo
     * @return True or False.
     */
    public static boolean isEmail(String correo) {
        Pattern pat;
        Matcher mat;
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z]{2,9}.)+([a-zA-Z]{2,9}.)+([a-zA-Z]{2,3}))$");
        //pat = Pattern.compile("^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            return true;
        }else{
            return false;
        }
    }

    /**
     * Valida si la fecha dada tiene el formato correcto. (dd/mm/yyyy)
     * @param fecha
     * @return True or False.
     */
    public static boolean isFecha(String fecha){
        boolean retorno = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(fecha);
            if(date!=null) retorno = true;
        } catch (ParseException ex) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Valida el nombre del archivo.
     * @param fileName
     * @return True or False.
     */
    public static boolean isCorrectFileName(String fileName) {
        Pattern pat;
        Matcher mat;
        pat = Pattern.compile("^([0-9a-zA-Z]{1,})\\.[a-zA-Z]{3}");
        mat = pat.matcher(fileName);
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean isNumeric(String cadena){
        boolean numeric;
        try{
            Double.parseDouble(cadena);
            numeric = true;
        }catch(NumberFormatException e){
            numeric = false;
        }
        return numeric;
    }

    /**
     * Devuelve la cantidad de meses que hay entre
     * la fecha de inicio y la fecha de fin.
     * @param date1 Fecha de inicio.
     * @param date2 Fecha de fin.
     * @return
     */
    public static int getDiffMonths(Date date1, Date date2) {
        long timeInMillis = date1.getTime() - date2.getTime();
        double number = 1000 * 60 * 60 * 24.0015 * 30.43675;
        double meses = timeInMillis / number;
        return (int)meses;
    }

    /**
     * Devuelve la cantidad de dias que hay entre
     * la fecha de inicio y la fecha de fin.
     * @param date1 Fecha de inicio.
     * @param date2 Fecha de fin.
     * @return
     */
    public static int getDiffDays(Date date1, Date date2) {
        long timeInMillis = date1.getTime() - date2.getTime();
        double number = 1000 * 60 * 60 * 24.0015;
        double days = timeInMillis / number;
        return (int)days;
    }
}