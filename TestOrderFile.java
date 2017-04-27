/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Model.OrderFile;

/**
 *
 * @author L304
 */
public class TestOrderFile {
    public static void main(String[] args) {
        OrderFile file = new OrderFile("H:/PRUEBAS/","Archivo.txt" , "ArchivoOrdenado.txt");
        file.open();
        /*for (int i = 0; i < 127; i++) {
        System.out.println((char)i+" : " + i);
        }*/
    }
}
