package hu.nye.progkor.GameShop.service;

import hu.nye.progkor.GameShop.model.Game;
import java.util.List;

public interface GameShopService {

    List<Game> getAllGames();

    Game getGame(Long id);

    Game createGame(Game game);

    Game updateGame(Long id, Game gameChange);

    void deleteGame(Long id);

}
