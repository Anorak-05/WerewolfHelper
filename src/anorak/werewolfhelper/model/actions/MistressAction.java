package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class MistressAction implements IGameEventAction {
    Player player;
    GameStructure structure;

    Player previouslySleptWith;

    public MistressAction(Player player, GameStructure structure) {
        this.player = player;
        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        Player sleepWith = new UIPlayerRequest("Mistress", "Where does the Mistress sleep tonight?")
                .fromPlayers(GlobalState.getInstance().getGame().getPlayers()
                        .excludeDead()
                        .excludePlayer(previouslySleptWith)
                        .get())
                .request();

        if (sleepWith.hasEffect(EEffect.IS_WEREWOLF)) {
            player.addEffect(EEffect.KILLED_BY_WEREWOLF);
        }

        if (sleepWith != player) {
            player.addEffect(EEffect.NOT_HOME);
        }
    }

    @Override
    public int getPriority() {
        return 120;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.PRE_FIRST_NIGHT, GamePhase.PRE_NIGHT);
    }
}
