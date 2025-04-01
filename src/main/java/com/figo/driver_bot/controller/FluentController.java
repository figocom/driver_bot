package com.figo.driver_bot.controller;

import com.figo.driver_bot.domain.Plan;
import com.figo.driver_bot.domain.Users;
import com.figo.driver_bot.dto.YandexPlanDto;
import com.figo.driver_bot.repository.PlanRepository;
import com.figo.driver_bot.repository.UsersRepository;
import com.figo.driver_bot.service.YandexService;
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

import static com.figo.driver_bot.util.Util.getAdminMarkup;

@UpdateController
public class FluentController {
    private final FluentTemplate fluentTemplate;
    private final UsersRepository usersRepository;
    private final YandexService yandexService;
    private final PlanRepository planRepository;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public FluentController(FluentTemplate fluentTemplate, UsersRepository usersRepository, YandexService yandexService, PlanRepository planRepository) {
        this.fluentTemplate = fluentTemplate;
        this.usersRepository = usersRepository;
        this.yandexService = yandexService;
        this.planRepository = planRepository;
    }

    @HandleMessage("/start")
    public void start(Update update) {
        User fromUser = update.getMessage().getFrom();
        Long chatId = fromUser.getId();
        Optional<Users> user = usersRepository.findById(chatId);
        if (user.isPresent() && user.get().getAdmin()) {
            fluentTemplate.sendText("Assalomu alaykum. Xush kelibsiz! \nQuyidagi buyruqlardan birini tanlang", getAdminMarkup().build());
        } else if (user.isEmpty()) {
            boolean isAdmin = false;
            String nickname = fromUser.getFirstName();
            if (fromUser.getLastName() != null) {
                nickname = " " + fromUser.getLastName();
            }
            if (chatId.equals(1490827145L) || chatId.equals(385801672L)) {
                isAdmin = true;
                fluentTemplate.sendText("Assalomu alaykum. Xush kelibsiz! \nQuyidagi buyruqlardan birini tanlang", getAdminMarkup().build());
            }
            Users users = new Users();
            users.setId(chatId);
            users.setChatId(String.valueOf(chatId));
            users.setUpdateAction("Yangi foydalanuvchi qo'shildi" + "\n" +
                    "<a href=\"tg://user?id=" + chatId + "\">" + nickname + "</a>" + "\n\n#user");
            users.setNickname(nickname);
            users.setAdmin(isAdmin); // Use setAdmin instead of setIsAdmin
            users.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime());
            users.setUsername(fromUser.getUserName());

            usersRepository.save(users);
            fluentTemplate.sendText(users.getUpdateAction(), -1002577532866L, "HTML");
        }
    }


    @HandleMessage("Admin qo'shish")
    public void addAdmin(Update update) {
        User fromUser = update.getMessage().getFrom();
        Long chatId = fromUser.getId();
        Optional<Users> user = usersRepository.findById(chatId);
        if (user.isPresent() && user.get().getAdmin()) {
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
        if (user.isPresent() && user.get().getAdmin()) {
            if (update.getMessage().hasContact()) {
                Long newAdminId = update.getMessage().getContact().getUserId();
                Optional<Users> newAdmin = usersRepository.findById(newAdminId);
                if (newAdmin.isEmpty()) {
                    fluentTemplate.sendText("Foydalanuvchilar royxatida bunday contact mavjud emas! \nFoydalanuvchi botga start bergan bolishi shart!");
                } else {
                    Users admin = newAdmin.get();
                    if (admin.getAdmin()) {
                        fluentTemplate.sendText("Admin roli allaqachon berilgan");
                    } else {
                        admin.setAdmin(true);
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
        if (user.isPresent() && user.get().getAdmin()) {
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
        if (user.isPresent() && user.get().getAdmin()) {
            String data = update.getCallbackQuery().getData();
            String[] split = data.split(":");
            String delAdminId = split[1];
            try {
                long adminId = Long.parseLong(delAdminId);
                if (adminId != 1490827145L && adminId != 385801672L && adminId != chatId) {
                    Optional<Users> newAdmin = usersRepository.findById(adminId);
                    if (newAdmin.isPresent()) {
                        Users admin = newAdmin.get();
                        if (admin.getAdmin()) {
                            admin.setAdmin(false);
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

    @HandleMessage("Odatiy tarifni belgilash")
    public void choiceCurrentPlan(Update update) {
        User fromUser = update.getMessage().getFrom();
        Long chatId = fromUser.getId();
        Optional<Users> user = usersRepository.findById(chatId);
        if (user.isPresent() && user.get().getAdmin()) {
            List<YandexPlanDto> plans = yandexService.getTariffList();
            plans = plans.stream().filter(YandexPlanDto::isIs_enabled).toList();
            if (!plans.isEmpty()) {
                InlineKeyboardMarkupBuilder builder = new InlineKeyboardMarkupBuilder();
                for (YandexPlanDto plan : plans) {
                    if (plan.isIs_enabled()) {
                        builder.addButton(plan.getName())
                                .callbackData("ap:" + plan.getId());
                        builder.addRow();
                    }
                }
                fluentTemplate.sendText("Tarifni tanlang", builder.build());
            } else {
                fluentTemplate.sendText("Yandex tizimida tarif mavjud emas iltimos yangi tarif qushing");
            }
        }
    }

    @HandleCallback(value = {"ap:"}, match = MatchType.STARTS_WITH)
    public void choiceCurrentPlanProcess(Update update) {
        User fromUser = update.getCallbackQuery().getFrom();
        Long chatId = fromUser.getId();
        Optional<Users> user = usersRepository.findById(chatId);
        if (user.isPresent() && user.get().getAdmin()) {
            String data = update.getCallbackQuery().getData();
            String[] split = data.split(":");
            String planId = split[1];
            if (planId != null) {
                List<YandexPlanDto> plans = yandexService.getTariffList();
                Optional<YandexPlanDto> planDto = plans.stream().filter(yandexPlanDto -> yandexPlanDto.getId().equals(planId)).findFirst();
                if (planDto.isPresent()) {
                    List<Plan> entities = planRepository.findAll(); // Retrieve all entities (for example, from DB)
                    for (Plan entity : entities) {
                        entity.setCurrentActive(false); // Set the currentActive flag
                    }
                    YandexPlanDto yandexPlanDto = planDto.get();
                    Plan plan   = new Plan();
                    plan.setName(yandexPlanDto.getName());
                    plan.setCurrentActive(true);
                    plan.setYandexId(planId);
                    plan.setUpdatedBy(chatId);
                    plan.setUpdatedAt(ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime());
                    plan.setUpdateAction("Doimiy tarif sifatida "+ yandexPlanDto.getName() + " tarifi " +   "<a href=\"tg://user?id=" + chatId + "\">" + user.get().getNickname() + "</a>" + " tomonidan tanlandi\n" + "Vaqt: " +
                            ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime().format(dateTimeFormatter) + "\n\n#tarif");
                    entities.add(plan);
                    planRepository.saveAll(entities);
                    fluentTemplate.deleteMessage(update.getCallbackQuery().getMessage().getMessageId());
                    fluentTemplate.sendText("Doimiy tarif tanlandi");
                    fluentTemplate.sendText(plan.getUpdateAction(), -1002577532866L, "HTML");
                }
            }
        }
    }

}


