package spo.ifsp.edu.br.projeto_lp2.services;

import org.springframework.stereotype.Service;
import spo.ifsp.edu.br.projeto_lp2.models.Coordinate;
import spo.ifsp.edu.br.projeto_lp2.models.UserType;

@Service
public class UserTypeService {

    private static final float SPECIAL_MIN_LON1 = -2.196998f;
    private static final float SPECIAL_MIN_LAT1 = -46.361899f;
    private static final float SPECIAL_MAX_LON1 = -15.411580f;
    private static final float SPECIAL_MAX_LAT1 = -34.276938f;
    private static final float SPECIAL_MIN_LON2 = -19.766959f;
    private static final float SPECIAL_MIN_LAT2 = -52.997614f;
    private static final float SPECIAL_MAX_LON2 = -23.966413f;
    private static final float SPECIAL_MAX_LAT2 = -44.428305f;
    private static final float NORMAL_MIN_LON = -26.155681f;
    private static final float NORMAL_MIN_LAT = -54.777426f;
    private static final float NORMAL_MAX_LON = -34.016466f;
    private static final float NORMAL_MAX_LAT = -46.603598f;


    public UserType getUserType(Coordinate coordinate) {
        var lat = coordinate.getLatitude();
        var lon = coordinate.getLongitude();
        var specialLonCondition1 = lon > SPECIAL_MAX_LON1 && lon < SPECIAL_MIN_LON1;
        var specialLonCondition2 = lon > SPECIAL_MAX_LON2 && lon < SPECIAL_MIN_LON2;
        var specialLatCondition1 = lat < SPECIAL_MAX_LAT1 && lat > SPECIAL_MIN_LAT1;
        var specialLatCondition2 = lat < SPECIAL_MAX_LAT2 && lat > SPECIAL_MIN_LAT2;

        if ((specialLonCondition1 && specialLatCondition1) || (specialLonCondition2 && specialLatCondition2) ) {
            return UserType.SPECIAL;
        }

        var normalLonCondition = lon > NORMAL_MAX_LON && lon < NORMAL_MIN_LON;
        var normalLatCondition = lat < NORMAL_MAX_LAT && lat > NORMAL_MIN_LAT;

        if (normalLatCondition && normalLonCondition) {
            return UserType.NORMAL;
        }

        return UserType.LABORIOUS;
    }
}
