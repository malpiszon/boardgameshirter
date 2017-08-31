package net.malpiszon.boardgameshirter.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import net.malpiszon.boardgameshirter.dtos.CardDto;
import net.malpiszon.boardgameshirter.services.impls.CardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CardController.class)
@WithMockUser
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService service;

    @Test
    public void givenCard_whenFindAll_thenReturnJsonArray() throws Exception {
        CardDto cardDto = new CardDto(20, 10);

        List<CardDto> cards = Collections.singletonList(cardDto);

        given(service.findAll()).willReturn(cards);

        mockMvc.perform(get("/api/v1/card")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].height", is(cardDto.getHeight())))
                .andExpect(jsonPath("$[0].width", is(cardDto.getWidth())));
    }
}