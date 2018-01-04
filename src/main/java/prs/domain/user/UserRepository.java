package prs.domain.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsernameAndPassword(String username, String pwd);
	//The method name is EXTREMlY important w Sprint go create custom method ; case my instance variables EXACTLY like Seans
	//if uppercase 'N' is important since his var was userNames

}
