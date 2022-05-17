package hu.nye.progkor.gameshop;

import hu.nye.progkor.gameshop.model.Game;
import hu.nye.progkor.gameshop.model.GameType;
import hu.nye.progkor.gameshop.model.exception.NotFoundException;
import hu.nye.progkor.gameshop.service.GameShopService;
import hu.nye.progkor.gameshop.service.impl.GameShopServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

@SpringBootTest
class GameShopServiceImplTest {

	private static final Game STARCRAFT = new Game(1L, "Starcraft", 1998, 10, GameType.REAL_TIME_STRATEGY,
					"A futuristic strategy game where you face off against a human or an AI and try to overpower them and win.");
	private static final Game WARCRAFT_3 = new Game(2L, "Warcraft 3", 2002, 30, GameType.REAL_TIME_STRATEGY,
					"A fantasy strategy game where you face off against a human or an AI and try to overpower them and win.");
	private static final Game BIOSHOCK = new Game(3L, "Bioshock", 2007, 20, GameType.FIRST_PERSON_SHOOTER,
					"A retrofuturistic first person shooter with a rich story.");
	private static final Game DOOM_ETERNAL = new Game(4L, "Doom Eternal", 2020, 20, GameType.FIRST_PERSON_SHOOTER,
					"A fast-paced first person shooter with an amazing soundtrack.");
	private static final Game BORDERLANDS_2 = new Game(5L, "Borderlands 2", 2012, 31, GameType.FIRST_PERSON_SHOOTER,
					"A bizarre first person shooter with a unique artstyle.");
	private static final Game NORTHGARD = new Game(6L, "Northgard", 2017, 29, GameType.REAL_TIME_STRATEGY,
					"A medieval strategy game where you face off against a human or an AI and try to overpower them and win.");
	private static final Game AMNESIA_THE_DARK_DESCENT = new Game(7L, "Amnesia: The Dark Descent", 2010, 20, GameType.HORROR,
					"You play as Daniel, who has amnesia, and explore the castle of Brennenburg.");
	private static final Game ALIEN_ISOLATION = new Game(8L, "Alien: Isolation", 2014, 20, GameType.HORROR,
					"You play as Amanda Ripley, and you investigate the disappearance of your mother Ellen Ripley on the ship of Sevastopol.");
	private static final List<Game> GAMES = List.of(
					STARCRAFT,
					WARCRAFT_3,
					BIOSHOCK,
					DOOM_ETERNAL,
					BORDERLANDS_2,
					NORTHGARD,
					AMNESIA_THE_DARK_DESCENT,
					ALIEN_ISOLATION
	);
	public static final long UNKNOWN_GAME_ID = -1L;
	public static final String  DEAD_SPACE_GAME_NAME = "Dead Space";

	private GameShopService underTest;

	@BeforeEach
	void setUp() {
		underTest = new GameShopServiceImpl(GAMES);
	}

	@Test
	void getGameShouldReturnGameWhenGivenIdOfExistingGame() {
		// when
		final Game actual = underTest.getGame(AMNESIA_THE_DARK_DESCENT.getId());
		// then
		assertThat(actual).isEqualTo(AMNESIA_THE_DARK_DESCENT);
	}

	@Test
	void getGameShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingGame() {
		// when then
		assertThrows(NotFoundException.class, () -> underTest.getGame(UNKNOWN_GAME_ID));
	}

	@Test
	void createGameShouldReturnGameWhenDelegateIt() {
		// given
		final Game deadSpaceGame = new Game(null, "Dead Space", 2007, 20, GameType.HORROR,
						"You play as Isaac Clark, a form engineer in this third person viewed horror game");
		final Game expectedDeadSpaceGame= new Game(9L, "Dead Space", 2007, 20, GameType.HORROR,
						"You play as Isaac Clark, a form engineer in this third person viewed horror game");
		// when
		final Game actual = underTest.createGame(deadSpaceGame);
		// then
		assertThat(actual).isEqualTo(expectedDeadSpaceGame);
	}
	@Test
	void updateGameShouldReturnUpdatedGameWhenGivenIdOfExistingGame() {
		// given
		final Game deadSpaceGame = new Game(null, "Dead Space", 2008, 20, GameType.HORROR, "A futuristic horror game with third person camera view where you play as former Engineer Isaac Clarke");
		final Game expectedGame = new Game(AMNESIA_THE_DARK_DESCENT.getId(), "Dead Space", 2008, 20, GameType.HORROR, "A futuristic horror game with third person camera view where you play as former Engineer Isaac Clarke");
		// when
		final Game actual = underTest.updateGame(AMNESIA_THE_DARK_DESCENT.getId(), deadSpaceGame);
		// then
		assertThat(actual).isEqualTo(expectedGame);
	}

	@Test
	void updateGameShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingGame() {
		// given
		final Game deadSpaceGame = new Game(null, "Dead Space", 2008, 20, GameType.HORROR, "A futuristic horror game with third person camera view where you play as former Engineer Isaac Clarke");
		// when then
		assertThrows(NotFoundException.class, () -> underTest.updateGame(UNKNOWN_GAME_ID, deadSpaceGame));
	}
}

