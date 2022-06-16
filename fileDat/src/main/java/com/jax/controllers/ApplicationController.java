package com.jax.controllers;

import com.jax.domain.TempData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {

    @FXML
    private TextField ageInfo;

    @FXML
    private TextArea alertText;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnFind;

    @FXML
    private Button btnLoad;

    @FXML
    private Button btnSave;

    @FXML
    private TextField emailInfo;

    @FXML
    private TextField fileName;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField nameInfo;

    private int count = 0;

    private TempData tempData;

    @FXML
    void deleteFile(MouseEvent event) {
        mappingData();
    }

    @FXML
    void editFile(MouseEvent event) {
        mappingData();
    }

    @FXML
    void findFile(MouseEvent event) {
        mappingData();
    }

    @FXML
    void loadFile(MouseEvent event) {
        System.out.println(this.readDatFile(fileName.getText()+".dat").getName());
        mappingData();
    }

    @FXML
    void saveFile(MouseEvent event) {
        mappingData();
        this.writeDatFile();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileName.setPromptText("Untitled");
        alertText.setText("Hello Jax Tony");
    }

    private void mappingData() {
        tempData = new TempData();
        if (checkDataInput()) {
            tempData.setName(nameInfo.getText());
            tempData.setEmail(emailInfo.getText());
            tempData.setAge(Double.parseDouble(ageInfo.getText()));
        }
    }

    private boolean checkDataInput() {
        if (nameInfo.getText().isBlank() || emailInfo.getText().isBlank() || ageInfo.getText().isBlank()) {
            return false;
        }
        return true;
    }

    private void writeDatFile(){
        String fileSave = "";
        if (!fileName.getText().isBlank()) {
            fileSave += fileName.getText();
        } else {
            fileSave += "Untitled";
        }
        fileSave += ".dat";

        try {
            File outputFile = new File(fileSave);
            System.out.println(outputFile.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(outputFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this.tempData);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private TempData readDatFile(String filePath){
        try {
            File inputFile = new File(filePath);
            if(inputFile.isFile()) {
                try {
                    FileInputStream fis = new FileInputStream(inputFile);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    ObjectInputStream ois = new ObjectInputStream(bis);

                    tempData = (TempData) ois.readObject();
                    return tempData;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return tempData;
        }catch (Exception e) {
            e.printStackTrace();
            return new TempData();
        }
    }
}
