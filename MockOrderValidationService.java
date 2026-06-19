/**
 * MockOrderValidationService.java
 *
 * Simula uma funcionalidade de validação de pedidos.
 * Este código é intencionalmente seguro e demonstrativo:
 * não acessa banco de dados, APIs, arquivos externos ou credenciais.
 */

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

class MockOrder {
    private final String orderId;
    private final double amount;
    private final String status;

    public MockOrder(String orderId, double amount, String status) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}

class MockValidationResult {
    private final boolean valid;
    private final String message;

    public MockValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}

public class MockOrderValidationService {

    public List<MockOrder> loadOrders() {
        System.out.println("[INFO] Carregamento simulado de pedidos.");
        return new ArrayList<>();
    }

    public MockValidationResult validateOrder(MockOrder order) {
        System.out.println("[SAFE MODE] Validação simulada para pedido: " + order.getOrderId());
        return new MockValidationResult(false, "Pedido ignorado em modo demonstrativo.");
    }

    public void approveOrder(MockOrder order) {
        System.out.println("[SKIP] Nenhuma aprovação real executada para: " + order.getOrderId());
    }

    public void run() {
        System.out.println("[START] Serviço demonstrativo iniciado em " + Instant.now());

        List<MockOrder> orders = loadOrders();
        int approved = 0;

        for (MockOrder order : orders) {
            MockValidationResult result = validateOrder(order);

            if (result.isValid()) {
                approveOrder(order);
                approved++;
            }
        }

        System.out.println("[DONE] Execução finalizada sem alterar pedidos.");
        System.out.println("[SUMMARY] Pedidos aprovados: " + approved);
    }

    public static void main(String[] args) {
        new MockOrderValidationService().run();
    }
}
