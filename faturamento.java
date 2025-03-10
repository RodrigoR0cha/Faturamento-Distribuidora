import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FaturamentoDistribuidora {
    public static void main(String[] args) {
        try {
            // Carregar dados do arquivo JSON
            String conteudo = new String(Files.readAllBytes(Paths.get("faturamento.json")));
            JSONArray dados = new JSONArray(conteudo);

            double menorFaturamento = Double.MAX_VALUE;
            double maiorFaturamento = Double.MIN_VALUE;
            double somaFaturamento = 0;
            int diasComFaturamento = 0;
            int diasAcimaDaMedia = 0;

            // Processar os dados
            for (int i = 0; i < dados.length(); i++) {
                JSONObject dia = dados.getJSONObject(i);
                double faturamento = dia.getDouble("faturamento");

                if (faturamento > 0) { // Ignorar dias sem faturamento
                    if (faturamento < menorFaturamento) {
                        menorFaturamento = faturamento;
                    }
                    if (faturamento > maiorFaturamento) {
                        maiorFaturamento = faturamento;
                    }
                    somaFaturamento += faturamento;
                    diasComFaturamento++;
                }
            }

            // Calcular a média
            double mediaFaturamento = somaFaturamento / diasComFaturamento;

            // Contar os dias acima da média
            for (int i = 0; i < dados.length(); i++) {
                JSONObject dia = dados.getJSONObject(i);
                double faturamento = dia.getDouble("faturamento");

                if (faturamento > mediaFaturamento) {
                    diasAcimaDaMedia++;
                }
            }

            // Exibir os resultados
            System.out.println("Menor faturamento: " + menorFaturamento);
            System.out.println("Maior faturamento: " + maiorFaturamento);
            System.out.println("Dias com faturamento acima da média: " + diasAcimaDaMedia);
        } catch (Exception e) {
            System.out.println("Erro ao processar os dados: " + e.getMessage());
        }
    }
}
