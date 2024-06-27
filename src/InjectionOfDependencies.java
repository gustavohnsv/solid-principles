public class InjectionOfDependencies {

    // Wrong use:

    static class EmailService {
        public void sendEmail(String to, String subject, String body) {
            System.out.format("Sending email to %s: %s -> %s\n", to, subject, body);
        }
    }

    static class SMSService {
        public void sendSMS(String to, String subject, String body) {
            System.out.format("Sending SMS to %s: %s -> %s\n", to, subject, body);
        }
    }

    static class NotificationService {
        private final EmailService emailService = new EmailService(); // Principle violation
        private final SMSService smsService = new SMSService(); // Principle violation

        public void sendNotification(String to, String subject, String body, String type) {
            if (type.equals("email")) {
                emailService.sendEmail(to, subject, body);
            } else {
                smsService.sendSMS(to, subject, body);
            }
        }
    }

    // Correct use:

    interface MessageService {
        void sendMessage(String to, String subject, String body);
    }

    static class EmailServiceWithInterface implements MessageService {
        @Override
        public void sendMessage(String to, String subject, String body) {}
    }

    static class SMSServiceWithInterface implements MessageService {
        @Override
        public void sendMessage(String to, String subject, String body) {}
    }

    static class NotificationServiceWithInterface {
        private final MessageService messageService;

        public NotificationServiceWithInterface(MessageService messageService) {
            this.messageService = messageService;
        }
        public void sendMessage(String to, String subject, String body) {
            messageService.sendMessage(to, subject, body);
        }
    }


    public static void main(String[] args) {}
}

/*
Anteriormente, a classe NotificationService dependia de duas classes, a classe EmailService e de SMSService, o que dificulta a manunteção
e os testes, pois precisamos instaciar sempre as duas classes, mesmo que não usamos. Com a correção, agora a classe NotificationServiceWithInterface
terá um MessageService, que serve tanto para 'email' quanto para 'sms'. Dessa forma, os testes ficam mais fáceis e a manuntenção também, pois agora a
minha classe não depende de outras duas obrigatoriamente
 */