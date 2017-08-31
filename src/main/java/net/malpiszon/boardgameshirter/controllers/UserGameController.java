package net.malpiszon.boardgameshirter.controllers;

import java.util.List;

import net.malpiszon.boardgameshirter.dtos.UserGameDto;
import net.malpiszon.boardgameshirter.services.IUserGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/${api.version}/mygames")
public class UserGameController {

    @Autowired
    private IUserGameService service;

    @RequestMapping(method = RequestMethod.GET)
    @PostFilter("hasAuthority(T(net.malpiszon.boardgameshirter.models.Privilege).ADMIN) or filterObject.userName == authentication.name")
    public List<UserGameDto> findAll() {
        return service.findAll();
    }
}
