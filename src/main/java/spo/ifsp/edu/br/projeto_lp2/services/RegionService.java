package spo.ifsp.edu.br.projeto_lp2.services;

import org.springframework.stereotype.Service;
import spo.ifsp.edu.br.projeto_lp2.models.Location;
import spo.ifsp.edu.br.projeto_lp2.models.Region;

@Service
public class RegionService {
    public Region getRegionByLocation(Location location) {
        return switch (location.getState().toUpperCase()) {
            case "ACRE", "AMAZONAS", "AMAPÁ", "PARÁ", "RONDÔNIA", "RORAIMA", "TOCANTINS" -> Region.NORTH;
            case "ALAGOAS", "BAHIA", "CEARÁ", "MARANHÃO", "PARAÍBA", "PERNAMBUCO", "PIAUÍ", "RIO GRANDE DO NORTE", "SERGIPE" ->
                    Region.NORTHEAST;
            case "DISTRITO FEDERAL", "GOIÁS", "MATO GROSSO", "MATO GROSSO DO SUL" -> Region.MIDWEST;
            case "ESPÍRITO SANTO", "MINAS GERAIS", "RIO DE JANEIRO", "SÃO PAULO" -> Region.SOUTHEAST;
            case "PARANÁ", "RIO GRANDE DO SUL", "SANTA CATARINA" -> Region.SOUTH;
            default -> null;
        };
    }

}
