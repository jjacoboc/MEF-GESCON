/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.hibernate.domain.TvinculoBaselegalId;
import pe.gob.mef.gescon.service.ArchivoService;
import pe.gob.mef.gescon.service.AsignacionService;
import pe.gob.mef.gescon.service.BaseLegalService;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.PassService;
import pe.gob.mef.gescon.service.PerfilService;
import pe.gob.mef.gescon.service.PreguntaService;
import pe.gob.mef.gescon.service.UserService;
import pe.gob.mef.gescon.service.VinculoBaseLegalService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Archivo;
import pe.gob.mef.gescon.web.bean.Asignacion;
import pe.gob.mef.gescon.web.bean.BaseLegal;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.Pass;
import pe.gob.mef.gescon.web.bean.Perfil;
import pe.gob.mef.gescon.web.bean.Pregunta;
import pe.gob.mef.gescon.web.bean.User;
import pe.gob.mef.gescon.web.bean.VinculoBaselegal;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class LoginMB implements Serializable {

    private static final Log log = LogFactory.getLog(LoginMB.class);
    private final String temppath = "\\\\OSC2018\\gescon\\temp\\";
    private String path = "\\\\OSC2018\\gescon\\files\\bl\\";
    private User user;
    private Perfil perfil;
    private List<Perfil> perfiles;
    private String login;
    private String pass;
    private BigDecimal notificaciones;
    private BigDecimal notificacionesAsignadas;
    private BigDecimal notificacionesRecibidas;
    private BigDecimal notificacionesAtendidas;
    private List<Consulta> listaNotificacionesAsignadas;
    private List<Consulta> listaNotificacionesRecibidas;
    private List<Consulta> listaNotificacionesAtendidas;
    private Consulta selectedNotification;
    private Asignacion selectedAsignacion;
    private BaseLegal selectedBaseLegal;
    private Boolean chkGobNacional;
    private Boolean chkGobRegional;
    private Boolean chkGobLocal;
    private Boolean chkMancomunidades;
    private DualListModel<BaseLegal> pickList;
    private List<BaseLegal> listaSource;
    private List<BaseLegal> listaTarget;
    private UploadedFile uploadFile;
    private File file;
    private String fMsjModBase; //Mensaje Moderador Base Legal
    private String fMsjUsuCreaBase; //Mensaje Usuario creacion Base Legal
    private Pregunta selectedPregunta;
    private List<Pregunta> flistaPregunta;
    private List<Asignacion> listaAsignacion;
    private String fSInfEspe; //SI Especialista
    private String fButtonEspe; //Botones Especialista
    private String fButtonMod;// Botones Moderador
    private String fButtonModPub;//Botones Moderador Publicar
    private String fMsjUsu1; //Mensaje 1 Usuario
    private String fMsjUsu2; //Mensaje 2 Usuario
    private String fSInfMod; //SI Moderador
    private String fButton; //Botones UE - ESPE
    private String fButtonUM; //Botones UE - MOD
    private TreeNode tree;
    private Categoria selectedCategoria;
    private BigDecimal entidadId;
    private String entidad;

    /**
     * Creates a new instance of LoginMB
     */
    public LoginMB() {
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the perfil
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the perfiles
     */
    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    /**
     * @param perfiles the perfiles to set
     */
    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    public BigDecimal getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(BigDecimal notificaciones) {
        this.notificaciones = notificaciones;
    }

    public BigDecimal getNotificacionesAsignadas() {
        return notificacionesAsignadas;
    }

    public void setNotificacionesAsignadas(BigDecimal notificacionesAsignadas) {
        this.notificacionesAsignadas = notificacionesAsignadas;
    }

    public BigDecimal getNotificacionesRecibidas() {
        return notificacionesRecibidas;
    }

    public void setNotificacionesRecibidas(BigDecimal notificacionesRecibidas) {
        this.notificacionesRecibidas = notificacionesRecibidas;
    }

    public BigDecimal getNotificacionesAtendidas() {
        return notificacionesAtendidas;
    }

    public void setNotificacionesAtendidas(BigDecimal notificacionesAtendidas) {
        this.notificacionesAtendidas = notificacionesAtendidas;
    }

    public List<Consulta> getListaNotificacionesAsignadas() {
        return listaNotificacionesAsignadas;
    }

    public void setListaNotificacionesAsignadas(List<Consulta> listaNotificacionesAsignadas) {
        this.listaNotificacionesAsignadas = listaNotificacionesAsignadas;
    }

    public List<Consulta> getListaNotificacionesRecibidas() {
        return listaNotificacionesRecibidas;
    }

    public void setListaNotificacionesRecibidas(List<Consulta> listaNotificacionesRecibidas) {
        this.listaNotificacionesRecibidas = listaNotificacionesRecibidas;
    }

    public List<Consulta> getListaNotificacionesAtendidas() {
        return listaNotificacionesAtendidas;
    }

    public void setListaNotificacionesAtendidas(List<Consulta> listaNotificacionesAtendidas) {
        this.listaNotificacionesAtendidas = listaNotificacionesAtendidas;
    }

    public Consulta getSelectedNotification() {
        return selectedNotification;
    }

    public void setSelectedNotification(Consulta selectedNotification) {
        this.selectedNotification = selectedNotification;
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
     * @return the selectedBaseLegal
     */
    public BaseLegal getSelectedBaseLegal() {
        return selectedBaseLegal;
    }

    /**
     * @param selectedBaseLegal the selectedBaseLegal to set
     */
    public void setSelectedBaseLegal(BaseLegal selectedBaseLegal) {
        this.selectedBaseLegal = selectedBaseLegal;
    }

    /**
     * @return the listaTarget
     */
    public List<BaseLegal> getListaTarget() {
        return listaTarget;
    }

    /**
     * @param listaTarget the listaTarget to set
     */
    public void setListaTarget(List<BaseLegal> listaTarget) {
        this.listaTarget = listaTarget;
    }

    /**
     * @return the chkGobNacional
     */
    public Boolean getChkGobNacional() {
        return chkGobNacional;
    }

    /**
     * @param chkGobNacional the chkGobNacional to set
     */
    public void setChkGobNacional(Boolean chkGobNacional) {
        this.chkGobNacional = chkGobNacional;
    }

    /**
     * @return the chkGobRegional
     */
    public Boolean getChkGobRegional() {
        return chkGobRegional;
    }

    /**
     * @param chkGobRegional the chkGobRegional to set
     */
    public void setChkGobRegional(Boolean chkGobRegional) {
        this.chkGobRegional = chkGobRegional;
    }

    /**
     * @return the chkGobLocal
     */
    public Boolean getChkGobLocal() {
        return chkGobLocal;
    }

    /**
     * @param chkGobLocal the chkGobLocal to set
     */
    public void setChkGobLocal(Boolean chkGobLocal) {
        this.chkGobLocal = chkGobLocal;
    }

    /**
     * @return the chkMancomunidades
     */
    public Boolean getChkMancomunidades() {
        return chkMancomunidades;
    }

    /**
     * @param chkMancomunidades the chkMancomunidades to set
     */
    public void setChkMancomunidades(Boolean chkMancomunidades) {
        this.chkMancomunidades = chkMancomunidades;
    }

    /**
     * @return the pickList
     */
    public DualListModel<BaseLegal> getPickList() {
        return pickList;
    }

    /**
     * @param pickList the pickList to set
     */
    public void setPickList(DualListModel<BaseLegal> pickList) {
        this.pickList = pickList;
    }

    /**
     * @return the listaSource
     */
    public List<BaseLegal> getListaSource() {
        return listaSource;
    }

    /**
     * @param listaSource the listaSource to set
     */
    public void setListaSource(List<BaseLegal> listaSource) {
        this.listaSource = listaSource;
    }

    /**
     * @return the uploadFile
     */
    public UploadedFile getUploadFile() {
        return uploadFile;
    }

    /**
     * @param uploadFile the uploadFile to set
     */
    public void setUploadFile(UploadedFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the fMsjModBase
     */
    public String getfMsjModBase() {
        return fMsjModBase;
    }

    /**
     * @param fMsjModBase the fMsjModBase to set
     */
    public void setfMsjModBase(String fMsjModBase) {
        this.fMsjModBase = fMsjModBase;
    }

    /**
     * @return the fMsjUsuCreaBase
     */
    public String getfMsjUsuCreaBase() {
        return fMsjUsuCreaBase;
    }

    /**
     * @param fMsjUsuCreaBase the fMsjUsuCreaBase to set
     */
    public void setfMsjUsuCreaBase(String fMsjUsuCreaBase) {
        this.fMsjUsuCreaBase = fMsjUsuCreaBase;
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

    public String ingresar() {
        String page = StringUtils.EMPTY;
        try {
            if (StringUtils.isNotBlank(this.getLogin()) && StringUtils.isNotBlank(this.getPass())) {
                UserService service = (UserService) ServiceFinder.findBean("UserService");
                this.setUser(service.getUserByLogin(this.getLogin()));
                if (this.getUser() != null) {
                    PassService passService = (PassService) ServiceFinder.findBean("PassService");
                    Pass pas = passService.getPassByUser(this.getUser());
                    if (pas != null) {
                        PerfilService perfilService = (PerfilService) ServiceFinder.findBean("PerfilService");
                        List<Perfil> listaperfiles = perfilService.getPerfilesByUser(this.getUser());
                        if (!CollectionUtils.isEmpty(listaperfiles)) {
                            this.setPerfil(listaperfiles.get(0));
                            AsignacionService asignacionService = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                            this.setNotificaciones(asignacionService.getNumberNotificationsByUser(this.getUser()));
                            this.setNotificacionesAsignadas(asignacionService.getNumberNotificationsAssignedByUser(this.getUser()));
                            this.setNotificacionesRecibidas(asignacionService.getNumberNotificationsReceivedByUser(this.getUser()));
                            this.setNotificacionesAtendidas(asignacionService.getNumberNotificationsServedByUser(this.getUser()));
                            if (this.getPerfil().getNperfilid().toString().equals(Constante.ROL_ADMINISTRADOR)) {
                                page = "/pages/indexAdmin?faces-redirect=true";
                            } else if (this.getPerfil().getNperfilid().toString().equals(Constante.ROL_MODERADOR)) {
                                page = "/index?faces-redirect=true";
                            } else if (this.getPerfil().getNperfilid().toString().equals(Constante.ROL_ESPECIALISTA)) {
                                page = "/index?faces-redirect=true";
                            } else if (this.getPerfil().getNperfilid().toString().equals(Constante.ROL_USUARIOEXTERNO)) {
                                page = "/index?faces-redirect=true";
                            } else if (this.getPerfil().getNperfilid().toString().equals(Constante.ROL_USUARIOINTERNO)) {
                                page = "/index?faces-redirect=true";
                            }

                        } else {
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "El usuario ingresado no cuenta con un rol asignado. \nComuníquese con el administrador del servicio.");
                            FacesContext.getCurrentInstance().addMessage(null, message);
                            return StringUtils.EMPTY;
                        }
                    } else {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "La contraseña ingresada es incorrecta.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return StringUtils.EMPTY;
                    }
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "El usuario ingresado no se encuentra registrado.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return StringUtils.EMPTY;
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Ingrese su usuario y contraseña para ingresar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return StringUtils.EMPTY;
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return page;
    }

    public void loadAssignedPanel(ActionEvent event) {
        try {
            if (event != null) {
                AsignacionService asignacionService = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.setListaNotificacionesAsignadas(asignacionService.getNotificationsAssignedPanelByUser(this.getUser()));
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void loadReceivedPanel(ActionEvent event) {
        try {
            if (event != null) {
                AsignacionService asignacionService = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.setListaNotificacionesRecibidas(asignacionService.getNotificationsReceivedPanelByUser(this.getUser()));
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void loadServedPanel(ActionEvent event) {
        try {
            if (event != null) {
                AsignacionService asignacionService = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.setListaNotificacionesAtendidas(asignacionService.getNotificationsServedPanelByUser(this.getUser()));
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
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

    public String toEditPendiente() {
        String pagina = null;
        try {
            LoginMB mb = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            int perfil_actual = Integer.parseInt(service.obtenerPerfilxUsuario(mb.getUser().getNusuarioid()).toString());
            int user_actual = Integer.parseInt(mb.getUser().getNusuarioid().toString());
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            this.setSelectedNotification(this.getListaNotificacionesAsignadas().get(index));
            Integer tipo = this.getSelectedNotification().getIdTipoConocimiento().intValue();
            Integer id = this.getSelectedNotification().getIdconocimiento().intValue();
            switch (tipo) {
                case 1: {
                    BaseLegalService servicebl = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
                    this.setSelectedBaseLegal(servicebl.getBaselegalById(BigDecimal.valueOf(id)));
                    ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
                    this.selectedBaseLegal.setArchivo(aservice.getLastArchivoByBaseLegal(this.getSelectedBaseLegal()));
                    this.setChkGobNacional(this.getSelectedBaseLegal().getNgobnacional().equals(BigDecimal.ONE));
                    this.setChkGobRegional(this.getSelectedBaseLegal().getNgobregional().equals(BigDecimal.ONE));
                    this.setChkGobLocal(this.getSelectedBaseLegal().getNgoblocal().equals(BigDecimal.ONE));
                    this.setChkMancomunidades(this.getSelectedBaseLegal().getNmancomunidades().equals(BigDecimal.ONE));
                    CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedBaseLegal().getNcategoriaid()));
                    this.setListaSource(servicebl.getTbaselegalesNotLinkedById(this.getSelectedBaseLegal().getNbaselegalid()));
                    this.setListaTarget(servicebl.getTbaselegalesLinkedById(this.getSelectedBaseLegal().getNbaselegalid()));
                    this.setPickList(new DualListModel<BaseLegal>(this.getListaSource(), this.getListaTarget()));
                    setListaAsignacion(servicebl.obtenerBaseLegalxAsig(this.getSelectedBaseLegal().getNbaselegalid(), mb.getUser().getNusuarioid(), Constante.BASELEGAL));
                    setSelectedAsignacion(getListaAsignacion().get(0));

                    if (StringUtils.isBlank(this.getSelectedBaseLegal().getVmsjmoderador())) {
                        this.setfMsjModBase("false");
                    } else {
                        this.setfMsjModBase("true");
                    }

                    if (StringUtils.isBlank(this.getSelectedBaseLegal().getVmsjusuariocreacion())) {
                        this.setfMsjUsuCreaBase("false");
                    } else {
                        this.setfMsjUsuCreaBase("true");
                    }

                    if (perfil_actual == Constante.MODERADOR) {
                        pagina = "/pages/Pendientes/moderarBaseLegal?faces-redirect=true";
                    }

                    if ((serviceasig.getUserCreacionByBaseLegal(this.getSelectedBaseLegal().getNbaselegalid()).intValue()) == user_actual) {
                        pagina = "/pages/Pendientes/responderBaseLegal?faces-redirect=true";
                    }

                    break;
                }
                case 2: {
                    int situacion;

                    this.setSelectedPregunta(service.getPreguntaById(BigDecimal.valueOf(id)));

                    setListaAsignacion(service.obtenerPreguntaxAsig(this.getSelectedPregunta().getNpreguntaid(), mb.getUser().getNusuarioid(), Constante.PREGUNTAS));
                    setFlistaPregunta(service.obtenerPreguntas(this.getSelectedPregunta().getNpreguntaid(), mb.getUser().getNusuarioid(), Constante.PREGUNTAS));

                    situacion = Integer.parseInt(this.getSelectedPregunta().getNsituacionid().toString());

                    if (getListaAsignacion().isEmpty() || getFlistaPregunta().isEmpty()) {
                        this.setfButton("false");
                        this.setfButtonUM("false");
                        this.setfButtonEspe("false");
                        this.setfButtonMod("false");
                        this.setfButtonModPub("false");
                    } else {
                        setSelectedAsignacion(getListaAsignacion().get(0));

                        getSelectedAsignacion().setDfecharecepcion(new Date());
                        serviceasig.saveOrUpdate(getSelectedAsignacion());
                        perfil_actual = Integer.parseInt(service.obtenerPerfilxUsuario(mb.getUser().getNusuarioid()).toString());
                        if (perfil_actual == Constante.ESPECIALISTA) {
                            this.setfButtonEspe("true");
                            this.setfButton("false");
                            this.setfButtonUM("false");
                            this.setfButtonMod("false");
                            this.setfButtonModPub("false");
                        } else {
                            if (perfil_actual == Constante.MODERADOR) {
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
                    
                    if (situacion == 1) {
                        pagina = "/pages/Pendientes/moderarPregunta?faces-redirect=true";
                    }
                    
                    if (situacion == 2 || situacion == 3) {
                        pagina = "/pages/Pendientes/responderPregunta?faces-redirect=true";
                    }
                    
                    if (situacion == 5) {
                        pagina = "/pages/Pendientes/publicarPregunta?faces-redirect=true";
                    }
                    
                    break;
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return pagina;
    }

    public void toMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('seePregDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toEdit(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modPubPregDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String DevEsp() {
        String pagina = null;
        try {

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setNsituacionid(BigDecimal.valueOf((long) 2));
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
            asignacion.setNusuarioid(serviceasig.getEspecialistaByCategoria(this.getSelectedPregunta().getNcategoriaid()));

            asignacion.setDfechacreacion(new Date());
            asignacion.setDfechaasignacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            pagina = "/index.xhtml";

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toSi(ActionEvent event) {
        try {

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String Rechazar() {
        String pagina = null;
        try {

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setNsituacionid(BigDecimal.valueOf((long) 7));
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());
            pagina = "/index.xhtml";

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toCancel(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modPubPregDialog').hide();");
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

    public void savePregEdit(ActionEvent event) throws Exception {
        try {

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user_savepreg = loginMB.getUser();

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            this.getSelectedPregunta().setVasunto(this.getSelectedPregunta().getVasunto().trim());
            this.getSelectedPregunta().setVdetalle(this.getSelectedPregunta().getVdetalle().trim());
            this.getSelectedPregunta().setNentidadid(this.getSelectedPregunta().getNentidadid());
            this.getSelectedPregunta().setVdatoadicional(this.getSelectedPregunta().getVdatoadicional().trim());
            this.getSelectedPregunta().setDfechamodificacion(new Date());
            this.getSelectedPregunta().setVusuariomodificacion(user_savepreg.getVlogin());

            service.saveOrUpdate(this.getSelectedPregunta());

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public void toCancelRespEdit(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('editRespPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editPubPregDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String sendSiMod() {
        String pagina = null;
        try {
            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjmoderador())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                pagina = null;
            } else {
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
                asignacion.setNusuarioid(serviceasig.getUserCreacionByPregunta(this.getSelectedPregunta().getNpreguntaid()));
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                this.fSInfMod = "true";
                pagina = "/index.xhtml";
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toCancelSiMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('siModPregDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toModPub(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('seePregDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String Publicar() {
        String pagina=null;
        try {
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setNsituacionid(BigDecimal.valueOf((long) 6));
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            pagina = "/index.xhtml";

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void savePubEdit(ActionEvent event) throws Exception {
        try {
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");

            this.getSelectedPregunta().setVrespuesta(this.getSelectedPregunta().getVrespuesta());
            this.getSelectedPregunta().setVasunto(this.getSelectedPregunta().getVasunto().trim());
            this.getSelectedPregunta().setVdetalle(this.getSelectedPregunta().getVdetalle().trim());
            this.getSelectedPregunta().setNentidadid(this.getSelectedPregunta().getNentidadid());
            this.getSelectedPregunta().setVdatoadicional(this.getSelectedPregunta().getVdatoadicional().trim());
            this.getSelectedPregunta().setDfechamodificacion(new Date());
            service.saveOrUpdate(this.getSelectedPregunta());

            RequestContext.getCurrentInstance().execute("PF('editRespPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editPubPregDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toResp(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('seePregDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveRespEdit(ActionEvent event) throws Exception {
        try {

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");

            this.getSelectedPregunta().setVrespuesta(this.getSelectedPregunta().getVrespuesta().toUpperCase());
            this.getSelectedPregunta().setVasunto(this.getSelectedPregunta().getVasunto().trim());
            this.getSelectedPregunta().setVdetalle(this.getSelectedPregunta().getVdetalle().trim());
            this.getSelectedPregunta().setNentidadid(this.getSelectedPregunta().getNentidadid());
            this.getSelectedPregunta().setVdatoadicional(this.getSelectedPregunta().getVdatoadicional().trim());
            this.getSelectedPregunta().setDfechamodificacion(new Date());
            service.saveOrUpdate(this.getSelectedPregunta());

            RequestContext.getCurrentInstance().execute("PF('editRespPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editPregDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public String Responder() {
        String pagina = null;
        try {

            if (StringUtils.isBlank(this.getSelectedPregunta().getVrespuesta())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese la respuesta.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                pagina = null;
            } else {
                PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
                this.getSelectedPregunta().setNsituacionid(BigDecimal.valueOf((long) 5));
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
                asignacion.setNusuarioid(serviceasig.getModeratorByCategoria(this.getSelectedPregunta().getNcategoriaid()));
                asignacion.setDfechacreacion(new Date());
                asignacion.setDfechaasignacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                pagina = "/index.xhtml";
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public String sendSi() {
        String pagina = null;
        try {

            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjespecialista())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                pagina = null;
            } else {
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
                asignacion.setNusuarioid(serviceasig.getUserCreacionByPregunta(this.getSelectedPregunta().getNpreguntaid()));
                asignacion.setDfechacreacion(new Date());
                asignacion.setDfechaasignacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                this.fSInfEspe = "true";

                pagina = "/index.xhtml";

            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toCancelSi(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('siDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String sendUsu() {
        String pagina = null;
        try {

            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjusuario2())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar");
                FacesContext.getCurrentInstance().addMessage(null, message);
                pagina = null;
            } else {
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
                asignacion.setNusuarioid(serviceasig.getEspecialistaByCategoria(this.getSelectedPregunta().getNcategoriaid()));
                asignacion.setDfechacreacion(new Date());
                asignacion.setDfechaasignacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                this.fMsjUsu2 = "true";
                
                pagina = "/index.xhtml";
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toCancelRespUsu(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respUsuPregDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String sendUsuMod() {
        String pagina = null;
        try {
            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjusuario1())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar");
                FacesContext.getCurrentInstance().addMessage(null, message);
                pagina = null;
            } else {
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
                asignacion.setNusuarioid(serviceasig.getModeratorByCategoria(this.getSelectedPregunta().getNcategoriaid()));
                asignacion.setDfechacreacion(new Date());
                asignacion.setDfechaasignacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                this.fMsjUsu1 = "true";
                pagina = "/index.xhtml";
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toCancelRespUsuMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respModPregDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toRespUsu(ActionEvent event) {
        try {

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toRespUsuMod(ActionEvent event) {
        try {

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public StreamedContent getPdf() throws IOException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String fileName = JSFUtils.getRequestParameter("fileName");
            FileInputStream fis = new FileInputStream(new File(fileName));
            return new DefaultStreamedContent(fis, "application/pdf");
        }
    }

    public void loadPickList(ActionEvent event) {
        try {
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            if (this.getSelectedBaseLegal() != null) {
                if (CollectionUtils.isEmpty(this.getListaSource())) {
                    this.setListaSource(service.getTbaselegalesNotLinkedById(this.getSelectedBaseLegal().getNbaselegalid()));
                }
                if (CollectionUtils.isEmpty(this.getListaTarget())) {
                    this.setListaTarget(service.getTbaselegalesLinkedById(this.getSelectedBaseLegal().getNbaselegalid()));
                }
            } else {
                if (CollectionUtils.isEmpty(this.getListaSource())) {
                    this.setListaSource(service.getTbaselegalesNotLinkedById(null));
                }
                if (CollectionUtils.isEmpty(this.getListaTarget())) {
                    this.setListaTarget(new ArrayList<BaseLegal>());
                }
            }
            this.setPickList(new DualListModel<BaseLegal>(this.getListaSource(), this.getListaTarget()));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void edit(ActionEvent event) {
        try {
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            if (this.getSelectedCategoria() != null) {
                this.getSelectedBaseLegal().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            }
            this.getSelectedBaseLegal().setVnombre(this.getSelectedBaseLegal().getVnombre().trim().toUpperCase());
            this.getSelectedBaseLegal().setVnumero(this.getSelectedBaseLegal().getVnumero().trim().toUpperCase());
            this.getSelectedBaseLegal().setNrangoid(this.getSelectedBaseLegal().getNrangoid());
            this.getSelectedBaseLegal().setNgobnacional(this.getChkGobNacional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgobregional(this.getChkGobRegional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgoblocal(this.getChkGobLocal() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNmancomunidades(this.getChkMancomunidades() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setVsumilla(this.getSelectedBaseLegal().getVsumilla().trim());
            this.getSelectedBaseLegal().setDfechapublicacion(this.getSelectedBaseLegal().getDfechapublicacion());
            this.getSelectedBaseLegal().setVtema(this.getSelectedBaseLegal().getVtema());
            this.getSelectedBaseLegal().setVusuariomodificacion(user.getVlogin());
            this.getSelectedBaseLegal().setDfechamodificacion(new Date());
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            service.saveOrUpdate(this.getSelectedBaseLegal());
            Tbaselegal tbaselegal = new Tbaselegal();
            BeanUtils.copyProperties(tbaselegal, this.getSelectedBaseLegal());

            ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
            if (this.getUploadFile() != null) {
                Archivo archivo = new Archivo();
                archivo.setNarchivoid(aservice.getNextPK());
                int version = this.getSelectedBaseLegal().getArchivo().getNversion().intValue();
                archivo.setNversion(BigDecimal.valueOf(version + 1));
                archivo.setTbaselegal(tbaselegal);
                archivo.setVnombre(this.getUploadFile().getFileName());
                archivo.setVruta(path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + archivo.getNversion().toString() + "\\" + archivo.getVnombre());
                archivo.setVusuariocreacion(user.getVlogin());
                archivo.setDfechacreacion(new Date());
                aservice.saveOrUpdate(archivo);
                saveFile(archivo);
            }

            VinculoBaseLegalService vservice = (VinculoBaseLegalService) ServiceFinder.findBean("VinculoBaseLegalService");
            vservice.deleteByBaseLegal(this.getSelectedBaseLegal());
            for (BaseLegal v : this.getListaTarget()) {
                TvinculoBaselegalId id = new TvinculoBaselegalId();
                id.setNbaselegalid(tbaselegal.getNbaselegalid());
                id.setNvinculoid(vservice.getNextPK());
                VinculoBaselegal vinculo = new VinculoBaselegal();
                vinculo.setId(id);
                vinculo.setTbaselegal(tbaselegal);
                vinculo.setNbaselegalvinculadaid(v.getNbaselegalid());
                vinculo.setNtipovinculo(v.getNestadoid());
                vinculo.setDfechacreacion(new Date());
                vinculo.setVusuariocreacion(user.getVlogin());
                vservice.saveOrUpdate(vinculo);

                BaseLegal bl = service.getBaselegalById(v.getNbaselegalid());
                bl.setNestadoid(v.getNestadoid());
                bl.setDfechamodificacion(new Date());
                bl.setVusuariomodificacion(user.getVlogin());
                service.saveOrUpdate(bl);
            }

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void PubBaseLegal(ActionEvent event) throws Exception {
        try {
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            if (this.getSelectedCategoria() != null) {
                this.getSelectedBaseLegal().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            }
            this.getSelectedBaseLegal().setVnombre(this.getSelectedBaseLegal().getVnombre().trim().toUpperCase());
            this.getSelectedBaseLegal().setVnumero(this.getSelectedBaseLegal().getVnumero().trim().toUpperCase());
            this.getSelectedBaseLegal().setNrangoid(this.getSelectedBaseLegal().getNrangoid());
            this.getSelectedBaseLegal().setNgobnacional(this.getChkGobNacional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgobregional(this.getChkGobRegional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgoblocal(this.getChkGobLocal() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNmancomunidades(this.getChkMancomunidades() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setVsumilla(this.getSelectedBaseLegal().getVsumilla().trim());
            this.getSelectedBaseLegal().setDfechapublicacion(this.getSelectedBaseLegal().getDfechapublicacion());
            this.getSelectedBaseLegal().setVtema(this.getSelectedBaseLegal().getVtema());
            this.getSelectedBaseLegal().setNestadoid(BigDecimal.valueOf(Long.valueOf(Constante.ESTADO_BASELEGAL_PUBLICADO)));
            this.getSelectedBaseLegal().setVusuariomodificacion(user.getVlogin());
            this.getSelectedBaseLegal().setDfechamodificacion(new Date());
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            service.saveOrUpdate(this.getSelectedBaseLegal());
            Tbaselegal tbaselegal = new Tbaselegal();
            BeanUtils.copyProperties(tbaselegal, this.getSelectedBaseLegal());

            ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
            if (this.getUploadFile() != null) {
                Archivo archivo = new Archivo();
                archivo.setNarchivoid(aservice.getNextPK());
                int version = this.getSelectedBaseLegal().getArchivo().getNversion().intValue();
                archivo.setNversion(BigDecimal.valueOf(version + 1));
                archivo.setTbaselegal(tbaselegal);
                archivo.setVnombre(this.getUploadFile().getFileName());
                archivo.setVruta(path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + archivo.getNversion().toString() + "\\" + archivo.getVnombre());
                archivo.setVusuariocreacion(user.getVlogin());
                archivo.setDfechacreacion(new Date());
                aservice.saveOrUpdate(archivo);
                saveFile(archivo);
            }

            VinculoBaseLegalService vservice = (VinculoBaseLegalService) ServiceFinder.findBean("VinculoBaseLegalService");
            vservice.deleteByBaseLegal(this.getSelectedBaseLegal());
            for (BaseLegal v : this.getListaTarget()) {
                TvinculoBaselegalId id = new TvinculoBaselegalId();
                id.setNbaselegalid(tbaselegal.getNbaselegalid());
                id.setNvinculoid(vservice.getNextPK());
                VinculoBaselegal vinculo = new VinculoBaselegal();
                vinculo.setId(id);
                vinculo.setTbaselegal(tbaselegal);
                vinculo.setNbaselegalvinculadaid(v.getNbaselegalid());
                vinculo.setNtipovinculo(v.getNestadoid());
                vinculo.setDfechacreacion(new Date());
                vinculo.setVusuariocreacion(user.getVlogin());
                vservice.saveOrUpdate(vinculo);

                BaseLegal bl = service.getBaselegalById(v.getNbaselegalid());
                bl.setNestadoid(v.getNestadoid());
                bl.setDfechamodificacion(new Date());
                bl.setVusuariomodificacion(user.getVlogin());
                service.saveOrUpdate(bl);
            }

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toSolInfoBase(ActionEvent event) {
        try {
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String sendSiModBase() {
        String pagina = null;
        try {

            if (StringUtils.isBlank(this.getSelectedBaseLegal().getVmsjmoderador())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                pagina = null;
            } else {
                BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
                this.getSelectedBaseLegal().setVmsjmoderador(this.getSelectedBaseLegal().getVmsjmoderador().toUpperCase());
                service.saveOrUpdate(this.getSelectedBaseLegal());

                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.BASELEGAL);
                asignacion.setNconocimientoid(this.getSelectedBaseLegal().getNbaselegalid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                asignacion.setNusuarioid(serviceasig.getUserCreacionByBaseLegal(this.getSelectedBaseLegal().getNbaselegalid()));
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);
                pagina = "/index.xhtml";
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toCancelSiModBase(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('siModBaseDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toRespSolInfoBase(ActionEvent event) {
        try {
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String sendSiRespBase() {
        String pagina = null;
        try {

            if (StringUtils.isBlank(this.getSelectedBaseLegal().getVmsjusuariocreacion())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                pagina = null;
            } else {
                BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
                this.getSelectedBaseLegal().setVmsjmoderador(this.getSelectedBaseLegal().getVmsjusuariocreacion().toUpperCase());
                service.saveOrUpdate(this.getSelectedBaseLegal());

                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.BASELEGAL);
                asignacion.setNconocimientoid(this.getSelectedBaseLegal().getNbaselegalid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                asignacion.setNusuarioid(serviceasig.getModeratorByCategoria(this.getSelectedBaseLegal().getNcategoriaid()));
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);
                pagina = "/index.xhtml";
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toCancelSiRespBase(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('siRespBaseDialog').show();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveFile(Archivo archivo) {
        try {
            if (this.getUploadFile() != null) {
                String id = archivo.getNarchivoid().toString();
                String version = archivo.getNversion().toString();
                String newPath = path + id + "\\" + version + "\\";
                File direc = new File(newPath);
                direc.mkdirs();
                this.setFile(new File(newPath, this.getUploadFile().getFileName()));
                FileOutputStream fileOutStream = new FileOutputStream(this.getFile());
                fileOutStream.write(this.getUploadFile().getContents());
                fileOutStream.flush();
                fileOutStream.close();
                File temp = new File(temppath, this.getUploadFile().getFileName());
                temp.delete();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void onTransfer(TransferEvent event) {
        int index;
        try {
            if (event != null) {
                if (event.isAdd()) {
                    Collections.sort(this.getListaSource(), BaseLegal.Comparators.ID);
                    for (BaseLegal ele : (List<BaseLegal>) event.getItems()) {
                        index = Collections.binarySearch(this.getListaSource(), ele, BaseLegal.Comparators.ID);
                        if (this.getListaTarget() == null) {
                            this.setListaTarget(new ArrayList<BaseLegal>());
                        }
                        this.getListaTarget().add(this.getListaSource().get(index));
//                        this.getListaTipoVinculo().add(BigDecimal.ZERO.toString());
                        this.getListaSource().remove(index);
                    }
                }
                if (event.isRemove()) {
                    Collections.sort(this.getListaTarget(), BaseLegal.Comparators.ID);
                    for (BaseLegal ele : (List<BaseLegal>) event.getItems()) {
                        index = Collections.binarySearch(this.getListaTarget(), ele, BaseLegal.Comparators.ID);
                        if (this.getListaSource() == null) {
                            this.setListaSource(new ArrayList<BaseLegal>());
                        }
                        this.getListaSource().add(this.getListaTarget().get(index));
                        this.getListaTarget().remove(index);
//                        this.getListaTipoVinculo().remove(index);
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public String redirect() {
        String page = null;
        try {
            page = (String) JSFUtils.getRequestParameter("page");
            JSFUtils.getSession().removeAttribute("administracionMB");
            JSFUtils.getSession().removeAttribute("baseLegalMB");
            JSFUtils.getSession().removeAttribute("categoriaMB");
            JSFUtils.getSession().removeAttribute("consultaMB");
            JSFUtils.getSession().removeAttribute("consultaMB");
            JSFUtils.getSession().removeAttribute("listaSessionMB");
            JSFUtils.getSession().removeAttribute("wikiMB");
            JSFUtils.getSession().removeAttribute("buenaPracticaMB");
            JSFUtils.getSession().removeAttribute("oportunidadMB");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return page.concat("?faces-redirect=true");
    }

    public void logout() {
        try {
            JSFUtils.getSession().invalidate();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
