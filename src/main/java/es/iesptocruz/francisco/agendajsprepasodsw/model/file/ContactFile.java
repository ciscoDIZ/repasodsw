package es.iesptocruz.francisco.agendajsprepasodsw.model.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ContactFile {
    File contactFile;
    public void createFile(String fileName){
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        try{
            this.contactFile = new File(fileName);
            boolean isExist =  contactFile.exists();
            if(!isExist){
                contactFile.createNewFile();
            }else {
                fileWriter = new FileWriter(contactFile);
                bufferedWriter = new BufferedWriter(fileWriter);
            }
        }catch (IOException e){

        }
    }
    public void insert(){

    }
}
