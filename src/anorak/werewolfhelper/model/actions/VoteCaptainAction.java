package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.structure.GameStructure;

public class VoteCaptainAction extends Action {
    GameStructure structure;

    public VoteCaptainAction(GameStructure structure) {
        super(EAction.VOTE_CAPTAIN);

        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        Player captain = new UIPlayerRequest("Captain", "Who is voted as Captain of the village?")
                .fromPlayers(
                        GlobalState.getInstance().getGame().getPlayers().excludeDead().get()
                )
                .request();

        captain.addEffect(EEffect.IS_CAPTAIN);

        structure.removeAllActions(this);
    }
}
