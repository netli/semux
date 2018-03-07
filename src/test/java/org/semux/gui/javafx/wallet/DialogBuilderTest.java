/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DialogBuilderTest extends ApplicationTest {

    private static Dialog testDialogBuilderDialog;

    @BeforeClass
    public static void loadDialogBuilderTestGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            GUITestHelper testDialogBuilderHelper = new GUITestHelper();
            testDialogBuilderHelper.checkLoadedStages();
            Stage testDialogBuilderStage = new Stage();
            testDialogBuilderDialog = new Alert(Alert.AlertType.INFORMATION);
            testDialogBuilderDialog.setTitle("Test dialog");
            testDialogBuilderDialog.setHeaderText("Test dialog header");
            testDialogBuilderStage.show();
            DialogBuilder stageBuilder = new DialogBuilder(testDialogBuilderDialog, 400);
            testDialogBuilderDialog.show();
        });
    }

    @Test
    public void testStageBuilder() {
        assertThat(testDialogBuilderDialog.getTitle(), is("Test dialog"));
        assertThat(testDialogBuilderDialog.getHeaderText(), is("Test dialog header"));
        assertTrue(testDialogBuilderDialog.getDialogPane().isVisible());
        PlatformImpl.runLater(() -> {
            Button acceptButton = (Button) testDialogBuilderDialog.getDialogPane().lookupButton(ButtonType.OK);
            clickOn(testDialogBuilderDialog.getDialogPane()).clickOn(acceptButton);
        });
    }

}