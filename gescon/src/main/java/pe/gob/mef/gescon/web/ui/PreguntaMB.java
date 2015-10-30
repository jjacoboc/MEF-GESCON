/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.service.AsignacionService;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.PreguntaService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Asignacion;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.Pregunta;

//import pe.gob.mef.gescon.web.bean.Politica;
/**
 *
 * @author JJacobo
 */
@ManagedBean
@ViewScoped
public class PreguntaMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(PreguntaMB.class);
    private List<Pregunta> listaPregunta;
    private List<Pregunta> flistaPregunta;
    private List<Asignacion> listaAsignacion;
    private Pregunta selectedPregunta;
    private Asignacion selectedAsignacion;
    private String asunto;
    private String detalle;
    private BigDecimal entidadId;
    private String datoAdicional;
    private String respuesta;
    private String msjusuario2;
    private String msjespecialista;
    private BigDecimal nsituacion;
    private String msjmoderador;
    private String msjusuario1;
    private String fSInfEspe; //SI Especialista
    private String fButtonEspe; //Botones Especialista
    private String fButtonMod;// Botones Moderador
    private String fButtonModPub;//Botones Moderador Publicar
    private String fMsjUsu1; //Mensaje 1 Usuario
    private String fMsjUsu2; //Mensaje 2 Usuario
    private String fSInfMod; //SI Moderador
    private String fButton; //Botones UE - ESPE
    private String fButtonUM; //Botones UE - MOD
    private String entidad;
    private String tema;
    private TreeNode tree;
    private Categoria selectedCategoria;

    /**
     * Creates a new instance of MaestroMB
     */
    public PreguntaMB() {
    }
    public BigDecimal cat1;

    /**
     * @return the listaPregunta
     */
    public List<Pregunta> getListaPregunta() {
        return listaPregunta;
    }

    /**
     * @param listaPregunta the listaPregunta to set
     */
    public void setListaPregunta(List<Pregunta> listaPregunta) {
        this.listaPregunta = listaPregunta;
    }

    /**
     * @return the flistaPregunta
     */
    public List<Pregunta> getFlistaPregunta() {
        return flistaPregunta;
    }

    /**
     * @param flistaPregunta the flistaPregunta to set
     */
    public void setFlistaPregunta(List<Pregunta> flistaPregunta) {
        this.flistaPregunta = flistaPregunta;
    }

    /**
     * @return the listaAsignacion
     */
    public List<Asignacion> getListaAsignacion() {
        return listaAsignacion;
    }

    /**
     * @param listaAsignacion the listaAsignacion to set
     */
    public void setListaAsignacion(List<Asignacion> listaAsignacion) {
        this.listaAsignacion = listaAsignacion;
    }

    /**
     * @return the selectedPregunta
     */
    public Pregunta getSelectedPregunta() {
        return selectedPregunta;
    }

    /**
     * @param selectedPregunta the selectedPregunta to set
     */
    public void setSelectedPregunta(Pregunta selectedPregunta) {
        this.selectedPregunta = selectedPregunta;
    }

    /**
     * @return the selectedAsignacion
     */
    public Asignacion getSelectedAsignacion() {
        return selectedAsignacion;
    }

    /**
     * @param selectedAsignacion the selectedAsignacion to set
     */
    public void setSelectedAsignacion(Asignacion selectedAsignacion) {
        this.selectedAsignacion = selectedAsignacion;
    }

    /**
     * @return the asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * @param asunto the asunto to set
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the entidadId
     */
    public BigDecimal getEntidadId() {
        return entidadId;
    }

    /**
     * @param entidadId the entidadId to set
     */
    public void setEntidadId(BigDecimal entidadId) {
        this.entidadId = entidadId;
    }

    /**
     * @return the datoAdicional
     */
    public String getDatoAdicional() {
        return datoAdicional;
    }

    /**
     * @param datoAdicional the datoAdicional to set
     */
    public void setDatoAdicional(String datoAdicional) {
        this.datoAdicional = datoAdicional;
    }

    /**
     * @return the respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * @return the msjusuario2
     */
    public String getMsjusuario2() {
        return msjusuario2;
    }

    /**
     * @param msjusuario2 the msjusuario2 to set
     */
    public void setMsjusuario2(String msjusuario2) {
        this.msjusuario2 = msjusuario2;
    }

    /**
     * @return the msjespecialista
     */
    public String getMsjespecialista() {
        return msjespecialista;
    }

    /**
     * @param msjespecialista the msjespecialista to set
     */
    public void setMsjespecialista(String msjespecialista) {
        this.msjespecialista = msjespecialista;
    }

    /**
     * @return the nsituacion
     */
    public BigDecimal getNsituacion() {
        return nsituacion;
    }

    /**
     * @param nsituacion the nsituacion to set
     */
    public void setNsituacion(BigDecimal nsituacion) {
        this.nsituacion = nsituacion;
    }

    /**
     * @return the msjmoderador
     */
    public String getMsjmoderador() {
        return msjmoderador;
    }

    /**
     * @param msjmoderador the msjmoderador to set
     */
    public void setMsjmoderador(String msjmoderador) {
        this.msjmoderador = msjmoderador;
    }

    /**
     * @return the msjusuario1
     */
    public String getMsjusuario1() {
        return msjusuario1;
    }

    /**
     * @param msjusuario1 the msjusuario1 to set
     */
    public void setMsjusuario1(String msjusuario1) {
        this.msjusuario1 = msjusuario1;
    }

    /**
     * @return the fSInfEspe
     */
    public String getfSInfEspe() {
        return fSInfEspe;
    }

    /**
     * @param fSInfEspe the fSInfEspe to set
     */
    public void setfSInfEspe(String fSInfEspe) {
        this.fSInfEspe = fSInfEspe;
    }

    /**
     * @return the fButtonEspe
     */
    public String getfButtonEspe() {
        return fButtonEspe;
    }

    /**
     * @param fButtonEspe the fButtonEspe to set
     */
    public void setfButtonEspe(String fButtonEspe) {
        this.fButtonEspe = fButtonEspe;
    }

    /**
     * @return the fButtonMod
     */
    public String getfButtonMod() {
        return fButtonMod;
    }

    /**
     * @param fButtonMod the fButtonMod to set
     */
    public void setfButtonMod(String fButtonMod) {
        this.fButtonMod = fButtonMod;
    }

    /**
     * @return the fButtonModPub
     */
    public String getfButtonModPub() {
        return fButtonModPub;
    }

    /**
     * @param fButtonModPub the fButtonModPub to set
     */
    public void setfButtonModPub(String fButtonModPub) {
        this.fButtonModPub = fButtonModPub;
    }

    /**
     * @return the fMsjUsu1
     */
    public String getfMsjUsu1() {
        return fMsjUsu1;
    }

    /**
     * @param fMsjUsu1 the fMsjUsu1 to set
     */
    public void setfMsjUsu1(String fMsjUsu1) {
        this.fMsjUsu1 = fMsjUsu1;
    }

    /**
     * @return the fMsjUsu2
     */
    public String getfMsjUsu2() {
        return fMsjUsu2;
    }

    /**
     * @param fMsjUsu2 the fMsjUsu2 to set
     */
    public void setfMsjUsu2(String fMsjUsu2) {
        this.fMsjUsu2 = fMsjUsu2;
    }

    /**
     * @return the fSInfMod
     */
    public String getfSInfMod() {
        return fSInfMod;
    }

    /**
     * @param fSInfMod the fSInfMod to set
     */
    public void setfSInfMod(String fSInfMod) {
        this.fSInfMod = fSInfMod;
    }

    /**
     * @return the fButton
     */
    public String getfButton() {
        return fButton;
    }

    /**
     * @param fButton the fButton to set
     */
    public void setfButton(String fButton) {
        this.fButton = fButton;
    }

    /**
     * @return the fButtonUM
     */
    public String getfButtonUM() {
        return fButtonUM;
    }

    /**
     * @param fButtonUM the fButtonUM to set
     */
    public void setfButtonUM(String fButtonUM) {
        this.fButtonUM = fButtonUM;
    }

    /**
     * @return the entidad
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * @param entidad the entidad to set
     */
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    /**
     * @return the tema
     */
    public String getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * @return the tree
     */
    public TreeNode getTree() {
        return tree;
    }

    /**
     * @param tree the tree to set
     */
    public void setTree(TreeNode tree) {
        this.tree = tree;
    }

    /**
     * @return the selectedCategoria
     */
    public Categoria getSelectedCategoria() {
        return selectedCategoria;
    }

    /**
     * @param selectedCategoria the selectedCategoria to set
     */
    public void setSelectedCategoria(Categoria selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }

    @PostConstruct
    public void init() {
        try {
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            listaPregunta = service.getPreguntas();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void cleanAttributes() {
        this.setSelectedCategoria(null);
        this.setEntidad(StringUtils.EMPTY);
        this.setEntidadId(null);
        this.setAsunto(StringUtils.EMPTY);
        this.setDetalle(StringUtils.EMPTY);
        this.setDatoAdicional(StringUtils.EMPTY);
        Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();

        if (iter.hasNext() == true) {
            iter.remove();
            FacesContext.getCurrentInstance().renderResponse();
        }
    }

    public void loadTree(ActionEvent event) {
        try {
            if (this.getTree() == null) {
                CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                createTree(service.getCategorias());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void createTree(List<Categoria> lista) {
        try {
            for (Categoria ele : lista) {
                String id = ele.getNcategoriasup() != null ? ele.getNcategoriasup().toString() : null;
                if (id != null) {
                    TreeNode parent = this.getNodeByIdCategoria(this.getTree(), id);
                    TreeNode node = new DefaultTreeNode(ele, parent);
                    node.setParent(parent);
                } else {
                    this.setTree(new DefaultTreeNode(ele, this.getTree()));
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public TreeNode getNodeByIdCategoria(TreeNode treeNode, String idCategoria) {
        try {
            if (treeNode != null) {
                Categoria categoria = (Categoria) treeNode.getData();
                if (categoria.getNcategoriaid().toString().equals(idCategoria)) {
                    return treeNode;
                }
                List<TreeNode> lista = treeNode.getChildren();
                if (lista != null && !lista.isEmpty()) {
                    for (TreeNode node : lista) {
                        if (node != null) {
                            TreeNode tn = this.getNodeByIdCategoria(node, idCategoria);
                            if (tn != null) {
                                return tn;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return null;
    }

    public void onNodeSelect(NodeSelectEvent event) {
        try {
            if (event != null) {
                this.setSelectedCategoria((Categoria) event.getTreeNode().getData());
                this.selectedPregunta.setNcategoriaid(this.selectedCategoria.getNcategoriaid());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toSave(ActionEvent event) {
        try {
            this.cleanAttributes();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toEnt(ActionEvent event) {
        try {
            this.entidad = "MEF";
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void save(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }
            BigDecimal idpregunta;

            Pregunta pregunta = new Pregunta();
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            idpregunta = service.getNextPK();
            pregunta.setNpreguntaid(idpregunta);
            pregunta.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            pregunta.setVasunto(this.getAsunto().trim());
            pregunta.setVdetalle(this.getDetalle().trim());
            pregunta.setNentidadid(this.getEntidadId());
            pregunta.setVdatoadicional(this.getDatoAdicional().trim());
            pregunta.setNactivo(BigDecimal.ONE);
            pregunta.setDfechacreacion(new Date());
            pregunta.setNsituacion(BigDecimal.valueOf(Long.parseLong("1")));
            service.saveOrUpdate(pregunta);

            Asignacion asignacion = new Asignacion();
            LoginMB mb = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            asignacion.setNconocimientoid(idpregunta);
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            asignacion.setNusuarioid(BigDecimal.valueOf(Long.parseLong("2")));
            asignacion.setDfechaasignacion(new Date());
            asignacion.setDfechacreacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            listaPregunta = service.getPreguntas();
            RequestContext.getCurrentInstance().execute("PF('newDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void activar(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedPregunta() != null) {
                    PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
                    this.getSelectedPregunta().setNactivo(BigDecimal.ONE);
                    this.getSelectedPregunta().setDfechamodificacion(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
                    service.saveOrUpdate(this.getSelectedPregunta());
                    this.setListaPregunta(service.getPreguntas());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar la pregunta a activar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void desactivar(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedPregunta() != null) {
                    PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
                    this.getSelectedPregunta().setNactivo(BigDecimal.ZERO);
                    this.getSelectedPregunta().setDfechamodificacion(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
                    service.saveOrUpdate(this.getSelectedPregunta());
                    this.setListaPregunta(service.getPreguntas());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar la base legal a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toSee(ActionEvent event) {
        try {
            if (event != null) {
                int perfil, situacion;
                int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
                this.setSelectedPregunta(this.getListaPregunta().get(index));

                PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
                LoginMB mb = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                listaAsignacion = service.obtenerPreguntaxAsig(this.getSelectedPregunta().getNpreguntaid(), mb.getUser().getNusuarioid(), Constante.PREGUNTAS);
                flistaPregunta = service.obtenerPreguntas(this.getSelectedPregunta().getNpreguntaid(), mb.getUser().getNusuarioid(), Constante.PREGUNTAS);

                situacion = Integer.parseInt(this.getSelectedPregunta().getNsituacion().toString());

                if (listaAsignacion.size() == 0 || flistaPregunta.isEmpty()) {
                    this.setfButton("false");
                    this.setfButtonUM("false");
                    this.setfButtonEspe("false");
                    this.setfButtonMod("false");
                    this.setfButtonModPub("false");
                } else {

                    setSelectedAsignacion(getListaAsignacion().get(0));

                    AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                    getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                    getSelectedAsignacion().setDfecharecepcion(new Date());
                    serviceasig.saveOrUpdate(getSelectedAsignacion());

                    perfil = Integer.parseInt(service.obtenerPerfilxUsuario(mb.getUser().getNusuarioid()).toString());

                    if (perfil == Constante.ESPECIALISTA) {
                        this.setfButtonEspe("true");
                        this.setfButton("false");
                        this.setfButtonUM("false");
                        this.setfButtonMod("false");
                        this.setfButtonModPub("false");
                    } else {
                        if (perfil == Constante.MODERADOR) {
                            this.setfButtonEspe("false");
                            this.setfButton("false");
                            this.setfButtonUM("false");
                            if (StringUtils.isBlank(this.getSelectedPregunta().getVrespuesta())) {
                                this.setfButtonMod("true");
                                this.setfButtonModPub("false");
                            } else {
                                this.setfButtonMod("false");
                                this.setfButtonModPub("true");
                            }
                        } else {

                            if (situacion == 1) {
                                if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjmoderador())) {
                                    this.setfButtonUM("false");
                                } else {
                                    this.setfButtonUM("true");
                                }
                            } else {
                                if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjespecialista())) {
                                    this.setfButton("false");
                                } else {
                                    this.setfButton("true");
                                }
                            }
                            this.setfButtonEspe("false");
                            this.setfButtonMod("false");
                            this.setfButtonModPub("false");
                        }
                    }
                }

                this.cat1 = this.getSelectedPregunta().getNcategoriaid();

                situacion = Integer.parseInt(this.getSelectedPregunta().getNsituacion().toString());

                if (situacion == 1) {
                    if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjmoderador())) {
                        this.fSInfMod = "false";
                    } else {
                        this.fSInfMod = "true";
                    }

                    if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjusuario1())) {
                        this.fMsjUsu1 = "false";
                    } else {
                        this.fMsjUsu1 = "true";
                    }
                    this.fSInfEspe = "false";
                    this.fMsjUsu2 = "false";
                }

                if (situacion == 2 || situacion == 3 || situacion == 4) {
                    if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjespecialista())) {
                        this.fSInfEspe = "false";
                    } else {
                        this.fSInfEspe = "true";
                    }

                    if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjusuario2())) {
                        this.fMsjUsu2 = "false";
                    } else {
                        this.fMsjUsu2 = "true";
                    }
                    this.fSInfMod = "false";
                    this.fMsjUsu1 = "false";
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toCancel(ActionEvent event) {
        try {
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            listaPregunta = service.getPreguntas();
            RequestContext.getCurrentInstance().execute("PF('respDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modpubDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void Rechazar(ActionEvent event) {
        try {
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setNsituacion(BigDecimal.valueOf(Long.parseLong("7")));
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('modDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toCancelSi(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('siDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toCancelSiMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('simodDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toCancelRespUsu(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respusuDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toCancelRespUsuMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respmodDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toSi(ActionEvent event) {
        try {

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('seeDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toModPub(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('seeDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toEdit(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modpubDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toResp(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('seeDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toCancelRespEdit(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('editrespDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editpregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editpubDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toRespUsu(ActionEvent event) {
        try {

            this.cleanAttributes();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toRespUsuMod(ActionEvent event) {
        try {

            this.cleanAttributes();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveResp(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }
            BigDecimal cat2;

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setVrespuesta(this.getSelectedPregunta().getVrespuesta().toUpperCase());
            this.getSelectedPregunta().setNcategoriaid(this.getSelectedPregunta().getNcategoriaid());

            cat2 = this.getSelectedPregunta().getNcategoriaid();

            if (Integer.parseInt(cat1.toString()) != Integer.parseInt(cat2.toString())) {
                this.getSelectedPregunta().setNsituacion(BigDecimal.valueOf(Long.parseLong("3")));
            } else {
                this.getSelectedPregunta().setNsituacion(BigDecimal.valueOf(Long.parseLong("6")));
            }

            service.saveOrUpdate(this.getSelectedPregunta());
            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('respDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendUsu(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }
            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjusuario2())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre de perfil.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setVmsjusuario2(this.getSelectedPregunta().getVmsjusuario2().toUpperCase());
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            asignacion.setNusuarioid(BigDecimal.valueOf(Long.parseLong("3")));
            asignacion.setDfechacreacion(new Date());
            asignacion.setDfechaasignacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            this.fMsjUsu2 = "true";

            this.setListaPregunta(service.getPreguntas());

            RequestContext.getCurrentInstance().execute("PF('respusuDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('seeDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendUsuMod(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }
            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjusuario1())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre de perfil.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setVmsjusuario1(this.getSelectedPregunta().getVmsjusuario1().toUpperCase());
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            asignacion.setNusuarioid(BigDecimal.valueOf(Long.parseLong("2")));
            asignacion.setDfechacreacion(new Date());
            asignacion.setDfechaasignacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            this.fMsjUsu1 = "true";

            this.setListaPregunta(service.getPreguntas());

            RequestContext.getCurrentInstance().execute("PF('respmodDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('seeDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendSi(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }
            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjespecialista())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre de perfil.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setVmsjespecialista(this.getSelectedPregunta().getVmsjespecialista().toUpperCase());
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            asignacion.setNusuarioid(BigDecimal.valueOf(Long.parseLong("4")));
            asignacion.setDfechacreacion(new Date());
            asignacion.setDfechaasignacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            this.fSInfEspe = "true";

            this.setListaPregunta(service.getPreguntas());

            RequestContext.getCurrentInstance().execute("PF('siDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('respDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendSiMod(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }
            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjmoderador())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre de perfil.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setVmsjmoderador(this.getSelectedPregunta().getVmsjmoderador().toUpperCase());
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            asignacion.setNusuarioid(BigDecimal.valueOf(Long.parseLong("4")));
            asignacion.setDfechaasignacion(new Date());
            asignacion.setDfechacreacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            this.fSInfMod = "true";

            this.setListaPregunta(service.getPreguntas());

            RequestContext.getCurrentInstance().execute("PF('simodDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void Publicar(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setNsituacion(BigDecimal.valueOf(Long.parseLong("6")));
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            //Asignacion asignacion = new Asignacion();
            //asignacion.setNasignacionid(serviceasig.getNextPK());
            //asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            //asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
            //asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            //asignacion.setNusuarioid(BigDecimal.valueOf(Long.parseLong("4")));
            //asignacion.setDfechacreacion(new Date());
            //serviceasig.saveOrUpdate(asignacion);
            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('modpubDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void Responder(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }

            if (StringUtils.isBlank(this.getSelectedPregunta().getVrespuesta())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese la respuesta.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setNsituacion(BigDecimal.valueOf(Long.parseLong("5")));
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            asignacion.setNusuarioid(BigDecimal.valueOf(Long.parseLong("2")));
            asignacion.setDfechacreacion(new Date());
            asignacion.setDfechaasignacion(new Date());
            serviceasig.saveOrUpdate(asignacion);
            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('respDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void DevEsp(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setNsituacion(BigDecimal.valueOf(Long.parseLong("2")));
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            asignacion.setNusuarioid(BigDecimal.valueOf(Long.parseLong("3")));
            asignacion.setDfechacreacion(new Date());
            asignacion.setDfechaasignacion(new Date());
            serviceasig.saveOrUpdate(asignacion);
            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('modDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveRespEdit(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");

            this.getSelectedPregunta().setVrespuesta(this.getSelectedPregunta().getVrespuesta().toUpperCase());
            this.getSelectedPregunta().setNcategoriaid(this.getSelectedPregunta().getNcategoriaid());
            this.getSelectedPregunta().setVasunto(this.getSelectedPregunta().getVasunto().trim());
            this.getSelectedPregunta().setVdetalle(this.getSelectedPregunta().getVdetalle().trim());
            this.getSelectedPregunta().setNentidadid(this.getSelectedPregunta().getNentidadid());
            this.getSelectedPregunta().setVdatoadicional(this.getSelectedPregunta().getVdatoadicional().trim());
            this.getSelectedPregunta().setDfechamodificacion(new Date());
            service.saveOrUpdate(this.getSelectedPregunta());

            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('editrespDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editpregDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public void savePregEdit(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");

            this.getSelectedPregunta().setNcategoriaid(this.getSelectedPregunta().getNcategoriaid());
            this.getSelectedPregunta().setVasunto(this.getSelectedPregunta().getVasunto().trim());
            this.getSelectedPregunta().setVdetalle(this.getSelectedPregunta().getVdetalle().trim());
            this.getSelectedPregunta().setNentidadid(this.getSelectedPregunta().getNentidadid());
            this.getSelectedPregunta().setVdatoadicional(this.getSelectedPregunta().getVdatoadicional().trim());
            this.getSelectedPregunta().setDfechamodificacion(new Date());
            service.saveOrUpdate(this.getSelectedPregunta());

            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('editrespDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editpregDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public void savePubEdit(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");

            this.getSelectedPregunta().setVrespuesta(this.getSelectedPregunta().getVrespuesta());
            this.getSelectedPregunta().setVasunto(this.getSelectedPregunta().getVasunto().trim());
            this.getSelectedPregunta().setVdetalle(this.getSelectedPregunta().getVdetalle().trim());
            this.getSelectedPregunta().setNentidadid(this.getSelectedPregunta().getNentidadid());
            this.getSelectedPregunta().setVdatoadicional(this.getSelectedPregunta().getVdatoadicional().trim());
            this.getSelectedPregunta().setDfechamodificacion(new Date());
            service.saveOrUpdate(this.getSelectedPregunta());

            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('editrespDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editpregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editpubDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }

}
