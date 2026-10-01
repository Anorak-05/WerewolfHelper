package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectRoleModel;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class WildChildChooseRoleModelAction implements IGameEventAction {
    GameStructure structure;

    public WildChildChooseRoleModelAction(GameStructure structure) {
        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        Player roleModel = new UIPlayerRequest("Wild Child", "The Wild Child chooses its role Model").request();

        roleModel.addEffect(new EffectRoleModel(roleModel, structure));
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.PRE_FIRST_NIGHT);
    }
}
