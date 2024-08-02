package com.waleed.development;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api")
@SessionAttributes("username")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (user.getAccounts() == null || user.getAccounts().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        User savedUser = userService.addUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> listActiveAccounts(@RequestParam String username, HttpSession session) {
        User user = userService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        session.setAttribute("user", user);
        List<Account> accounts = userService.getActiveAccounts(user);
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/account")
    public ResponseEntity<Account> addAccount(HttpSession session, @RequestBody Account account) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Account savedAccount = userService.addAccount(user, account);
        return ResponseEntity.ok(savedAccount);
    }

    @PutMapping("/account")
    public ResponseEntity<Account> updateAccount(HttpSession session, @RequestParam String accountNumber, @RequestBody Double lastTransactionAmount) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Account updatedAccount = userService.updateAccount(accountNumber, lastTransactionAmount);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/account")
    public ResponseEntity<String> deleteAccount(HttpSession session, @RequestParam String accountNumber) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return ResponseEntity.badRequest().body("User not found in session");
        }
        userService.deleteAccount(accountNumber);
        return ResponseEntity.ok("Account deleted successfully");
    }

}