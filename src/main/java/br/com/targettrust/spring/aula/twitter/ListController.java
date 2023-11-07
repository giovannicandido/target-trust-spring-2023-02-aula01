package br.com.targettrust.spring.aula.twitter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ListController {
    List<Follower> followers = new ArrayList<>(
            List.of(
                    new Follower("1", "Nome", "@giovanni"),
                    new Follower("2", "Felipe", "@felipe")
            )

    );

    @GetMapping("/2/lists/{id}/followers")
    @ResponseStatus(HttpStatus.OK)
    public List<Follower> getFollowersOfList(@PathVariable(name = "id") String listId,
                                             @RequestParam(name = "name", required = false) String startName,
                                             @RequestParam(name = "max_results", required = false, defaultValue = "10") Integer maxResults,
                                             @RequestParam(name = "ids", required = false) List<String> ids) {
        System.out.println("Id recebido: " + listId);

        return followers.stream()
                .filter(follower -> {
                    if(ids != null && !ids.isEmpty()) {
                        return ids.contains(follower.getId());
                    }
                    return true;
                })
                .filter(follower -> {
                    if(startName != null) {
                        return follower.getName().toLowerCase().startsWith(startName.toLowerCase());
                    }
                    return true;
                })
                .toList();
    }

    @PostMapping("/2/lists/")
    public void createFollower(@RequestBody Follower follower) {
        System.out.println(follower);
    }
}
