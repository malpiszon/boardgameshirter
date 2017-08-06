package net.malpiszon.boardgameshirter.controllers;

import java.util.List;
import javax.validation.Valid;

import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;
import net.malpiszon.boardgameshirter.models.Game;
import net.malpiszon.boardgameshirter.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/${api.version}/game")
public class GameController {

    @Autowired
    private GameService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Game> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('T(net.malpiszon.boardgameshirter.models.Privilege).ADMIN')")
    public void createGame(@RequestBody @Valid final Game game) throws EntityAlreadyExistsException {
        service.save(game);
    }
}
