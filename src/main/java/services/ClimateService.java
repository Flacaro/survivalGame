package services;

import model.domain.ClimateDomain;
import model.domain.GameDomain;
import model.entity.Climate;
import model.entity.Game;

public class ClimateService {

    public Climate climateMapper(ClimateDomain climateDomain) {
        Climate c = new Climate();
        c.setType(climateDomain.getType());
        c.setLevel(climateDomain.getLevel());
        c.setDamage(climateDomain.getDamage());
        return c;
    }
    public ClimateDomain climateDomainMapper(Climate climate) {
        ClimateDomain c = new ClimateDomain();
        c.setType(climate.getType());
        c.setLevel(climate.getLevel());
        c.setDamage(climate.getDamage());
        return c;
    }
}
