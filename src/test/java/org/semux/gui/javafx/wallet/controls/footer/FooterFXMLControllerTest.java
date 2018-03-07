/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.controls.footer;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import org.junit.BeforeClass;
import org.junit.Test;
import org.semux.gui.javafx.wallet.GUITestHelper;
import org.testfx.framework.junit.ApplicationTest;

public class FooterFXMLControllerTest extends ApplicationTest {

    private static GUITestHelper testFooterHelper = new GUITestHelper();

    @BeforeClass
    public static void loadFooterGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            testFooterHelper.checkLoadedStages();
            Stage testFooterStage = new Stage();
            try {
                FXMLLoader footerLoader = new FXMLLoader(FooterFXMLController.class.getResource("FooterFXML.fxml"));
                HBox rootFooterHbox = new HBox();
                footerLoader.setRoot(rootFooterHbox);
                FooterFXMLController footerController = new FooterFXMLController();
                footerLoader.setController(footerController);
                Parent rootFooter = footerLoader.load();
                testFooterStage.setScene(new Scene(rootFooter));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            testFooterStage.show();
            testFooterStage.toFront();
            testFooterStage.requestFocus();
        });
    }

    @Test
    public void testPeersSyncTextField() {
        testFooterHelper.testCharTextField("peersSyncTextField");
    }

}