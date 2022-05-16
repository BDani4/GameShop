package hu.nye.progkor.gameshop.service;

import java.util.List;

import hu.nye.progkor.gameshop.model.Game;

/**
 * Game Shop Service.
 */
public interface GameShopService {

    List<Game> getAllGames();

    Game getGame(Long id);

    Game createGame(Game game);

    Game updateGame(Long id, Game gameChange);

    void deleteGame(Long id);

}
