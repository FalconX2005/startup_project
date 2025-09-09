package uz.pdp.startupproject.tgbot;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class TelegramBotService extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(TelegramBotService.class);

    private final PasswordResetService passwordResetService;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.username}")
    private String botUsername;

    private final Map<Long, ResetSession> userState = new ConcurrentHashMap<>();

    private enum State { WAITING_FOR_USERNAME, DONE }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) return;

        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        if ("/start".equals(text)) {
            sendMessage(chatId, "Parolni tiklash uchun username kiriting:");
            userState.put(chatId, new ResetSession(State.WAITING_FOR_USERNAME));
            return;
        }

        ResetSession session = userState.get(chatId);
        if (session != null && session.state == State.WAITING_FOR_USERNAME) {
            try {
                String code = passwordResetService.startReset(text, chatId);
                session.state = State.DONE;
                sendMessage(chatId, "Parolni tiklash kodingiz: *" + code + "*\nIltimos, uni web-saytda kiriting.");
            } catch (UsernameNotFoundException e) {
                log.warn("Foydalanuvchi topilmadi: {}", text);
                sendMessage(chatId, "Foydalanuvchi topilmadi.");
            }
        }
    }

    public void sendPasswordChangedMessage(Long chatId) {
        sendMessage(chatId, "✅ Parolingiz muvaffaqiyatli o‘zgartirildi.");
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage msg = new SendMessage(String.valueOf(chatId), text);
        msg.setParseMode("Markdown");
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            log.error("Xabar yuborishda xatolik: ", e);
        }
    }

    @Override
    public String getBotUsername() { return botUsername; }

    @Override
    public String getBotToken() { return botToken; }

    static class ResetSession {
        State state;
        ResetSession(State state) { this.state = state; }
    }
}
