package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.SavedFromWerewolfKillAction;
import anorak.werewolfhelper.model.base.Effect;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectSaved extends Effect {
    public EffectSaved(Player player, GameStructure structure) {
        super(player, structure);

        structure.addAction(this, new SavedFromWerewolfKillAction(player));
    }
}