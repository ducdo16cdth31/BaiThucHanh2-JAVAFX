package sample;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ObservableFloatValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import sample.model.Matrix;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller {
    @FXML TableView   firstMatrix;
    @FXML TableView   secondMatrix;
    @FXML TableView   resultMatrix;
    @FXML TextField   txtcol;
    @FXML TextField   txtrow;
    int col, row;
    Matrix firstMatrixData;
    Matrix secondMatrixData;
    Matrix resultMatrixData;
    //Khởi tạo các TableView với dữ liệu rỗng liên kết với mảng hai chiều
    public void InitTableView(float[][] data, int col, int row, TableView table) {
        ObservableList<float[]> obdata = FXCollections.observableArrayList();
        obdata.addAll(Arrays.asList(data));
    // obdata.remove(0);
        for (int i = 0; i < data[0].length; i++) {
    // Khởi tạo cell cho ma trận chứa các textfield
            final int colNo = i;
            TableColumn tc = new TableColumn();
            tc.setCellFactory(TextFieldTableCell.forTableColumn());
            tc.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<float[],
                                String>>() {
                public void handle(TableColumn.CellEditEvent<float[], String> t) {
                    ((float[])
                            t.getTableView().getItems().get(t.getTablePosition().getRow()))[colNo] = Float
                            .parseFloat(t.getNewValue());
                };
            });
            tc.setPrefWidth(30);
            table.getColumns().add(tc);
        }
// Load dữ liệu ra TableView
        table.setItems(obdata);
// Cho phép chỉnh sửa
        table.setEditable(true);
    }
    //Load dữ liệu từ mảng hai chiều vào TableView
    public void loadDataFromMatrix2TableView(float[][] data, int col, int row,
                                             TableView table) {
        ObservableList<float[]> obdata = FXCollections.observableArrayList();
        obdata.addAll(Arrays.asList(data));
// obdata.remove(0);
        for (int i = 0; i < data[0].length; i++) {
// Khởi tạo cell cho ma trận chứa các textfield
            final int colNo = i;
            TableColumn tc = new TableColumn();
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<float[],
                                String>, ObservableFloatValue>() {
                @Override
                public ObservableFloatValue call(TableColumn.CellDataFeatures<float[],
                                        String> p) {
                    return new SimpleFloatProperty(p.getValue()[colNo]);
                }
            });tc.setPrefWidth(30);
            table.getColumns().add(tc);
        }
// Load dữ liệu ra TableView
        table.setItems(obdata);
    }
    //Lấy dữ liệu dưới dạng mảng 2 chiều từ TableView
    public float[][] getDataFromTableView(TableView table, int col, int row) {
        float[][] cells = new float[row][col];
        ObservableList<float[]> obdata = table.getItems();
        int i = 0;
        int j = 0;
        for (float[] item : obdata) {
            for (float value : item) {
                cells[i][j] = value;
                j++;
            }
            j = 0;
            i++;
        }
        return cells;
    }
    public void hideTableHeader(TableView t){
        Pane header = (Pane)t.lookup("TableHeaderRow");
        header.setVisible(false);
        header.setMaxHeight(0);
        header.setMinHeight(0);
        header.setPrefHeight(0);
    }
    public void KhoiTaoClick(ActionEvent e) {
        try {
            col = Integer.parseInt(txtcol.getText());
            row = Integer.parseInt(txtrow.getText());
            firstMatrixData = new Matrix(row, col);
            secondMatrixData = new Matrix(row, col);
            resultMatrixData = new Matrix(row, col);
            InitTableView(firstMatrixData.getData(), col, row, firstMatrix);
            InitTableView(secondMatrixData.getData(), col, row,
                    secondMatrix);
            hideTableHeader(firstMatrix);
            hideTableHeader(secondMatrix);
        } catch (Exception err) {
        }
    }
    public void CongClick(ActionEvent e) {
        firstMatrixData.setData(getDataFromTableView(firstMatrix, col, row));
        secondMatrixData.setData(getDataFromTableView(secondMatrix, col, row));
        resultMatrixData = firstMatrixData.Add(secondMatrixData);loadDataFromMatrix2TableView(resultMatrixData.getData(), col, row,
                resultMatrix);
        hideTableHeader(resultMatrix);
    }
    public void XoaClick(ActionEvent event) {
        txtcol.setText("");
        txtrow.setText("");
        firstMatrixData.deleteMatrix();
        secondMatrixData.deleteMatrix();
        resultMatrixData.deleteMatrix();
        firstMatrix.getColumns().clear();
        secondMatrix.getColumns().clear();
        resultMatrix.getColumns().clear();
    }
}


