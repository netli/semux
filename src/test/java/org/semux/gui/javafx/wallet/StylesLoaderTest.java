/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class StylesLoaderTest extends ApplicationTest {

    private static Scene testStylesLoaderScene;
    private static Label testStylesLoaderOutputLabel;

    @BeforeClass
    public static void loadStylesLoaderTestGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            GUITestHelper testStylesLoaderHelper = new GUITestHelper();
            testStylesLoaderHelper.checkLoadedStages();
            Stage testStylesLoaderStage = new Stage();
            AnchorPane stylesLoaderSceneAnchorPane = new AnchorPane();
            testStylesLoaderOutputLabel = new Label();
            stylesLoaderSceneAnchorPane.getChildren().add(testStylesLoaderOutputLabel);
            stylesLoaderSceneAnchorPane.getStyleClass().add("anchor-pane");
            testStylesLoaderScene = new Scene(stylesLoaderSceneAnchorPane, 600, 300);
            testStylesLoaderStage.setScene(testStylesLoaderScene);
            testStylesLoaderStage.show();
            testStylesLoaderStage.toFront();
            testStylesLoaderStage.requestFocus();
            testStylesLoaderOutputLabel.setText(testStylesLoaderScene.getStylesheets().toString());
        });
    }

    @Test
    public void testStylesLoader() {
        assertThat(testStylesLoaderOutputLabel.getText(), is("[]"));
        StylesLoader stylesLoader = new StylesLoader(testStylesLoaderScene);
        PlatformImpl.runLater(() -> {
            testStylesLoaderOutputLabel.setText(testStylesLoaderScene.getStylesheets().toString());
        });
        clickOn(testStylesLoaderScene).clickOn(testStylesLoaderOutputLabel);
        assertThat(testStylesLoaderOutputLabel.getText(), not("[]"));
    }

}