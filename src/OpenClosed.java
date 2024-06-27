public class OpenClosed {

    // Wrong use:

    static class OldProduct {
        private final String name;
        private final double price;
        private final String discount;

        public OldProduct(String name, double price, String discount) {
            this.name = name;
            this.price = price;
            this.discount = discount;
        }

        public double getPrice() { // Principle violation
            if (discount.isEmpty()) {
                return price;
            } else if (discount.equals("seasonal")) {
                return price * 0.9;
            } else if (discount.equals("weekly")) {
                return price * 0.3;
            }
            return price;
        }
    }

    // Correct use:

    interface DiscountStrategy {
        double applyDiscount(double price);
    }

    static class SeasonalStrategy implements DiscountStrategy {
        @Override
        public double applyDiscount(double price) {
            return price * 0.9;
        }
    }

    static class WeeklyStrategy implements DiscountStrategy {
        @Override
        public double applyDiscount(double price) {
            return price * 0.3;
        }
    }

    //    Possible new discount type
    //    static class MonthlyStrategy implements DiscountStrategy {
    //        @Override
    //        public double applyDiscount(double price) {
    //            return price * 0.5;
    //        }
    //    }

    static class NewProduct {
        private final String name;
        private final double price;
        private final DiscountStrategy discountStrategy;

        NewProduct(String name, double price, DiscountStrategy discountStrategy) {
            this.name = name;
            this.price = price;
            this.discountStrategy = discountStrategy;
        }

        public double getPrice() {
                return discountStrategy.applyDiscount(price);
        }
    }

    public static void main(String[] args) {}

}
/*
Anteriormente, a classe OldProduct possuia uma implementação inicial para receber o preço calculando o desconto. Porém,
caso quisermos adicionar um novo desconto, teriamos que modificar o método de aplicar disconto. Pensando em um código curto
não parece ser ruim, mas pensando em um código extenso, isso poderia ser muito trabalhoso e ineficiente. Com a correção,
agora possuimos uma nova classe NewProduct e recebemos a interface DiscountStrategy, que possui o método de
aplicar desconto, e, possíveis novos discontos podem ser implementados estendendo a classe, sem que seja
necessário modificar a classe NewProduct
 */