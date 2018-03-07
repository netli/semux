/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.secondary.accounts;

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

public class AccountsManagerFXMLControllerTest extends ApplicationTest {

    private static GUITestHelper testAccountsManagerHelper = new GUITestHelper();

    @BeforeClass
    public static void loadAccountsManagerGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            testAccountsManagerHelper.checkLoadedStages();
            Stage testAccountsStage = new Stage();
            try {
                Parent mainAccountsNode = FXMLLoader
                        .load(AccountsManagerFXMLController.class.getResource("AccountsManagerFXML.fxml"));
                testAccountsStage.setScene(new Scene(mainAccountsNode));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            testAccountsStage.show();
            testAccountsStage.toFront();
            testAccountsStage.requestFocus();
        });
    }

    @Test
    public void testRenameAccountButton() {
        testAccountsManagerHelper.testButton("renameAccountButton", "Rename Account");
    }

    @Test
    public void testNewAccountButton() {
        testAccountsManagerHelper.testButton("newAccountButton", "New Account");
    }

    @Test
    public void testDeleteAccountButton() {
        testAccountsManagerHelper.testButton("deleteAccountButton", "Delete Account");
    }

    @Test
    public void testAccountsTableView() {
        testAccountsManagerHelper.testTableView("accountsTableView");
        testAccountsManagerHelper.testTableColumn("numberAccountsTableColumn");
        testAccountsManagerHelper.testTableColumn("nameAccountsTableColumn");
        testAccountsManagerHelper.testTableColumn("addressAccountsTableColumn");
        testAccountsManagerHelper.testTableColumn("availableAccountsTableColumn");
        testAccountsManagerHelper.testTableColumn("lockedAccountsTableColumn");
    }

    @After
    public void closeAccountsManagerPopupWindows() {
        press(KeyCode.ESCAPE);
    }

}