package hu.nye.progkor.gameshop.controller;

import java.util.List;

import hu.nye.progkor.gameshop.model.Game;
import hu.nye.progkor.gameshop.service.GameShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Game Rest Controller.
 */
@RestController
@RequestMapping("/api/v1/game")
public class GameRestController {

    private final GameShopService gameShopService;

    public GameRestController(GameShopService gameShopService) {
        this.gameShopService = gameShopService;
    }

    /**
     * Javadoc comment.
     */
    @GetMapping
    public List<Game> getAllGames() {
        return gameShopService.getAllGames();
    }

    /**
     * Javadoc comment.
     */
    @GetMapping("/{id}")
    Game game(final @PathVariable("id") Long id) {
        return gameShopService.getGame(id);
    }

    /**
     * Javadoc comment.
     */
    @PostMapping
    Game createGame(final @RequestBody Game game) {
        return gameShopService.createGame(game);
    }

    /**
     * Javadoc comment.
     */
    @PutMapping("/{id}")
    Game updateGame(final @PathVariable Long id, final @RequestBody Game gameChange) {
        return gameShopService.updateGame(id, gameChange);
    }

    /**
     * Javadoc comment.
     */
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteGame(final @PathVariable Long id) {
        gameShopService.deleteGame(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
