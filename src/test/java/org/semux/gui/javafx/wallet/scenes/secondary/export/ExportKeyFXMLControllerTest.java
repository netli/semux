/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.secondary.export;

import com.sun.javafx.application.PlatformImpl;

import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.semux.gui.javafx.wallet.GUITestHelper;
import org.testfx.framework.junit.ApplicationTest;

public class ExportKeyFXMLControllerTest extends ApplicationTest {

    private static GUITestHelper testExportKeyHelper = new GUITestHelper();

    @BeforeClass
    public static void loadExportKeyGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            testExportKeyHelper.checkLoadedStages();
            Stage testExportKeyStage = new Stage();
            try {
                Parent mainExportKeyNode = FXMLLoader
                        .load(ExportKeyFXMLController.class.getResource("ExportKeyFXML.fxml"));
                testExportKeyStage.setScene(new Scene(mainExportKeyNode));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            testExportKeyStage.show();
            testExportKeyStage.toFront();
            testExportKeyStage.requestFocus();
        });
    }

    @Test
    public void testCopyKeyButton() {
        testExportKeyHelper.testButton("copyKeyButton", "Copy private key");
    }

    @Test
    public void testExportKeyTableView() {
        testExportKeyHelper.testTableView("exportKeyTableView");
        testExportKeyHelper.testTableColumn("numberExportKeyTableColumn");
        testExportKeyHelper.testTableColumn("addressExportKeyTableColumn");
        testExportKeyHelper.testTableColumn("nameExportKeyTableColumn");
        testExportKeyHelper.testTableColumn("keyExportKeyTableColumn");
    }

    @After
    public void closeExportKeyPopupWindows() {
        press(KeyCode.ESCAPE);
    }

}