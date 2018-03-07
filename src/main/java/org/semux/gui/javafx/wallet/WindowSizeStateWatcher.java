/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Watches for the changes in window size, state, position parameters of stages.
 */
public class WindowSizeStateWatcher {

    /**
     * Watcher constructor. Changes stage window size, state, position accordingly
     * to the previous stage window parameters.
     * 
     * @param newStage
     *            window size, state, position of this stage will be changed to
     *            previous stage window parameters
     * @param oldStage
     *            window size, state, position of this stage will be transferred to
     *            new stage
     */
    public WindowSizeStateWatcher(Stage newStage, Stage oldStage) {
        if (oldStage.isMaximized() == true) {
            Screen screenPrime = Screen.getPrimary();
            Rectangle2D bounds = screenPrime.getVisualBounds();
            newStage.setWidth(bounds.getWidth());
            newStage.setHeight(bounds.getHeight());
            newStage.setMaximized(true);
        } else {
            newStage.setX(oldStage.getX());
            newStage.setY(oldStage.getY());
            newStage.setWidth(oldStage.getWidth());
            newStage.setHeight(oldStage.getHeight());
        }
    }

    /**
     * Watcher constructor. Restores window state, size when stage gets new scene.
     * 
     * @param stage
     *            stage that got new scene
     * @param wasMaximized
     *            window size, state of stage before scene change
     * @param previousWidth
     *            stage width before scene change
     * @param previousHeight
     *            stage height before scene change
     */
    public WindowSizeStateWatcher(Stage stage, boolean wasMaximized, double previousWidth, double previousHeight) {
        if (wasMaximized == true) {
            Screen screenPrime = Screen.getPrimary();
            Rectangle2D bounds = screenPrime.getVisualBounds();
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());
            stage.setMaximized(true);
        } else {
            stage.setWidth(previousWidth);
            stage.setHeight(previousHeight);
        }
    }

}