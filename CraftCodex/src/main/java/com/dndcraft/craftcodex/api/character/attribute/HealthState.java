package com.dndcraft.craftcodex.api.character.attribute;

public enum HealthState {

    HEALTHY, // HP = 100
    MINOR_INJURY, // HP <= 99  HP >= 80
    MODERATE_INJURY, // HP <=79 HP >= 60
    SEVERE_INJURY, // HP <= 59 HP >= 40
    CRITICAL_INJURY, //HP <= 40 HP != 0
    BROKEN, // HP = 0

}
