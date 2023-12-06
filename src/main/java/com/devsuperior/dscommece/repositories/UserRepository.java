package com.devsuperior.dscommece.repositories;

import com.devsuperior.dscommece.entities.User;
import com.devsuperior.dscommece.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByPhone(String phone);

    @Query(nativeQuery = true,
            value= """
			SELECT tb_user.email AS username, tb_user.password, tb_role.id AS roleId, tb_role.authority
			FROM tb_user
			INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id
			INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
			WHERE tb_user.email = :email
		"""
            )
	List<UserDetailsProjection> searcUserAndRoleByEmail(String email);
}
