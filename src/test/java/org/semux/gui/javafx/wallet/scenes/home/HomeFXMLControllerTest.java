/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.home;

import com.sun.javafx.application.PlatformImpl;

import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import org.junit.BeforeClass;
import org.junit.Test;
import org.semux.gui.javafx.wallet.GUITestHelper;
import org.testfx.framework.junit.ApplicationTest;

public class HomeFXMLControllerTest extends ApplicationTest {

    private static GUITestHelper testHomeHelper = new GUITestHelper();

    @BeforeClass
    public static void loadHomeGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            testHomeHelper.checkLoadedStages();
            Stage testHomeStage = new Stage();
            try {
                Parent mainHomeNode = FXMLLoader.load(HomeFXMLController.class.getResource("HomeFXML.fxml"));
                testHomeStage.setScene(new Scene(mainHomeNode));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            testHomeStage.show();
            testHomeStage.toFront();
            testHomeStage.requestFocus();
        });
    }

    @Test
    public void testAccountNameHomeTextField() {
        testHomeHelper.testBoundTextField("accountNameTextField");
    }

    @Test
    public void testBlockNumberHomeTextField() {
        testHomeHelper.testBoundTextField("blockNumberTextField");
    }

    @Test
    public void testBlockTimeHomeTextField() {
        testHomeHelper.testBoundTextField("blockTimeTextField");
    }

    @Test
    public void testCoinbaseHomeTextField() {
        testHomeHelper.testBoundTextField("coinbaseTextField");
    }

    @Test
    public void testStatusHomeTextField() {
        testHomeHelper.testBoundTextField("statusTextField");
    }

    @Test
    public void testAvailableHomeTextField() {
        testHomeHelper.testBoundTextField("availableTextField");
    }

    @Test
    public void testLockedHomeTextField() {
        testHomeHelper.testBoundTextField("lockedTextField");
    }

    @Test
    public void testTotalBalanceHomeTextField() {
        testHomeHelper.testBoundTextField("totalBalanceTextField");
    }

    @Test
    public void testTransactionsHomeListView() {
        testHomeHelper.testListView("transactionsListView");
    }

}