package com.dndcraft.gaia.command;

import com.dndcraft.atlas.command.CommandTemplate;
import com.dndcraft.gaia.api.GaiaAPI;

public class BaseCommand extends CommandTemplate {
    protected GaiaAPI instance = GaiaAPI.getApi();
}
