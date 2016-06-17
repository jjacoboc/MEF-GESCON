/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.common.Items;
import pe.gob.mef.gescon.common.Parameters;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.EstadoBaseLegalService;
import pe.gob.mef.gescon.service.MaestroDetalleService;
import pe.gob.mef.gescon.service.MaestroService;
import pe.gob.mef.gescon.service.ParametroService;
import pe.gob.mef.gescon.service.PerfilService;
import pe.gob.mef.gescon.service.RangoService;
import pe.gob.mef.gescon.service.SituacionService;
import pe.gob.mef.gescon.service.TipoConocimientoService;
import pe.gob.mef.gescon.service.UserService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Maestro;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class ListaSessionMB implements Serializable{

    private List<SelectItem> listaSiNo;
    private List<SelectItem> listaModulos;
    private List<SelectItem> listaModulosActivos;
    private List<SelectItem> listaTipoRango;
    private List<SelectItem> listaTipoRangoActivo;
    private List<SelectItem> listaCategoria;
    private List<SelectItem> listaCategoriaActiva;
    private List<SelectItem> listaRangoBaseLegal;
    private List<SelectItem> listaEstadoBaseLegal;
    private List<SelectItem> listaEstadoBaseLegalVinculo;
    private List<SelectItem> listaSituacion;
    private List<SelectItem> listaTipoConocimiento;
    private List<SelectItem> listaTipoDiscusion;
    private List<SelectItem> listaTipoDiscusionActivo;
    private List<SelectItem> listaParametro;
    private List<SelectItem> listaParametroActivo;
    private List<SelectItem> listaPerfil;
    private List<SelectItem> listaPerfilActivo;
    private List<SelectItem> listaProfesion;
    private List<SelectItem> listaProfesionActiva;
    private List<SelectItem> listaCargo;
    private List<SelectItem> listaCargoActivo;
    private List<SelectItem> listaPliego;
    private List<SelectItem> listaPliegoActivo;
    private List<SelectItem> listaArea;
    private List<SelectItem> listaAreaActivo;
    private List<SelectItem> listaSector;
    private List<SelectItem> listaSectorActivo;
    private List<SelectItem> listaGobierno;
    private List<SelectItem> listaGobiernoActivo;
    private List<SelectItem> listaEntidad;
    private List<SelectItem> listaEntidadActivo;
    private List<SelectItem> listaTipoDocumentos;
    private List<SelectItem> listaTipoDocumentosActivos;
    private List<SelectItem> listaTipoVideos;
    private List<SelectItem> listaTipoVideosActivos;
    private List<SelectItem> listaTipoAudios;
    private List<SelectItem> listaTipoAudiosActivos;
    private List<SelectItem> listaTipoImagenes;
    private List<SelectItem> listaTipoImagenesActivas;
    private List<SelectItem> listaTipoArchivosTexto;
    private List<SelectItem> listaTipoArchivosTextoActivos;
    private List<SelectItem> listaTipoLinks;
    private List<SelectItem> listaTipoLinksActivos;
    private List<SelectItem> listaTipoOtrosArchivos;
    private List<SelectItem> listaTipoOtrosArchivosActivos;
    private List<SelectItem> listaMaestros;
    private List<SelectItem> listaUsuarios;
    private List<SelectItem> listaUsuariosActivos;
    private List<SelectItem> filterEstado;
    private List<SelectItem> filterModulos;
    private List<SelectItem> filterPerfiles;
    
    /**
     * Creates a new instance of ListaSessionMB
     */
    public ListaSessionMB() {
    }

    /**
     * @return the listaSiNo
     */
    public List<SelectItem> getListaSiNo() {
        if(listaSiNo == null){
            listaSiNo =  new Items(Parameters.getListaSiNo(), null, "codigo","descripcion").getItems();
        }
        return listaSiNo;
    }

    /**
     * @param listaSiNo the listaSiNo to set
     */
    public void setListaSiNo(List<SelectItem> listaSiNo) {
        this.listaSiNo = listaSiNo;
    }

    public List<SelectItem> getListaModulos() throws Exception {
        if(listaModulos == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_MODULOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaModulos =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaModulos;
    }

    public void setListaModulos(List<SelectItem> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public List<SelectItem> getListaModulosActivos() throws Exception {
        if(listaModulosActivos == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_MODULOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaModulosActivos =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaModulosActivos;
    }

    public void setListaModulosActivos(List<SelectItem> listaModulosActivos) {
        this.listaModulosActivos = listaModulosActivos;
    }

    public List<SelectItem> getListaTipoRango() throws Exception {
        if(listaTipoRango == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_RANGOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoRango =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoRango;
    }

    public void setListaTipoRango(List<SelectItem> listaTipoRango) {
        this.listaTipoRango = listaTipoRango;
    }

    public List<SelectItem> getListaTipoRangoActivo() throws Exception {
        if(listaTipoRangoActivo == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_RANGOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoRangoActivo =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoRangoActivo;
    }

    public void setListaTipoRangoActivo(List<SelectItem> listaTipoRangoActivo) {
        this.listaTipoRangoActivo = listaTipoRangoActivo;
    }

    /**
     * @return the listaCategoria
     * @throws java.lang.Exception
     */
    public List<SelectItem> getListaCategoria() throws Exception {
        if(listaCategoria == null){
            CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            listaCategoria =  new Items(service.getCategorias(), null, "ncategoriaid","vnombre").getItems();
        }
        return listaCategoria;
    }

    /**
     * @param listaCategoria the listaCategoria to set
     */
    public void setListaCategoria(List<SelectItem> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public List<SelectItem> getListaCategoriaActiva() throws Exception {
        if(listaCategoriaActiva == null){
            CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            listaCategoriaActiva =  new Items(service.getCategoriasActived(), null, "ncategoriaid","vnombre").getItems();
        }
        return listaCategoriaActiva;
    }

    public void setListaCategoriaActiva(List<SelectItem> listaCategoriaActiva) {
        this.listaCategoriaActiva = listaCategoriaActiva;
    }

    /**
     * @return the listaRangoBaseLegal
     * @throws java.lang.Exception
     */
    public List<SelectItem> getListaRangoBaseLegal() throws Exception {
        if(listaRangoBaseLegal == null){
            RangoService service = (RangoService) ServiceFinder.findBean("RangoService");
            listaRangoBaseLegal =  new Items(service.getRangos(), null, "nrangoid","vnombre").getItems();
        }
        return listaRangoBaseLegal;
    }

    /**
     * @param listaRangoBaseLegal the listaRangoBaseLegal to set
     */
    public void setListaRangoBaseLegal(List<SelectItem> listaRangoBaseLegal) {
        this.listaRangoBaseLegal = listaRangoBaseLegal;
    }

    /**
     * @return the listaEstadoBaseLegal
     * @throws java.lang.Exception
     */
    public List<SelectItem> getListaEstadoBaseLegal() throws Exception {
        if(listaEstadoBaseLegal == null){
            EstadoBaseLegalService service = (EstadoBaseLegalService) ServiceFinder.findBean("EstadoBaseLegalService");
            listaEstadoBaseLegal =  new Items(service.getEstadosBaselegal(), null, "nestadoid","vnombre").getItems();
        }
        return listaEstadoBaseLegal;
    }

    /**
     * @param listaEstadoBaseLegal the listaEstadoBaseLegal to set
     */
    public void setListaEstadoBaseLegal(List<SelectItem> listaEstadoBaseLegal) {
        this.listaEstadoBaseLegal = listaEstadoBaseLegal;
    }

    /**
     * @return the listaEstadoBaseLegalVinculo
     * @throws java.lang.Exception
     */
    public List<SelectItem> getListaEstadoBaseLegalVinculo() throws Exception {
        if(listaEstadoBaseLegalVinculo == null){
            EstadoBaseLegalService service = (EstadoBaseLegalService) ServiceFinder.findBean("EstadoBaseLegalService");
            listaEstadoBaseLegalVinculo =  new Items(service.getEstadosBaselegalToLink(), null, "nestadoid","vnombre").getItems();
        }
        return listaEstadoBaseLegalVinculo;
    }

    /**
     * @param listaEstadoBaseLegalVinculo the listaEstadoBaseLegalVinculo to set
     */
    public void setListaEstadoBaseLegalVinculo(List<SelectItem> listaEstadoBaseLegalVinculo) {
        this.listaEstadoBaseLegalVinculo = listaEstadoBaseLegalVinculo;
    }

    /**
     * @return the listaSituacion
     * @throws java.lang.Exception
     */
    public List<SelectItem> getListaSituacion() throws Exception {
        if(listaSituacion == null){
            SituacionService service = (SituacionService) ServiceFinder.findBean("SituacionService");
            listaSituacion =  new Items(service.getSituacions(), null, "nsituacionid","vnombre").getItems();
        }
        return listaSituacion;
    }

    /**
     * @param listaSituacion the listaSituacion to set
     */
    public void setListaSituacion(List<SelectItem> listaSituacion) {
        this.listaSituacion = listaSituacion;
    }

    public List<SelectItem> getListaTipoConocimiento() throws Exception {
        if(listaTipoConocimiento == null){
            TipoConocimientoService service = (TipoConocimientoService) ServiceFinder.findBean("TipoConocimientoService");
            listaTipoConocimiento =  new Items(service.getTipoConocimientos(), null, "ntpoconocimientoid","vnombre").getItems();
        }
        return listaTipoConocimiento;
    }

    public void setListaTipoConocimiento(List<SelectItem> listaTipoConocimiento) {
        this.listaTipoConocimiento = listaTipoConocimiento;
    }

    public List<SelectItem> getListaTipoDiscusion() throws Exception {
        if(listaTipoDiscusion == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPODISCUSION);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoDiscusion =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoDiscusion;
    }

    public void setListaTipoDiscusion(List<SelectItem> listaTipoDiscusion) {
        this.listaTipoDiscusion = listaTipoDiscusion;
    }

    public List<SelectItem> getListaTipoDiscusionActivo() throws Exception {
        if(listaTipoDiscusionActivo == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPODISCUSION);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoDiscusionActivo =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoDiscusionActivo;
    }

    public void setListaTipoDiscusionActivo(List<SelectItem> listaTipoDiscusionActivo) {
        this.listaTipoDiscusionActivo = listaTipoDiscusionActivo;
    }

    public List<SelectItem> getListaParametro() throws Exception {
        if(listaParametro == null){
            ParametroService parametroService = (ParametroService) ServiceFinder.findBean("ParametroService");
            listaParametro =  new Items(parametroService.getParametros(), null, "nparametroid","vvalor").getItems();
        }
        return listaParametro;
    }

    public void setListaParametro(List<SelectItem> listaParametro) {
        this.listaParametro = listaParametro;
    }

    public List<SelectItem> getListaParametroActivo() throws Exception {
        if(listaParametro == null){
            ParametroService parametroService = (ParametroService) ServiceFinder.findBean("ParametroService");
            listaParametro =  new Items(parametroService.getParametrosActived(), null, "nparametroid","vvalor").getItems();
        }
        return listaParametroActivo;
    }

    public void setListaParametroActivo(List<SelectItem> listaParametroActivo) {
        this.listaParametroActivo = listaParametroActivo;
    }

    public List<SelectItem> getListaPerfil() throws Exception {
        if(listaPerfil == null){
            PerfilService perfilService = (PerfilService) ServiceFinder.findBean("PerfilService");
            listaPerfil =  new Items(perfilService.getPerfils(), null, "nperfilid","vnombre").getItems();
        }
        return listaPerfil;
    }

    public void setListaPerfil(List<SelectItem> listaPerfil) {
        this.listaPerfil = listaPerfil;
    }

    public List<SelectItem> getListaPerfilActivo() throws Exception {
        if(listaPerfil == null){
            PerfilService perfilService = (PerfilService) ServiceFinder.findBean("PerfilService");
            listaPerfil =  new Items(perfilService.getPerfilsActived(), null, "nperfilid","vnombre").getItems();
        }
        return listaPerfilActivo;
    }

    public void setListaPerfilActivo(List<SelectItem> listaPerfilActivo) {
        this.listaPerfilActivo = listaPerfilActivo;
    }

    public List<SelectItem> getListaProfesion() throws Exception {
        if(listaProfesion == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_PROFESIONES);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaProfesion =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaProfesion;
    }

    public void setListaProfesion(List<SelectItem> listaProfesion) {
        this.listaProfesion = listaProfesion;
    }

    public List<SelectItem> getListaProfesionActiva() throws Exception {
        if(listaProfesionActiva == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_PROFESIONES);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaProfesionActiva =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaProfesionActiva;
    }

    public void setListaProfesionActiva(List<SelectItem> listaProfesionActiva) {
        this.listaProfesionActiva = listaProfesionActiva;
    }

    /**
     * @return the listaCargo
     */
    public List<SelectItem> getListaCargo() throws Exception {
        if(listaCargo == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_CARGO);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaCargo =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaCargo;
    }

    /**
     * @param listaCargo the listaCargo to set
     */
    public void setListaCargo(List<SelectItem> listaCargo) {
        this.listaCargo = listaCargo;
    }

    /**
     * @return the listaCargoActivo
     */
    public List<SelectItem> getListaCargoActivo() throws Exception{
        if(listaCargoActivo == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_CARGO);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaCargoActivo =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaCargoActivo;
    }

    /**
     * @param listaCargoActivo the listaCargoActivo to set
     */
    public void setListaCargoActivo(List<SelectItem> listaCargoActivo) {
        this.listaCargoActivo = listaCargoActivo;
    }

    /**
     * @return the listaPliego
     */
    public List<SelectItem> getListaPliego() throws Exception{
        if(listaPliego == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_PLIEGO);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaPliego =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaPliego;
    }

    /**
     * @param listaPliego the listaPliego to set
     */
    public void setListaPliego(List<SelectItem> listaPliego) {
        this.listaPliego = listaPliego;
    }

    /**
     * @return the listaPliegoActivo
     */
    public List<SelectItem> getListaPliegoActivo() throws Exception{
        if(listaPliegoActivo == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_PLIEGO);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaPliegoActivo =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaPliegoActivo;
    }

    /**
     * @param listaPliegoActivo the listaPliegoActivo to set
     */
    public void setListaPliegoActivo(List<SelectItem> listaPliegoActivo) {
        this.listaPliegoActivo = listaPliegoActivo;
    }

    /**
     * @return the listaArea
     */
    public List<SelectItem> getListaArea() throws Exception{
        if(listaArea == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_AREA);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaArea =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaArea;
    }

    /**
     * @param listaArea the listaArea to set
     */
    public void setListaArea(List<SelectItem> listaArea) {
        this.listaArea = listaArea;
    }

    /**
     * @return the listaAreaActivo
     */
    public List<SelectItem> getListaAreaActivo() throws Exception{
        if(listaAreaActivo == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_AREA);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaAreaActivo =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaAreaActivo;
    }

    /**
     * @param listaAreaActivo the listaAreaActivo to set
     */
    public void setListaAreaActivo(List<SelectItem> listaAreaActivo) {
        this.listaAreaActivo = listaAreaActivo;
    }

    /**
     * @return the listaSector
     */
    public List<SelectItem> getListaSector() throws Exception{
        if(listaSector == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_SECTOR);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaSector =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaSector;
    }

    /**
     * @param listaSector the listaSector to set
     */
    public void setListaSector(List<SelectItem> listaSector) {
        this.listaSector = listaSector;
    }

    /**
     * @return the listaSectorActivo
     */
    public List<SelectItem> getListaSectorActivo() throws Exception{
        if(listaSectorActivo == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_SECTOR);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaSectorActivo =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaSectorActivo;
    }

    /**
     * @param listaSectorActivo the listaSectorActivo to set
     */
    public void setListaSectorActivo(List<SelectItem> listaSectorActivo) {
        this.listaSectorActivo = listaSectorActivo;
    }

    /**
     * @return the listaGobierno
     */
    public List<SelectItem> getListaGobierno() throws Exception{
        if(listaGobierno == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_GOBIERNO);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaGobierno =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaGobierno;
    }

    /**
     * @param listaGobierno the listaGobierno to set
     */
    public void setListaGobierno(List<SelectItem> listaGobierno) {
        this.listaGobierno = listaGobierno;
    }

    /**
     * @return the listaGobiernoActivo
     */
    public List<SelectItem> getListaGobiernoActivo() throws Exception{
        if(listaGobiernoActivo == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_GOBIERNO);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaGobiernoActivo =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaGobiernoActivo;
    }

    /**
     * @param listaGobiernoActivo the listaGobiernoActivo to set
     */
    public void setListaGobiernoActivo(List<SelectItem> listaGobiernoActivo) {
        this.listaGobiernoActivo = listaGobiernoActivo;
    }

    /**
     * @return the listaEntidad
     */
    public List<SelectItem> getListaEntidad() throws Exception{
        if(listaEntidad == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_ENTIDAD);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaEntidad =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaEntidad;
    }

    /**
     * @param listaEntidad the listaEntidad to set
     */
    public void setListaEntidad(List<SelectItem> listaEntidad) {
        this.listaEntidad = listaEntidad;
    }

    /**
     * @return the listaEntidadActivo
     */
    public List<SelectItem> getListaEntidadActivo() throws Exception{
        if(listaEntidadActivo == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_ENTIDAD);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaEntidadActivo =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaEntidadActivo;
    }

    /**
     * @param listaEntidadActivo the listaEntidadActivo to set
     */
    public void setListaEntidadActivo(List<SelectItem> listaEntidadActivo) {
        this.listaEntidadActivo = listaEntidadActivo;
    }

    public List<SelectItem> getListaTipoDocumentos() throws Exception {
        if(listaTipoDocumentos == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPODOCUMENTOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoDocumentos =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoDocumentos;
    }

    public void setListaTipoDocumentos(List<SelectItem> listaTipoDocumentos) {
        this.listaTipoDocumentos = listaTipoDocumentos;
    }

    public List<SelectItem> getListaTipoDocumentosActivos() throws Exception {
        if(listaTipoDocumentosActivos == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPODOCUMENTOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoDocumentosActivos =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoDocumentosActivos;
    }

    public void setListaTipoDocumentosActivos(List<SelectItem> listaTipoDocumentosActivos) {
        this.listaTipoDocumentosActivos = listaTipoDocumentosActivos;
    }

    public List<SelectItem> getListaTipoVideos() throws Exception {
        if(listaTipoVideos == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPOVIDEOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoVideos =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoVideos;
    }

    public void setListaTipoVideos(List<SelectItem> listaTipoVideos) {
        this.listaTipoVideos = listaTipoVideos;
    }

    public List<SelectItem> getListaTipoVideosActivos() throws Exception {
        if(listaTipoVideosActivos == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPOVIDEOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoVideosActivos =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoVideosActivos;
    }

    public void setListaTipoVideosActivos(List<SelectItem> listaTipoVideosActivos) {
        this.listaTipoVideosActivos = listaTipoVideosActivos;
    }

    public List<SelectItem> getListaTipoAudios() throws Exception {
        if(listaTipoAudios == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPOAUDIOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoAudios =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoAudios;
    }

    public void setListaTipoAudios(List<SelectItem> listaTipoAudios) {
        this.listaTipoAudios = listaTipoAudios;
    }

    public List<SelectItem> getListaTipoAudiosActivos() throws Exception {
        if(listaTipoAudiosActivos == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPOAUDIOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoAudiosActivos =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoAudiosActivos;
    }

    public void setListaTipoAudiosActivos(List<SelectItem> listaTipoAudiosActivos) {
        this.listaTipoAudiosActivos = listaTipoAudiosActivos;
    }

    public List<SelectItem> getListaTipoImagenes() throws Exception {
        if(listaTipoImagenes == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPOIMAGENES);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoImagenes =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoImagenes;
    }

    public void setListaTipoImagenes(List<SelectItem> listaTipoImagenes) {
        this.listaTipoImagenes = listaTipoImagenes;
    }

    public List<SelectItem> getListaTipoImagenesActivas() throws Exception {
        if(listaTipoImagenesActivas == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPOIMAGENES);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoImagenesActivas =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoImagenesActivas;
    }

    public void setListaTipoImagenesActivas(List<SelectItem> listaTipoImagenesActivas) {
        this.listaTipoImagenesActivas = listaTipoImagenesActivas;
    }

    public List<SelectItem> getListaTipoArchivosTexto() throws Exception {
        if(listaTipoArchivosTexto == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPOARCHIVOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoArchivosTexto =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoArchivosTexto;
    }

    public void setListaTipoArchivosTexto(List<SelectItem> listaTipoArchivosTexto) {
        this.listaTipoArchivosTexto = listaTipoArchivosTexto;
    }

    public List<SelectItem> getListaTipoArchivosTextoActivos() throws Exception {
        if(listaTipoArchivosTextoActivos == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPOARCHIVOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoArchivosTextoActivos =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoArchivosTextoActivos;
    }

    public void setListaTipoArchivosTextoActivos(List<SelectItem> listaTipoArchivosTextoActivos) {
        this.listaTipoArchivosTextoActivos = listaTipoArchivosTextoActivos;
    }

    public List<SelectItem> getListaTipoLinks() throws Exception {
        if(listaTipoLinks == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPOLINKS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoLinks =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoLinks;
    }

    public void setListaTipoLinks(List<SelectItem> listaTipoLinks) {
        this.listaTipoLinks = listaTipoLinks;
    }

    public List<SelectItem> getListaTipoLinksActivos() throws Exception {
        if(listaTipoLinksActivos == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPOLINKS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoLinksActivos =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoLinksActivos;
    }

    public void setListaTipoLinksActivos(List<SelectItem> listaTipoLinksActivos) {
        this.listaTipoLinksActivos = listaTipoLinksActivos;
    }

    public List<SelectItem> getListaTipoOtrosArchivos() throws Exception {
        if(listaTipoOtrosArchivos == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPOOTROSARCHIVOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoOtrosArchivos =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoOtrosArchivos;
    }

    public void setListaTipoOtrosArchivos(List<SelectItem> listaTipoOtrosArchivos) {
        this.listaTipoOtrosArchivos = listaTipoOtrosArchivos;
    }

    public List<SelectItem> getListaTipoOtrosArchivosActivos() throws Exception {
        if(listaTipoOtrosArchivosActivos == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPOOTROSARCHIVOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoOtrosArchivosActivos =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoOtrosArchivosActivos;
    }

    public void setListaTipoOtrosArchivosActivos(List<SelectItem> listaTipoOtrosArchivosActivos) {
        this.listaTipoOtrosArchivosActivos = listaTipoOtrosArchivosActivos;
    }

    public List<SelectItem> getListaMaestros() throws Exception {
        if(listaMaestros == null){
            MaestroService service = (MaestroService) ServiceFinder.findBean("MaestroService");
            listaMaestros =  new Items(service.getMaestros(), null, "nmaestroid","vnombre").getItems();
        }
        return listaMaestros;
    }

    public void setListaMaestros(List<SelectItem> listaMaestros) {
        this.listaMaestros = listaMaestros;
    }

    public List<SelectItem> getListaUsuarios() throws Exception {
        if(listaUsuarios == null){
            UserService service = (UserService) ServiceFinder.findBean("UserService");
            listaUsuarios =  new Items(service.getUsers(), null, "nusuarioid","vnombreCompleto").getItems();
        }
        return listaUsuarios;
    }

    public void setListaUsuarios(List<SelectItem> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<SelectItem> getListaUsuariosActivos() {
        return listaUsuariosActivos;
    }

    public void setListaUsuariosActivos(List<SelectItem> listaUsuariosActivos) {
        this.listaUsuariosActivos = listaUsuariosActivos;
    }

    /**
     * @return the filterEstado
     */
    public List<SelectItem> getFilterEstado() {
        if(filterEstado == null){
            filterEstado =  new ArrayList<SelectItem>();
            filterEstado.add(new SelectItem("", "Todos"));
            filterEstado.addAll(new Items(Parameters.getListaEstado(), null, "codigo","descripcion").getItems());
        }
        return filterEstado;
    }

    /**
     * @param filterEstado the filterEstado to set
     */
    public void setFilterEstado(List<SelectItem> filterEstado) {
        this.filterEstado = filterEstado;
    }

    public List<SelectItem> getFilterModulos() throws Exception {
        if(filterModulos == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_MODULOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            filterModulos =  new ArrayList<SelectItem>();
            filterModulos.add(new SelectItem("", "Todos"));
            filterModulos.addAll(new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems());
        }
        return filterModulos;
    }

    public void setFilterModulos(List<SelectItem> filterModulos) {
        this.filterModulos = filterModulos;
    }

    public List<SelectItem> getFilterPerfiles() throws Exception {
        if(filterPerfiles == null){
            PerfilService perfilService = (PerfilService) ServiceFinder.findBean("PerfilService");
            filterPerfiles =  new ArrayList<SelectItem>();
            filterPerfiles.add(new SelectItem("", "Todos"));
            filterPerfiles.addAll(new Items(perfilService.getPerfils(), null, "nperfilid","vnombre").getItems());
        }
        return filterPerfiles;
    }

    public void setFilterPerfiles(List<SelectItem> filterPerfiles) {
        this.filterPerfiles = filterPerfiles;
    }
    
}
