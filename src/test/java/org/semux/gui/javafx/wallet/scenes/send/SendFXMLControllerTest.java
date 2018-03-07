/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.send;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
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

public class SendFXMLControllerTest extends ApplicationTest {

    private static GUITestHelper testSendHelper = new GUITestHelper();

    @BeforeClass
    public static void loadSendGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            testSendHelper.checkLoadedStages();
            Stage testSendStage = new Stage();
            try {
                Parent mainSendNode = FXMLLoader.load(SendFXMLController.class.getResource("SendFXML.fxml"));
                testSendStage.setScene(new Scene(mainSendNode));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            testSendStage.show();
            testSendStage.toFront();
            testSendStage.requestFocus();
            testSendHelper.testInitChoiceBox("fromAddressChoiceBox");
        });
    }

    @Test
    public void testFromAddressSendChoiceBox() {
        ChoiceBox fromAddressChoiceBox = testSendHelper.testChoiceBox("fromAddressChoiceBox");
    }

    @Test
    public void testToAddressSendTextField() {
        TextField toAddressTextField = testSendHelper.testCharTextField("toAddressTextField");
        write("Address");
    }

    @Test
    public void testAddressBookButton() {
        testSendHelper.testButton("addressBookButton", "Address book");
    }

    @Test
    public void testAmountSendTextField() {
        TextField amountTextField = testSendHelper.testDoubleFormatTextField("amountTextField");
        press(KeyCode.BACK_SPACE);
        write("9");
        assertThat(amountTextField.getText(), is("0.9"));
        press(KeyCode.ENTER);
    }

    @Test
    public void testFeeSendTextField() {
        TextField feeTextField = testSendHelper.testDoubleFormatTextField("feeTextField");
        press(KeyCode.BACK_SPACE);
        write("6");
        assertThat(feeTextField.getText(), is("0.006"));
        press(KeyCode.ENTER);
    }

    @Test
    public void testDataSendTextField() {
        TextField dataTextField = testSendHelper.testCharTextField("dataTextField");
        write("Data");
    }

    @Test
    public void testTextRadioButton() {
        RadioButton textRadioButton = testSendHelper.testRadioButton("textRadioButton", "Text");
    }

    @Test
    public void testHexRadioButton() {
        RadioButton hexRadioButton = testSendHelper.testRadioButton("hexRadioButton", "Hex");
    }

    @Test
    public void testClearSendButton() {
        testSendHelper.testButton("clearButton", "Clear");
        testToAddressSendTextField();
        testAmountSendTextField();
    }

    @Test
    public void testSendButton() {
        testSendHelper.testButton("sendButton", "Send");
    }

    @After
    public void closeSendPopupWindows() {
        press(KeyCode.ESCAPE);
        testSendHelper.checkNewLoadedStage();
    }

}