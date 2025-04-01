package com.figo.driver_bot.handler;


import org.khasanof.annotation.ExceptionController;
import org.khasanof.annotation.exception.HandleException;
import org.khasanof.service.template.FluentTemplate;
import org.springframework.web.client.HttpStatusCodeException;

@ExceptionController
public class ExceptionHandler {
    private final FluentTemplate fluentTemplate;

    public ExceptionHandler(FluentTemplate fluentTemplate) {
        this.fluentTemplate = fluentTemplate;
    }

    @HandleException(HttpStatusCodeException.class)
    public void handleException(final HttpStatusCodeException e) {
        fluentTemplate.sendText("Error http from yandex: "+e.getResponseBodyAsString() + "\n\n #error", -1002577532866L);
    }
}
