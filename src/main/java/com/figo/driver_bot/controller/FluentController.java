package com.figo.driver_bot.controller;

import com.figo.driver_bot.domain.Users;
import com.figo.driver_bot.repository.UsersRepository;
import org.jetbrains.annotations.NotNull;
import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.annotation.methods.HandleCallback;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.enums.MatchType;
import org.khasanof.service.template.FluentTemplate;
import org.khasanof.utils.keyboards.inline.InlineKeyboardMarkupBuilder;
import org.khasanof.utils.keyboards.reply.ReplyKeyboardMarkupBuilder;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@UpdateController
public class FluentController {
    private final FluentTemplate fluentTemplate;
    private final UsersRepository usersRepository;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

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
            fluentTemplate.sendText("Assalomu alaykum. Xush kelibsiz! \nQuyidagi buyruqlardan birini tanlang", getAdminMarkup().build());
        } else if (user.isEmpty()) {
            boolean isAdmin = false;
            String nickname = fromUser.getFirstName();
            if (fromUser.getLastName() != null) {
                nickname = " " + fromUser.getLastName();
            }
            if (chatId.equals(1490827145L) || chatId.equals(385801672L)) {
                isAdmin = true;
            }
            Users users = Users.builder().id(chatId).chatId(String.valueOf(chatId)).updateAction("Yangi foydalanuvchi qo'shildi" + "\n" +
                            "<a href=\"tg://user?id=" + chatId + "\">" + nickname + "</a>" + "\n\n#user").
                    nickname(nickname).isAdmin(isAdmin).createdAt(ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime())
                    .username(fromUser.getUserName()).build();
            usersRepository.save(users);
            fluentTemplate.sendText(users.getUpdateAction(), -1002577532866L, "HTML");
        }
    }


    @HandleMessage("Admin qo'shish")
    public void addAdmin(Update update) {
        User fromUser = update.getMessage().getFrom();
        Long chatId = fromUser.getId();
        Optional<Users> user = usersRepository.findById(chatId);
        if (user.isPresent() && user.get().getIsAdmin()) {
            fluentTemplate.sendText("Kimni admin qilmoqchisiz? Foydalanuvchining kontaktini ulashing! \nEslatma !!! \nFoydalanuvchi botga start bergan bolishi shart!");
        }
    }

    @HandleAny
    public void addAdminProcess(Update update) {
        boolean hasMessage = update.hasMessage();
        if (!hasMessage) {
            return;
        }
        User fromUser = update.getMessage().getFrom();
        Long chatId = fromUser.getId();
        Optional<Users> user = usersRepository.findById(chatId);
        if (user.isPresent() && user.get().getIsAdmin()) {
            if (update.getMessage().hasContact()) {
                Long newAdminId = update.getMessage().getContact().getUserId();
                Optional<Users> newAdmin = usersRepository.findById(newAdminId);
                if (newAdmin.isEmpty()) {
                    fluentTemplate.sendText("Foydalanuvchilar royxatida bunday contact mavjud emas! \nFoydalanuvchi botga start bergan bolishi shart!");
                } else {
                    Users admin = newAdmin.get();
                    if (admin.getIsAdmin()) {
                        fluentTemplate.sendText("Admin roli allaqachon berilgan");
                    } else {
                        admin.setIsAdmin(true);
                        admin.setUpdateAction("Foydalanuvchi " + "<a href=\"tg://user?id=" + admin.getChatId() + "\">" + admin.getNickname() + "</a>" + " ga admin roli " +
                                "<a href=\"tg://user?id=" + chatId + "\">" + user.get().getNickname() + "</a>" + " tomonidan berildi\n" + "Vaqt: " +
                                ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime().format(dateTimeFormatter) + "\n\n#admin");
                        admin.setUpdatedAt(ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime());
                        admin.setUpdatedBy(chatId);
                        usersRepository.save(admin);
                        fluentTemplate.sendText("Admin qo'shildi");
                        fluentTemplate.sendText(admin.getUpdateAction(), -1002577532866L, "HTML");
                        fluentTemplate.sendText("Sizga admin roli berildi.", admin.getId(), getAdminMarkup().build());
                    }
                }
            }
        }
    }

    @HandleMessage("Admin o'chirish")
    public void removeAdmin(Update update) {
        User fromUser = update.getMessage().getFrom();
        Long chatId = fromUser.getId();
        Optional<Users> user = usersRepository.findById(chatId);
        if (user.isPresent() && user.get().getIsAdmin()) {
            InlineKeyboardMarkupBuilder builder = new InlineKeyboardMarkupBuilder();
            List<Users> admins = usersRepository.findByIsAdminTrue();
            int countAdmins = 0;
            for (Users admin : admins) {
                if (!admin.getId().equals(1490827145L) && !(admin.getId().equals(385801672L)) && !admin.getId().equals(chatId)) {
                    builder.addButton(admin.getNickname())
                            .callbackData("del:" + admin.getId());
                    builder.addRow();
                    countAdmins++;
                }
            }
            if (countAdmins > 0) {
                fluentTemplate.sendText("O'chiriladigan adminni tanlang", builder.build());
            } else {
                fluentTemplate.sendText("Siz o'chirishizgiz mumkin bolgan admin yo'q");
            }
        }
    }

    @HandleCallback(value = {"del:"}, match = MatchType.STARTS_WITH)
    public void removeAdminProcess(Update update) {
        User fromUser = update.getCallbackQuery().getFrom();
        Long chatId = fromUser.getId();
        Optional<Users> user = usersRepository.findById(chatId);
        if (user.isPresent() && user.get().getIsAdmin()) {
            String data = update.getCallbackQuery().getData();
            String[] split = data.split(":");
            String delAdminId = split[1];
            try {
                long adminId = Long.parseLong(delAdminId);
                if (adminId != 1490827145L && adminId != 385801672L && adminId != chatId) {
                    Optional<Users> newAdmin = usersRepository.findById(adminId);
                    if (newAdmin.isPresent()) {
                        Users admin = newAdmin.get();
                        if (admin.getIsAdmin()) {
                            admin.setIsAdmin(false);
                            admin.setUpdateAction("Foydalanuvchi " + "<a href=\"tg://user?id=" + admin.getChatId() + "\">" + admin.getNickname() + "</a>" + " dan admin roli " +
                                    "<a href=\"tg://user?id=" + chatId + "\">" + user.get().getNickname() + "</a>" + " tomonidan olindi\n" + "Vaqt: " +
                                    ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime().format(dateTimeFormatter) + "\n\n#admin");
                            admin.setUpdatedAt(ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime());
                            admin.setUpdatedBy(chatId);
                            usersRepository.save(admin);
                            fluentTemplate.sendText("Admin olindi");
                            fluentTemplate.deleteMessage(update.getCallbackQuery().getMessage().getMessageId());
                            fluentTemplate.sendText(admin.getUpdateAction(), -1002577532866L, "HTML");
                            ReplyKeyboardMarkupBuilder builder = new ReplyKeyboardMarkupBuilder();
                            builder.addButton("/start");
                            builder.oneTimeKeyboard(true);
                            builder.resizeKeyboard(true);
                            fluentTemplate.sendText("Sizdan admin roli olib tashlandi!", admin.getId(), builder.build());
                        }
                    }
                }
            } catch (NumberFormatException e) {
                fluentTemplate.sendText("Notog'ri callback -> Admin o'chirish -> callback data =" + data);
            }
        }
    }

    private static @NotNull ReplyKeyboardMarkupBuilder getAdminMarkup() {
        ReplyKeyboardMarkupBuilder builder = new ReplyKeyboardMarkupBuilder();
        builder.oneTimeKeyboard(true);
        builder.resizeKeyboard(true);
        builder.addButton("Admin qo'shish");
        builder.addButton("Admin o'chirish");
        builder.addRow();
        builder.addButton("Odatiy tarifni belgilash");
        builder.addRow();
        builder.addButton("Muddat qushish");
        builder.addButton("Muddat kamaytirish");
        return builder;
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

