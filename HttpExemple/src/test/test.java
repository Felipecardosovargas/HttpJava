package com.httpexamples.test;

import com.httpexamples.PostRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostRequestTest {

    @Test
    public void testPostRequest() {
        // Verifica se a URL de postagem está correta
        assertEquals("http://httpbin.org/forms/post", PostRequest.URL_POST);

        // Verifica se o arquivo JSON existe
        assertNotNull(PostRequest.FILE_JSON);

        // Teste adicional pode ser feito para verificar se a resposta da solicitação é esperada
    }
}
