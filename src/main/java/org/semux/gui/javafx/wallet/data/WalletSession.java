/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.data;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.semux.gui.javafx.wallet.data.WalletAccount;

/**
 * All current session data loaded and stored here.
 */
public class WalletSession {

    private WalletAccount currentAccount;
    private int accountChangesCounter;
    private IntegerProperty accountChanges = new SimpleIntegerProperty();
    private ObservableList<WalletAccount> observableAccountsList;

    /**
     * @param accountLoaded
     *            current account loaded from application preloader
     * @param accountsListLoaded
     *            list of all accounts
     */
    public WalletSession(WalletAccount accountLoaded, List<WalletAccount> accountsListLoaded) {
        observableAccountsList = FXCollections.observableList(accountsListLoaded);
        currentAccount = accountLoaded;
        accountChangesCounter++;
        accountChanges.set(accountChangesCounter);
    }

    /**
     * @return the currentAccount
     */
    public WalletAccount getCurrentAccount() {
        return currentAccount;
    }

    /**
     * Changes currently used account.
     * 
     * @param newAccount
     *            account selected as current
     */
    public void setCurrentAccount(WalletAccount newAccount) {
        currentAccount = newAccount;
        accountChangesCounter++;
        accountChanges.set(accountChangesCounter);
    }

    /**
     * Counter indicates account change.
     * 
     * @return the accountChangeOccurred
     */
    public IntegerProperty getAccountChangeOccurred() {
        return accountChanges;
    }

    /**
     * @return the observableAccountsList
     */
    public ObservableList<WalletAccount> getObservableAccountsList() {
        return observableAccountsList;
    }

}