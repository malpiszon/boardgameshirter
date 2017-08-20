package net.malpiszon.boardgameshirter.controllers;

import java.util.List;

import net.malpiszon.boardgameshirter.dtos.SuggestionDto;
import net.malpiszon.boardgameshirter.services.SuggestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/${api.version}/suggestions")
public class SuggestionsController {

    @Autowired
    private SuggestionsService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<SuggestionDto> getSuggestions() {
        return service.getSuggestions();
    }
}
