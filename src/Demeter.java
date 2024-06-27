public class Demeter {

    // Wrong use:

    static class OldInformation {
        private final String firstNotSecretPromise;
        private final String secondNotSecretPromise;
        private final String firstSecretPromise;
        private final String secondSecretPromise;

        public OldInformation(String firstNotSecretPromise, String secondNotSecretPromise, String firstSecretPromise, String secondSecretPromise) {
            this.firstNotSecretPromise = firstNotSecretPromise;
            this.secondNotSecretPromise = secondNotSecretPromise;
            this.firstSecretPromise = firstSecretPromise;
            this.secondSecretPromise = secondSecretPromise;
        }
        public String getFirstNotSecretPromise() {
            return this.firstNotSecretPromise;
        }
        public String getSecondNotSecretPromise() {
            return this.secondNotSecretPromise;
        }
        public String getFirstSecret() {
            return this.firstSecretPromise;
        }
        public String getSecondSecret() {
            return this.secondSecretPromise;
        }
    }

    static class OldHuman {
        private final String name;
        private final OldInformation oldInformation;

        OldHuman(String name, OldInformation oldInformation) {
            this.name = name;
            this.oldInformation = oldInformation;
        }
        OldInformation getOldInformation() {
            return oldInformation;
        }
    }

    void oldShowHumanAllSecrets(OldHuman oldHuman) {
        String firstNotSecret = oldHuman.getOldInformation().getFirstNotSecretPromise();
        String secondNotSecret = oldHuman.getOldInformation().getSecondNotSecretPromise();
        System.out.println("First notSecret: " + firstNotSecret);
        System.out.println("Second notSecret: " + secondNotSecret);
    }

    // Correct use:

    static class NewInformation {
        private final String firstNotSecretPromise;
        private final String secondNotSecretPromise;
        private final String firstSecretPromise;
        private final String secondSecretPromise;

        NewInformation(String firstNotSecretPromise, String secondNotSecretPromise, String firstSecretPromise, String secondSecretPromise) {
            this.firstNotSecretPromise = firstNotSecretPromise;
            this.secondNotSecretPromise = secondNotSecretPromise;
            this.firstSecretPromise = firstSecretPromise;
            this.secondSecretPromise = secondSecretPromise;
        }
        String getFirstNotSecretPromise() {
            return this.firstNotSecretPromise;
        }
        String getSecondNotSecretPromise() {
            return this.secondNotSecretPromise;
        }
        String getFirstSecret() {
            return this.firstSecretPromise;
        }
        String getSecondSecret() {
            return this.secondSecretPromise;
        }
    }

    static class NewHuman {
        private final String name;
        private final NewInformation newInformation;
        NewHuman(String name, NewInformation newInformation) {
            this.name = name;
            this.newInformation = newInformation;
        }
        String getFirstNotSecretPromise() {
            return this.newInformation.getFirstNotSecretPromise();
        }
        String getSecondNotSecretPromise() {
            return this.newInformation.getSecondNotSecretPromise();
        }
    }

    void newShowHumanAllSecrets(NewHuman newHuman) {
        String firstNotSecret = newHuman.getFirstNotSecretPromise();
        String secondNotSecret = newHuman.getSecondNotSecretPromise();
        System.out.println("First notSecret: " + firstNotSecret);
        System.out.println("Second notSecret: " + secondNotSecret);
    }

    public static void main(String[] args) {}

}
/*
Anteriormente, possuimos duas classes principais: OldInformation e OldHuman, cada uma com seus respectivos campos e métodos.
O problema começa a aparecer quando avançamos para oldShowALlHumanSecrets, pois para obtermos os dois campos, precisamos
ter conhecimento da classe inteira. Isso gera um acoplamento muito forte, tornando o código suscetível a erros. Com a
correção, temos agora as classes NewInformation e NewHuman, cada uma com seus respectivos campos e métodos. Em contrapartida,
agora quando desejarmos obter os campos "não secretos", poderemos utilizar os métodos diretamente da classe NewHuman, sem que
seja necessário na função NewShowHumanAllSecrets, ter conhecimento sobre a classe inteira, diminuindo o acoplamento
 */