/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.ProductEJB;
import ejb.SubcategoryEJB;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import model.SubCategory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
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
        jasperPrint = JasperFillManager.fillReport("/Users/havu2601/Documents/graffiti/graffiti/src/main/webapp/WEB-INF/report1.jasper", new HashMap(), beanCollectionDataSource);

    }
    
    public void PDF() throws JRException, IOException{
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=reportProduct.pdf");
        try(ServletOutputStream outStream = httpServletResponse.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
            outStream.flush();
        } 
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportarExcel() throws JRException, IOException{
        init();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition","attachment; filename=reportProduct.xlsx");
    try (ServletOutputStream outStream = response.getOutputStream()) {
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
        exporter.exportReport();

        outStream.flush();
    }
        FacesContext.getCurrentInstance().responseComplete();			
    }
    
    public void exportarDOCX() throws JRException, IOException{
        init();
        
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition","attachment; filename=reportProduct.docx");
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
