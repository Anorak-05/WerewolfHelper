package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.CaptainTieBreakerAction;
import anorak.werewolfhelper.model.actions.VoteCaptainAction;
import anorak.werewolfhelper.model.actions.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectIsCaptain extends Effect {
    EffectIsCaptain(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);


        structure.addAction(this, new CaptainTieBreakerAction());
    }

    @Override
    public void end() {
        super.end();

        IGameEventAction voteCaptainAction = new VoteCaptainAction(structure);
        structure.addAction(voteCaptainAction);
    }
}