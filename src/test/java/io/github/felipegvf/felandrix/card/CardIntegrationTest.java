package io.github.felipegvf.felandrix.card;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.server.core.TypeReferences;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CardIntegrationTest {

    private static final String POSTGRES_DOCKER_IMAGE = "postgres:14.1-alpine";
    private static final String BASE_PATH = "http://localhost:";

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(POSTGRES_DOCKER_IMAGE);

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    @Order(1)
    public void shouldAddCard() throws Exception {
        var request = post("/api/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "card_name": "XP",
                          "card_status": true,
                          "card_due_day": 27
                        }
                        """);
        mockMvc.perform(request).andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void shouldReturnOneCardAndUpdateItsName() throws Exception {

        TypeReferences.CollectionModelType<CardDTO> collectionModelType = new TypeReferences.CollectionModelType<>() {
        };
        TypeReferences.EntityModelType<CardDTO> entityModelType = new TypeReferences.EntityModelType<>() {
        };

        var traverson = new Traverson(URI.create(BASE_PATH + port + "/api/"), MediaTypes.HAL_JSON);
        CollectionModel<CardDTO> cards = traverson.follow("cards").toObject(collectionModelType);

        if (cards != null) {
            var content = cards.getContent().stream().toList();

            Assertions.assertEquals("XP", content.get(0).getCardName());
            Assertions.assertEquals(27, content.get(0).getCardDueDay());
            Assertions.assertTrue(content.get(0).isCardStatus());

            URI uri = URI.create(content.get(0).getLink().getCard().getHref());

            var request = patch(uri.getPath())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                            {
                              "card_name": "XP1"
                            }
                            """);
            mockMvc.perform(request).andExpect(status().isNoContent());
            traverson = new Traverson(uri, MediaTypes.HAL_JSON);
            EntityModel<CardDTO> card = traverson.follow().toObject(entityModelType);
            System.out.println(card);

        }

    }

}
