/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

/**
 * Formats TextFields. Applies filter, enables converter.
 */
public class TextFieldFormatter {

    private TextFormatter formatter;
    private StringConverter converter;
    private final TextField field;

    /**
     * Formatter constructor.
     * 
     * @param field
     *            TextField to format
     */
    public TextFieldFormatter(TextField field) {
        this.field = field;
    }

    /**
     * Makes TextField accept only double values.
     * 
     * @param initialValue
     *            default output value
     */
    public void initFormatter(double initialValue) {
        Pattern validEditingState = Pattern.compile("^(?!\\.)(([1-9][0-9]*)|0)?(\\.[0-9]*)?");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return change;
            } else {
                return null;
            }
        };

        StringConverter<Double> stringConverter = new StringConverter<Double>() {
            @Override
            public Double fromString(String string) {
                if (string.isEmpty()) {
                    return initialValue;
                } else {
                    return Double.valueOf(string);
                }
            }

            @Override
            public String toString(Double doubleValue) {
                return doubleValue.toString();
            }
        };

        TextFormatter<Double> textFormatter = new TextFormatter<>(stringConverter, initialValue, filter);
        formatter = textFormatter;
        converter = stringConverter;
        field.setTextFormatter(formatter);
    }

    /**
     * @return the formatter
     */
    public TextFormatter getFormatter() {
        return formatter;
    }

    /**
     * @return the converter
     */
    public StringConverter getConverter() {
        return converter;
    }

}