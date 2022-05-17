package hu.nye.progkor.gameshop.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.nye.progkor.gameshop.model.Game;
import hu.nye.progkor.gameshop.model.GameType;
import hu.nye.progkor.gameshop.model.exception.NotFoundException;
import hu.nye.progkor.gameshop.service.GameShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Game Shop Service Implementation.
 */
@Service
public class GameShopServiceImpl implements GameShopService {

    private static final List<Game> DATA_BASE = new ArrayList<>();

    @Autowired
    public GameShopServiceImpl() {
        DATA_BASE.add(new Game(1L, "Starcraft", 1998, 10, GameType.REAL_TIME_STRATEGY,
                "A futuristic strategy game where you face off against a human or an AI and try to overpower them and win."));
        DATA_BASE.add(new Game(2L, "Warcraft 3", 2002, 30, GameType.REAL_TIME_STRATEGY,
                "A fantasy strategy game where you face off against a human or an AI and try to overpower them and win."));
        DATA_BASE.add(new Game(3L, "Bioshock", 2007, 20, GameType.FIRST_PERSON_SHOOTER,
                "A retrofuturistic first person shooter with a rich story."));
        DATA_BASE.add(new Game(4L, "Doom Eternal", 2020, 20, GameType.FIRST_PERSON_SHOOTER,
                "A fast-paced first person shooter with an amazing soundtrack."));
        DATA_BASE.add(new Game(5L, "Borderlands 2", 2012, 31, GameType.FIRST_PERSON_SHOOTER,
                "A bizarre first person shooter with a unique artstyle."));
        DATA_BASE.add(new Game(6L, "Northgard", 2017, 29, GameType.REAL_TIME_STRATEGY,
                "A medieval strategy game where you face off against a human or an AI and try to overpower them and win."));
        DATA_BASE.add(new Game(7L, "Amnesia: The Dark Descent", 2010, 20, GameType.HORROR,
                "You play as Daniel, who has amnesia, and explore the castle of Brennenburg."));
        DATA_BASE.add(new Game(8L, "Alien: Isolation", 2014, 20, GameType.HORROR,
                "You play as Amanda Ripley, and you investigate the disappearance of your mother Ellen Ripley on the ship of Sevastopol."));
        }

    public GameShopServiceImpl(final List<Game> games) {
        DATA_BASE.addAll(games);
    }

    @Override
    public List<Game> getAllGames() {
        return Collections.unmodifiableList(DATA_BASE);
    }

    @Override
    public Game getGame(final Long id) {
        return DATA_BASE.stream()
                .filter(game -> game.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Game createGame(final Game game) {
        game.setId(getNextId());
        DATA_BASE.add(game);
        return game;
    }

    @Override
    public Game updateGame(final Long id, Game gameChange) {
        final Game game = getGame(id);
        game.setName(gameChange.getName());
        game.setReleaseYear(gameChange.getReleaseYear());
        game.setPrice(gameChange.getPrice());
        game.setCorrespondingGameType(gameChange.getCorrespondingGameType());
        game.setDescription(gameChange.getDescription());
        return game;
    }

    @Override
    public void deleteGame(final Long id) {
        final Game game = getGame(id);
        DATA_BASE.remove(game);
    }

    private long getNextId() {
        return getLastId() + 1L;
    }

    private long getLastId() {
        return DATA_BASE.stream()
                .mapToLong(Game::getId)
                .max()
                .orElse(0);
    }
}
