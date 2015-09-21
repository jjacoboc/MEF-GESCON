/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.util;

import jxl.write.WritableWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

/**
 *
 * @author jjacobo
 */
public class ExcelUtil {
    private Workbook libroExcel;
    private Sheet hojaExcel;
    private Row filaExcel;
    private Cell celdaExcel;
    private int ctaFila;

    /**
     * Constructor de la clase.
     */
    public ExcelUtil() {
        
    }
    
    /**
     * Constructor de la clase que crea un documento excel de una hoja.
     * @param nombre Nombre de la Hoja, tipo String.
     */
    public ExcelUtil(String nombre) {
        creaHoja(nombre);
        this.ctaFila = 0;
    }

    /**
     * Constructor de la clase que crea un documento excel de una hoja y su cabecera.
     * @param nombre Nombre de la hoja, tipo String.
     * @param cabeceras Títulos de la cabecera, tipo String[].
     */
    public ExcelUtil(String nombre, String[] cabeceras) {
        creaHoja(nombre);
        generaCabecera(cabeceras);
        this.ctaFila = 1;
    }

    /**
     * Método que obtiene el libro de excel.
     * @return libroExcel Libro de excel, tipo SXSSFWorkbook.
     */
    public SXSSFWorkbook retornaHoja() {
        return (SXSSFWorkbook)this.libroExcel;
    }
    
    /**
     * Método que obtiene el libro de excel.
     * @return libroExcel Libro de excel, tipo WritableWorkbook.
     */
    public WritableWorkbook retornaHojaReescribible() {
        return (WritableWorkbook)this.libroExcel;
    }

    /**
     * Método que crea un libro excel con una hoja.
     * @param nombre Nombre de la hoja, tipo String.
     */
    private void creaHoja(String nombre) {
        this.libroExcel = new SXSSFWorkbook(100);
        this.hojaExcel = (Sheet)this.libroExcel.createSheet(nombre);
    }
    
    /**
     * Método que crea una fila en la hoja del libro de excel.
     * @return filaExcel Fila creada, tipo Row.
     */
    public Row creaFila() {
        this.filaExcel = (Row)this.hojaExcel.createRow(this.ctaFila);
        this.ctaFila++;
        return filaExcel;
    }

    /**
     * Método que crea una celda con un determinado valor tipo cadena.
     * @param filaExcel Fila donde crear la celda, tipo Row.
     * @param posicion Posición de la columna donde crear la celda, tipo int.
     * @param valor Valor que tendrá la celda, tipo String.
     * @param estiloCelda Estilo de la celda, tipo XSSFCellStyle.
     */
    public void creaCelda(Row filaExcel, int posicion, String valor, XSSFCellStyle estiloCelda) {
        this.celdaExcel = (Cell)filaExcel.createCell(posicion);
        this.celdaExcel.setCellValue(valor);
        this.celdaExcel.setCellStyle(estiloCelda);
    }    
    
    /**
     * Método que crea una celda con un determinado valor tipo decimal.
     * @param filaExcel Fila donde crear la celda, tipo Row.
     * @param posicion Posición de la columna donde crear la celda, tipo int.
     * @param valor Valor decimal que tendrá la celda, tipo Double.
     * @param estiloCelda Estilo de la celda, tipo XSSFCellStyle.
     */
    public void creaCelda(Row filaExcel, int posicion, Double valor, XSSFCellStyle estiloCelda) {
        this.celdaExcel = (Cell)filaExcel.createCell(posicion);
        estiloCelda.setDataFormat(this.libroExcel.getCreationHelper().createDataFormat().getFormat("#0.00"));
        this.celdaExcel.setCellValue(valor);
        this.celdaExcel.setCellStyle(estiloCelda);
    }
    
    /**
     * Método que crea el estilo de la cabecera.
     * @return estilo Estilo de la cabecera, tipo XSSFCellStyle.
     */
    public XSSFCellStyle creaEstiloCabecera() {
        XSSFCellStyle estilo = (XSSFCellStyle)libroExcel.createCellStyle();
        estilo.setBorderBottom(XSSFCellStyle.BORDER_THIN);          
        estilo.setBorderTop(XSSFCellStyle.BORDER_THIN);
        estilo.setBorderRight(XSSFCellStyle.BORDER_THIN);
        estilo.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
        estilo.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        Font fuente = creaFuenteCabecera();
        estilo.setFont(fuente);
        return estilo;
    }
    
    /**
     * Método que crea la fuente para la cabecera.
     * @return fuente Fuente de la cabecera, tipo Font.
     */
    private Font creaFuenteCabecera() {
        Font fuente = libroExcel.createFont();
        fuente.setFontHeightInPoints((short)9);
        fuente.setFontName("Courier New");
        fuente.setItalic(true);          
        fuente.setColor((short)0xc);
        return fuente;
    }
    
