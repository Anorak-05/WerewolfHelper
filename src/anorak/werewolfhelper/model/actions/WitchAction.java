package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectKilledByWerewolf;

import java.util.Arrays;
import java.util.List;

public class WitchAction implements IGameEventAction {
    private final Player player;

    private int numHealing = 1;
    private int numKilling = 1;

    public WitchAction(Player player) {
        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        if (player.hasEffect(EffectKilledByWerewolf.class)) return;

        List<Player> deadPlayers = GlobalState.getInstance().getGame().getPlayersByEffect(EffectKilledByWerewolf.class);

        if (!deadPlayers.isEmpty() && numHealing > 0) {
           new UIDisplayRequest("witch","Dies sind die Toten: " + Arrays.deepToString(deadPlayers.toArray())).request();

            if (new UIBooleanRequest("witch", "Möchte die Hexe die heute jemanden heilen?").request()) {
                numHealing--;

                // TODO: Spieler muss tot sein -> muss EffectKilledByWerewolf besitzen
                Player toHeal = new UIPlayerRequest("witch","Wähle einen Toten", List.of(EffectKilledByWerewolf.class)).request();
                toHeal.getEffect(EffectKilledByWerewolf.class).end();
            }
        }

        if (numKilling > 0) {
            if (new UIBooleanRequest("witch", "Möchte die Hexe jemanden töten?").request()) {
                numKilling--;
                Player toKill = new UIPlayerRequest("witch","Wähle dein Opfer").request();

                toKill.die();
            }
        }
    }

    @Override
    public int getPriority() {
        return 40;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.FIRST_NIGHT, GamePhase.NIGHT);
    }
}