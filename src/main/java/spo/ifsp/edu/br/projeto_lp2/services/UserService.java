package spo.ifsp.edu.br.projeto_lp2.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spo.ifsp.edu.br.projeto_lp2.dataBaseLoading.CsvUserHttpClient;
import spo.ifsp.edu.br.projeto_lp2.dataBaseLoading.JsonUserHttpClient;
import spo.ifsp.edu.br.projeto_lp2.models.Region;
import spo.ifsp.edu.br.projeto_lp2.models.User;
import spo.ifsp.edu.br.projeto_lp2.repositories.UserRepository;
import spo.ifsp.edu.br.projeto_lp2.models.UserType;
import spo.ifsp.edu.br.projeto_lp2.models.pagination.UserPage;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private RegionService regionService;
    private UserTypeService userTypeService;

    public UserService(UserRepository userRepository, RegionService regionService, UserTypeService userTypeService) {
        this.userRepository = userRepository;
        this.regionService = regionService;
        this.userTypeService = userTypeService;
    }

    public void insertInitialUsers() {
        var jsonHttpClient = new JsonUserHttpClient();
        var csvHttpClient = new CsvUserHttpClient();

        try {
            var usersFromJson = jsonHttpClient.getUsers();
            var usersFromCsv = csvHttpClient.getUsers();

            List<User> users = new ArrayList<User>();
            users.addAll(usersFromJson);
            users.addAll(usersFromCsv);

            for (User user : users) {
                var region = regionService.getRegionByLocation(user.getLocation());
                var userType = userTypeService.getUserType(user.getLocation().getCoordinates());

                user.getLocation().setRegion(region);
                user.setType(userType);
            }

            userRepository.saveAll(users);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("Error: user not found"));
    }

    public UserPage<User> findAllUsers(Pageable pageable) {
        var page = userRepository.findAll(pageable);
        return UserPage.of(page);
    }

    public UserPage<User> getUsersFromTypes(Pageable pageable, List<UserType> types) {
        var page = userRepository.findAllByTypeIn(pageable, types);
        return UserPage.of(page);
    }

    public UserPage<User> getUsersFromRegions(Pageable pageable, List<Region> regions) {
        var page = userRepository.findAllByLocationRegionIn(pageable, regions);
        return UserPage.of(page);
    }

    public UserPage<User> getUsersFromTypesAndRegions(Pageable pageable, List<UserType> types, List<Region> regions) {
        var page = userRepository.findAllByTypeInAndLocationRegionIn(pageable, types, regions);
        return UserPage.of(page);
    }
}
