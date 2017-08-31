package net.malpiszon.boardgameshirter.controllers;

import java.util.List;
import javax.validation.Valid;

import net.malpiszon.boardgameshirter.dtos.GameDto;
import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;
import net.malpiszon.boardgameshirter.exceptions.NoSuchEntityException;
import net.malpiszon.boardgameshirter.services.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/${api.version}/game")
public class GameController {

    @Autowired
    private IGameService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<GameDto> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority(T(net.malpiszon.boardgameshirter.models.Privilege).ADMIN)")
    public void createGame(@RequestBody @Valid final GameDto game) throws EntityAlreadyExistsException, NoSuchEntityException {
        service.save(game);
    }
}
