/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet;

import javafx.stage.Stage;

import org.junit.Test;
import org.semux.gui.javafx.wallet.data.WalletAccount;
import org.semux.gui.javafx.wallet.data.WalletSession;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Start this test first to load data for other tests.
 */
public class ApplicationLoaderTest extends ApplicationTest {

    private static Stage testApplicationLoaderStage;
    private static ApplicationLoader testApplicationLoader;

    @Test
    public void testApplicationLoaderStart() {
        WalletAccount testWalletAccount = new WalletAccount();
        testWalletAccount.nameProperty().set("Test");
        List<WalletAccount> testAccountsList = new ArrayList();
        testAccountsList.add(testWalletAccount);
        WalletSession testWalletSession = new WalletSession(testWalletAccount, testAccountsList);
        ApplicationLoader.setCurrentSession(testWalletSession);
    }

}