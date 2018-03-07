/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet;

import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Builder for dialog windows. Loads logo, sets graphic and .css styles.
 * 
 * @author jeredy
 */
public class DialogBuilder implements Options {

    /**
     * Builder constructor dialog windows. Loads logo, sets graphic and .css styles.
     * 
     * @param dialog
     *            dialog window to build
     * @param initialWidth
     *            initial dialog window width
     */
    public DialogBuilder(Dialog dialog, float initialWidth) {
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        try {
            Image logo = new Image(getClass().getResourceAsStream(LOGO_ICON_PATH));
            stage.getIcons().add(logo);
            ImageView imageView = new ImageView(logo);
            dialog.setGraphic((Node) imageView);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.getDialogPane().setMinWidth(initialWidth);
        StylesLoader stylesLoader = new StylesLoader(dialog);
    }

}