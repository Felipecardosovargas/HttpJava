import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HttpRequestGUI extends Application {

    private TextField urlField;
    private ComboBox<String> methodComboBox;
    private TextArea headersArea;
    private TextArea bodyArea;
    private Button sendButton;
    private TextArea responseArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("HTTP Request GUI");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        // URL field
        urlField = new TextField();
        urlField.setPromptText("URL");
        GridPane.setConstraints(urlField, 0, 0);

        // Method combo box
        methodComboBox = new ComboBox<>();
        methodComboBox.getItems().addAll("GET", "POST", "PUT", "DELETE");
        methodComboBox.setValue("GET"); // Default value
        GridPane.setConstraints(methodComboBox, 1, 0);

        // Headers area
        headersArea = new TextArea();
        headersArea.setPromptText("Headers (optional)");
        GridPane.setConstraints(headersArea, 0, 1, 2, 1);

        // Body area
        bodyArea = new TextArea();
        bodyArea.setPromptText("Request Body (optional)");
        GridPane.setConstraints(bodyArea, 0, 2, 2, 1);

        // Send button
        sendButton = new Button("Send Request");
        GridPane.setConstraints(sendButton, 0, 3);

        // Response area
        responseArea = new TextArea();
        responseArea.setEditable(false);
        responseArea.setWrapText(true);
        responseArea.setPromptText("Response");
        GridPane.setConstraints(responseArea, 0, 4, 2, 1);

        grid.getChildren().addAll(urlField, methodComboBox, headersArea, bodyArea, sendButton, responseArea);

        sendButton.setOnAction(e -> {
            sendRequest();
        });

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void sendRequest() {
        // Implementar aqui a lógica para enviar a requisição HTTP com base nos dados fornecidos pelo usuário
        // Você pode usar a biblioteca HttpClient para isso
    }

    public static void main(String[] args) {
        launch(args);
    }
}
