package com.figo.driver_bot.controller;

import com.figo.driver_bot.domain.Users;
import com.figo.driver_bot.repository.UsersRepository;
import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.service.template.FluentTemplate;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@UpdateController
public class FluentController {
    private final FluentTemplate fluentTemplate;
    private final UsersRepository usersRepository;

    public FluentController(FluentTemplate fluentTemplate, UsersRepository usersRepository) {
        this.fluentTemplate = fluentTemplate;
        this.usersRepository = usersRepository;
    }

    @HandleMessage("/start")
    public void start(Update update) {
        User fromUser = update.getMessage().getFrom();
        Long chatId = fromUser.getId();
        Optional<Users> user = usersRepository.findById(chatId);
        if (user.isPresent() && user.get().getIsAdmin()) {
            fluentTemplate.sendText(String.valueOf(chatId));
        } else if (user.isEmpty()){
            boolean isAdmin = false;
            String nickname = fromUser.getFirstName();
            if (fromUser.getLastName() != null) {
                nickname = " " + fromUser.getLastName();
            }
            if (chatId.equals(1490827145L) || chatId.equals(385801672L)) {
                isAdmin = true;
            }
            Users users = Users.builder().id(chatId).chatId(String.valueOf(chatId)).updateAction("Добавлен новый пользователь").
                    nickname(nickname).isAdmin(isAdmin).createdAt(ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime())
                    .username(fromUser.getUserName()).build();
             usersRepository.save(users);
            String groupMessage = users.getUpdateAction() + "\n" +
                    "<a href=\"tg://user?id=" + users.getChatId() + "\">" + users.getNickname() + "</a>";

            fluentTemplate.sendText(groupMessage,-1002577532866L, "HTML");
        }
    }


//    @HandleMessage(value = "/username : {name:[a-z]}", match = MatchType.VAR_EXPRESSION)
//    void startWithAbsHandler(Update update, @BotVariable("name") String username) {
//        fluentTemplate.sendText("Username : " + username);
//    }
//    @HandleMessage(value = "/button")
//    public void handleButtonCommand() {
//        InlineKeyboardMarkupBuilder builder = new InlineKeyboardMarkupBuilder();
//
//        builder.addButton("First")
//                .callbackData("First");
//        builder.addButton("Second")
//                .callbackData("Second");
//        builder.addRow();
//
//        builder.addButton("Third")
//                .callbackData("Third");
//        builder.addButton("Fourth")
//                .callbackData("Fourth");
//
//        fluentTemplate.sendText("This is inline button", builder.build());
//    }
//    @HandleCallback({"EN", "RU", "UZ"})
//    private void callBack() {
//        // ...
//    }
//
//    @HandleCallbacks(value = {
//            @HandleCallback({"NEXT", "PREV"}),
//            @HandleCallback({"TOP", "BOTTOM"}),
//            @HandleCallback(value = {"INDEX"}, match = MatchType.STARTS_WITH)
//    })
//    private void multiCallback(Update update) {
//        // ...
//    }
//
//    @HandleMessage(value = "/rbutton")
//    public void sendTextWithReplyKeyboardExample() {
//        ReplyKeyboardMarkupBuilder builder = new ReplyKeyboardMarkupBuilder();
//        builder.oneTimeKeyboard(true);
//
//        builder.addButton("First");
//        builder.addButton("Second");
//
//        builder.addRow();
//
//        builder.addButton("Third");
//        builder.addButton("Fourth");
//
//        fluentTemplate.sendText("This is reply button", builder.build());
//    }
}
