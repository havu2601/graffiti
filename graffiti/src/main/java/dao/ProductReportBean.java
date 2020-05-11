/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.ProductEJB;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author DELL
 */
@Named(value = "productReportBean")
@ViewScoped
public class ProductReportBean implements Serializable {

    @EJB private ProductEJB ejbProduct;
    
    List<Product> listProduct;
    JasperPrint jasperPrint;
    public void init() throws JRException{
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listProduct);
        jasperPrint = JasperFillManager.fillReport("C:\\Users\\DELL\\reportGra.jasper", new HashMap(), beanCollectionDataSource);
    }
    
    public void PDF() throws JRException, IOException{
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=reportGra.pdf");
        try(ServletOutputStream outStream = httpServletResponse.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
            outStream.flush();
        } 
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportarExcel() throws JRException, IOException{
        init();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition","attachment; filename=jsfReporte.xlsx");
    try (ServletOutputStream outStream = response.getOutputStream()) {
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
        exporter.exportReport();

        outStream.flush();
    }
        FacesContext.getCurrentInstance().responseComplete();			
    }
    
    public void exportarDOCX() throws JRException, IOException{
        init();
        
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition","attachment; filename=jsfReporte.docx");
        ServletOutputStream outStream = response.getOutputStream();

        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
        exporter.exportReport();

        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();			
    }
    
    public List<Product> getListProduct() {
        listProduct = ejbProduct.findAll();
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }
    
    
}
