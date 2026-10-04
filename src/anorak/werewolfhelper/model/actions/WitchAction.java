package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectKilledByWerewolf;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.Arrays;
import java.util.List;

public class WitchAction implements IGameEventAction {

    private int numHealing = 1;
    private int numKilling = 1;

    @Override
    public void respondToGameEvent() {
        List<Player> dyingPlayers = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .includeHasEffect(EffectKilledByWerewolf.class)
                .get();

        if (!dyingPlayers.isEmpty() && numHealing > 0) {
           new UIDisplayRequest("witch","Dies sind die Toten: " + Arrays.deepToString(dyingPlayers.toArray())).request();

            if (new UIBooleanRequest("witch", "Möchte die Hexe die heute jemanden heilen?").request()) {
                numHealing--;

                Player toHeal = new UIPlayerRequest("witch","Wähle einen Toten")
                        .fromPlayers(dyingPlayers)
                        .request();
                toHeal.getEffect(EffectKilledByWerewolf.class).end();
            }
        }

        if (numKilling > 0) {
            if (new UIBooleanRequest("witch", "Möchte die Hexe jemanden töten?").request()) {
                numKilling--;
                Player toKill = new UIPlayerRequest("witch","Wähle dein Opfer")
                        .fromPlayers(
                                GlobalState.getInstance().getGame().getPlayers().get()
                        )
                        .request();

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