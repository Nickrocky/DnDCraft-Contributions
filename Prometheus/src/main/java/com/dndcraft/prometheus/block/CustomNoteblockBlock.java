package com.dndcraft.prometheus.block;

import lombok.RequiredArgsConstructor;
import org.bukkit.Instrument;

@RequiredArgsConstructor
public enum CustomNoteblockBlock {

    AA(Instrument.BANJO, 0, false),
    AB(Instrument.BANJO, 0, true),
    AC(Instrument.BANJO, 1, false),
    AD(Instrument.BANJO, 1, true),
    AE(Instrument.BANJO, 2, false),
    AF(Instrument.BANJO, 2, true),
    AG(Instrument.BANJO, 3, false),
    AH(Instrument.BANJO, 3, true),
    AI(Instrument.BANJO, 4, false),
    AJ(Instrument.BANJO, 4, true),
    AK(Instrument.BANJO, 5, false),
    AL(Instrument.BANJO, 5, true),
    AM(Instrument.BANJO, 6, false),
    AN(Instrument.BANJO, 6, true),
    AO(Instrument.BANJO, 7, false),
    AP(Instrument.BANJO, 7, true),
    AQ(Instrument.BANJO, 8, false),
    AR(Instrument.BANJO, 8, true),
    AS(Instrument.BANJO, 9, false),
    AT(Instrument.BANJO, 9, true),
    AU(Instrument.BANJO, 10, false),
    AV(Instrument.BANJO, 10, true),
    AW(Instrument.BANJO, 11, false),
    AX(Instrument.BANJO, 11, true),
    AY(Instrument.BANJO, 12, false),
    AZ(Instrument.BANJO, 12, true),
    BA(Instrument.BANJO, 13, false),
    BB(Instrument.BANJO, 13, true),
    BC(Instrument.BANJO, 14, false),
    BD(Instrument.BANJO, 14, true),
    BE(Instrument.BANJO, 15, false),
    BF(Instrument.BANJO, 15, true),
    BG(Instrument.BANJO, 16, false),
    BH(Instrument.BANJO, 16, true),
    BI(Instrument.BANJO, 17, false),
    BJ(Instrument.BANJO, 17, true),
    BK(Instrument.BANJO, 18, false),
    BL(Instrument.BANJO, 18, true),
    BM(Instrument.BANJO, 19, false),
    BN(Instrument.BANJO, 19, true),
    BO(Instrument.BANJO, 20, false),
    BP(Instrument.BANJO, 20, true),
    BQ(Instrument.BANJO, 21, false),
    BR(Instrument.BANJO, 21, true),
    BS(Instrument.BANJO, 22, false),
    BT(Instrument.BANJO, 22, true),
    BU(Instrument.BANJO, 21, false),
    BV(Instrument.BANJO, 21, true),
    BW(Instrument.BANJO, 22, false),
    BX(Instrument.BANJO, 22, true),
    BY(Instrument.BANJO, 23, false),
    BZ(Instrument.BANJO, 23, true),
    CA(Instrument.BANJO, 24, false),
    CB(Instrument.BANJO, 24, true),
    ;

    private final Instrument instrument;
    private final int note;
    private final boolean isPowered;

}
