package net.malpiszon.boardgameshirter.controllers;

import java.util.List;
import javax.validation.Valid;

import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;
import net.malpiszon.boardgameshirter.models.Card;
import net.malpiszon.boardgameshirter.models.UserGame;
import net.malpiszon.boardgameshirter.services.CardService;
import net.malpiszon.boardgameshirter.services.UserGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/${api.version}/mygames")
public class UserGameController {

    @Autowired
    private UserGameService service;

    @RequestMapping(method = RequestMethod.GET)
    @PostFilter("hasAuthority(T(net.malpiszon.boardgameshirter.models.Privilege).ADMIN) or filterObject.userName == authentication.name")
    public List<UserGame> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return service.findAll();
    }
}
