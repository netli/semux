/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.secondary.book;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.semux.gui.javafx.wallet.DialogBuilder;
import org.semux.gui.javafx.wallet.Options;

/**
 * User address book controller.
 */
public class AddressBookFXMLController implements Initializable, Options {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<?> addressBookTableView;
    @FXML
    private TableColumn<?, ?> numberAddressBookTableColumn;
    @FXML
    private TableColumn<?, ?> nameAddressBookTableColumn;
    @FXML
    private TableColumn<?, ?> addressAddressBookTableColumn;
    @FXML
    private Button addAddressButton;
    @FXML
    private Button copyAddressButton;
    @FXML
    private Button deleteAddressButton;

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
     * Adds new address to address book on mouse click.
     */
    @FXML
    private void add() {
        // Dialog creation
        final Dialog dialogAdd = new Dialog();
        dialogAdd.setTitle("Add new address");
        dialogAdd.setHeaderText("");
        DialogBuilder dialogBuilder = new DialogBuilder(dialogAdd, INIT_ADD_ADDRESS_WIDTH);
        ButtonType acceptButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialogAdd.getDialogPane().getButtonTypes().addAll(acceptButtonType, ButtonType.CANCEL);
        // Pane with controls
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(20, 10, 10, 10));
        final TextField nameTextField = new TextField();
        nameTextField.setPromptText("Name");
        nameTextField.setMaxWidth(dialogAdd.getDialogPane().getMinWidth() / 2);
        final TextField addressTextField = new TextField();
        addressTextField.setPromptText("Address");
        addressTextField.setMinWidth(dialogAdd.getDialogPane().getMinWidth() - 20);
        grid.add(new Label("Name"), 0, 0);
        grid.add(nameTextField, 1, 0);
        grid.add(new Label("Address"), 0, 1);
        grid.add(addressTextField, 1, 1);
        // Accept button binding
        final Node acceptButton = dialogAdd.getDialogPane().lookupButton(acceptButtonType);
        BooleanBinding disable = nameTextField.textProperty().isEmpty()
                .or(addressTextField.textProperty().isEmpty());
        acceptButton.disableProperty().bind(disable);
        // Inputs validation
        ValidationSupport validationName = new ValidationSupport();
        validationName.registerValidator(nameTextField, Validator.createEmptyValidator("Name is Required."));
        ValidationSupport validationAddress = new ValidationSupport();
        validationAddress.registerValidator(addressTextField, Validator.createEmptyValidator("Address is Required."));
        // Show the dialog window
        dialogAdd.getDialogPane().setContent(grid);
        nameTextField.requestFocus();
        dialogAdd.show();
        // On confirmation
        acceptButton.addEventFilter(ActionEvent.ACTION, event -> {
            if (true) {
                Notifications.create()
                        .title("Add address.")
                        .text("Address successfully added.")
                        .position(Pos.CENTER)
                        .owner(anchorPane.getScene().getWindow())
                        .showInformation();
            } else {
                add();
                Notifications.create()
                        .title("Add address.")
                        .text("Please enter correct address.")
                        .position(Pos.CENTER)
                        .showError();
            }
        });
    }

    /**
     * Adds new address to address book on enter key pressed.
     */
    @FXML
    private void addOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            add();
        }
    }

    /**
     * Copies address to clipboard on mouse click.
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
     * Copies address to clipboard on enter key pressed.
     */
    @FXML
    private void copyOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            copy();
        }
    }

    /**
     * Deletes address from address book on mouse click.
     */
    @FXML
    private void delete() {
        if (true) {
            Notifications.create()
                    .title("Delete address.")
                    .text("Address successfully deleted.")
                    .position(Pos.CENTER)
                    .owner(anchorPane.getScene().getWindow())
                    .showInformation();
        } else {
            Notifications.create()
                    .title("Delete address.")
                    .text("Failed to delete address.")
                    .position(Pos.CENTER)
                    .owner(anchorPane.getScene().getWindow())
                    .showError();
        }
    }

    /**
     * Deletes address from address book on enter key pressed.
     */
    @FXML
    private void deleteOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            delete();
        }
    }

}