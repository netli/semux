/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.preloader;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.semux.gui.javafx.wallet.Options;

public class SemuxPreloaderFXMLController implements Initializable, Options {

    @FXML
    private ImageView imageView;

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image banner = new Image(getClass().getResourceAsStream(LOGO_ICON_PATH));
        imageView.setImage(banner);
    }

}