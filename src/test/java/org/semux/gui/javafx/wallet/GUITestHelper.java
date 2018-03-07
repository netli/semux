/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet;

import com.sun.javafx.application.PlatformImpl;
import com.sun.javafx.stage.StageHelper;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.ListView;
import javafx.scene.control.ChoiceBox;

import org.junit.Ignore;
import org.loadui.testfx.GuiTest;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@Ignore
public class GUITestHelper extends ApplicationTest {

    private Node testNode(String nodeId) {
        Node node = GuiTest.find("#" + nodeId);
        assertTrue(node.isVisible());
        clickOn(node);
        return node;
    }

    public Button testButton(String buttonId, String buttonText) {
        Button button = GuiTest.find("#" + buttonId);
        assertThat(button.getText(), is(buttonText));
        assertTrue(button.isVisible());
        clickOn(button);
        return button;
    }

    public TableView testTableView(String tableViewId) {
        TableView tableView = GuiTest.find("#" + tableViewId);
        assertTrue(tableView.isVisible());
        clickOn(tableView);
        return tableView;
    }

    public ListView testListView(String listViewId) {
        ListView listView = GuiTest.find("#" + listViewId);
        assertTrue(listView.isVisible());
        clickOn(listView);
        return listView;
    }

    public ChoiceBox testChoiceBox(String choiceBoxId) {
        ChoiceBox choiceBox = GuiTest.find("#" + choiceBoxId);
        assertTrue(choiceBox.isVisible());
        clickOn(choiceBox);
        return choiceBox;
    }

    public void testInitChoiceBox(String choiceBoxId) {
        ChoiceBox choiceBox = GuiTest.find("#" + choiceBoxId);
        choiceBox.getItems().add("Test");
        choiceBox.getSelectionModel().selectFirst();
    }

    public TextField testCharTextField(String textFieldId) {
        TextField textField = GuiTest.find("#" + textFieldId);
        textField.clear();
        assertThat(textField.getText(), is(""));
        assertTrue(textField.isVisible());
        clickOn(textField);
        textField.setText("Test");
        assertThat(textField.getText(), is("Test"));
        return textField;
    }

    public TextField testBoundTextField(String textFieldId) {
        TextField textField = GuiTest.find("#" + textFieldId);
        assertTrue(textField.isVisible());
        clickOn(textField);
        return textField;
    }

    public TextField testDoubleFormatTextField(String textFieldId) {
        TextField textField = GuiTest.find("#" + textFieldId);
        assertTrue(textField.isVisible());
        clickOn(textField);
        return textField;
    }

    public RadioButton testRadioButton(String radioButtonId, String radioButtonText) {
        RadioButton radioButton = GuiTest.find("#" + radioButtonId);
        assertThat(radioButton.getText(), is(radioButtonText));
        assertTrue(radioButton.isVisible());
        clickOn(radioButton);
        return radioButton;
    }

    public Node testTableColumn(String tableColumnId) {
        return testNode(tableColumnId);
    }

    public Node testImageView(String imageViewId) {
        return testNode(imageViewId);
    }

    public Node testMenu(String menuId) {
        return testNode(menuId);
    }

    public void checkLoadedStages() {
        if (StageHelper.getStages().size() > 0) {
            StageHelper.getStages().get(0).hide();
        }
    }

    public void checkNewLoadedStage() {
        if (StageHelper.getStages().size() > 1) {
            PlatformImpl.runLater(() -> {
                StageHelper.getStages().get(1).hide();
            });
        }
    }

}
