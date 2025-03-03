package com.company;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotOptions;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

@Service
public class MyBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String USERNAME;

    @Value("${telegram.bot.token}")
    private String TOKEN;

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String text = message.getText();

        System.out.println(chatId);
        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(chatId)
                .text(text)
                .build();

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

//    @Scheduled(fixedRate = 2000)
//    @Scheduled(cron = "0 59 * * * *")
//    @Scheduled(cron = "12 30 5 5 6 *")
//    public void sendMessage() {
//        SendMessage sendMessage = SendMessage.builder()
//                .chatId(1174220995L)
//                .text("Hello").build();
//
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }


}
