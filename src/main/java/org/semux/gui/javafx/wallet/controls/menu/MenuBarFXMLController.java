/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.controls.menu;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.semux.gui.javafx.wallet.*;

/**
 * Custom menu controller. Can be imported .jar packed along with .fxml file
 * into Scene Builder as custom control.
 */
public class MenuBarFXMLController extends HBox implements Options {

    private final Stage exportStage = new Stage();
    private final Stage accountsStage = new Stage();

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private Menu walletMenu;
    @FXML
    private MenuItem changePasswordMenuItem;
    @FXML
    private MenuItem recoverAccountsMenuItem;
    @FXML
    private MenuItem backupWalletMenuItem;
    @FXML
    private MenuItem importKeyMenuItem;
    @FXML
    private MenuItem exportKeyMenuItem;
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private MenuItem helpMenuItem;
    @FXML
    private Menu accountMenu;
    @FXML
    private MenuItem manageAccountsMenuItem;

    /**
     * Constructor loads .fxml file and allows menu to be imported with controller.
     */
    public MenuBarFXMLController() {
        try {
            FXMLLoader menuBarLoader = new FXMLLoader(getClass().getResource("MenuBarFXML.fxml"));
            menuBarLoader.setRoot(this);
            menuBarLoader.setController(this);
            menuBarLoader.load();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        // Secondary stages prepared
        exportStage.initOwner(ApplicationLoader.getStage());
        exportStage.initModality(Modality.APPLICATION_MODAL);
        exportStage.setTitle("Export private key");
        exportStage.setResizable(false);
        accountsStage.initOwner(ApplicationLoader.getStage());
        accountsStage.initModality(Modality.APPLICATION_MODAL);
        accountsStage.setTitle("Accounts manager");
        accountsStage.setResizable(false);
    }

    /**
     * Closes application.
     */
    @FXML
    private void exit() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Shows the change password dialog.
     */
    @FXML
    private void changePassword() {
        // Dialog creation
        final Dialog dialogChange = new Dialog();
        dialogChange.setTitle("Change password");
        dialogChange.setHeaderText("");
        DialogBuilder dialogBuilder = new DialogBuilder(dialogChange, INIT_CHANGE_PASSWORD_WIDTH);
        ButtonType acceptButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        dialogChange.getDialogPane().getButtonTypes().addAll(acceptButtonType, ButtonType.CANCEL);
        // Pane with controls
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(20, 10, 10, 10));
        final PasswordField oldPasswordField = new PasswordField();
        oldPasswordField.setPromptText("Old Password");
        oldPasswordField.setMinWidth(dialogChange.getDialogPane().getMinWidth() / 2);
        final PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("New Password");
        final PasswordField repeatPasswordField = new PasswordField();
        repeatPasswordField.setPromptText("Repeat Password");
        grid.add(new Label("Old Password"), 0, 0);
        grid.add(oldPasswordField, 1, 0);
        grid.add(new Label("New Password"), 0, 1);
        grid.add(newPasswordField, 1, 1);
        grid.add(new Label("Repeat Password"), 0, 2);
        grid.add(repeatPasswordField, 1, 2);
        // Accept button binding
        final Node acceptButton = dialogChange.getDialogPane().lookupButton(acceptButtonType);
        BooleanProperty passwordInputsEqual = new SimpleBooleanProperty(false);
        BooleanProperty newPasswordSizeCorrect = new SimpleBooleanProperty(false);
        newPasswordField.textProperty().addListener((observable, initialValue, newValue) -> {
            newPasswordSizeCorrect
                    .set((newValue.length() < MIN_PASSWORD_SIZE) || (newValue.length() > MAX_PASSWORD_SIZE));
            passwordInputsEqual.set(!newValue.equals(repeatPasswordField.getText()));
        });
        BooleanProperty repeatPasswordSizeCorrect = new SimpleBooleanProperty(false);
        repeatPasswordField.textProperty().addListener((observable, initialValue, newValue) -> {
            repeatPasswordSizeCorrect
                    .set((newValue.length() < MIN_PASSWORD_SIZE) || (newValue.length() > MAX_PASSWORD_SIZE));
            passwordInputsEqual.set(!newValue.equals(newPasswordField.getText()));
        });
        BooleanBinding disable = oldPasswordField.textProperty().isEmpty()
                .or(newPasswordField.textProperty().isEmpty()
                        .or(repeatPasswordField.textProperty().isEmpty())
                        .or(newPasswordSizeCorrect))
                .or(repeatPasswordSizeCorrect)
                .or(passwordInputsEqual);
        acceptButton.disableProperty().bind(disable);
        // Inputs validation
        ValidationSupport validationOld = new ValidationSupport();
        validationOld.registerValidator(oldPasswordField, Validator.createEmptyValidator("Password is Required."));
        ValidationSupport validationNew = new ValidationSupport();
        validationNew.registerValidator(newPasswordField, Validator.createEmptyValidator("New Password is Required."));
        ValidationSupport validationRepeat = new ValidationSupport();
        validationRepeat.registerValidator(repeatPasswordField, Validator.createEmptyValidator("Repeat New Password."));
        // Show the dialog window
        dialogChange.getDialogPane().setContent(grid);
        oldPasswordField.requestFocus();
        dialogChange.show();
        // On confirmation
        acceptButton.addEventFilter(ActionEvent.ACTION, event -> {
            if (true) {
                Notifications.create()
                        .title("Password change.")
                        .text("Password successfully changed. Use new password.")
                        .position(Pos.CENTER)
                        .owner(menuBar.getScene().getWindow())
                        .showWarning();
            } else {
                changePassword();
                Notifications.create()
                        .title("Password change.")
                        .text("Please enter correct password.")
                        .position(Pos.CENTER)
                        .showError();
            }
        });
    }

