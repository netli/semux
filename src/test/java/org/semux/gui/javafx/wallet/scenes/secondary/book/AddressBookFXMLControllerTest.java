/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.secondary.book;

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

public class AddressBookFXMLControllerTest extends ApplicationTest {

    private static GUITestHelper testAddressBookHelper = new GUITestHelper();

    @BeforeClass
    public static void loadAddressBookGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            testAddressBookHelper.checkLoadedStages();
            Stage testAddressBookStage = new Stage();
            try {
                Parent mainAddressBookNode = FXMLLoader
                        .load(AddressBookFXMLController.class.getResource("AddressBookFXML.fxml"));
                testAddressBookStage.setScene(new Scene(mainAddressBookNode));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            testAddressBookStage.show();
            testAddressBookStage.toFront();
            testAddressBookStage.requestFocus();
        });
    }

    @Test
    public void testAddAddressButton() {
        testAddressBookHelper.testButton("addAddressButton", "Add");
    }

    @Test
    public void testCopyAddressButton() {
        testAddressBookHelper.testButton("copyAddressButton", "Copy");
    }

    @Test
    public void testDeleteAddressButton() {
        testAddressBookHelper.testButton("deleteAddressButton", "Delete");
    }

    @Test
    public void testAddressBookTableView() {
        testAddressBookHelper.testTableView("addressBookTableView");
        testAddressBookHelper.testTableColumn("numberAddressBookTableColumn");
        testAddressBookHelper.testTableColumn("nameAddressBookTableColumn");
        testAddressBookHelper.testTableColumn("addressAddressBookTableColumn");
    }

    @After
    public void closeAddressBookPopupWindows() {
        press(KeyCode.ESCAPE);
    }

}