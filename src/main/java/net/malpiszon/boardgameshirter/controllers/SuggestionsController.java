package net.malpiszon.boardgameshirter.controllers;

import java.util.List;
import javax.validation.Valid;

import net.malpiszon.boardgameshirter.dtos.Suggestion;
import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;
import net.malpiszon.boardgameshirter.models.Card;
import net.malpiszon.boardgameshirter.services.CardService;
import net.malpiszon.boardgameshirter.services.SuggestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/${api.version}/suggestions")
public class SuggestionsController {

    @Autowired
    private SuggestionsService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Suggestion> getSuggestions() {
        return service.getSuggestions();
    }
}
