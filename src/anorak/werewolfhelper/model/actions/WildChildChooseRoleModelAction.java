package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.roles.WildChild;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class WildChildChooseRoleModelAction extends Action {
    GameStructure structure;

    public WildChildChooseRoleModelAction(GameStructure structure) {
        super(EAction.WILD_CHILD_CHOOSE_ROLE_MODEL);

        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        Player roleModel = new UIPlayerRequest("Wild Child", "The Wild Child chooses its role Model")
                .fromPlayers(
                        GlobalState.getInstance().getGame().getPlayers()
                                .excludeDead()
                                .excludeHasRole(WildChild.class)
                                .get()
                )
                .request();

        roleModel.addEffect(EEffect.ROLE_MODEL);
    }
}
