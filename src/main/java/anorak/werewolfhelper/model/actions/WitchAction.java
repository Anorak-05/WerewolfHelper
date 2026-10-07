package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;

import java.util.Arrays;
import java.util.List;

public class WitchAction extends Action {

    private int numHealing = 1;
    private int numKilling = 1;

    public WitchAction() {
        super(EAction.WITCH);
    }

    @Override
    public void respondToGameEvent() {
        List<Player> dyingPlayers = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .includeHasEffect(EEffect.KILLED_BY_WEREWOLF)
                .get();

        if (!dyingPlayers.isEmpty() && numHealing > 0) {
           new UIDisplayRequest("Witch","Dies sind die Toten: " + Arrays.deepToString(dyingPlayers.toArray())).request();

            if (new UIBooleanRequest("Witch", "Möchte die Hexe die heute jemanden heilen?").request()) {
                numHealing--;

                Player toHeal = new UIPlayerRequest("witch","Wähle einen Toten")
                        .fromPlayers(dyingPlayers)
                        .request();
                toHeal.removeEffect(EEffect.KILLED_BY_WEREWOLF);
            }
        }

        if (numKilling > 0) {
            if (new UIBooleanRequest("Witch", "Möchte die Hexe jemanden töten?").request()) {
                numKilling--;
                Player toKill = new UIPlayerRequest("Witch","Wähle dein Opfer")
                        .fromPlayers(
                                GlobalState.getInstance().getGame().getPlayers()
                                        .excludeDead()
                                        .get()
                        )
                        .request();

                toKill.addEffect(EEffect.KILLED_BY_WITCH_POISON);
            }
        }
    }
}