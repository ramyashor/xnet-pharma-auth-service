/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xnet.auth.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xnet.auth.entity.AppUser;

/**
 *
 * @author ramya
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Serializable>{
    
    AppUser findByUsername(String userName);
}
