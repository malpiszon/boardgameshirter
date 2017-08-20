package net.malpiszon.boardgameshirter.controllers;

import java.util.List;
import javax.validation.Valid;

import net.malpiszon.boardgameshirter.dtos.ShirtDto;
import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;
import net.malpiszon.boardgameshirter.services.ShirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/${api.version}/shirt")
public class ShirtController {

    @Autowired
    private ShirtService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<ShirtDto> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority(T(net.malpiszon.boardgameshirter.models.Privilege).ADMIN)")
    public void createShirt(@RequestBody @Valid final ShirtDto shirt) throws EntityAlreadyExistsException {
        service.save(shirt);
    }
}
