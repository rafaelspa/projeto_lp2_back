package spo.ifsp.edu.br.projeto_lp2.utils;

import spo.ifsp.edu.br.projeto_lp2.models.Region;

import java.util.ArrayList;
import java.util.List;

public class RegionUtil {
    public static List<Region> getRegionsByString(String regions) {
        List<Region> regionsList = new ArrayList<>();
        if (regions != null) {
            String[] regionsArray = regions.split(",");
            for (String region : regionsArray) {
                regionsList.add(Region.valueOf(region.toUpperCase()));
            }
        }
        return regionsList;
    }
}
