/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author JJacobo
 */
public class Categoria implements Serializable, Comparable<Categoria>{
    
    private BigDecimal ncategoriaid;
    private String vdescripcion;
    private BigDecimal nestado;
    private BigDecimal ncategoriasup;
    private BigDecimal nnivel;
    private String vnombre;
    private String vusuariomod;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private Date dfechamod;
    private BigDecimal nflagwiki;
    private BigDecimal nflagbl;
    private BigDecimal nflagpr;
    private BigDecimal nflagct;
    private BigDecimal nflagbp;
    private BigDecimal nflagom;
    private Blob bimagen;
    private String vimagennombre;
    private String imagen;
    private StreamedContent content;
    private List<Categoria> children;
    
    public Categoria(){
        
    }

    /**
     * @return the ncategoriaid
     */
    public BigDecimal getNcategoriaid() {
        return ncategoriaid;
    }

    /**
     * @param ncategoriaid the ncategoriaid to set
     */
    public void setNcategoriaid(BigDecimal ncategoriaid) {
        this.ncategoriaid = ncategoriaid;
    }

    /**
     * @return the vdescripcion
     */
    public String getVdescripcion() {
        return vdescripcion;
    }

    /**
     * @param vdescripcion the vdescripcion to set
     */
    public void setVdescripcion(String vdescripcion) {
        this.vdescripcion = vdescripcion;
    }

    /**
     * @return the nestado
     */
    public BigDecimal getNestado() {
        return nestado;
    }

    /**
     * @param nestado the nestado to set
     */
    public void setNestado(BigDecimal nestado) {
        this.nestado = nestado;
    }

    /**
     * @return the ncategoriasup
     */
    public BigDecimal getNcategoriasup() {
        return ncategoriasup;
    }

    /**
     * @param ncategoriasup the ncategoriasup to set
     */
    public void setNcategoriasup(BigDecimal ncategoriasup) {
        this.ncategoriasup = ncategoriasup;
    }

    /**
     * @return the nnivel
     */
    public BigDecimal getNnivel() {
        return nnivel;
    }

    /**
     * @param nnivel the nnivel to set
     */
    public void setNnivel(BigDecimal nnivel) {
        this.nnivel = nnivel;
    }

    /**
     * @return the vnombre
     */
    public String getVnombre() {
        return vnombre;
    }

    /**
     * @param vnombre the vnombre to set
     */
    public void setVnombre(String vnombre) {
        this.vnombre = vnombre;
    }

    /**
     * @return the vusuariomod
     */
    public String getVusuariomod() {
        return vusuariomod;
    }

    /**
     * @param vusuariomod the vusuariomod to set
     */
    public void setVusuariomod(String vusuariomod) {
        this.vusuariomod = vusuariomod;
    }

    /**
     * @return the vusuariocreacion
     */
    public String getVusuariocreacion() {
        return vusuariocreacion;
    }

    /**
     * @param vusuariocreacion the vusuariocreacion to set
     */
    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }

    /**
     * @return the dfechacreacion
     */
    public Date getDfechacreacion() {
        return dfechacreacion;
    }

    /**
     * @param dfechacreacion the dfechacreacion to set
     */
    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    /**
     * @return the dfechamod
     */
    public Date getDfechamod() {
        return dfechamod;
    }

    /**
     * @param dfechamod the dfechamod to set
     */
    public void setDfechamod(Date dfechamod) {
        this.dfechamod = dfechamod;
    }

    /**
     * @return the nflagwiki
     */
    public BigDecimal getNflagwiki() {
        return nflagwiki;
    }

    /**
     * @param nflagwiki the nflagwiki to set
     */
    public void setNflagwiki(BigDecimal nflagwiki) {
        this.nflagwiki = nflagwiki;
    }

    /**
     * @return the nflagbl
     */
    public BigDecimal getNflagbl() {
        return nflagbl;
    }

    /**
     * @param nflagbl the nflagbl to set
     */
    public void setNflagbl(BigDecimal nflagbl) {
        this.nflagbl = nflagbl;
    }

    /**
     * @return the nflagpr
     */
    public BigDecimal getNflagpr() {
        return nflagpr;
    }

    /**
     * @param nflagpr the nflagpr to set
     */
    public void setNflagpr(BigDecimal nflagpr) {
        this.nflagpr = nflagpr;
    }

    /**
     * @return the nflagct
     */
    public BigDecimal getNflagct() {
        return nflagct;
    }

    /**
     * @param nflagct the nflagct to set
     */
    public void setNflagct(BigDecimal nflagct) {
        this.nflagct = nflagct;
    }

    /**
     * @return the nflagbp
     */
    public BigDecimal getNflagbp() {
        return nflagbp;
    }

    /**
     * @param nflagbp the nflagbp to set
     */
    public void setNflagbp(BigDecimal nflagbp) {
        this.nflagbp = nflagbp;
    }

    /**
     * @return the nflagom
     */
    public BigDecimal getNflagom() {
        return nflagom;
    }

    /**
     * @param nflagom the nflagom to set
     */
    public void setNflagom(BigDecimal nflagom) {
        this.nflagom = nflagom;
    }

    /**
     * @return the bimagen
     */
    public Blob getBimagen() {
        return bimagen;
    }

    /**
     * @param bimagen the bimagen to set
     */
    public void setBimagen(Blob bimagen) {
        this.bimagen = bimagen;
    }

    /**
     * @return the vimagennombre
     */
    public String getVimagennombre() {
        return vimagennombre;
    }

    /**
     * @param vimagennombre the vimagennombre to set
     */
    public void setVimagennombre(String vimagennombre) {
        this.vimagennombre = vimagennombre;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the content
     */
    public StreamedContent getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(StreamedContent content) {
        this.content = content;
    }

    /**
     * @return the children
     */
    public List<Categoria> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List<Categoria> children) {
        this.children = children;
    }
    
    @Override
    public int compareTo(Categoria o) {
        return Comparators.ID.compare(this, o);
    }
    
    public static class Comparators {
        public static Comparator<Categoria> ID = new Comparator<Categoria>() {
            @Override
            public int compare(Categoria o1, Categoria o2) {
                return o1.getNcategoriaid().intValue() - o2.getNcategoriaid().intValue();
            }
        };
    }
}
