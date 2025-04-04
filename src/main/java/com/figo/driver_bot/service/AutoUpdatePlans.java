package com.figo.driver_bot.service;

import com.figo.driver_bot.domain.Drivers;
import com.figo.driver_bot.dto.GetOneDriverResponse;
import com.figo.driver_bot.repository.DriversRepository;
import org.khasanof.service.template.FluentTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class AutoUpdatePlans {

    private final DriverUpdateService driverUpdateService;
    private final DriversRepository driversRepository;
    private final FluentTemplate fluentTemplate;

    public AutoUpdatePlans(DriverUpdateService driverUpdateService, DriversRepository driversRepository, FluentTemplate fluentTemplate) {
        this.driverUpdateService = driverUpdateService;
        this.driversRepository = driversRepository;
        this.fluentTemplate = fluentTemplate;
    }

    @Scheduled(cron = "0 0 1 * * *", zone = "Asia/Tashkent")
    public void autoUpdatePlans() {
        fluentTemplate.sendText("Muddati tugagan haydovchilarni tarifini ozgartirish ishlari boshlandi! \n\n#start_auto_update", -1002577532866L);
        List<Drivers> driversList = driversRepository.findAll();
        for (Drivers driver : driversList) {
            if(driver.getEndWill().isBefore(ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime())){
                GetOneDriverResponse oneDriver = driverUpdateService.getOneDriver(driver.getDriverId());
                if (oneDriver.getProfile().getWorkStatus().equals("working")){
                    oneDriver.getAccount().setWorkRuleId(driver.getNextPlanId());
                    ResponseEntity<String> updated = driverUpdateService.updateDriverProfile(oneDriver, driver.getDriverId());
                    if (updated.getStatusCode() != HttpStatus.NO_CONTENT) {
                        fluentTemplate.sendText("Haydovchi ismi familyasi: " +oneDriver.getPerson().getFullName().getFirstName() +" "+
                                oneDriver.getPerson().getFullName().getLastName() +" ni \n"+driver.getCurrentPlan() +" tarifidan "
                                +driver.getNextPlan()+ " tarifga o'tkazishda xatolik roy berdi \nPrava raqami : " +driver.getDriverCardNumber()
                                +"\n\nError http from yandex: " + updated.getBody() + "\nError code:"+updated.getStatusCode()+"\n\n #error", -1002577532866L);
                        return;
                    }
                    else {
                        fluentTemplate.sendText("Haydovchi ismi familyasi: " +oneDriver.getPerson().getFullName().getFirstName() +" "+
                                oneDriver.getPerson().getFullName().getLastName() + " ni \n"+driver.getCurrentPlan() +" tarifidan "
                                +driver.getNextPlan()+ " tarifga o'tkazildi \nPrava raqami : " +driver.getDriverCardNumber() +"\n\n#change_plan", -1002577532866L);
                        driversRepository.deleteById(driver.getId());
                    }
                }else {
                    driversRepository.deleteById(driver.getId());
                }
            }
        }

        fluentTemplate.sendText("Muddati tugagan haydovchilarni tarifini ozgartirish ishlari tugadi! \n\n#finish_auto_update", -1002577532866L);
    }
}
