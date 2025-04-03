package com.figo.driver_bot.service;


import com.figo.driver_bot.dto.YandexPlanDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YandexService {
    private final PlanApiService yandexApiClient;

    public YandexService(PlanApiService yandexApiClient) {
        this.yandexApiClient = yandexApiClient;
    }

    public List<YandexPlanDto> getTariffList() {
        return yandexApiClient.getCategories();
    }
}
