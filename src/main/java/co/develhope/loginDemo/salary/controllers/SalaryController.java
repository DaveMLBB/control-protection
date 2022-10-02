package co.develhope.loginDemo.salary.controllers;

import co.develhope.loginDemo.salary.entities.CreateSalaryDTO;
import co.develhope.loginDemo.salary.entities.Salary;
import co.develhope.loginDemo.user.entities.User;
import co.develhope.loginDemo.user.repositories.UserRepository;
import co.develhope.loginDemo.user.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import co.develhope.loginDemo.salary.repositories.SalaryRepository;

import java.util.List;


@RestController
public class SalaryController {

    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/salary/user/all")
    @PreAuthorize("hasRole('"+ Roles.ADMIN +"')")
    public ResponseEntity<List<Salary>> getAll(){
        List<Salary> salaries = salaryRepository.findAll();
        return new ResponseEntity<List<Salary>>(salaries, HttpStatus.OK);
    }

    @GetMapping("/salary/user/{id}")
    @PreAuthorize("hasRole('"+ Roles.ADMIN +"')")
    public ResponseEntity<Salary> getSalaryByUid(@PathVariable Long id){
        Salary salary = salaryRepository.findByUserId(id);
        return new ResponseEntity<Salary>(salary, HttpStatus.OK);
    }

    @PostMapping("/salary/create/user/{id}")
    @PreAuthorize("hasRole('"+ Roles.ADMIN +"')")
    public Salary createSalary(@PathVariable Long id, @RequestBody CreateSalaryDTO salaryDTO){
        Salary s = new Salary();
        s.setAmount(salaryDTO.getAmount());
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("id not found"));
        s.setUser(user);
        return salaryRepository.save(s);
    }
}
