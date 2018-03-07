/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.data;

import org.semux.gui.javafx.wallet.data.WalletAccount;
import org.semux.gui.javafx.wallet.data.WalletSession;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Updates external session data in new thread.
 */
public class WalletUpdater {

    private int startDelay = 2000;
    private int updateFrequency = 1000;

    private int blockCounterExample = 1;
    private int accountCounterExample = 1;
    private int availableExample = 2;
    private int lockedExample = 1;
    private String test = "test";

    public WalletUpdater(WalletSession currentSession) {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                blockCounterExample++;
                availableExample += 2;
                currentSession.getCurrentAccount().blockNumberProperty().set(blockCounterExample);
                currentSession.getCurrentAccount().availableProperty().set(availableExample);
                String combinedName = test + accountCounterExample;
                currentSession.getCurrentAccount().nameProperty().set(combinedName);
                if (accountCounterExample % 3 == 0) {
                    lockedExample++;
                    currentSession.getObservableAccountsList().get(1).lockedProperty().set(lockedExample);
                }
                if (blockCounterExample % 5 == 0) {
                    ++accountCounterExample;
                    String accCombinedName = test + accountCounterExample;
                    blockCounterExample = 1;
                    availableExample = 2;
                    lockedExample = 1;
                    WalletAccount acc = new WalletAccount();
                    acc.blockNumberProperty().set(blockCounterExample);
                    acc.availableProperty().set(availableExample);
                    acc.nameProperty().set(accCombinedName);
                    currentSession.getObservableAccountsList().add(acc);
                    currentSession.setCurrentAccount(acc);
                }
            }
        }, startDelay, updateFrequency);
    }

}