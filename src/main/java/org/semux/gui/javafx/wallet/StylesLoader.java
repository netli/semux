/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet;

import javafx.scene.Scene;
import javafx.scene.control.Dialog;

/**
 * Loads .css stylesheet and adds it to scene.
 */
public class StylesLoader implements Options {

    private static String currentTheme = DEFAULT_STYLES_PATH;

    /**
     * Loader constructor for scenes. Loads .css stylesheet and adds it to scene.
     * 
     * @param scene
     *            scene .css file is added to
     */
    public StylesLoader(Scene scene) {
        try {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource(currentTheme).toExternalForm());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Loader constructor for dialogs. Loads .css stylesheet and adds it to scene.
     * 
     * @param dialog
     *            dialog window .css file is added to
     */
    public StylesLoader(Dialog dialog) {
        try {
            dialog.getDialogPane().getStylesheets().clear();
            dialog.getDialogPane().getStylesheets().add(getClass().getResource(currentTheme).toExternalForm());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String getCurrentTheme() {
        return currentTheme;
    }

    public static void setCurrentTheme(String currentTheme) {
        StylesLoader.currentTheme = currentTheme;
    }

}