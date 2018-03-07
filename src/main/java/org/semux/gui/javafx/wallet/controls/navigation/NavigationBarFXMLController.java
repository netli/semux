/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.controls.navigation;

import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.semux.gui.javafx.wallet.Options;
import org.semux.gui.javafx.wallet.ApplicationLoader;
import org.semux.gui.javafx.wallet.WindowSizeStateWatcher;
import org.semux.gui.javafx.wallet.DialogBuilder;
import org.semux.gui.javafx.wallet.StageBuilder;
import org.semux.gui.javafx.wallet.StylesLoader;

/**
 * Custom navigation buttons bar controller. Can be imported .jar packed along
 * with .fxml file into Scene Builder as custom control.
 */
public class NavigationBarFXMLController extends HBox implements Options {

    private Stage currentStage;
    private Stage currentLockedStage;
    private Scene currentSceneHome;
    private Scene currentSceneSend;
    private Scene currentSceneReceive;
    private Scene currentSceneTransactions;
    private Scene currentSceneDelegates;
    private boolean isLocked = false;

    @FXML
    private Button homeNavigationButton;
    @FXML
    private Button sendNavigationButton;
    @FXML
    private Button receiveNavigationButton;
    @FXML
    private Button transactionsNavigationButton;
    @FXML
    private Button delegatesNavigationButton;
    @FXML
    private Button lockNavigationButton;
    @FXML
    private GridPane gridPane;

