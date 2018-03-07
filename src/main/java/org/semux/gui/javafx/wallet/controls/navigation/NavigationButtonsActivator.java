/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.controls.navigation;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import org.semux.gui.javafx.wallet.Options;

/**
 * Manages navigation bar buttons appearance.
 */
public class NavigationButtonsActivator implements Options {

    /**
     * Constructor loads icons in the buttons backgrounds and makes one button per
     * scene appear activated.
     * 
     * @param root
     *            root of the scene (Anchor Pane by default)
     * @param buttonText
     *            text of each button defines its icon
     */
    public NavigationButtonsActivator(AnchorPane root, String buttonText) {
        HBox hBox = (HBox) root.getChildrenUnmodifiable().get(1);
        GridPane gridPane = (GridPane) hBox.getChildren().get(0);
        List<Node> gridNodes = gridPane.getChildren();
        List<Button> navigationButtonsList = new ArrayList<>();
        gridNodes.stream().forEach((node) -> {
            navigationButtonsList.add((Button) node);
        });
        navigationButtonsList.stream().forEach((button) -> {
            switch (button.getText().toLowerCase()) {
            case "home":
                button.setStyle("-fx-background-image: url('" + HOME_ICON_PATH + "');");
                break;
            case "send":
                button.setStyle("-fx-background-image: url('" + SEND_ICON_PATH + "');");
                break;
            case "receive":
                button.setStyle(
                        "-fx-background-image: url('" + RECEIVE_ICON_PATH + "');");
                break;
            case "transactions":
                button.setStyle(
                        "-fx-background-image: url('" + TRANSACTIONS_ICON_PATH + "');");
                break;
            case "delegates":
                button.setStyle(
                        "-fx-background-image: url('" + DELEGATES_ICON_PATH + "');");
                break;
            case "lock":
                button.setStyle("-fx-background-image: url('" + LOCK_ICON_PATH + "');");
                break;
            }
            if (button.getText().equalsIgnoreCase(buttonText)) {
                button.getStyleClass().add("navigation-button-active");
            }
        });
    }

}