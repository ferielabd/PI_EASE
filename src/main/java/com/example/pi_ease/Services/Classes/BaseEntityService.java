package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.BaseAdditionalFields;
import com.example.pi_ease.DAO.Entities.BaseEntity;
import com.example.pi_ease.DAO.Entities.GenErrorMessage;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.Exceptions.ItemNotFoundException;
import com.example.pi_ease.RestControllers.AuthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public abstract class BaseEntityService<E extends BaseEntity, D extends JpaRepository<E, Long>> {

    private D dao;

    private AuthController authenticationService;

    public BaseEntityService(){
    }

    public void setDao(D dao) {
        this.dao = dao;
    }

    /**
     * For Circular dependency
     */
    @Autowired
    public void setAuthenticationService(@Lazy AuthController authenticationService) {
        this.authenticationService = authenticationService;
    }

    public List<E> findAll() {

        return dao.findAll();
    }

    public Optional<E> findById(long id) {
        List<E> all = dao.findAll();
        return dao.findById(id);
    }

    public E save(E entity) {

        setAdditionalFields(entity);

        entity = dao.save(entity);

        return entity;
    }

    private void setAdditionalFields(E entity) {

        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();


        if (baseAdditionalFields == null) {

            baseAdditionalFields = new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }

        if (entity.getId() == 0) {

            baseAdditionalFields.setCreateDate(new Date());
        }

        baseAdditionalFields.setUpdateDate(new Date());
    }

    public void delete(E entity) {
        dao.delete(entity);
    }

    public E getByIdWithControl(long id) {

        Optional<E> entityOptional = findById(id);

        E entity;
        if (entityOptional.isPresent()) {
            entity = entityOptional.get();
        } else {
            throw new ItemNotFoundException(GenErrorMessage.ITEM_NOT_FOUND);
        }

        return entity;
    }


    public boolean existsById(long id) {
        return dao.existsById(id);
    }

    public D getDao() {
        return dao;
    }

    public Long getCurrentCustomerId() {
        User currentCustomer = authenticationService.getCurrentUser();
        Long userId = currentCustomer.getId();

        return userId;
    }


}