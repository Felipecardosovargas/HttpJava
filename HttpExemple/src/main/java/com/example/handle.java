import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequestGUI extends Application {

    // Outros componentes UI

    private TextArea responseArea;

    // ...

    private void sendRequest() {
        String url = urlField.getText();
        String method = methodComboBox.getValue();
        String headers = headersArea.getText();
        String body = bodyArea.getText();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method(method, HttpRequest.BodyPublishers.ofString(body));

        // Adiciona cabeçalhos, se existirem
        if (!headers.isBlank()) {
            String[] headerLines = headers.split("\n");
            for (String headerLine : headerLines) {
                String[] parts = headerLine.split(":");
                if (parts.length == 2) {
                    requestBuilder.header(parts[0].trim(), parts[1].trim());
                }
            }
        }

        HttpRequest request = requestBuilder.build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(this::handleResponse)
                .join();
    }

    private void handleResponse(String responseBody) {
        // Exemplo de manipulação da resposta JSON usando a biblioteca Jackson
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            // Exibe a resposta JSON de forma amigável na área de resposta
            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
            responseArea.setText(prettyJson);
        } catch (IOException e) {
            e.printStackTrace();
            responseArea.setText("Erro ao analisar a resposta JSON.");
        }
    }

    // Outros métodos

    // ...

    public static void main(String[] args) {
        launch(args);
    }
}
