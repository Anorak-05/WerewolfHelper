package anorak.werewolfhelper.model.structure;

public enum GamePhase {
    PRE_FIRST_NIGHT(1),
    FIRST_NIGHT(2),
    POST_FIRST_NIGHT(3),
    PRE_MORNING(4),
    MORNING(5),
    POST_MORNING(6),
    PRE_DISCUSSION(7),
    DISCUSSION(8),
    POST_DISCUSSION(9),
    PRE_VOTE(10),
    VOTE(11),
    POST_VOTE(12),
    PRE_NIGHT(13),
    NIGHT(14),
    POST_NIGHT(3),
    PLAYER_KILLED(-1);

    final int nextPhaseIndex;

    GamePhase(int nextPhaseIndex) {
        this.nextPhaseIndex = nextPhaseIndex;
    }

    public GamePhase next() {
        return GamePhase.values()[nextPhaseIndex];
    }

    public static GamePhase getFirst() {
        return PRE_FIRST_NIGHT;
    }
}
