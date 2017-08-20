package net.malpiszon.boardgameshirter.services;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import net.malpiszon.boardgameshirter.dtos.ShirtDto;
import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;
import net.malpiszon.boardgameshirter.models.Shirt;
import net.malpiszon.boardgameshirter.repositories.ShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShirtService {

    @Autowired
    private ShirtRepository shirtRepository;

    private static Predicate<Shirt> shirtExists(String name) {
        return s -> s.getName().equals(name);
    }

    @Transactional
    public void save(final ShirtDto shirt) throws EntityAlreadyExistsException {
        List<Shirt> existing = shirtRepository.findAll();

        if (existing.stream().filter(shirtExists(shirt.getName())).count() > 0) {
            throw new EntityAlreadyExistsException(String.format("There already exists a shirt type with name '%s'", shirt.getName()));
        }

        shirtRepository.save(new Shirt(shirt.getName(), shirt.getHeight(), shirt.getWidth()));
    }

    public List<ShirtDto> findAll() {
        List<ShirtDto> shirts = Lists.newArrayList();
        shirts.addAll(shirtRepository.findAll().stream().map(s -> new ShirtDto(s.getName(), s.getHeight(), s.getWidth())).collect(Collectors.toList()));

        return shirts;
    }
}
