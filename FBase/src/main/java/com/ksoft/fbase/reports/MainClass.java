package com.ksoft.fbase.reports;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
 
public class MainClass {

//http://javabeansinjasper.blogspot.in/

public static void main(String[] str)
{
    try
    {
    	
    	Student st = new Student("Kannan","10264012","Rajendran","Btech");
    	Student.addStudent(st);
    	st = new Student("Ajith","10264011","Mani","Btech");
    	Student.addStudent(st);
    	st = new Student("Kujan","1026400","Babu","Btech");
    	Student.addStudent(st);
    	
        JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
        JasperDesign jasperDesign = null;
        Map parameters = new HashMap();
        
        jasperDesign = JRXmlLoader.load("./reports/report1.jrxml");
        jasperReport = JasperCompileManager.compileReport(jasperDesign);
        //jasperReport = (JasperReport)JRLoader.loadObject("./reports/report1.jasper");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Student.getStudentList());
        jasperPrint  = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        //JasperExportManager.exportReportToPdfFile(jasperPrint,"./reports/StudentInfo.pdf");
        JasperExportManager.exportReportToHtmlFile(jasperPrint,"./reports/StudentInfo.html");
        JasperViewer.viewReport(jasperPrint);
    }
    catch(Exception ex)
    {
        System.out.println("EXCEPTION: "+ex);
    }
}
 
}