/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * User account data.
 */
public class WalletAccount {

    private StringProperty name = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private IntegerProperty blockNumber = new SimpleIntegerProperty();
    private StringProperty blockTime = new SimpleStringProperty();
    private StringProperty coinbase = new SimpleStringProperty();
    private StringProperty status = new SimpleStringProperty();
    private DoubleProperty available = new SimpleDoubleProperty();
    private DoubleProperty locked = new SimpleDoubleProperty();
    private DoubleProperty totalBalance = new SimpleDoubleProperty();

    /**
     * @return blockNumber
     */
    public IntegerProperty blockNumberProperty() {
        return blockNumber;
    }

    /**
     * @return the blockTime
     */
    public StringProperty blockTimeProperty() {
        return blockTime;
    }

    /**
     * @return the coinbase
     */
    public StringProperty coinbaseProperty() {
        return coinbase;
    }

    /**
     * @return the status
     */
    public StringProperty statusProperty() {
        return status;
    }

    /**
     * @return the available
     */
    public DoubleProperty availableProperty() {
        return available;
    }

    /**
     * @return the locked
     */
    public DoubleProperty lockedProperty() {
        return locked;
    }

    /**
     * @return the totalBalance
     */
    public DoubleProperty totalBalanceProperty() {
        return totalBalance;
    }

    /**
     * @return the name
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * @return the address
     */
    public StringProperty addressProperty() {
        return address;
    }

}