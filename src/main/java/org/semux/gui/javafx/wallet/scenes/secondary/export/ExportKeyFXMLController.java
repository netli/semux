/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.secondary.export;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import org.controlsfx.control.Notifications;

/**
 * Private key export scene controller.
 */
public class ExportKeyFXMLController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<?> exportKeyTableView;
    @FXML
    private TableColumn<?, ?> numberExportKeyTableColumn;
    @FXML
    private TableColumn<?, ?> addressExportKeyTableColumn;
    @FXML
    private TableColumn<?, ?> nameExportKeyTableColumn;
    @FXML
    private TableColumn<?, ?> keyExportKeyTableColumn;
    @FXML
    private Button copyKeyButton;

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Copies selected private key on mouse click.
     */
    @FXML
    private void copy() {
        if (true) {
            Notifications.create()
                    .title("Copy private key.")
                    .text("Private key copied to clipboard.")
                    .position(Pos.CENTER)
                    .owner(anchorPane.getScene().getWindow())
                    .showInformation();
        } else {
            Notifications.create()
                    .title("Copy private key.")
                    .text("Failed to copy private key.")
                    .position(Pos.CENTER)
                    .owner(anchorPane.getScene().getWindow())
                    .showError();
        }
    }

    /**
     * Copies selected private key on enter key pressed.
     */
    @FXML
    private void copyOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            copy();
        }
    }

}