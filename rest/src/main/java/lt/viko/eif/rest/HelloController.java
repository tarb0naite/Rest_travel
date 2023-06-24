package lt.viko.eif.rest;

import lt.viko.eif.ktarbonaite.travelagency.Duomenys;
import lt.viko.eif.ktarbonaite.travelagency.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    private Map<Integer, Account> accounts = new HashMap<>();

    public HelloController() {
        accounts.put(1, Duomenys.account1);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getUsers() {
        List<Account> userList = new ArrayList<>(accounts.values());
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getUserById(@PathVariable int id) {
        Account account = accounts.get(id);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Void> createUser(@RequestBody Account account) {
        int id = accounts.size() + 1;
        account.setId(id);
        accounts.put(id, account);
        return ResponseEntity.created(URI.create("/accounts/" + id)).build();
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id,
                                           @RequestBody Account account) {
        Account existingUser = accounts.get(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        account.setId(id);
        accounts.put(id, account);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        Account account = accounts.remove(id);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
