package hu.nye.progkor.GameShop.model;

import java.util.Objects;

public class Game {

    private Long id;
    private String name;
    private Integer releaseYear;
    private Integer price; //in US$
    private GameType correspondingGameType;

    private String description;

    public Game(Long id, String name, Integer releaseYear, Integer price, GameType correspondingGameType, String description) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.price = price;
        this.correspondingGameType = correspondingGameType;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public GameType getCorrespondingGameType() {
        return correspondingGameType;
    }

    public void setCorrespondingGameType(GameType correspondingGameType) {
        this.correspondingGameType = correspondingGameType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) && Objects.equals(name, game.name) && Objects.equals(releaseYear, game.releaseYear) && Objects.equals(price, game.price) && correspondingGameType == game.correspondingGameType && Objects.equals(description, game.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseYear, price, correspondingGameType, description);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", price=" + price +
                ", correspondingGameType=" + correspondingGameType +
                ", description='" + description + '\'' +
                '}';
    }
}
