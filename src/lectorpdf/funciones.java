/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorpdf;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author omene
 */
public class funciones {

    public File[] obtenArchivos(){
    
        File[] files;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos pdf","pdf");
        try{
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setAcceptAllFileFilterUsed(false);
            fc.setFileFilter(filter);
            fc.setMultiSelectionEnabled(true);
            fc.setDialogTitle("Selecciona los archivos a procesar");
            int respuesta;
            respuesta = fc.showOpenDialog(fc);
            if (respuesta == JFileChooser.APPROVE_OPTION){
                files = fc.getSelectedFiles();
                return files;
            }else{
                return null;
            }
        }catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Se ha producido un error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
    }
    
    public String asignaSave(){
        String archivo;
        int respuesta;
        try{
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setDialogTitle("Captura archivo de salida");
            respuesta = fc.showSaveDialog(fc);
            if(respuesta == JFileChooser.APPROVE_OPTION){
                archivo =fc.getSelectedFile().getAbsolutePath() + ".csv";
                return archivo;
            } else {
                return null;
            }
        }catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Se ha producido un error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public void creaArchivo(String ruta) throws IOException{
        
        FileWriter archivo = null;
        try{
            archivo = new FileWriter(ruta);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Se ha producido un error", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(null != archivo){
                archivo.close();
            }
        }
    }
    
    public void escribeArchivo(String ruta, String[][] valores, String registroFile, boolean titulos){
        
        File archivo;
        FileWriter escribe;
        int i;
        String datatitulos;
        String datavalores;
        try{
            datatitulos = "";
            archivo = new File(ruta);
            escribe = new FileWriter(ruta, true);
            if(titulos == true){
                for(i = 0; i<valores[0].length;i++){
                    datatitulos = datatitulos + valores[0][i] + ",";
                }
                escribe.write(datatitulos + "Nombre archivo" + "\r\n");
            }
            datavalores = "";
            for(i=0;i < valores[1].length;i++){
                datavalores = datavalores + valores[1][i] + ",";
            }
            escribe.write(datavalores + registroFile +"\r\n");
            escribe.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Se ha producido un error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
