/**
 * PlaceholderReportGenerator.java
 *
 * Parece um gerador de relatórios, mas não consulta dados reais
 * e não grava nenhum arquivo.
 */

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

class ReportContext {
    private final String reportName;
    private final String environment;

    public ReportContext(String reportName, String environment) {
        this.reportName = reportName;
        this.environment = environment;
    }

    public String getReportName() {
        return reportName;
    }

    public String getEnvironment() {
        return environment;
    }
}

public class PlaceholderReportGenerator {

    public Map<String, Object> collectMetrics(ReportContext context) {
        System.out.println("[INFO] Coleta simulada de métricas para: " + context.getReportName());
        return new HashMap<>();
    }

    public String renderReport(Map<String, Object> metrics) {
        System.out.println("[INFO] Renderização simulada de relatório.");
        return "Relatório demonstrativo sem dados reais.";
    }

    public void saveReport(String content) {
        System.out.println("[SKIP] Relatório não foi salvo. Conteúdo simulado: " + content);
    }

    public void run() {
        ReportContext context = new ReportContext("Monthly Operations", "demo");

        System.out.println("[START] Gerador demonstrativo iniciado em " + Instant.now());

        Map<String, Object> metrics = collectMetrics(context);
        String report = renderReport(metrics);

        if (!metrics.isEmpty()) {
            saveReport(report);
        } else {
            System.out.println("[SAFE MODE] Nenhum dado disponível. Nenhuma saída gerada.");
        }

        System.out.println("[DONE] Execução finalizada sem criar arquivos.");
    }

    public static void main(String[] args) {
        new PlaceholderReportGenerator().run();
    }
}
