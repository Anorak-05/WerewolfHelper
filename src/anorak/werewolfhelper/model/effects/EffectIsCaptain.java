package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.CaptainTieBreakerAction;
import anorak.werewolfhelper.model.actions.VoteCaptainAction;
import anorak.werewolfhelper.model.base.Effect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectIsCaptain extends Effect {
    public EffectIsCaptain(Player player, GameStructure structure) {
        super(player, structure);

        structure.addAction(this, new CaptainTieBreakerAction());
    }

    @Override
    public void end() {
        super.end();

        IGameEventAction voteCaptainAction = new VoteCaptainAction(structure);
        structure.addAction(voteCaptainAction);
    }
}