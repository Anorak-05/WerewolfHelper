package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.structure.GameStructure;

public class AmorAction extends Action {
    GameStructure structure;

    public AmorAction(GameStructure structure) {
        super(EAction.AMOR);

        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        Player loverA = new UIPlayerRequest("Amor", "Amor chooses the first Player of the Couple")
                .fromPlayers(GlobalState.getInstance().getGame().getPlayers()
                        .excludeDead()
                        .get())
                .request();
        Player loverB = new UIPlayerRequest("Amor", "Amor chooses the second Player of the Couple")
                .fromPlayers(GlobalState.getInstance().getGame().getPlayers()
                        .excludeDead()
                        .excludePlayer(loverA)
                        .get())
                .request();

        loverA.addEffect(EEffect.IN_LOVE);
        loverB.addEffect(EEffect.IN_LOVE);

        new UIDisplayRequest("Amor", "Touch the Players that are in love: " + loverA + ", " + loverB).request();
    }
}
