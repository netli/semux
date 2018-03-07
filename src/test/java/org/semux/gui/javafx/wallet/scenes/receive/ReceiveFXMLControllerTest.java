/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.receive;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.After;
import org.testfx.framework.junit.ApplicationTest;
import org.semux.gui.javafx.wallet.GUITestHelper;

public class ReceiveFXMLControllerTest extends ApplicationTest {

    private static GUITestHelper testReceiveHelper = new GUITestHelper();

    @BeforeClass
    public static void loadReceiveGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            testReceiveHelper.checkLoadedStages();
            Stage testReceiveStage = new Stage();
            try {
                Parent mainReceiveNode = FXMLLoader.load(ReceiveFXMLController.class.getResource("ReceiveFXML.fxml"));
                testReceiveStage.setScene(new Scene(mainReceiveNode));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            testReceiveStage.show();
            testReceiveStage.toFront();
            testReceiveStage.requestFocus();
        });
    }

    @Test
    public void testReceiveTableView() {
        testReceiveHelper.testTableView("accountsReceiveTableView");
        testReceiveHelper.testTableColumn("numberReceiveTableColumn");
        testReceiveHelper.testTableColumn("nameReceiveTableColumn");
        testReceiveHelper.testTableColumn("addressReceiveTableColumn");
        testReceiveHelper.testTableColumn("availableReceiveTableColumn");
        testReceiveHelper.testTableColumn("lockedReceiveTableColumn");
    }

    @Test
    public void testReceiveImageView() {
        testReceiveHelper.testImageView("qrCodeImageView");
    }

    @Test
    public void testReceiveCopyButton() {
        testReceiveHelper.testButton("copyReceiveButton", "Copy Address");
    }

    @After
    public void closeReceivePopupWindows() {
        press(KeyCode.ESCAPE);
    }

}