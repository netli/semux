/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.controls.menu;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.semux.gui.javafx.wallet.GUITestHelper;
import org.testfx.framework.junit.ApplicationTest;

public class MenuBarFXMLControllerTest extends ApplicationTest {

    private static GUITestHelper testMenuBarHelper = new GUITestHelper();

    @BeforeClass
    public static void loadMenuBarGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            testMenuBarHelper.checkLoadedStages();
            Stage testMenuBarStage = new Stage();
            try {
                FXMLLoader menuBarLoader = new FXMLLoader(MenuBarFXMLController.class.getResource("MenuBarFXML.fxml"));
                HBox rootMenuBarHbox = new HBox();
                menuBarLoader.setRoot(rootMenuBarHbox);
                MenuBarFXMLController menuBarController = new MenuBarFXMLController();
                menuBarLoader.setController(menuBarController);
                Parent rootMenuBar = menuBarLoader.load();
                Scene testMenuBarScene = new Scene(rootMenuBar);
                testMenuBarStage.setScene(testMenuBarScene);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            testMenuBarStage.show();
            testMenuBarStage.toFront();
            testMenuBarStage.requestFocus();
        });
    }

    @Test
    public void testMainMenuBar() {
        testMenuBarHelper.testMenu("menuBar");
    }

    @Test
    public void testFileMenu() {
        testMenuBarHelper.testMenu("fileMenu");
    }

    @Test
    public void testWalletMenu() {
        testMenuBarHelper.testMenu("walletMenu");
    }

    @Test
    public void testAccountMenu() {
        testMenuBarHelper.testMenu("accountMenu");
    }

    @Test
    public void testHelpMenu() {
        testMenuBarHelper.testMenu("helpMenu");
    }

    @Test
    public void testExitMenuItem() {
        clickOn("#fileMenu").moveBy(0, 10).moveTo("#exitMenuItem");
    }

    @Test
    public void testChangePasswordMenuItem() {
        clickOn("#walletMenu").moveBy(0, 10).clickOn("#changePasswordMenuItem");
    }

    @Test
    public void testRecoverAccountMenuItem() {
        clickOn("#walletMenu").moveBy(0, 10).clickOn("#recoverAccountsMenuItem");
    }

    @Test
    public void testBackupWalletMenuItem() {
        clickOn("#walletMenu").moveBy(0, 10).clickOn("#backupWalletMenuItem");
    }

    @Test
    public void testImportKeyMenuItem() {
        clickOn("#walletMenu").moveBy(0, 10).clickOn("#importKeyMenuItem");
    }

    @Test
    public void testExportKeyMenuItem() {
        clickOn("#walletMenu").moveBy(0, 10).clickOn("#exportKeyMenuItem");
    }

    @Test
    public void testAccountsManagerMenuItem() {
        clickOn("#accountMenu").moveBy(0, 10).clickOn("#manageAccountsMenuItem");
    }

    @Test
    public void testAboutMenuItem() {
        clickOn("#helpMenu").moveBy(0, 10).clickOn("#aboutMenuItem");
    }

    @Test
    public void testHelpMenuItem() {
        clickOn("#helpMenu").moveBy(0, 10).clickOn("#helpMenuItem");
    }

    @After
    public void closeMenuBarPopupWindows() {
        press(KeyCode.ESCAPE);
        testMenuBarHelper.checkNewLoadedStage();
    }

}