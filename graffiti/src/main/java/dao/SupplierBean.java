/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import ejb.ImportEJB;
import ejb.SupplierEJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.Id;
import model.Supplier;

/**
 *
 * @author Administrator
 */
@Named(value = "supplierBean")
@ViewScoped
public class SupplierBean implements Serializable {

    @EJB
    SupplierEJB supplierEJB;
    
    @EJB
    private ImportEJB importEJB;
    List<Supplier> listSupplier;
    Supplier newSupplier;
    private String supplierId;
    private String supplierName;
    private String supplierEmail;
    private String supplierContact;
    private String supplierAddress;
    private Supplier selectedSupplier;
  

    @PostConstruct
    public void init() {
        newSupplier = new Supplier();
        listSupplier = supplierEJB.findAll();//Show table
       
    }

    public void show(List<Supplier> rs, String msg) {
        listSupplier = new ArrayList<>();
        if (rs.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } else {
            listSupplier.addAll(rs);
        }
    }

    public String addNewSupplier() {
        supplierEJB.addSupplier(newSupplier);
        return "supplier.xhtml?faces-redirect=true";
    }

    public void load() {
        newSupplier = supplierEJB.findById(Integer.parseInt(supplierId));
    }
    

    public String update() {
        supplierEJB.edit(newSupplier);
        return "supplier.xhtml?faces-redirect=true";
    }
    
   



    public List<Supplier> getListSupplier() {
        return listSupplier;
    }

    public void setListSupplier(List<Supplier> listSupplier) {
        this.listSupplier = listSupplier;
    }

    public Supplier getNewSupplier() {
        return newSupplier;
    }

    public void setNewSupplier(Supplier newSupplier) {
        this.newSupplier = newSupplier;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }
      public Supplier getSelectedSupplier() {
        return selectedSupplier;
    }

    public void setSelectedSupplier(Supplier selectedSupplier) {
        this.selectedSupplier = selectedSupplier;
    }
   

}
