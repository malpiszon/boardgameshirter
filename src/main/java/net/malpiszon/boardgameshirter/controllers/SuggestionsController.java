package net.malpiszon.boardgameshirter.controllers;

import java.util.List;

import net.malpiszon.boardgameshirter.dtos.SuggestionDto;
import net.malpiszon.boardgameshirter.services.ISuggestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/${api.version}/suggestions")
public class SuggestionsController {

    @Autowired
    private ISuggestionsService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<SuggestionDto> getSuggestions() {
        return service.getSuggestions();
    }
}
