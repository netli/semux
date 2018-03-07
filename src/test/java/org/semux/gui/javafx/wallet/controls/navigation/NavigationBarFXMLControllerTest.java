/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.controls.navigation;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.semux.gui.javafx.wallet.GUITestHelper;
import org.testfx.framework.junit.ApplicationTest;
import org.semux.gui.javafx.wallet.Options;

public class NavigationBarFXMLControllerTest extends ApplicationTest implements Options {

    private static Stage testNavigationStage;
    private static Scene testNavigationScene;
    private static Scene newNavigationTestScene;
    private static NavigationBarFXMLController navigationController;
    private static GUITestHelper testNavigationBarHelper = new GUITestHelper();

    @BeforeClass
    public static void loadNavigationGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            testNavigationBarHelper.checkLoadedStages();
            testNavigationStage = new Stage();
            newNavigationTestScene = new Scene(new HBox());
            try {
                FXMLLoader navigationLoader = new FXMLLoader(
                        NavigationBarFXMLController.class.getResource("NavigationBarFXML.fxml"));
                HBox rootNavigationHbox = new HBox();
                navigationLoader.setRoot(rootNavigationHbox);
                navigationController = new NavigationBarFXMLController();
                navigationLoader.setController(navigationController);
                Parent rootNavigation = navigationLoader.load();
                testNavigationScene = new Scene(rootNavigation);
                testNavigationStage.setScene(testNavigationScene);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            testNavigationStage.show();
            testNavigationStage.toFront();
            testNavigationStage.requestFocus();
        });
    }

    @Test
    public void testHomeNavigationButton() {
        navigationController.setCurrentSceneHome(newNavigationTestScene);
        testNavigationBarHelper.testButton("homeNavigationButton", "Home");
    }

    @Test
    public void testSendNavigationButton() {
        navigationController.setCurrentSceneSend(newNavigationTestScene);
        testNavigationBarHelper.testButton("sendNavigationButton", "Send");
    }

    @Test
    public void testReceiveNavigationButton() {
        navigationController.setCurrentSceneReceive(newNavigationTestScene);
        testNavigationBarHelper.testButton("receiveNavigationButton", "Receive");
    }

    @Test
    public void testTransactionsNavigationButton() {
        navigationController.setCurrentSceneTransactions(newNavigationTestScene);
        testNavigationBarHelper.testButton("transactionsNavigationButton", "Transactions");
    }

    @Test
    public void testDelegatesNavigationButton() {
        navigationController.setCurrentSceneDelegates(newNavigationTestScene);
        testNavigationBarHelper.testButton("delegatesNavigationButton", "Delegates");
    }

    @Test
    public void testLockNavigationButton() {
        testNavigationBarHelper.testButton("lockNavigationButton", "Lock");
    }

    @After
    public void bringBackTestScene() {
        PlatformImpl.runLater(() -> {
            if (testNavigationScene.getRoot().isDisabled()) {
                navigationController.getCurrentLockedStage().hide();
                testNavigationScene.getRoot().setDisable(false);
                testNavigationStage.setScene(testNavigationScene);
                testNavigationStage.show();
            }
            testNavigationStage.setScene(testNavigationScene);
        });
    }

}