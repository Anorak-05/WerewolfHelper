package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectInLove;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class AmorAction implements IGameEventAction {
    GameStructure structure;

    public AmorAction(GameStructure structure) {
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

        loverA.addEffect(new EffectInLove(loverA, structure, loverB));
        loverB.addEffect(new EffectInLove(loverB, structure, loverA));

        new UIDisplayRequest("Amor", "Touch the Players that are in love").request();
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.FIRST_NIGHT);
    }
}
