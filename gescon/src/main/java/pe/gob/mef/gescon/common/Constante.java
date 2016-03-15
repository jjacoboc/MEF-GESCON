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
    
    public static String FORMAT_DATE_SHORT = "dd/MM/yyyy";
    public static String FORMAT_DATE_MEDIUM = "dd/MMM/yyyy";
    public static String FORMAT_DATE_LONG = "EEE, dd/MMM/yyyy";
    public static String FORMAT_DATETIME_SHORT = "dd/MM/yyyy hh:mm:ss";
    public static String FORMAT_DATETIME_MEDIUM = "dd/MMM/yyyy hh:mm:ss";
    public static String FORMAT_DATETIME_LONG = "EEE, dd/MM/yyyy hh:mm:ss a";
    public static String SUCCESS = "1";
    public static String DEFAULT_THEME = "redmond";
    public static String DEFAULT_FLAG_MENU = "1,1,1,1,1,1";
    public static String STRING_SPLIT_CHAR = ",";
    public static String STRING_EMPTY_SPACE = " ";
    public static String STRING_DOUBLE_LINE = "--";
    public static String STRING_NINGUNO = "NINGUNO";
    public static String STRING_VACANTE = "VACANTE";
    public static String FILE_CONTENT_TYPE_PDF = "application/pdf";
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
    public static String ESTADO_OPORTUNIDAD_MEJORA = "1";
    public static String ESTADO_BASELEGAL_PUBLICADO = "3";
    public static String ESTADO_BASELEGAL_DEROGADA = "4";
    public static String ESTADO_BASELEGAL_CONCORDADO = "5";
    public static String ESTADO_BASELEGAL_MODIFICADA = "6";
    public static String SITUACION_POR_VERIFICAR = "1";
    public static String SITUACION_ASIGNADO = "2";
    public static String SITUACION_DERIVADO = "3";
    public static String SITUACION_OBSERVADO = "4";
    public static String SITUACION_POR_PUBLICAR = "5";
    public static String SITUACION_PUBLICADO = "6";
    public static String SITUACION_RECHAZADO = "7";
    
    public static int ADMINISTRADOR =21;
    public static int ESPECIALISTA =2;
    public static int MODERADOR =1;
    public static int USUARIOEXTERNO =3;
    public static int USUARIOINTERNO =4;
    
    public static String DIAS_CADUCIDAD_CLAVE = "1";
    public static String CALIFICACION1 = "2";
    public static String CALIFICACION2 = "3";
    public static String CALIFICACION3 = "4";
    public static String CALIFICACION4 = "5";
    public static String CALIFICACION5 = "6";
    public static String CLAVE_DEFAULT = "7";
    
    // Tipo de Conocimiento
    public static BigDecimal BASELEGAL = new BigDecimal(1);
    public static BigDecimal PREGUNTAS = new BigDecimal(2);
    public static BigDecimal WIKI = new BigDecimal(3);
    public static BigDecimal CONTENIDO = new BigDecimal(4);
    public static BigDecimal BUENAPRACTICA = new BigDecimal(5);
    public static BigDecimal OPORTUNIDADMEJORA = new BigDecimal(6);
    // Maestros del Sistema
    public static BigDecimal MAESTRO_MODULOS = new BigDecimal(1);
    public static BigDecimal MAESTRO_RANGOS = new BigDecimal(2);
    public static BigDecimal MAESTRO_TIPODISCUSION = new BigDecimal(3);
    public static BigDecimal MAESTRO_PROFESIONES = new BigDecimal(4);
    public static BigDecimal MAESTRO_TIPODOCUMENTOS = new BigDecimal(5);
    public static BigDecimal MAESTRO_TIPOVIDEOS = new BigDecimal(6);
    public static BigDecimal MAESTRO_TIPOAUDIOS = new BigDecimal(7);
    public static BigDecimal MAESTRO_TIPOIMAGENES = new BigDecimal(8);
    public static BigDecimal MAESTRO_TIPOARCHIVOS = new BigDecimal(9);
    public static BigDecimal MAESTRO_TIPOLINKS = new BigDecimal(10);
    public static BigDecimal MAESTRO_TIPOOTROSARCHIVOS = new BigDecimal(11);
    // Modulos del Sistema (Maestro Detalle)
    public static BigDecimal MODULO_ADMINISTRACION = new BigDecimal(1);
    public static BigDecimal MODULO_BASELEGAL = new BigDecimal(2);
    public static BigDecimal MODULO_PREGUNTAS = new BigDecimal(3);
    public static BigDecimal MODULO_WIKI = new BigDecimal(4);
    public static BigDecimal MODULO_CONTENIDO = new BigDecimal(5);
    public static BigDecimal MODULO_BUENAPRACTICA = new BigDecimal(6);
    public static BigDecimal MODULO_OPORTUNIDADMEJORA = new BigDecimal(7);
}
