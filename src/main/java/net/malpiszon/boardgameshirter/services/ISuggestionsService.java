package net.malpiszon.boardgameshirter.services;

import java.util.List;

import net.malpiszon.boardgameshirter.dtos.SuggestionDto;

public interface ISuggestionsService {

    List<SuggestionDto> getSuggestions();
}
