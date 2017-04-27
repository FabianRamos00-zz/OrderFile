/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author L304
 */
public class OrderFile {
    
    public static final String AUXFILE = "datos";
    public static final String EXT = ".tmp";
    private DataFile[] filesAux;
    private DataFile fileSource;
    private DataFile resultFile;
    
    public OrderFile(String pathName, String fileNameSource, String newFileName) {
        filesAux = new DataFile[2];
        for (int i = 0; i < filesAux.length; i++) {
            this.filesAux[i] = new DataFile(pathName + AUXFILE + i + EXT, OpenForm.WRITE);
        }
        
        this.fileSource = new DataFile(pathName + fileNameSource, OpenForm.READ);
        this.resultFile = new DataFile(pathName + newFileName, OpenForm.WRITE);
    }
    
    public void open(){
        fileSource.open();
        filesAux[0].open();
        filesAux[1].open();
        merge();
        fileSource.close();
    }
    
    public void merge(){
        int count = 0;
        //Copiar el Origen En el Resultado
        fileSource.copy(resultFile.getFile().getName());
        while (count != 1) {
            System.out.println(count);
            count = 0;
            for (int i = 0; i < filesAux.length; i++) {
            filesAux[i].setOpenForm(OpenForm.WRITE);
            filesAux[i].open();
            }
            resultFile.setOpenForm(OpenForm.READ);
            resultFile.open();
        String lineOne = "", lineTwo = "";
        int active = 0;
        lineOne = resultFile.read();
        filesAux[active].write(lineOne);
        while (lineOne != null) {
            lineTwo = resultFile.read();
            if (lineTwo != null) {
                if (lineOne.compareTo(lineTwo) > 0) {
                    active = (active ==0)?1:0;
                    count++;
                    System.out.println("Cambió");
                }
                filesAux[active].write(lineTwo);
            }
            lineOne = lineTwo;
        }
        for (int i = 0; i < filesAux.length; i++) {
            filesAux[i].close();
            filesAux[i].setOpenForm(OpenForm.READ);
            filesAux[i].open();
        }
        resultFile.setOpenForm(OpenForm.WRITE);
        resultFile.open();
        lineOne = filesAux[0].read();
        lineTwo = filesAux[1].read();
        while (lineTwo != null && lineOne != null) {            
            if (lineOne.compareTo(lineTwo) < 0) {
                resultFile.write(lineOne);
                lineOne = filesAux[0].read();
            }else{
                resultFile.write(lineTwo);
                lineTwo = filesAux[1].read();
            }
        }
        while (lineOne != null) {            
            resultFile.write(lineOne);
            lineOne = filesAux[0].read();
        }
        while (lineTwo != null) {            
            resultFile.write(lineTwo);
            lineTwo = filesAux[1].read();
        }
        for (int i = 0; i < filesAux.length; i++) {
            filesAux[i].close();
        }
        resultFile.close();
        }
    }
}