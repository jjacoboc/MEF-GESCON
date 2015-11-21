package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Mtcategoria generated by hbm2java
 */
public class Mtcategoria implements java.io.Serializable {

    private BigDecimal ncategoriaid;
    private String vdescripcion;
    private BigDecimal nestado;
    private BigDecimal ncategoriasup;
    private BigDecimal nnivel;
    private String vnombre;
    private String vusuariomodificacion;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;
    private BigDecimal nflagwiki;
    private BigDecimal nflagbl;
    private BigDecimal nflagpr;
    private BigDecimal nflagct;
    private BigDecimal nflagbp;
    private BigDecimal nflagom;
    private Blob bimagen;
    private String vimagennombre;
    private Set tconocimientos = new HashSet(0);
    private Set tcategoriaUsers = new HashSet(0);
    private Set tpreguntas = new HashSet(0);
    private Set tbaselegals = new HashSet(0);

    public Mtcategoria() {
    }

    public Mtcategoria(BigDecimal ncategoriaid) {
        this.ncategoriaid = ncategoriaid;
    }

    public Mtcategoria(BigDecimal ncategoriaid, String vdescripcion, BigDecimal nestado, BigDecimal ncategoriasup, BigDecimal nnivel, String vnombre, String vusuariomodificacion, String vusuariocreacion, Date dfechacreacion, Date dfechamodificacion, BigDecimal nflagwiki, BigDecimal nflagbl, BigDecimal nflagpr, BigDecimal nflagct, BigDecimal nflagbp, BigDecimal nflagom, Blob bimagen, String vimagennombre, Set tconocimientos, Set tcategoriaUsers, Set tpreguntas, Set tbaselegals) {
        this.ncategoriaid = ncategoriaid;
        this.vdescripcion = vdescripcion;
        this.nestado = nestado;
        this.ncategoriasup = ncategoriasup;
        this.nnivel = nnivel;
        this.vnombre = vnombre;
        this.vusuariomodificacion = vusuariomodificacion;
        this.vusuariocreacion = vusuariocreacion;
        this.dfechacreacion = dfechacreacion;
        this.dfechamodificacion = dfechamodificacion;
        this.nflagwiki = nflagwiki;
        this.nflagbl = nflagbl;
        this.nflagpr = nflagpr;
        this.nflagct = nflagct;
        this.nflagbp = nflagbp;
        this.nflagom = nflagom;
        this.bimagen = bimagen;
        this.vimagennombre = vimagennombre;
        this.tconocimientos = tconocimientos;
        this.tcategoriaUsers = tcategoriaUsers;
        this.tpreguntas = tpreguntas;
        this.tbaselegals = tbaselegals;
    }

    public BigDecimal getNcategoriaid() {
        return this.ncategoriaid;
    }

    public void setNcategoriaid(BigDecimal ncategoriaid) {
        this.ncategoriaid = ncategoriaid;
    }

    public String getVdescripcion() {
        return this.vdescripcion;
    }

    public void setVdescripcion(String vdescripcion) {
        this.vdescripcion = vdescripcion;
    }

    public BigDecimal getNestado() {
        return this.nestado;
    }

    public void setNestado(BigDecimal nestado) {
        this.nestado = nestado;
    }

    public BigDecimal getNcategoriasup() {
        return this.ncategoriasup;
    }

    public void setNcategoriasup(BigDecimal ncategoriasup) {
        this.ncategoriasup = ncategoriasup;
    }

    public BigDecimal getNnivel() {
        return this.nnivel;
    }

    public void setNnivel(BigDecimal nnivel) {
        this.nnivel = nnivel;
    }

    public String getVnombre() {
        return this.vnombre;
    }

    public void setVnombre(String vnombre) {
        this.vnombre = vnombre;
    }

    public String getVusuariomodificacion() {
        return this.vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
    }

    public String getVusuariocreacion() {
        return this.vusuariocreacion;
    }

    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }
    public Date getDfechacreacion() {
        return this.dfechacreacion;
    }

    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }
    public Date getDfechamodificacion() {
        return this.dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

    public BigDecimal getNflagwiki() {
        return this.nflagwiki;
    }

    public void setNflagwiki(BigDecimal nflagwiki) {
        this.nflagwiki = nflagwiki;
    }

    public BigDecimal getNflagbl() {
        return this.nflagbl;
    }

    public void setNflagbl(BigDecimal nflagbl) {
        this.nflagbl = nflagbl;
    }

    public BigDecimal getNflagpr() {
        return this.nflagpr;
    }

    public void setNflagpr(BigDecimal nflagpr) {
        this.nflagpr = nflagpr;
    }

    public BigDecimal getNflagct() {
        return this.nflagct;
    }

    public void setNflagct(BigDecimal nflagct) {
        this.nflagct = nflagct;
    }

    public BigDecimal getNflagbp() {
        return this.nflagbp;
    }

    public void setNflagbp(BigDecimal nflagbp) {
        this.nflagbp = nflagbp;
    }

    public BigDecimal getNflagom() {
        return this.nflagom;
    }

    public void setNflagom(BigDecimal nflagom) {
        this.nflagom = nflagom;
    }

    public Blob getBimagen() {
        return this.bimagen;
    }

    public void setBimagen(Blob bimagen) {
        this.bimagen = bimagen;
    }

    public String getVimagennombre() {
        return this.vimagennombre;
    }

    public void setVimagennombre(String vimagennombre) {
        this.vimagennombre = vimagennombre;
    }

    public Set getTconocimientos() {
        return this.tconocimientos;
    }

    public void setTconocimientos(Set tconocimientos) {
        this.tconocimientos = tconocimientos;
    }

    public Set getTcategoriaUsers() {
        return this.tcategoriaUsers;
    }

    public void setTcategoriaUsers(Set tcategoriaUsers) {
        this.tcategoriaUsers = tcategoriaUsers;
    }

    public Set getTpreguntas() {
        return this.tpreguntas;
    }

    public void setTpreguntas(Set tpreguntas) {
        this.tpreguntas = tpreguntas;
    }

    public Set getTbaselegals() {
        return this.tbaselegals;
    }

    public void setTbaselegals(Set tbaselegals) {
        this.tbaselegals = tbaselegals;
    }

}
