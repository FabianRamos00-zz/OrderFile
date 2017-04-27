/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Model.*;
import Model.OpenForm;
import java.util.Collection;

/**
 * ARCHIVO DE TEXTO DE NUMERO DE LINEAS DESCONOCIDO
 * QUEREMOS CREAR OTRO ARCHIVO TEXTO QUE CONTENGA 
 * TODAS LAS LINEAS DEL ARCHIVO LEIDO Y ORDENARLO ALFABETICAMENTE
 * @author RA302
 */
public class TestFilePractics {
    public static void main(String[] args) {
        DataFile dataFile = new DataFile("F:/Programación 2/Order/PRUEBAS/Archivo.txt", OpenForm.WRITE);
        dataFile.open();
        for (int i = 0; i < 1000; i++) {
           dataFile.write("Linea " + i);
        }
        dataFile.close();
        
        dataFile = new DataFile("G:/PRUEBAS/Archivo.txt", OpenForm.READ);
        dataFile.open();
        String line = ".";
        while (line != null) {
           line = dataFile.read();
           System.out.println(line);
        }
        dataFile.close();
        
        dataFile.open();
        System.out.println(dataFile.order("G:/PROGRAMACION II/ArchivoOrdenado.txt"));
        dataFile.close();
    }
}
