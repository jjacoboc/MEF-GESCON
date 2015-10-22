/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.common;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author jjacobo
 */
public class Constante implements Serializable {
    
    public static String FORMAT_SIMPLE_DATE = "dd/MM/yyyy";
    public static String SUCCESS = "1";
    public static String DEFAULT_THEME = "redmond";
    public static String DEFAULT_FLAG_MENU = "1,1,1,1,1,1";
    public static String STRING_SPLIT_CHAR = ",";
    public static String STRING_EMPTY_SPACE = " ";
    public static String STRING_DOUBLE_LINE = "--";
    public static String STRING_NINGUNO = "NINGUNO";
    public static String STRING_VACANTE = "VACANTE";
    public static String FILE_CONTENT_TYPE_XLS = "application/vnd.ms-excel";
    public static String FILE_CONTENT_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String ROL_ADMINISTRADOR = "21";
    public static String ROL_MODERADOR = "1";
    public static String ROL_ESPECIALISTA = "2";
    public static String ROL_USUARIOEXTERNO = "3";
    public static String ROL_USUARIOINTERNO = "4";
    public static String SEVERETY_ALERTA = "Alerta.";
    public static String SEVERETY_ERROR = "Error.";
    public static String ESTADO_ACTIVO = "1";
    public static String ESTADO_INACTIVO = "0";
    public static String ESTADO_BASELEGAL_REGISTRADO = "1";
    public static String ESTADO_BASELEGAL_PUBLICADO = "3";
    public static String ESTADO_BASELEGAL_CONCORDADO = "5";
    public static int ADMINISTRADOR =21;
    public static int ESPECIALISTA =2;
    public static int MODERADOR =1;
    public static int USUARIOEXTERNO =3;
    public static int USUARIOINTERNO =4;
    
    public static BigDecimal BASELEGAL = new BigDecimal(1);
    public static BigDecimal PREGUNTAS = new BigDecimal(2);
}
