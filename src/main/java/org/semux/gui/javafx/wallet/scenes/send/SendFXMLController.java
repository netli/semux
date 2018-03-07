/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.send;

import java.awt.Robot;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.semux.gui.javafx.wallet.*;
import org.semux.gui.javafx.wallet.controls.navigation.NavigationButtonsActivator;

/**
 * Send scene controller. Allows user to create new transaction and send SEMs to
 * desired address.
 */
public class SendFXMLController implements Initializable, Options {

    private final Stage bookStage = new Stage();
    private final ToggleGroup radioButtons = new ToggleGroup();
    private TextFieldFormatter amountFormatter;
    private TextFieldFormatter feeFormatter;
    private double amount;
    private double fee;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField toAddressTextField;
    @FXML
    private ChoiceBox fromAddressChoiceBox;
    @FXML
    private Button addressBookButton;
    @FXML
    private TextField amountTextField;
    @FXML
    private TextField feeTextField;
    @FXML
    private TextField dataTextField;
    @FXML
    private RadioButton textRadioButton;
    @FXML
    private RadioButton hexRadioButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button sendButton;

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Navigation buttons initialization
        NavigationButtonsActivator activator = new NavigationButtonsActivator(anchorPane, "send");
        // Radio buttons grouped
        textRadioButton.setToggleGroup(radioButtons);
        hexRadioButton.setToggleGroup(radioButtons);
        textRadioButton.setSelected(true);
        bookStage.initOwner(ApplicationLoader.getStage());
        bookStage.setTitle("Address book");
        bookStage.setResizable(false);
        // Inputs validation
        ValidationSupport validation = new ValidationSupport();
        validation.registerValidator(toAddressTextField, Validator.createEmptyValidator("Address is Required."));
        validation.registerValidator(amountTextField, Validator.createEmptyValidator("Amount is Required."));
        validation.registerValidator(feeTextField, Validator.createEmptyValidator("Fee is Required."));
        // Send binding
        BooleanProperty amountSendCorrect = new SimpleBooleanProperty(false);
        amountTextField.textProperty().addListener((observable, initialValue, newValue) -> {
            amount = (double) amountFormatter.getConverter().fromString(amountTextField.getText());
            amountSendCorrect.set(amount <= DEFAULT_FEE);
        });
        BooleanProperty feeAmountCorrect = new SimpleBooleanProperty(false);
        feeTextField.textProperty().addListener((observable, initialValue, newValue) -> {
            fee = (double) feeFormatter.getConverter().fromString(feeTextField.getText());
            feeAmountCorrect.set(fee < DEFAULT_FEE);
        });
        BooleanBinding disableSend = toAddressTextField.textProperty().isEmpty()
                .or(amountSendCorrect)
                .or(feeAmountCorrect);
        sendButton.disableProperty().bind(disableSend);
        // Formatters
        // Amount formatter
        amountFormatter = new TextFieldFormatter(amountTextField);
        amountFormatter.initFormatter(0.0);
        // Fee formatter
        feeFormatter = new TextFieldFormatter(feeTextField);
        feeFormatter.initFormatter(DEFAULT_FEE);
    }

    /**
     * Opens new stage with address book scene on mouse click.
     */
    @FXML
    private void openAddressBook() throws Exception {
        bookStage.hide();
        String scenePath = RUN_PATH + MAIN_PACKAGE_NAME + "scenes/secondary/book/AddressBookFXML.fxml";
        try {
            Scene bookScene = new Scene(FXMLLoader.load(getClass()
                    .getResource(scenePath)), INIT_BOOK_WIDTH, INIT_BOOK_HEIGHT);
            StageBuilder builder = new StageBuilder(bookStage, bookScene);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Opens new stage with address book scene on enter key pressed.
     */
    @FXML
    private void openAddressBookOnEnterKeyPressed(KeyEvent key) throws Exception {
        if (key.getCode().equals(KeyCode.ENTER)) {
            openAddressBook();
        }
    }

    /**
     * Clears input fields on mouse click.
     */
    @FXML
    private void clear() {
        toAddressTextField.clear();
        amountTextField.clear();
        feeTextField.clear();
        dataTextField.clear();
        fromAddressChoiceBox.requestFocus();
    }

    /**
     * Clears input fields on enter key pressed.
     */
    @FXML
    private void clearOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            clear();
        }
    }

    /**
     * Attempts to create new transaction on mouse click.
     */
    @FXML
    private void send() {
        System.out.println(radioButtons.getSelectedToggle());
        if (true) {
            Notifications.create()
                    .title("Send transaction.")
                    .text("Transaction successfully sent.")
                    .position(Pos.CENTER)
                    .owner(anchorPane.getScene().getWindow())
                    .showInformation();
        } else {
            Notifications.create()
                    .title("Send transaction.")
                    .text("Failed to send transaction.")
                    .position(Pos.CENTER)
                    .owner(anchorPane.getScene().getWindow())
                    .showError();
        }
        fromAddressChoiceBox.requestFocus();
    }

    /**
     * Attempts to create new transaction on enter key pressed.
     */
    @FXML
    private void sendOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            send();
        }
    }

    /**
     * Focuses on next element.
     */
    @FXML
    private void fireTabOnEnter(KeyEvent key) {
        if (key.getCode() == KeyCode.ENTER) {
            try {
                Robot bot = new Robot();
                bot.keyPress(java.awt.event.KeyEvent.VK_TAB);
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }
    }

}