package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.controller.base.requests.UIMultiplePlayersRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class PiedPiperAction implements IGameEventAction {
    GameStructure structure;

    public PiedPiperAction(GameStructure structure) {
        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        List<Player> notYetEnchanted = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .excludeHasEffect(EEffect.ENCHANTED)
                .get();
        List<Player> toEnchant = new UIMultiplePlayersRequest("PiedPiper", "Who does the Pied Piper enchant tonight?")
                .fromPlayers(notYetEnchanted)
                .setMin(2)
                .setMax(2)
                .request();

        new UIDisplayRequest("PiedPiper", "Touch the enchanted players " + toEnchant).request();

        toEnchant.forEach(p -> p.addEffect(EEffect.ENCHANTED));
    }

    @Override
    public int getPriority() {
        return 50;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.FIRST_NIGHT, GamePhase.NIGHT);
    }
}
