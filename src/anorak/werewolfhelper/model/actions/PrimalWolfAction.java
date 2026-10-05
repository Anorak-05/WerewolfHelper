package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.Arrays;
import java.util.List;

public class PrimalWolfAction extends Action {
    int transformations = 1;
    GameStructure structure;

    public PrimalWolfAction(GameStructure structure) {
        super(EAction.PRIMAL_WOLF);

        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        if (transformations > 0) {
            List<Player> deadPlayers = GlobalState.getInstance().getGame().getPlayers()
                    .excludeDead()
                    .includeHasEffect(EEffect.KILLED_BY_WEREWOLF)
                    .get();

            new UIDisplayRequest("PrimalWolf","These players were killed by Werewolves tonight: " + Arrays.deepToString(deadPlayers.toArray())).request();

            if (new UIBooleanRequest("PrimalWolf", "Does the Primal Wolf want to convert one of the werewolf's victims?").request()) {
                Player toConvert = new UIPlayerRequest("PrimalWolf", "Who does the Primal Wolf convert?").request();
                toConvert.addEffect(EEffect.IS_WEREWOLF);
                new UIDisplayRequest("PrimalWolf", "Touch the converted Player").request();
            }
        }
    }
}