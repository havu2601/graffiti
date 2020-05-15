/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import ejb.ImportEJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Import;
import model.Supplier;
import model.UserAccount;
import dao.UserBean;
import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 *
 * @author Administrator
 */
@Named(value = "importBean")
@ViewScoped
public class ImportBean implements Serializable {

    @EJB
    private ImportEJB importEJB;
    List<Import> listImport;
    Import newImport;

    private String importId;
    private Date importDate;
    private String importStatus;
    private String supplierId;
    private String userId;
    Supplier newSupplier;
    
    String searchStr;

    UserAccount newUserAccount;
//    HttpServletRequest req = (HttpServletRequest) request;
//        UserBean session =(UserBean) req.getSession(true).getAttribute("userSession");
    FacesContext req = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) req.getExternalContext().getSession(false);
    String sessionId = session.getId();

    @PostConstruct
    public void init() {
        newImport = new Import();
        newSupplier = new Supplier();
        newUserAccount = new UserAccount();
        listImport = importEJB.findAll();
        FacesContext req = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) req.getExternalContext().getSession(false);
        UserBean userSession = (UserBean) session.getAttribute("userSession");
        userId = userSession.getAcc().getUserId().toString();
        importDate = Date.from(Instant.now());
    }
    
 
    
    
    
    
    

    public void show(List<Import> rs, String msg) {
        listImport = new ArrayList<>();
        if (rs.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } else {
            listImport.addAll(rs);
        }
    }

    public String addNewImport() {

        if (null == newImport.getImportId()) {
            updateImport();
            newImport.setImportDate(importDate);
            newImport.setSupplierId(newSupplier);
            newImport.setUserId(newUserAccount);
            importEJB.AddNew(newImport);
        } else {
            updateImport();
            newImport.setImportDate(importDate);
            newImport.setSupplierId(newSupplier);
            newImport.setUserId(newUserAccount);
            importEJB.updateImport(newImport);
        }
        importEJB.AddNew(newImport);

        return "import.xhtml?faces-redirect=true";
    }

    private void updateImport() {
        newSupplier = new Supplier();
        newSupplier.setSupplierId(Integer.parseInt(supplierId));
        newUserAccount = new UserAccount();
        newUserAccount.setUserId(Integer.parseInt(userId));
    }

    public void reset() {
        newImport = new Import();
    }

    public String remove(int id) {
        try {
            importEJB.delete(importEJB.findById(id));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cannot remove!", "Cannot remove!"));
        }
        return "import.xhtml?faces-redirect=true";
    }

    public void loadSupplier(int id) {
        newImport = importEJB.findById(id);
        supplierId = newImport.getSupplierId().getSupplierId().toString();
    }

    public ImportBean() {
    }

    public ImportEJB getImportEJB() {
        return importEJB;
    }

    public void setImportEJB(ImportEJB importEJB) {
        this.importEJB = importEJB;
    }

    public List<Import> getListImport() {
        return listImport;
    }

    public void setListImport(List<Import> listImport) {
        this.listImport = listImport;
    }

    public Import getNewImport() {
        return newImport;
    }

    public void setNewImport(Import newImport) {
        this.newImport = newImport;
    }

    public String getImportId() {
        return importId;
    }

    public void setImportId(String importId) {
        this.importId = importId;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public String getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

   

}
