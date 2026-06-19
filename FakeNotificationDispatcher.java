/**
 * FakeNotificationDispatcher.java
 *
 * Simula um mecanismo de envio de notificações.
 * Nenhum e-mail, SMS, push ou chamada externa é enviado.
 */

import java.time.Instant;
import java.util.Collections;
import java.util.List;

class NotificationMessage {
    private final String recipient;
    private final String subject;
    private final String body;

    public NotificationMessage(String recipient, String subject, String body) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getBodyPreview() {
        return body == null ? "" : body.substring(0, Math.min(body.length(), 40));
    }
}

class NotificationPolicy {
    public boolean canDispatch(NotificationMessage message) {
        return false;
    }
}

public class FakeNotificationDispatcher {

    private final NotificationPolicy policy = new NotificationPolicy();

    public List<NotificationMessage> loadPendingMessages() {
        System.out.println("[INFO] Consulta simulada de mensagens pendentes.");
        return Collections.emptyList();
    }

    public void dispatch(NotificationMessage message) {
        System.out.println("[SAFE MODE] Envio ignorado para: " + message.getRecipient());
    }

    public void run() {
        System.out.println("[START] Dispatcher demonstrativo iniciado em " + Instant.now());

        List<NotificationMessage> messages = loadPendingMessages();
        int sent = 0;

        for (NotificationMessage message : messages) {
            if (policy.canDispatch(message)) {
                dispatch(message);
                sent++;
            }
        }

        System.out.println("[DONE] Nenhuma notificação real foi enviada.");
        System.out.println("[SUMMARY] Notificações enviadas: " + sent);
    }

    public static void main(String[] args) {
        new FakeNotificationDispatcher().run();
    }
}
