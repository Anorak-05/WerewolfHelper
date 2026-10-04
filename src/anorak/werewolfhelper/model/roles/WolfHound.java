package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class WolfHound extends Role {
    public WolfHound(String name) {
        super(name);
    }

    @Override
    public void setup(Player player, GameStructure structure) {
        super.setup(player, structure);

        structure.addAction(this, new IGameEventAction() {
            @Override
            public void respondToGameEvent() {
                boolean isWolfhoundEvil = new UIBooleanRequest("Wolfhound", "Did the wolfhound wake up together with the Werewolves?").request();

                if (isWolfhoundEvil) {
                    player.addEffect(EEffect.IS_WEREWOLF);
                }
            }

            @Override
            public int getPriority() {
                return 0;
            }

            @Override
            public List<GamePhase> getPhases() {
                return List.of(GamePhase.FIRST_NIGHT);
            }
        });
    }
}
