public class SingleResponsibility {

    // Wrong use:

    static class Order { // Principle violation
        int calculateTotalSum() { return 0; }
        String getItem () { return null; }
        int getItemsCount() { return 0; }
        boolean addItem() { return true; }
        boolean removeItem() { return true; }
        void showOneOrder() {}
        void showOrders() {}
        void load() {}
        void save() {}
        void update() {}
        void delete() {}
    }

    // Correct use:

    static class OrderOperations {
        int calculateTotalSum() { return 0; }
        String getItem () { return null; }
        int getItemsCount() { return 0; }
        boolean addItem() { return true; }
        boolean removeItem() { return true; }
    }

    static class OrderRepository {
        void load() {}
        void save() {}
        void update() {}
        void delete() {}
    }

    static class OrderViewer {
        void showOneOrder() {}
        void showOrders() {}
    }

    public static void main(String[] args) {}

}

/*
Anteriormente, a classe Order fazia diversas funções, como gerenciamento de pedidos, impressão de pedidos e operações com os pedidos.
Dessa forma, a classe acaba "perdendo" sua responsabilidade única e precisa dar suporte para outras funções. Com a correção, agora
a classe foi divida em 3 outras, cada uma tendo sua responsabiliade única
 */
