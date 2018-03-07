/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WindowSizeStateWatcherTest extends ApplicationTest {

    public static Stage testWindowWatcherStage;

    @BeforeClass
    public static void loadWindowSizeStateWatcherTestGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            GUITestHelper testWindowSizeStateWatcherHelper = new GUITestHelper();
            testWindowSizeStateWatcherHelper.checkLoadedStages();
            testWindowWatcherStage = new Stage();
            Scene testWindowSizeStateWatcherScene = new Scene(new AnchorPane(), 600, 600);
            testWindowWatcherStage.setScene(testWindowSizeStateWatcherScene);
            testWindowWatcherStage.show();
            testWindowWatcherStage.toFront();
            testWindowWatcherStage.requestFocus();
        });
    }

    @Test
    public void testWindowSizeStateWatcher() {
        PlatformImpl.runLater(() -> {
            WindowSizeStateWatcher testWindowWatcher = new WindowSizeStateWatcher(testWindowWatcherStage, true, 300,
                    300);
            assertTrue(testWindowWatcherStage.isMaximized());
            clickOn(testWindowWatcherStage);
            testWindowWatcher = new WindowSizeStateWatcher(testWindowWatcherStage, false, 300, 300);
        });
        assertFalse(testWindowWatcherStage.isMaximized());
        clickOn(testWindowWatcherStage);
    }

}