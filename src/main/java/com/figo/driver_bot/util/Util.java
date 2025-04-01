package com.figo.driver_bot.util;

import org.jetbrains.annotations.NotNull;
import org.khasanof.utils.keyboards.reply.ReplyKeyboardMarkupBuilder;

public class Util {

    public static @NotNull ReplyKeyboardMarkupBuilder getAdminMarkup() {
        ReplyKeyboardMarkupBuilder builder = new ReplyKeyboardMarkupBuilder();
        builder.oneTimeKeyboard(true);
        builder.resizeKeyboard(true);
        builder.addButton("Admin qo'shish");
        builder.addButton("Admin o'chirish");
        builder.addRow();
        builder.addButton("Odatiy tarifni belgilash");
        builder.addRow();
        builder.addButton("Muddat belgilash");
        return builder;
    }
}
