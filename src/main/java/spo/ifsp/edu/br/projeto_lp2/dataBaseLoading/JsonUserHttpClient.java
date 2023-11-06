package spo.ifsp.edu.br.projeto_lp2.dataBaseLoading;

import spo.ifsp.edu.br.projeto_lp2.models.User;
import spo.ifsp.edu.br.projeto_lp2.utils.JsonUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class JsonUserHttpClient {
    private final HttpClient httpClient;

    public JsonUserHttpClient() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    public List<User> getUsers() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://storage.googleapis.com/juntossomosmais-code-challenge/input-backend.json"))
                .header("accept", "app")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        List<User> users = JsonUtil.getUsersFromJson(response.body());
        return users;
    }
}
