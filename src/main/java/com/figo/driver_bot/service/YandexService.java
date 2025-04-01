package com.figo.driver_bot.service;


import com.figo.driver_bot.dto.YandexPlanDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YandexService {
    private final YandexApiClient yandexApiClient;

    public YandexService(YandexApiClient yandexApiClient) {
        this.yandexApiClient = yandexApiClient;
    }

    public List<YandexPlanDto> getTariffList() {
        return yandexApiClient.getCategories();
    }
}
