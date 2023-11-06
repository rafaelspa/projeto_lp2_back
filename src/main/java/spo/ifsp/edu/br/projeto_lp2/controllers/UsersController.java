package spo.ifsp.edu.br.projeto_lp2.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import spo.ifsp.edu.br.projeto_lp2.models.Region;
import spo.ifsp.edu.br.projeto_lp2.models.User;
import spo.ifsp.edu.br.projeto_lp2.models.UserType;
import spo.ifsp.edu.br.projeto_lp2.services.UserService;
import spo.ifsp.edu.br.projeto_lp2.utils.RegionUtil;
import spo.ifsp.edu.br.projeto_lp2.utils.UserTypeUtil;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
        this.userService.insertInitialUsers();
    }

    @GetMapping
    public List<User> getAll() {
        return userService.findAllUsers();
    }

    @GetMapping("paged")
    public Page<User> getAllPaged(
            @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = true, defaultValue = "10") Integer size,
            @RequestParam(value = "types", required = false) String types,
            @RequestParam(value = "regions", required = false) String regions
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);

        List<UserType> typesToFilter = types != null ? UserTypeUtil.getUserTypesByString(types) : null;
        List<Region> regionsToFilter = regions != null ? RegionUtil.getRegionsByString(regions) : null;

        if (regionsToFilter != null && typesToFilter != null) {
            return userService.getUsersFromTypesAndRegions(pageable, typesToFilter, regionsToFilter);
        }

        if (typesToFilter != null) {
            return userService.getUsersFromTypes(pageable, typesToFilter);
        }

        if (regionsToFilter != null) {
            return userService.getUsersFromRegions(pageable, regionsToFilter);
        }

        return userService.findAllUsers(pageable);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) throws Exception {
        return userService.getUserById(id);
    }
}
