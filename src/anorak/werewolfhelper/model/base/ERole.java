package anorak.werewolfhelper.model.base;

import anorak.werewolfhelper.model.roles.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum ERole {
    ACTOR("Actor", Actor::new),
    AMOR("Amor", Amor::new),
    BEAR_LEADER("Bear Leader",BearLeader::new),
    BIG_BAD_WOLF("Big Bad Wolf", BigBadWolf::new),
    CLEAN_SOUL("Clean Soul", CleanSoul::new),
    DEVOTED_SERVANT("Devoted Servant", DevotedServant::new),
    FOOL("Fool", Fool::new),
    FOX("Fox", Fox::new),
    HUNTER("Hunter", Hunter::new),
    KNIGHT_WITH_THE_RUSTY_SWORD("Knight With The Rusty Sword", KnightWithTheRustySword::new),
    LITTLE_GIRL("Little Girl", LittleGirl::new),
    OLD_ONE("Old One", OldOne::new),
    PRIMAL_WOLF("Primal Wolf", PrimalWolf::new),
    SAVIOR("Savior", Savior::new),
    SCAPEGOAT("Scapegoat", Scapegoat::new),
    SEER("Seer", Seer::new),
    STUTTERING_JUDGE("Stuttering Judge", StutteringJudge::new),
    THIEF("Thief", Thief::new),
    THREE_BROTHERS("Three Brothers", ThreeBrothers::new),
    TWO_SISTERS("Two Sisters", TwoSisters::new),
    VILLAGER("Villager", Villager::new),
    WEREWOLF("Werewolf", Werewolf::new),
    WHITE_WOLF("White Wolf", WhiteWolf::new),
    WILD_CHILD("Wild Child", WildChild::new),
    WITCH("Witch", Witch::new),
    WOLF_HOUND("Wolfhound", WolfHound::new);

    private final String name;
    private final Function<String, Role> factory;

    ERole(String name, Function<String, Role> factory) {
        this.name = name;
        this.factory = factory;
    }

    public Role create() {
        return factory.apply(name);
    }

    public String getName() {
        return name;
    }

    public static Role getRoleByName(String roleName) {
        ERole role = Arrays.stream(ERole.values())
                .filter(r -> r.name.trim().equalsIgnoreCase(roleName.trim()))
                .findFirst().orElse(null);
        if (role == null) return null;

        return role.create();
    }

    public static List<String> getAllRoleNames() {
        return Arrays.stream(ERole.values()).map(r -> r.name).toList();
    }

    @Override
    public String toString() {
        return name;
    }
}