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
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.PreguntaService;
import pe.gob.mef.gescon.util.JSFUtils;

import pe.gob.mef.gescon.util.ServiceFinder;
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
    private Pregunta selectedPregunta;
    private String asunto;
    private String detalle;
    private BigDecimal entidadId;
    private String datoAdicional;
    private String respuesta;
    private String msjusuario;
    private String msjespecialista;
    private BigDecimal nsituacion;
    private String fSinf;
    private String fvSinf;
    private String fMusu;
    private String fvMusu;
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
     * @return the msjusuario
     */
    public String getMsjusuario() {
        return msjusuario;
    }

    /**
     * @param msjusuario the msjusuario to set
     */
    public void setMsjusuario(String msjusuario) {
        this.msjusuario = msjusuario;
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
     * @return the fSinf
     */
    public String getfSinf() {
        return fSinf;
    }

    /**
     * @param fSinf the fSinf to set
     */
    public void setfSinf(String fSinf) {
        this.fSinf = fSinf;
    }

    /**
     * @return the fvSinf
     */
    public String getFvSinf() {
        return fvSinf;
    }

    /**
     * @param fvSinf the fvSinf to set
     */
    public void setFvSinf(String fvSinf) {
        this.fvSinf = fvSinf;
    }

    /**
     * @return the fMusu
     */
    public String getfMusu() {
        return fMusu;
    }

    /**
     * @param fMusu the fMusu to set
     */
    public void setfMusu(String fMusu) {
        this.fMusu = fMusu;
    }

    /**
     * @return the fvMusu
     */
    public String getFvMusu() {
        return fvMusu;
    }

    /**
     * @param fvMusu the fvMusu to set
     */
    public void setFvMusu(String fvMusu) {
        this.fvMusu = fvMusu;
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
                this.setSelectedCategoria((Categoria) event.getTreeNode().getData());                this.selectedPregunta.setNcategoriaid(this.selectedCategoria.getNcategoriaid());
                
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
            this.entidad="MEF";
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
            Pregunta pregunta = new Pregunta();
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            pregunta.setNpreguntaid(service.getNextPK());
            pregunta.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            pregunta.setVasunto(this.getAsunto().trim());
            pregunta.setVdetalle(this.getDetalle().trim());
            pregunta.setNentidadid(this.getEntidadId());
            pregunta.setVdatoadicional(this.getDatoAdicional().trim());
            pregunta.setNactivo(BigDecimal.ONE);
            pregunta.setDfechacreacion(new Date());
            pregunta.setNsituacion(BigDecimal.valueOf(Long.parseLong("2")));
            service.saveOrUpdate(pregunta);
            this.setListaPregunta(service.getPreguntas());
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
//                if(this.getSelectedMaestro() == null) {
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Seleccione el maestro que desea editar.");
//                    FacesContext.getCurrentInstance().addMessage(null, message);
//                }
                int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
                this.setSelectedPregunta(this.getListaPregunta().get(index));

                this.cat1 = this.getSelectedPregunta().getNcategoriaid();
                
                if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjespecialista())) {
                    this.setfSinf("false");
                } else {
                    this.setfSinf("true");
                }

                if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjespecialista())) {
                    this.fvSinf = "false";

                } else {
                    this.fvSinf = "true";
                }

                if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjusuario())) {
                    this.setfMusu("false");
                } else {
                    this.setfMusu("true");
                }

                if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjusuario())) {
                    this.fvMusu = "false";

                } else {
                    this.fvMusu = "true";
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

    public void toCancelRespUsu(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respusuDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toSi(ActionEvent event) {
        try {
            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjespecialista())) {
                this.setfSinf("false");
            } else {
                this.setfSinf("true");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toResp(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('seeDialog').hide();");
            this.cleanAttributes();
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
            
            if(Integer.parseInt(cat1.toString()) != Integer.parseInt(cat2.toString())){
            this.getSelectedPregunta().setNsituacion(BigDecimal.valueOf(Long.parseLong("3")));
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
            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjusuario())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre de perfil.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setVmsjusuario(this.getSelectedPregunta().getVmsjusuario().toUpperCase());
            service.saveOrUpdate(this.getSelectedPregunta());
            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('respusuDialog').hide();");

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
            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('siDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void publish(ActionEvent event) throws Exception {
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
            this.getSelectedPregunta().setNsituacion(BigDecimal.valueOf(Long.parseLong("4")));
            service.saveOrUpdate(this.getSelectedPregunta());
            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('respDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

}
