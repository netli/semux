/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.delegates;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.semux.gui.javafx.wallet.GUITestHelper;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DelegatesFXMLControllerTest extends ApplicationTest {

    private static GUITestHelper testDelegatesHelper = new GUITestHelper();

    @BeforeClass
    public static void loadDelegatesGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            testDelegatesHelper.checkLoadedStages();
            Stage testDelegatesStage = new Stage();
            try {
                Parent mainDelegatesNode = FXMLLoader
                        .load(DelegatesFXMLController.class.getResource("DelegatesFXML.fxml"));
                testDelegatesStage.setScene(new Scene(mainDelegatesNode));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            testDelegatesStage.show();
            testDelegatesStage.toFront();
            testDelegatesStage.requestFocus();
            testDelegatesHelper.testInitChoiceBox("addressDelegatesChoiceBox");
        });
    }

    @Test
    public void testDelegatesTableView() {
        testDelegatesHelper.testTableView("delegatesTableView");
        testDelegatesHelper.testTableColumn("rankDelegatesTableColumn");
        testDelegatesHelper.testTableColumn("nameDelegatesTableColumn");
        testDelegatesHelper.testTableColumn("addressDelegatesTableColumn");
        testDelegatesHelper.testTableColumn("votesDelegatesTableColumn");
        testDelegatesHelper.testTableColumn("votesFromMeDelegatesTableColumn");
        testDelegatesHelper.testTableColumn("statusDelegatesTableColumn");
        testDelegatesHelper.testTableColumn("rateDelegatesTableColumn");
    }

    @Test
    public void testDelegateAddressChoiceBox() {
        ChoiceBox addressDelegatesChoiceBox = testDelegatesHelper.testChoiceBox("addressDelegatesChoiceBox");
    }

    @Test
    public void testDelegateTextField() {
        TextField delegateTextField = testDelegatesHelper.testCharTextField("delegateTextField");
    }

    @Test
    public void testVotesDelegatesTextField() {
        TextField votesTextField = testDelegatesHelper.testDoubleFormatTextField("votesTextField");
        press(KeyCode.BACK_SPACE);
        write("9");
        assertThat(votesTextField.getText(), is("0.009"));
        press(KeyCode.ENTER);
    }

    @Test
    public void testVoteDelegatesButton() {
        testVotesDelegatesTextField();
        testDelegatesHelper.testButton("voteButton", "Vote");
    }

    @Test
    public void testUnvotesDelegatesTextField() {
        TextField unvotesTextField = testDelegatesHelper.testDoubleFormatTextField("unvotesTextField");
        press(KeyCode.BACK_SPACE);
        write("9");
        assertThat(unvotesTextField.getText(), is("0.009"));
        press(KeyCode.ENTER);
    }

    @Test
    public void testUnvoteDelegatesButton() {
        testUnvotesDelegatesTextField();
        testDelegatesHelper.testButton("unvoteButton", "Unvote");
    }

    @Test
    public void testRegisterDelegateTextField() {
        TextField registerNameTextField = testDelegatesHelper.testCharTextField("registerNameTextField");
        write("Name");
    }

    @Test
    public void testRegisterDelegateButton() {
        testRegisterDelegateTextField();
        testDelegatesHelper.testButton("registerDelegateButton", "Register as delegate");
    }

    @After
    public void closeDelegatesPopupWindows() {
        press(KeyCode.ESCAPE);
    }

}