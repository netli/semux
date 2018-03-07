/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TextFieldFormatterTest extends ApplicationTest {

    private static TextField testTextFieldFormatterOutputTextField;

    @BeforeClass
    public static void loadTextFieldFormatterTestGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            GUITestHelper testTextFieldFormatterHelper = new GUITestHelper();
            testTextFieldFormatterHelper.checkLoadedStages();
            Stage testTextFieldFormatterStage = new Stage();
            AnchorPane textFieldFormatterSceneAnchorPane = new AnchorPane();
            testTextFieldFormatterOutputTextField = new TextField();
            TextFieldFormatter testTextFieldFormatter = new TextFieldFormatter(testTextFieldFormatterOutputTextField);
            testTextFieldFormatter.initFormatter(7);
            textFieldFormatterSceneAnchorPane.getChildren().add(testTextFieldFormatterOutputTextField);
            Scene testTextFieldFormatterScene = new Scene(textFieldFormatterSceneAnchorPane, 600, 300);
            testTextFieldFormatterStage.setScene(testTextFieldFormatterScene);
            testTextFieldFormatterStage.show();
            testTextFieldFormatterStage.toFront();
            testTextFieldFormatterStage.requestFocus();
        });
    }

    @Test
    public void testTextFieldFormatter() {
        assertThat(testTextFieldFormatterOutputTextField.getText(), is("7.0"));
        clickOn(testTextFieldFormatterOutputTextField);
        press(KeyCode.BACK_SPACE);
        write("A2");
        assertThat(testTextFieldFormatterOutputTextField.getText(), is("7.2"));
    }

}