    /**
     * Recovers accounts from backup file.
     */
    @FXML
    private void recoverAccounts() {
        if (true) {
            Notifications.create()
                    .title("Account recovery.")
                    .text("Account successfully recovered.")
                    .position(Pos.CENTER)
                    .owner(menuBar.getScene().getWindow())
                    .showInformation();
        } else {
            Notifications.create()
                    .title("Account recovery.")
                    .text("Account recovery failed.")
                    .position(Pos.CENTER)
                    .showError();
        }
    }

    /**
     * Backup the wallet.
     */
    @FXML
    private void backupWallet() {
        if (true) {
            Notifications.create()
                    .title("Wallet backup.")
                    .text("Wallet backup successfully created.")
                    .position(Pos.CENTER)
                    .owner(menuBar.getScene().getWindow())
                    .showInformation();
        } else {
            Notifications.create()
                    .title("Wallet backup.")
                    .text("Wallet backup failed.")
                    .position(Pos.CENTER)
                    .showError();
        }
    }

    /**
     * Imports private key into this wallet.
     */
    @FXML
    private void importKey() {
        // Dialog creation
        final TextInputDialog dialogImportKey = new TextInputDialog("");
        dialogImportKey.setTitle("Import private key");
        dialogImportKey.setHeaderText("");
        dialogImportKey.setContentText("Private key");
        DialogBuilder builder = new DialogBuilder(dialogImportKey, INIT_IMPORT_KEY_WIDTH);
        final TextField textField = dialogImportKey.getEditor();
        textField.setMinWidth(dialogImportKey.getDialogPane().getMinWidth() - 20);
        // Inputs validation
        ValidationSupport validation = new ValidationSupport();
        validation.registerValidator(textField, Validator.createEmptyValidator("Key is Required."));
        // Accept button binding
        final Button acceptButton = (Button) dialogImportKey.getDialogPane().lookupButton(ButtonType.OK);
        BooleanBinding disable = textField.textProperty().isEmpty();
        acceptButton.disableProperty().bind(disable);
        // Show the dialog window
        dialogImportKey.show();
        // On confirmation
        acceptButton.addEventFilter(ActionEvent.ACTION, event -> {
            if (true) {
                Notifications.create()
                        .title("Private key import.")
                        .text("Private key successfully imported.")
                        .position(Pos.CENTER)
                        .owner(menuBar.getScene().getWindow())
                        .showInformation();
            } else {
                importKey();
                Notifications.create()
                        .title("Private key import.")
                        .text("Please enter correct key.")
                        .position(Pos.CENTER)
                        .showError();
            }
        });
    }

    /**
     * Shows the export private key scene.
     */
    @FXML
    private void exportKey() {
        exportStage.hide();
        String scenePath = RUN_PATH + MAIN_PACKAGE_NAME + "scenes/secondary/export/ExportKeyFXML.fxml";
        try {
            Scene exportScene = new Scene(FXMLLoader.load(getClass()
                    .getResource(scenePath)), INIT_EXPORT_WIDTH, INIT_EXPORT_HEIGHT);
            StageBuilder builder = new StageBuilder(exportStage, exportScene);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Opens account manager.
     */
    @FXML
    private void manageAccounts() {
        accountsStage.hide();
        String scenePath = RUN_PATH + MAIN_PACKAGE_NAME + "scenes/secondary/accounts/AccountsManagerFXML.fxml";
        try {
            Scene accountsScene = new Scene(FXMLLoader.load(getClass()
                    .getResource(scenePath)), INIT_ACCOUNTS_WIDTH, INIT_ACCOUNTS_HEIGHT);
            StageBuilder builder = new StageBuilder(accountsStage, accountsScene);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Shows the about dialog.
     */
    @FXML
    private void about() {
        Alert aboutDialog = new Alert(AlertType.INFORMATION);
        aboutDialog.setTitle("About");
        aboutDialog.setHeaderText("Semux Project 2017-2018");
        DialogBuilder builder = new DialogBuilder(aboutDialog, INIT_ABOUT_WIDTH);
        aboutDialog.showAndWait();
    }

    /**
     * Opens wiki.
     */
    @FXML
    private void help() {
        System.out.println("Help");
    }

}