package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Account;
import com.example.pi_ease.DAO.Repositories.accountRepo;
import com.example.pi_ease.Services.Interfaces.IAccountService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
@AllArgsConstructor
@Service
public class AccountService<amount> implements IAccountService {
    private accountRepo accountrepo;

    @Override
    public Account add(Account a) {
        return accountrepo.save(a);
    }

    @Override
    public Account edit(Account a) {
        return accountrepo.save(a);
    }

    @Override
    public List<Account> selectAll() {
        return accountrepo.findAll();
    }

    @Override
    public Account selectById(Integer idAccount) {
        return accountrepo.findById(idAccount).get();
    }

    @Override
    public void deleteById(Long idAccount) {

    }

    @Override
    public void deleteById(Integer idAccount) {
        accountrepo.deleteById(idAccount);

    }

    @Override
    public void delete(Account a) {
        accountrepo.delete(a);

    }

    @Override
    public List<Account> addAll(List<Account> list) {
        return accountrepo.saveAll(list);
    }

    @Override
    public void deleteAll(List<Account> list) {
        accountrepo.deleteAll(list);

    }

}
