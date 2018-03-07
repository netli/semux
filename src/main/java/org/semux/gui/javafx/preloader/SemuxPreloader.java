/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.preloader;

import javafx.application.Preloader;
import javafx.application.Preloader.StateChangeNotification;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.semux.gui.javafx.wallet.Options;

public class SemuxPreloader extends Preloader implements Options {

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Pane rootPreloader = FXMLLoader.load(getClass().getResource("SemuxPreloaderFXML.fxml"));
        Scene scenePreloader = new Scene(rootPreloader);
        scenePreloader.setFill(Color.TRANSPARENT);
        stage.setScene(scenePreloader);
        stage.initStyle(StageStyle.TRANSPARENT);
        Image logo = new Image(getClass().getResourceAsStream(LOGO_ICON_PATH));
        stage.setTitle("Semux Wallet");
        stage.getIcons().add(logo);
        stage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if (stateChangeNotification.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }

}