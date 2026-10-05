package anorak.werewolfhelper.model.actions.base;

import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

import static anorak.werewolfhelper.model.structure.GamePhase.*;

public enum EAction {
    // PRE NIGHT
    BITTER_OLD_MAN                  (PRE_FIRST_NIGHT),
    THIEF                           (PRE_FIRST_NIGHT),
    ACTOR_SWITCH                    (PRE_FIRST_NIGHT, PRE_NIGHT),
    AMOR                            (PRE_FIRST_NIGHT),
    SEER                            (PRE_FIRST_NIGHT, PRE_NIGHT),
    FOX                             (PRE_FIRST_NIGHT, PRE_NIGHT),
    STUTTERING_JUDGE_CHOOSE_SIGN    (PRE_FIRST_NIGHT),
    TWO_SISTERS                     (PRE_FIRST_NIGHT, PRE_NIGHT),
    THREE_BROTHERS                  (PRE_FIRST_NIGHT, PRE_NIGHT),
    WILD_CHILD_CHOOSE_ROLE_MODEL    (PRE_FIRST_NIGHT),
    MISTRESS                        (PRE_FIRST_NIGHT, PRE_NIGHT),
    SAVIOR                          (PRE_FIRST_NIGHT, PRE_NIGHT),
    DIE_BY_RUSTY_SWORD              (PRE_NIGHT),

    // NIGHT
    WEREWOLF                        (FIRST_NIGHT, NIGHT),
    WOLF_HOUND                      (FIRST_NIGHT),
    WHITE_WOLF                      (NIGHT),
    PRIMAL_WOLF                     (FIRST_NIGHT, NIGHT),
    BIG_BAD_WOLF                    (FIRST_NIGHT, NIGHT),
    SAVIOR_WEREWOLF_KILL_SAVE       (FIRST_NIGHT, NIGHT),
    WITCH                           (FIRST_NIGHT, NIGHT),
    PIED_PIPER                      (FIRST_NIGHT, NIGHT),
    ENCHANTED                       (FIRST_NIGHT, NIGHT),

    // POST NIGHT
    MISTRESS_RETURN_HOME            (POST_FIRST_NIGHT, POST_NIGHT),

    // MORNING
    ANGEL_DEACTIVATE                (MORNING),
    EXECUTE_DEATHS_OF_NIGHT         (MORNING),
    BEAR_LEADER                     (MORNING),
    ANGEL_ACTIVATE                  (MORNING),

    // DISCUSSION
    VOTE_CAPTAIN                    (DISCUSSION),

    // PRE VOTE
    STUTTERING_JUDGE_REMINDER       (PRE_VOTE),
    EXPOSED_FOOL                    (PRE_VOTE),
    SCAPEGOAT_VOTING                (PRE_VOTE),

    // VOTE
    VOTE_LYNCHING                   (VOTE),
    STUTTERING_JUDGE                (VOTE),
    SCAPEGOAT                       (VOTE),
    CAPTAIN_TIEBREAKER              (VOTE),
    FOOL                            (VOTE),
    LYNCHING                        (VOTE),
    DEVOTED_SERVANT                 (VOTE),
    KILL_LYNCH_CANDIDATE            (VOTE),


    // POST VOTE
    CLEAR_VOTES                     (POST_VOTE),
    ACTOR_SWITCH_BACK               (POST_FIRST_NIGHT, POST_NIGHT),

    // PLAYER KILLED
    BASIC_WIN_CHECK                 (PLAYER_KILLED),
    BITTER_OLD_MAN_WIN_CHECK        (PLAYER_KILLED),
    PIED_PIPER_WIN_CHECK            (PLAYER_KILLED),
    WHITE_WOLF_WIN_CHECK            (PLAYER_KILLED),
    WILD_CHILD_TURN_EVIL            (PLAYER_KILLED),
    KILL_REMAINING_LOVER            (PLAYER_KILLED);

    private final List<GamePhase> phases;

    EAction(GamePhase... phases) {
        this.phases = List.of(phases);
    }

    public int getPriority() {
        return ordinal();
    }

    public List<GamePhase> getPhases() {
        return phases;
    }
}