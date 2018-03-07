/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet;

/**
 * URLs, paths, sizes, constants.
 */
public interface Options {

    // URLS
    String HELP_URL = "https://github.com/semuxproject/semux/wiki";

    String MAIN_PACKAGE_NAME = "wallet/";
    String SCENES_PACKAGE_NAME = "scenes/";
    String RESOURCES_PACKAGE_NAME = "resources/";

    String RUN_PATH = "/org/semux/gui/javafx/";

    String HOME_SCENE_PATH = SCENES_PACKAGE_NAME + "home/HomeFXML.fxml";
    String SEND_SCENE_PATH = SCENES_PACKAGE_NAME + "send/SendFXML.fxml";
    String RECEIVE_SCENE_PATH = SCENES_PACKAGE_NAME + "receive/ReceiveFXML.fxml";
    String TRANSACTIONS_SCENE_PATH = SCENES_PACKAGE_NAME + "transactions/TransactionsFXML.fxml";
    String DELEGATES_SCENE_PATH = SCENES_PACKAGE_NAME + "delegates/DelegatesFXML.fxml";

    String RESOURCES_PATH = "/org/semux/gui/";

    String HOME_ICON_PATH = RESOURCES_PATH + "home.png";
    String SEND_ICON_PATH = RESOURCES_PATH + "send.png";
    String RECEIVE_ICON_PATH = RESOURCES_PATH + "receive.png";
    String TRANSACTIONS_ICON_PATH = RESOURCES_PATH + "transactions.png";
    String DELEGATES_ICON_PATH = RESOURCES_PATH + "delegates.png";
    String LOCK_ICON_PATH = RESOURCES_PATH + "lock.png";
    String LOGO_ICON_PATH = RESOURCES_PATH + "logo.png";

    String DEFAULT_STYLES_PATH = RUN_PATH + MAIN_PACKAGE_NAME + RESOURCES_PACKAGE_NAME + "styles/default.css";

    // Main scenes
    float INIT_MAIN_SCENE_WIDTH = 900;
    float INIT_MAIN_SCENE_HEIGHT = 600;

    float MIN_MAIN_STAGE_WIDTH = 800;
    float MIN_MAIN_STAGE_HEIGHT = 500;

    // Secondary scenes
    float INIT_BOOK_WIDTH = 500;
    float INIT_BOOK_HEIGHT = 550;

    float INIT_EXPORT_WIDTH = 800;
    float INIT_EXPORT_HEIGHT = 450;

    float INIT_ACCOUNTS_WIDTH = 800;
    float INIT_ACCOUNTS_HEIGHT = 450;

    // Dialogs
    float INIT_IMPORT_KEY_WIDTH = 550;

    float INIT_UNLOCK_WIDTH = 400;

    float INIT_CHANGE_PASSWORD_WIDTH = 500;

    float INIT_ADD_ADDRESS_WIDTH = 650;

    float INIT_ABOUT_WIDTH = 450;

    float INIT_RENAME_ACCOUNT_WIDTH = 450;

    float INIT_NEW_ACCOUNT_WIDTH = 450;

    // Constants
    double DEFAULT_FEE = 0.005;

    int MIN_PASSWORD_SIZE = 4;
    int MAX_PASSWORD_SIZE = 30;

    int MIN_NAME_SIZE = 4;
    int MAX_NAME_SIZE = 16;

    int TRANSACTION_LIMIT = 1024;

}