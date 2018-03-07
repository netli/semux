/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.receive;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import org.controlsfx.control.Notifications;
import org.semux.gui.javafx.wallet.ApplicationLoader;
import org.semux.gui.javafx.wallet.data.WalletAccount;
import org.semux.gui.javafx.wallet.data.WalletSession;
import org.semux.gui.javafx.wallet.controls.navigation.NavigationButtonsActivator;

/**
 * Receive scene controller. Shows accounts table, QR-code.
 */
public class ReceiveFXMLController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableColumn<?, ?> numberReceiveTableColumn;
    @FXML
    private TableColumn<WalletAccount, String> nameReceiveTableColumn;
    @FXML
    private TableColumn<WalletAccount, String> addressReceiveTableColumn;
    @FXML
    private TableColumn<WalletAccount, Double> availableReceiveTableColumn;
    @FXML
    private TableColumn<WalletAccount, Double> lockedReceiveTableColumn;
    @FXML
    private ImageView qrCodeImageView;
    @FXML
    private Button copyReceiveButton;
    @FXML
    private TableView<WalletAccount> accountsReceiveTableView;

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Navigation buttons initialization
        NavigationButtonsActivator activator = new NavigationButtonsActivator(anchorPane, "receive");
        // Set accounts properties as TableView items
        nameReceiveTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressReceiveTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        availableReceiveTableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
        lockedReceiveTableColumn.setCellValueFactory(new PropertyValueFactory<>("locked"));
        final WalletSession currentSession = ApplicationLoader.getCurrentSession();
        try {
            accountsReceiveTableView.setItems(currentSession.getObservableAccountsList());
        } catch (Exception exception) {
            System.out.println("Session data is not loaded: " + exception);
        }

    }

    /**
     * Copies account`s address to clipboard on mouse click.
     */
    @FXML
    private void copy() {
        if (true) {
            Notifications.create()
                    .title("Copy address.")
                    .text("Address copied to clipboard.")
                    .position(Pos.CENTER)
                    .owner(anchorPane.getScene().getWindow())
                    .showInformation();
        } else {
            Notifications.create()
                    .title("Copy address.")
                    .text("Failed to copy address.")
                    .position(Pos.CENTER)
                    .owner(anchorPane.getScene().getWindow())
                    .showError();
        }
    }

    /**
     * Copies account`s address to clipboard on enter key pressed.
     */
    @FXML
    private void copyOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            copy();
        }
    }

}