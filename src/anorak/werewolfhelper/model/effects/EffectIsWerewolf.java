package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.WerewolfAction;
import anorak.werewolfhelper.model.base.Effect;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectIsWerewolf extends Effect {

    public EffectIsWerewolf(Player player, GameStructure structure) {
        super(player, structure);

        structure.addAction(this, new WerewolfAction());
    }
}
