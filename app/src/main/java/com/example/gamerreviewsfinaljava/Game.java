package com.example.gamerreviewsfinaljava;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Parcelable {
    private String title;
    private int imageResId;
    private String releaseDate;
    private String developer;
    private String publisher;
    private String description;

    // Constructor
    public Game(String title, int imageResId, String releaseDate, String developer, String publisher, String description) {
        this.title = title;
        this.imageResId = imageResId;
        this.releaseDate = releaseDate;
        this.developer = developer;
        this.publisher = publisher;
        this.description = description;
    }

    // Parcel constructor
    protected Game(Parcel in) {
        title = in.readString();
        imageResId = in.readInt();
        releaseDate = in.readString();
        developer = in.readString();
        publisher = in.readString();
        description = in.readString();
    }

    // Parcelable boilerplate
    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }
        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(imageResId);
        dest.writeString(releaseDate);
        dest.writeString(developer);
        dest.writeString(publisher);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Getters
    public String getTitle()       { return title; }
    public int    getImageResId()  { return imageResId; }
    public String getReleaseDate() { return releaseDate; }
    public String getDeveloper()   { return developer; }
    public String getPublisher()   { return publisher; }
    public String getDescription() { return description; }

    // For backward compatibility
    public String getName() { return title; }

    // Static factory
    public static List<Game> getGame() {
        List<Game> games = new ArrayList<>();
        games.add(new Game("The Witcher 3", R.drawable.witcher3, "May 19, 2015", "CD Projekt Red", "CD Projekt", "An open-world action RPG."));
        games.add(new Game("Red Dead Redemption 2", R.drawable.rdr2, "October 26, 2018", "Rockstar Games", "Rockstar Games", "An epic tale of life in America’s unforgiving heartland."));
        games.add(new Game("Cyberpunk 2077", R.drawable.cyberpunk2077, "December 10, 2020", "CD Projekt Red", "CD Projekt", "An open-world RPG set in the futuristic Night City."));
        games.add(new Game("Elden Ring", R.drawable.eldenring, "February 25, 2022", "FromSoftware", "Bandai Namco", "A vast open-world RPG with deep lore and challenging gameplay."));
        games.add(new Game("Dark Souls 3", R.drawable.darksouls3, "March 24, 2016", "FromSoftware", "Bandai Namco", "A dark fantasy action RPG with punishing difficulty."));
        games.add(new Game("God of War Ragnarok", R.drawable.godofwarragnarok, "November 9, 2022", "Santa Monica Studio", "Sony Interactive Entertainment", "Kratos and Atreus face the end of the world in Norse mythology."));
        games.add(new Game("Horizon Forbidden West", R.drawable.horizonfw, "February 18, 2022", "Guerrilla Games", "Sony Interactive Entertainment", "Aloy continues her journey in a lush post-apocalyptic world."));
        games.add(new Game("The Last of Us Part II", R.drawable.tlou2, "June 19, 2020", "Naughty Dog", "Sony Interactive Entertainment", "A deeply emotional post-apocalyptic narrative-driven game."));
        games.add(new Game("Ghost of Tsushima", R.drawable.ghostoftsushima, "July 17, 2020", "Sucker Punch Productions", "Sony Interactive Entertainment", "A samurai adventure set in feudal Japan."));
        games.add(new Game("Bloodborne", R.drawable.bloodborne, "March 24, 2015", "FromSoftware", "Sony Computer Entertainment", "A gothic horror action RPG with Lovecraftian themes."));
        games.add(new Game("Sekiro: Shadows Die Twice", R.drawable.sekiro, "March 22, 2019", "FromSoftware", "Activision", "A fast-paced samurai action game with challenging combat."));
        games.add(new Game("Assassin's Creed Valhalla", R.drawable.acvalhalla, "November 10, 2020", "Ubisoft Montreal", "Ubisoft", "Lead Viking raids and build your settlement in medieval England."));
        games.add(new Game("Far Cry 6", R.drawable.farcry6, "October 7, 2021", "Ubisoft Toronto", "Ubisoft", "Fight against a ruthless dictator in a tropical open world."));
        games.add(new Game("Resident Evil Village", R.drawable.re8, "May 7, 2021", "Capcom", "Capcom", "Survival horror game featuring vampires and werewolves."));
        games.add(new Game("Doom Eternal", R.drawable.doometernal, "March 20, 2020", "id Software", "Bethesda Softworks", "Fast-paced demon-slaying first-person shooter."));
        games.add(new Game("Metro Exodus", R.drawable.metroexodus, "February 15, 2019", "4A Games", "Deep Silver", "Survive in a post-apocalyptic Russia filled with dangers."));
        games.add(new Game("Final Fantasy VII Remake", R.drawable.ff7remake, "April 10, 2020", "Square Enix", "Square Enix", "A reimagining of the classic RPG with modern graphics and gameplay."));
        games.add(new Game("Persona 5 Royal", R.drawable.p5r, "October 31, 2019", "Atlus", "Atlus", "A stylish RPG about high school students changing the world."));
        games.add(new Game("Monster Hunter: World", R.drawable.mhw, "January 26, 2018", "Capcom", "Capcom", "Hunt massive creatures in a vibrant ecosystem."));
        games.add(new Game("Death Stranding", R.drawable.deathstranding, "November 8, 2019", "Kojima Productions", "Sony Interactive Entertainment", "A unique open-world adventure about reconnecting America."));
        games.add(new Game("Marvel’s Spider-Man", R.drawable.spiderman, "September 7, 2018", "Insomniac Games", "Sony Interactive Entertainment", "Swing through New York City as the iconic superhero."));
        games.add(new Game("Halo Infinite", R.drawable.haloinfinite, "December 8, 2021", "343 Industries", "Xbox Game Studios", "A new chapter in Master Chief’s legendary journey."));
        games.add(new Game("Gears 5", R.drawable.gears5, "September 10, 2019", "The Coalition", "Xbox Game Studios", "A third-person shooter featuring intense squad-based combat."));
        return games;
    }
    }

