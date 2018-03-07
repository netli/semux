/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.delegates;

import java.awt.Robot;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import org.controlsfx.control.Notifications;
import org.semux.gui.javafx.wallet.TextFieldFormatter;
import org.semux.gui.javafx.wallet.controls.navigation.NavigationButtonsActivator;
import org.semux.gui.javafx.wallet.Options;

/**
 * Delegates scene controller. Processes votes, unvotes, delegates
 * registrations.
 */
public class DelegatesFXMLController implements Initializable, Options {

    private TextFieldFormatter votesFormatter;
    private TextFieldFormatter unvotesFormatter;
    private double votes;
    private double unvotes;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<?> delegatesTableView;
    @FXML
    private TableColumn<?, ?> nameDelegatesTableColumn;
    @FXML
    private TableColumn<?, ?> addressDelegatesTableColumn;
    @FXML
    private TableColumn<?, ?> rankDelegatesTableColumn;
    @FXML
    private TableColumn<?, ?> votesDelegatesTableColumn;
    @FXML
    private TableColumn<?, ?> votesFromMeDelegatesTableColumn;
    @FXML
    private TableColumn<?, ?> statusDelegatesTableColumn;
    @FXML
    private TableColumn<?, ?> rateDelegatesTableColumn;
    @FXML
    private TextField delegateTextField;
    @FXML
    private ChoiceBox<?> addressDelegatesChoiceBox;
    @FXML
    private TextField votesTextField;
    @FXML
    private Button voteButton;
    @FXML
    private TextField unvotesTextField;
    @FXML
    private Button unvoteButton;
    @FXML
    private TextField registerNameTextField;
    @FXML
    private Button registerDelegateButton;

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Navigation buttons initialization
        NavigationButtonsActivator activator = new NavigationButtonsActivator(anchorPane, "delegates");
        // Vote binding
        BooleanProperty votesAmountCorrect = new SimpleBooleanProperty(false);
        votesTextField.textProperty().addListener((observable, initialValue, newValue) -> {
            votes = (double) votesFormatter.getConverter().fromString(votesTextField.getText());
            votesAmountCorrect.set(votes <= DEFAULT_FEE);
        });
        BooleanBinding disableVotes = votesTextField.textProperty().isEmpty()
                .or(votesAmountCorrect);
        voteButton.disableProperty().bind(disableVotes);
        // Unvote binding
        BooleanProperty unvotesAmountCorrect = new SimpleBooleanProperty(false);
        unvotesTextField.textProperty().addListener((observable, initialValue, newValue) -> {
            unvotes = (double) unvotesFormatter.getConverter().fromString(unvotesTextField.getText());
            unvotesAmountCorrect.set(unvotes <= DEFAULT_FEE);
        });
        BooleanBinding disableUnvotes = unvotesTextField.textProperty().isEmpty()
                .or(unvotesAmountCorrect);
        unvoteButton.disableProperty().bind(disableUnvotes);
        // Register binding
        BooleanProperty nameSizeCorrect = new SimpleBooleanProperty(false);
        registerNameTextField.textProperty().addListener((observable, initialValue, newValue) -> {
            nameSizeCorrect.set((newValue.length() < MIN_NAME_SIZE) || (newValue.length() > MAX_NAME_SIZE));
        });
        BooleanBinding disableButton = registerNameTextField.textProperty().isEmpty()
                .or(nameSizeCorrect);
        registerDelegateButton.disableProperty().bind(disableButton);
        // Votes formatter
        votesFormatter = new TextFieldFormatter(votesTextField);
        votesFormatter.initFormatter(DEFAULT_FEE);
        // Unvotes formatter
        unvotesFormatter = new TextFieldFormatter(unvotesTextField);
        unvotesFormatter.initFormatter(DEFAULT_FEE);
    }

    /**
     * Votes for delegate on mouse click.
     */
    @FXML
    private void vote() {
        if (true) {
            Notifications.create()
                    .title("Vote delegate.")
                    .text("Successfully voted.")
                    .position(Pos.CENTER)
                    .owner(anchorPane.getScene().getWindow())
                    .showInformation();
        } else {
            Notifications.create()
                    .title("Vote delegate.")
                    .text("Failed to vote.")
                    .position(Pos.CENTER)
                    .owner(anchorPane.getScene().getWindow())
                    .showError();
        }
        votesTextField.clear();
        delegatesTableView.requestFocus();
    }

    /**
     * Votes for delegate on enter key pressed.
     */
    @FXML
    private void voteOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            vote();
        }
    }

    /**
     * Unvotes delegate on mouse click.
     */
    @FXML
    private void unvote() {
        if (true) {
            Notifications.create()
                    .title("Unvote delegate.")
                    .text("Successfully unvoted.")
                    .position(Pos.CENTER)
                    .owner(anchorPane.getScene().getWindow())
                    .showInformation();
        } else {
            Notifications.create()
                    .title("Unvote delegate.")
                    .text("Failed to unvote.")
                    .position(Pos.CENTER)
                    .owner(anchorPane.getScene().getWindow())
                    .showError();
        }
        unvotesTextField.clear();
        delegatesTableView.requestFocus();
    }

    /**
     * Unvotes delegate on enter key pressed.
     */
    @FXML
    private void unvoteOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            unvote();
        }
    }

    /**
     * Registers delegate on mouse click.
     */
    @FXML
    private void register() {
        if (true) {
            Notifications.create()
                    .title("Register as delegate.")
                    .text("Successfully registered.")
                    .position(Pos.CENTER)
                    .owner(anchorPane.getScene().getWindow())
                    .showInformation();
        } else {
            Notifications.create()
                    .title("Register as delegate.")
                    .text("Failed to register.")
                    .position(Pos.CENTER)
                    .owner(anchorPane.getScene().getWindow())
                    .showError();
        }
        registerNameTextField.clear();
        delegatesTableView.requestFocus();
    }

    /**
     * Registers delegate on enter key pressed.
     */
    @FXML
    private void registerOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            register();
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
                exception.printStackTrace();
            }
        }
    }

}