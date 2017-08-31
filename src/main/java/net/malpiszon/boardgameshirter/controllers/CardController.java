package net.malpiszon.boardgameshirter.controllers;

import java.util.List;

import net.malpiszon.boardgameshirter.dtos.CardDto;
import net.malpiszon.boardgameshirter.services.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/${api.version}/card")
public class CardController {

    @Autowired
    private ICardService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<CardDto> findAll() {
        return service.findAll();
    }
}
