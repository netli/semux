/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.home;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import org.semux.gui.javafx.wallet.ApplicationLoader;
import org.semux.gui.javafx.wallet.data.WalletAccount;
import org.semux.gui.javafx.wallet.data.WalletSession;
import org.semux.gui.javafx.wallet.controls.navigation.NavigationButtonsActivator;

/**
 * Home scene controller. Outputs account`s state and transactions data.
 */
public class HomeFXMLController implements Initializable {

    private WalletAccount currentAccount;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField accountNameTextField;
    @FXML
    private TextField blockNumberTextField;
    @FXML
    private TextField blockTimeTextField;
    @FXML
    private TextField coinbaseTextField;
    @FXML
    private TextField statusTextField;
    @FXML
    private TextField availableTextField;
    @FXML
    private TextField lockedTextField;
    @FXML
    private TextField totalBalanceTextField;
    @FXML
    private ListView<?> transactionsListView;

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Navigation buttons initialization
        NavigationButtonsActivator activator = new NavigationButtonsActivator(anchorPane, "home");
        try {
            // Binding properties
            final WalletSession currentSession = ApplicationLoader.getCurrentSession();
            currentAccount = currentSession.getCurrentAccount();
            initBindings();
            // Check for account change and rebind if occurred
            currentSession.getAccountChangeOccurred().addListener((observable, initialValue, newValue) -> {
                currentAccount = currentSession.getCurrentAccount();
                initBindings();
            });
        } catch (Exception exception) {
            System.out.println("Session data is not loaded: " + exception);
        }
    }

    /**
     * Binds current account properties to TextFields.
     */
    private void initBindings() {
        accountNameTextField.textProperty().bind(currentAccount.nameProperty());
        blockNumberTextField.textProperty().bind(currentAccount.blockNumberProperty().asString());
        blockTimeTextField.textProperty().bind(currentAccount.blockTimeProperty());
        coinbaseTextField.textProperty().bind(currentAccount.coinbaseProperty());
        statusTextField.textProperty().bind(currentAccount.statusProperty());
        availableTextField.textProperty().bind(currentAccount.availableProperty().asString().concat("   SEM"));
        lockedTextField.textProperty().bind(currentAccount.lockedProperty().asString().concat("   SEM"));
        totalBalanceTextField.textProperty().bind(currentAccount.totalBalanceProperty().asString().concat("   SEM"));
    }

}