package hu.nye.progkor.gameshop.controller;

import java.util.List;

import hu.nye.progkor.gameshop.model.Game;
import hu.nye.progkor.gameshop.model.exception.NotFoundException;
import hu.nye.progkor.gameshop.service.GameShopService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Game Controller Java doc comment.
 */
@Controller
@RequestMapping("/game")
public class GameController {

    private final GameShopService gameShopService;

    public GameController(final GameShopService gameShopService) {
        this.gameShopService = gameShopService;
    }

    /**
     * Lists all the games.
     */
    @GetMapping("/list")
    public String getAllGame(final Model model) {
        final List<Game> games = gameShopService.getAllGames();
        model.addAttribute("games", games);
        return "/game/list";
    }

    /**
     * Lists one game by id.
     */
    @GetMapping("/{id}")
    public String getGame(final Model model, final @PathVariable Long id) {
        final Game game = gameShopService.getGame(id);
        model.addAttribute("game", game);
        return "/game/edit";
    }

    /**
     * Updates an already created game.
     */
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createGame(final Model model,
                             final @RequestParam(value = "id", required = false) Long id,
                             final Game gameChanges) {
        final Game game = gameShopService.updateGame(id, gameChanges);
        model.addAttribute("game", game);
        return "/game/edit";
    }

    /**
     * Creates a new game in the list.
     */
    @PostMapping(value = "/create")
    public String createGame(final Model model, final Game game) {
        final Game savedGame = gameShopService.createGame(game);
        model.addAttribute("game", savedGame);
        return "game/edit";
    }

    /**
     * Creates a new game in the list.
     */
    @GetMapping("/create")
    public String createGamePre(final Model model) {
        return "game/create";
    }

    /**
     * Deletes a game from the list.
     */
    @GetMapping("/{id}/delete")
    public String deleteGame(final Model model, final @PathVariable("id") Long id) {
        try {
            gameShopService.deleteGame(id);
        } catch (NotFoundException e) {
            // Ignored
        }
        final List<Game> games = gameShopService.getAllGames();
        model.addAttribute("games", games);
        return "/game/list";
    }
}
