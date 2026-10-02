package anorak.werewolfhelper;

import anorak.werewolfhelper.logging.ILogger;
import anorak.werewolfhelper.model.Game;
import anorak.werewolfhelper.model.Player;

import static anorak.werewolfhelper.model.base.ERole.*;

public class Main {

    public static void main(String[] args) {
        Game game = GlobalState.getInstance().getGame();
        ILogger logger = GlobalState.getInstance().getLogger();

        game.addPlayer(new Player(VILLAGER.create(), "V1"));
        game.addPlayer(new Player(VILLAGER.create(), "V2"));
        game.addPlayer(new Player(VILLAGER.create(), "V3"));
        game.addPlayer(new Player(VILLAGER.create(), "V4"));
        game.addPlayer(new Player(VILLAGER.create(), "V5"));
        game.addPlayer(new Player(WEREWOLF.create(), "W"));
        game.addPlayer(new Player(ANGEL.create(), "A"));

//        game.addPlayer(new Player(new Villager(), "Nils"));
//        game.addPlayer(new Player(new Villager(), "Tom"));
//        game.addPlayer(new Player(new Villager(), "Tim"));
//        game.addPlayer(new Player(new Witch(), "Struppi"));
//        game.addPlayer(new Player(new Werewolf(), "Hans"));
//        game.addPlayer(new Player(new Werewolf(), "Heinz"));
//        game.addPlayer(new Player(new Seer(), "Fay"));
//        game.addPlayer(new Player(new Hunter(), "Lennard"));

        //game.startFromUI();
        game.start();

        //System.out.println(logger.getLogs());
    }
}