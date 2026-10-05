package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIMultiplePlayersRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.roles.BitterOldMan;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class BitterOldManAction extends Action {
    GameStructure structure;

    public BitterOldManAction(GameStructure structure) {
        super(EAction.BITTER_OLD_MAN);

        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        List<Player> groupA = new UIMultiplePlayersRequest("BitterOldMan",
                "The Bitter Old Man sees a divided village: who belongs to the one of the two halves?")
                .fromPlayers(GlobalState.getInstance().getGame().getPlayers().excludeDead().get())
                .request();
        boolean bitterOldManInGroupA = groupA.stream().anyMatch(p -> p.getRole().getClass() == BitterOldMan.class);

        List<Player> hatedByBitterOldMan;

        if (bitterOldManInGroupA) {
            hatedByBitterOldMan = GlobalState.getInstance().getGame().getPlayers().excludeDead().get();
            hatedByBitterOldMan.removeAll(groupA);
        } else {
            hatedByBitterOldMan = groupA;
        }

        hatedByBitterOldMan.forEach(p -> p.addEffect(EEffect.HATED_BY_BITTER_OLD_MAN));
    }
}
