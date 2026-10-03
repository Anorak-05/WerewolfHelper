package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectIsCaptain;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class VoteCaptainAction implements IGameEventAction {
    GameStructure structure;

    public VoteCaptainAction(GameStructure structure) {
        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        Player captain = new UIPlayerRequest("Captain", "Who is voted as Captain of the village?")
                .fromPlayers(
                        GlobalState.getInstance().getGame().getPlayers().excludeDead().get()
                )
                .request();

        captain.addEffect(new EffectIsCaptain(captain, structure));

        structure.removeAllActions(this);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.PRE_DISCUSSION);
    }
}
