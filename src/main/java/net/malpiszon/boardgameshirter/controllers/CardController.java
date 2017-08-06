package net.malpiszon.boardgameshirter.controllers;

import java.util.List;
import javax.validation.Valid;

import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;
import net.malpiszon.boardgameshirter.models.Card;
import net.malpiszon.boardgameshirter.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/${api.version}/card")
public class CardController {

    @Autowired
    private CardService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Card> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('T(net.malpiszon.boardgameshirter.models.Privilege).ADMIN')")
    public void createCardType(@RequestBody @Valid final Card card) throws EntityAlreadyExistsException {
        service.save(card);
    }
}
