package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectIsWerewolf;
import anorak.werewolfhelper.model.effects.EffectKilledByWerewolf;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.Arrays;
import java.util.List;

public class PrimalWolfAction implements IGameEventAction {
    int transformations = 1;
    GameStructure structure;

    public PrimalWolfAction(GameStructure structure) {
        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        if (transformations > 0) {
            List<Player> deadPlayers = GlobalState.getInstance().getGame().getPlayers()
                    .excludeDead()
                    .includeHasEffect(EffectKilledByWerewolf.class)
                    .get();

            new UIDisplayRequest("PrimalWolf","These players were killed by Werewolves tonight: " + Arrays.deepToString(deadPlayers.toArray())).request();

            if (new UIBooleanRequest("PrimalWolf", "Does the Primal Wolf want to convert one of the werewolf's victims?").request()) {
                Player toConvert = new UIPlayerRequest("PrimalWolf", "Who does the Primal Wolf convert?").request();
                toConvert.addEffect(new EffectIsWerewolf(toConvert, structure));
                new UIDisplayRequest("PrimalWolf", "Touch the converted Player").request();
            }
        }
    }

    @Override
    public int getPriority() {
        return 20;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.FIRST_NIGHT, GamePhase.NIGHT);
    }
}