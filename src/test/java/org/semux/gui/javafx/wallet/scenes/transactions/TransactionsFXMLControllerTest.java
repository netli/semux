/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.transactions;

import com.sun.javafx.application.PlatformImpl;

import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import org.junit.BeforeClass;
import org.junit.Test;
import org.semux.gui.javafx.wallet.GUITestHelper;
import org.testfx.framework.junit.ApplicationTest;

public class TransactionsFXMLControllerTest extends ApplicationTest {

    private static GUITestHelper testTransactionsHelper = new GUITestHelper();

    @BeforeClass
    public static void loadTransactionsGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            testTransactionsHelper.checkLoadedStages();
            Stage testTransactionsStage = new Stage();
            try {
                Parent mainTransactionsNode = FXMLLoader
                        .load(TransactionsFXMLController.class.getResource("TransactionsFXML.fxml"));
                testTransactionsStage.setScene(new Scene(mainTransactionsNode));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            testTransactionsStage.show();
            testTransactionsStage.toFront();
            testTransactionsStage.requestFocus();
        });
    }

    @Test
    public void testTransactionsTableView() {
        testTransactionsHelper.testTableView("transactionsTableView");
        testTransactionsHelper.testTableColumn("numberTransactionsTableColumn");
        testTransactionsHelper.testTableColumn("typeTransactionsTableColumn");
        testTransactionsHelper.testTableColumn("destinationTransactionsTableColumn");
        testTransactionsHelper.testTableColumn("valueTransactionsTableColumn");
        testTransactionsHelper.testTableColumn("timeTransactionsTableColumn");
        testTransactionsHelper.testTableColumn("statusTransactionsTableColumn");
    }

}