    /**
     * Constructor loads .fxml file and allows navigation bar to be imported with
     * controller.
     */
    public NavigationBarFXMLController() {
        try {
            FXMLLoader navigationBarLoader = new FXMLLoader(getClass().getResource("NavigationBarFXML.fxml"));
            navigationBarLoader.setRoot(this);
            navigationBarLoader.setController(this);
            navigationBarLoader.load();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        // Has to be set after initialization of controllers
        Platform.runLater(() -> {
            setDefaultScenes();
        });
    }

    /**
     * Handles mouse click on any place of stage event, calls unlock method.
     */
    private class LockEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            unlock(evt);
        }
    }

    private void setDefaultScenes() {
        currentSceneHome = ApplicationLoader.getSceneHome();
        currentSceneSend = ApplicationLoader.getSceneSend();
        currentSceneReceive = ApplicationLoader.getSceneReceive();
        currentSceneTransactions = ApplicationLoader.getSceneTransactions();
        currentSceneDelegates = ApplicationLoader.getSceneDelegates();
    }

    /**
     * Checks stage before scene change.
     *
     * @return checked current stage
     */
    private Stage ckeckStage() {
        homeNavigationButton.requestFocus();
        currentStage = (Stage) gridPane.getScene().getWindow();
        boolean isMaximized = currentStage.isMaximized();
        double stageWidth = currentStage.getWidth();
        double stageHeight = currentStage.getHeight();
        WindowSizeStateWatcher watcher = new WindowSizeStateWatcher(currentStage, isMaximized, stageWidth, stageHeight);
        return currentStage;
    }

    /**
     * Shows unlock dialog and unlocks wallet if password is correct.
     */
    private void unlock(Event evt) {
        if (isLocked == true) {
            // Dialog creation
            final Dialog dialogUnlock = new Dialog();
            dialogUnlock.setTitle("Unlock the Wallet");
            dialogUnlock.setHeaderText("");
            dialogUnlock.setContentText("Please enter the password");
            dialogUnlock.initModality(Modality.APPLICATION_MODAL);
            DialogBuilder dialogBuilder = new DialogBuilder(dialogUnlock, INIT_UNLOCK_WIDTH);
            ButtonType acceptButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialogUnlock.getDialogPane().getButtonTypes().addAll(acceptButtonType, ButtonType.CANCEL);
            // Pane with controls
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(15);
            grid.setPadding(new Insets(50, 10, 10, 10));
            final PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");
            passwordField.setMinWidth(dialogUnlock.getDialogPane().getMinWidth() - 20);
            grid.add(new Label("Password"), 0, 0);
            grid.add(passwordField, 1, 0);
            // Accept button binding
            final Node acceptButton = dialogUnlock.getDialogPane().lookupButton(acceptButtonType);
            BooleanBinding disable = passwordField.textProperty().isEmpty();
            acceptButton.disableProperty().bind(disable);
            // Inputs validation
            ValidationSupport validation = new ValidationSupport();
            validation.registerValidator(passwordField, Validator.createEmptyValidator("Password is Required."));
            // Show the dialog window
            dialogUnlock.getDialogPane().setContent(grid);
            passwordField.requestFocus();
            dialogUnlock.show();
            // On confirmation
            acceptButton.addEventFilter(ActionEvent.ACTION, event -> {
                if (true) {
                    Scene scene = gridPane.getParent().getScene();
                    scene.getRoot().setDisable(false);
                    StageBuilder stageBuilder = new StageBuilder(currentStage, scene);
                    WindowSizeStateWatcher watcher = new WindowSizeStateWatcher(currentStage, currentLockedStage);
                    currentLockedStage.hide();
                    isLocked = false;
                    lockNavigationButton.getStyleClass().remove("navigation-button-active");
                } else {
                    unlock(evt);
                    Notifications.create()
                            .title("Wrong password.")
                            .text("Please enter correct password.")
                            .position(Pos.CENTER)
                            .showWarning();
                }
            });
        }
    }

    @FXML
    private void loadHomeScene() {
        StylesLoader stylesLoader = new StylesLoader(currentSceneHome);
        ckeckStage().setScene(currentSceneHome);
    }

    @FXML
    private void loadHomeSceneOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            loadHomeScene();
        }
    }

    @FXML
    private void loadSendScene() {
        StylesLoader stylesLoader = new StylesLoader(currentSceneSend);
        ckeckStage().setScene(currentSceneSend);
    }

    @FXML
    private void loadSendSceneOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            loadSendScene();
        }
    }

    @FXML
    private void loadReceiveScene() {
        StylesLoader stylesLoader = new StylesLoader(currentSceneReceive);
        ckeckStage().setScene(currentSceneReceive);
    }

    @FXML
    private void loadReceiveSceneOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            loadReceiveScene();
        }
    }

    @FXML
    private void loadTransactionsScene() {
        StylesLoader stylesLoader = new StylesLoader(currentSceneTransactions);
        ckeckStage().setScene(currentSceneTransactions);
    }

    @FXML
    private void loadTransactionsSceneOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            loadTransactionsScene();
        }
    }

    @FXML
    private void loadDelegatesScene() {
        StylesLoader stylesLoader = new StylesLoader(currentSceneDelegates);
        ckeckStage().setScene(currentSceneDelegates);
    }

    @FXML
    private void loadDelegatesSceneOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            loadDelegatesScene();
        }
    }

    /**
     * Invokes locked wallet stage on mouse click.
     */
    @FXML
    private void lockScreen() {
        if (isLocked == false) {
            currentLockedStage = new Stage();
            currentLockedStage.addEventFilter(MouseEvent.MOUSE_CLICKED, new LockEventHandler());
            lockNavigationButton.getStyleClass().add("navigation-button-active");
            currentStage = (Stage) gridPane.getScene().getWindow();
            Scene lockedScene = gridPane.getParent().getScene();
            currentLockedStage.initStyle(StageStyle.TRANSPARENT);
            lockedScene.setFill(Color.TRANSPARENT);
            lockedScene.getRoot().setDisable(true);
            currentLockedStage.setTitle("Semux Wallet - Locked");
            StageBuilder builder = new StageBuilder(currentLockedStage, lockedScene);
            WindowSizeStateWatcher watcher = new WindowSizeStateWatcher(currentLockedStage, currentStage);
            currentStage.setMaximized(false);
            currentStage.hide();
            isLocked = true;
        }
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public Stage getCurrentLockedStage() {
        return currentLockedStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public Scene getCurrentSceneHome() {
        return currentSceneHome;
    }

    public void setCurrentSceneHome(Scene currentSceneHome) {
        this.currentSceneHome = currentSceneHome;
    }

    public Scene getCurrentSceneSend() {
        return currentSceneSend;
    }

    public void setCurrentSceneSend(Scene currentSceneSend) {
        this.currentSceneSend = currentSceneSend;
    }

    public Scene getCurrentSceneReceive() {
        return currentSceneReceive;
    }

    public void setCurrentSceneReceive(Scene currentSceneReceive) {
        this.currentSceneReceive = currentSceneReceive;
    }

    public Scene getCurrentSceneTransactions() {
        return currentSceneTransactions;
    }

    public void setCurrentSceneTransactions(Scene currentSceneTransactions) {
        this.currentSceneTransactions = currentSceneTransactions;
    }

    public Scene getCurrentSceneDelegates() {
        return currentSceneDelegates;
    }

    public void setCurrentSceneDelegates(Scene currentSceneDelegates) {
        this.currentSceneDelegates = currentSceneDelegates;
    }

}