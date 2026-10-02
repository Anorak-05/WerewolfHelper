package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.effects.EffectAngelActive;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class Angel extends Role {
    boolean firstMorning = true;

    public Angel(String name) {
        super(name);
    }

    @Override
    public void setup(Player player, GameStructure structure) {
        super.setup(player, structure);

        structure.addAction(new IGameEventAction() {
            @Override
            public void respondToGameEvent() {
                player.addEffect(new EffectAngelActive(player, structure));
                structure.removeAllActions(this);
            }

            @Override
            public int getPriority() {
                return 0;
            }

            @Override
            public List<GamePhase> getPhases() {
                return List.of(GamePhase.POST_MORNING);
            }
        });

        structure.addAction(new IGameEventAction() {
            @Override
            public void respondToGameEvent() {
                if (firstMorning) {
                    firstMorning = false;
                    return;
                }
                structure.removeAllActions(this);
                player.removeEffect(EffectAngelActive.class);
            }

            @Override
            public int getPriority() {
                return 10;
            }

            @Override
            public List<GamePhase> getPhases() {
                return List.of(GamePhase.PRE_MORNING);
            }
        });
    }

    @Override
    public void end() {
        super.end();

        if (player.hasEffect(EffectAngelActive.class)) {
            new UIDisplayRequest("Angel", "Game ended - Angel won").request();
            GlobalState.getInstance().getGame().endGame();
        }
    }
}