    /**
     * Méto que genera las cabeceras de la hoja del libro excel.
     * @param matrizCabeceras Arreglo de títulos del cabecera, tipo String[].
     */
    private void generaCabecera(String[] matrizCabeceras) {
        this.filaExcel = creaFila();
        XSSFCellStyle estiloCelda = creaEstiloCabecera();
        int ctaCelda = 0;
        while(ctaCelda < matrizCabeceras.length) {
            creaCelda(this.filaExcel, ctaCelda, matrizCabeceras[ctaCelda], estiloCelda);          
            ctaCelda++;
        }
    }

    /**
     * Método que crea la fuente para las filas pares.
     * @return fuente Fuente creada, tipo Font.
     */
    public Font creaFuenteFilaPar() {
        Font fuente = this.libroExcel.createFont();
        fuente.setFontHeightInPoints((short)9);
        fuente.setFontName("Courier New");
        fuente.setColor(Font.COLOR_NORMAL);
        return fuente;
    }

    /**
     * Método que crea la fuente para las filas impares.
     * @return fuente Fuente creada, tipo Font.
     */
    public Font creaFuenteFilaImpar() {
        Font fuente = this.libroExcel.createFont();
        fuente.setFontHeightInPoints((short)9);
        fuente.setFontName("Courier New");
        fuente.setColor(Font.BOLDWEIGHT_BOLD);
        return fuente;
    }

    /**
     * Método que crea el estilo para las filas pares.
     * @return estilo Estilo creado, tipo XSSFCellStyle.
     */
    public XSSFCellStyle creaEstiloFilaPar() {
        XSSFCellStyle estilo = (XSSFCellStyle) this.libroExcel.createCellStyle();
        Font fuente = creaFuenteFilaPar();
        estilo.setFont(fuente);
        return estilo;
    }

    /**
     * Método que crea el estilo para las filas impares.
     * @return estilo Estilo creado, tipo XSSFCellStyle.
     */
    public XSSFCellStyle creaEstiloFilaImpar() {
        XSSFCellStyle estilo = (XSSFCellStyle) this.libroExcel.createCellStyle();
        Font fuente = creaFuenteFilaImpar();
        estilo.setFont(fuente);
        estilo.setFillForegroundColor(new XSSFColor(new java.awt.Color(202, 225, 255)));
        estilo.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        return estilo;
    }
    
    /**
     * Método para crear un determinado número de filas para la cabecera.
     * @param nroFilas Número de filas, tipo int.
     */
    public void crearFilasCabecera(int nroFilas) {
        for(int ctaFilaLocal = 0; ctaFilaLocal < nroFilas; ctaFilaLocal++) {
            this.hojaExcel.createRow(ctaFilaLocal);
        }
        this.ctaFila = nroFilas++;
    }
    
    /**
     * Método que establece una región combinada de la hoja excel.
     * @param valor Valor que tendrá la celda, tipo String.
     * @param filaDesde Fila desde, tipo int.
     * @param filaHasta Fila hasta, tipo int.
     * @param columnaDesde Columna desde, tipo int.
     * @param columnaHasta Columna hasta, tipo int.
     * @param estilo Estilo de las celdas agrupadas, tipo XSSFCellStyle.
     */
    public void crearCeldaCombinada(String valor, int filaDesde, int filaHasta, int columnaDesde, int columnaHasta, XSSFCellStyle estilo) {
        Row fila;
        Cell celda;
        for(int ctaFilaLocal = filaDesde; ctaFilaLocal <= filaHasta; ctaFilaLocal++) {
            fila = this.hojaExcel.getRow(ctaFilaLocal);
            for (int ctaColumnaLocal = columnaDesde; ctaColumnaLocal <= columnaHasta; ctaColumnaLocal++) {
                celda = fila.createCell(ctaColumnaLocal);
                celda.setCellStyle(estilo);
                 if (ctaColumnaLocal == columnaDesde) {
                    celda.setCellValue(valor);
                }
            }
        }
        this.hojaExcel.addMergedRegion(new CellRangeAddress(filaDesde, filaHasta, columnaDesde, columnaHasta));
    }
    
    /**
     * Método que crea el estilo para las filas pares con borde derecho.
     * @return estilo Estilo creado, tipo XSSFCellStyle.
     */
    public XSSFCellStyle crearEstiloBordeDerechoPar() {
        XSSFCellStyle estilo = (XSSFCellStyle) this.libroExcel.createCellStyle();
        estilo.setBorderRight(XSSFCellStyle.BORDER_THIN);
        Font fuente = creaFuenteFilaPar();
        estilo.setFont(fuente);
        return estilo;
    }

    /**
     * Método que crea el estilo para las filas impares con borde derecho.
     * @return estilo Estilo creado, tipo XSSFCellStyle.
     */
    public XSSFCellStyle crearEstiloBordeDerechoImpar() {
        XSSFCellStyle estilo = (XSSFCellStyle) this.libroExcel.createCellStyle();
        estilo.setBorderRight(XSSFCellStyle.BORDER_THIN);
        Font fuente = creaFuenteFilaImpar();
        estilo.setFont(fuente);
        estilo.setFillForegroundColor(new XSSFColor(new java.awt.Color(202, 225, 255)));
        estilo.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        return estilo;
    }
}
