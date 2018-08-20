package me.mancy.arandisdrops.utils;

import me.mancy.arandisdrops.data.Strings;

public class FormattedMessage  {

    private String message;

    public FormattedMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return Strings.prefix + " " + message;
    }
}
