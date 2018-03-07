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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class StageBuilderTest extends ApplicationTest {

    private static Stage testStageBuilderStage;
    private static Scene testStageBuilderScene;

    @BeforeClass
    public static void loadStageBuilderTestGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            GUITestHelper testStageBuilderHelper = new GUITestHelper();
            testStageBuilderHelper.checkLoadedStages();
            testStageBuilderStage = new Stage();
            testStageBuilderStage.setTitle("StageBuilder test scene");
            AnchorPane stageBuilderSceneAnchorPane = new AnchorPane();
            stageBuilderSceneAnchorPane.getStyleClass().add("anchor-pane");
            testStageBuilderScene = new Scene(stageBuilderSceneAnchorPane, 600, 300);
            StageBuilder stageBuilder = new StageBuilder(testStageBuilderStage, testStageBuilderScene);
        });
    }

    @Test
    public void testBuiltStage() {
        PlatformImpl.runLater(() -> {
            assertThat(testStageBuilderStage.getTitle(), is("StageBuilder test scene"));
            assertTrue(testStageBuilderScene.getRoot().isVisible());
            clickOn(testStageBuilderStage);
        });
    }

}