package com.figo.driver_bot.controller;

import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.expression.BotVariable;
import org.khasanof.annotation.methods.HandleCallback;
import org.khasanof.annotation.methods.HandleCallbacks;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.enums.MatchType;
import org.khasanof.service.template.FluentTemplate;
import org.khasanof.utils.keyboards.inline.InlineKeyboardMarkupBuilder;
import org.khasanof.utils.keyboards.reply.ReplyKeyboardMarkupBuilder;
import org.telegram.telegrambots.meta.api.objects.Update;

@UpdateController
public class FluentController {
    private final FluentTemplate fluentTemplate;

    public FluentController(FluentTemplate fluentTemplate) {
        this.fluentTemplate = fluentTemplate;
    }

    @HandleMessage("/start")
    public void startExample() {
        fluentTemplate.sendText("Hello World!!!");
    }


    @HandleMessage(value = "/username : {name:[a-z]}", match = MatchType.VAR_EXPRESSION)
    void startWithAbsHandler(Update update, @BotVariable("name") String username) {
        fluentTemplate.sendText("Username : " + username);
    }
    @HandleMessage(value = "/button")
    public void handleButtonCommand() {
        InlineKeyboardMarkupBuilder builder = new InlineKeyboardMarkupBuilder();

        builder.addButton("First")
                .callbackData("First");
        builder.addButton("Second")
                .callbackData("Second");
        builder.addRow();

        builder.addButton("Third")
                .callbackData("Third");
        builder.addButton("Fourth")
                .callbackData("Fourth");

        fluentTemplate.sendText("This is inline button", builder.build());
    }
    @HandleCallback({"EN", "RU", "UZ"})
    private void callBack() {
        // ...
    }

    @HandleCallbacks(value = {
            @HandleCallback({"NEXT", "PREV"}),
            @HandleCallback({"TOP", "BOTTOM"}),
            @HandleCallback(value = {"INDEX"}, match = MatchType.STARTS_WITH)
    })
    private void multiCallback(Update update) {
        // ...
    }

    @HandleMessage(value = "/rbutton")
    public void sendTextWithReplyKeyboardExample() {
        ReplyKeyboardMarkupBuilder builder = new ReplyKeyboardMarkupBuilder();
        builder.oneTimeKeyboard(true);

        builder.addButton("First");
        builder.addButton("Second");

        builder.addRow();

        builder.addButton("Third");
        builder.addButton("Fourth");

        fluentTemplate.sendText("This is reply button", builder.build());
    }
}
