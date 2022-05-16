package hu.nye.progkor.GameShop.controller;

import hu.nye.progkor.GameShop.model.Game;
import hu.nye.progkor.GameShop.service.GameShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/game")
public class GameRestController {

    private final GameShopService gameShopService;

    public GameRestController(GameShopService gameShopService) {
        this.gameShopService = gameShopService;
    }

    @GetMapping
    public List<Game> getAllGames(){
        return gameShopService.getAllGames();
    }

    @GetMapping("/{id}")
    Game game(final @PathVariable("id") Long id){
        return gameShopService.getGame(id);
    }

    @PostMapping
    Game createGame(final @RequestBody Game game){
        return gameShopService.createGame(game);
    }

    @PutMapping("/{id}")
    Game updateGame(final @PathVariable Long id,final @RequestBody Game gameChange){
        return gameShopService.updateGame(id,gameChange);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteGame(final @PathVariable Long id){
        gameShopService.deleteGame(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
