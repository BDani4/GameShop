package hu.nye.progkor.GameShop.controller;

import hu.nye.progkor.GameShop.model.Game;
import hu.nye.progkor.GameShop.model.exception.NotFoundException;
import hu.nye.progkor.GameShop.service.GameShopService;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/game")
public class GameController {

    private final GameShopService gameShopService;

    public GameController(final GameShopService gameShopService) {
        this.gameShopService = gameShopService;
    }

    @GetMapping
    public String getAllGame(final Model model) {
        final List<Game> games = gameShopService.getAllGames();
        model.addAttribute("games", games);
        return "/game/list";
    }

    @GetMapping("/{id}")
    public String getGame(final Model model, final @PathVariable Long id) {
        final Game game = gameShopService.getGame(id);
        model.addAttribute("game", game);
        return "/game/edit";
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createGame(final Model model,
                             final @RequestParam(value = "id", required = false) Long id,
                             final Game gameChanges){

        final Game game = gameShopService.updateGame(id, gameChanges);
        model.addAttribute("game", game);
        return "/game/edit";
    }

    @GetMapping("/create")
    public String createGamePre(final Model model){
        return "game/create";
    }

    @PostMapping("/create")
    public String createGame(final Model model, final Game game){
        final Game savedGame = gameShopService.createGame(game);
        model.addAttribute("game", savedGame);
        return "game/edit";
    }

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
