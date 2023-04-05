package com.example.pi_ease.Services.Interfaces;

import com.example.pi_ease.DAO.Entities.Account;

import java.util.List;

public interface IAccountService {
    Account add(Account a);
    Account edit(Account a);
    List<Account> selectAll();
    Account selectById(Integer idAccount);
    void deleteById(Long idAccount);

    void deleteById(Integer idAccount);
    void delete(Account a);
    List<Account> addAll(List<Account> list);
    void deleteAll(List<Account> list);
}
