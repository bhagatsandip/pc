package com.planetclubs.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.Login;
import com.planetclubs.model.Users;
import com.planetclubs.util.Constants;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public Users insertNewUserInfo(Users user) {

		// inserts into database & return bookId (primary_key)
		int userId = (Integer) sessionFactory.getCurrentSession().save(user);
		List<Users> users = sessionFactory.getCurrentSession().createCriteria(Users.class).add(Restrictions.eq("userId", userId)).list();
		return users.get(0);
		
	}

	@Override
	@Transactional
	public Users getUserInfo(int userId) {

		// retrieve user object based on the id supplied in the formal argument
		List<Users> user = sessionFactory.getCurrentSession().createCriteria(Users.class).add(Restrictions.eq("userId", userId)).list();
		return user.get(0);
	}
	
	@Override
	@Transactional
	public Users getUserInfoByEmail(String emailId) {

		// retrieve user object based on the id supplied in the formal argument
		List<Users> user = sessionFactory.getCurrentSession().createCriteria(Users.class).add(Restrictions.eq("email", emailId)).list();
		if(user != null){
			return user.get(0);
		}
		return null;

	}
	
	@Override
	@Transactional
	public Users getUserInfoByEmailToken(String token) {

		// retrieve user object based on the id supplied in the formal argument
		List<Users> user = sessionFactory.getCurrentSession().createCriteria(Users.class).add(Restrictions.eq("resetPasswordToken", token)).list();
		if(user != null){
			return user.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public String updateUserInfo(Users updateUser) {

		// update database with book information and return success msg
		sessionFactory.getCurrentSession().update(updateUser);
		return "SUCCESS";
	}

	@Override
	@Transactional
	public Users loginUser(Login login) {
		String query = "from Users where email=? and password=? and userType=?";
		  Query queryObj = sessionFactory.getCurrentSession().createQuery(query);
		  queryObj.setString(0, login.getUsername());
		  queryObj.setString(1, login.getPassword());
		  queryObj.setString(2, login.getUserType());
		  List<Users> userList = queryObj.list();
		  if(userList.size() > 0){
			  return userList.get(0);
		  }
		return null;
	}

	@Override
	@Transactional
	public String checkIfEmailExist(String email) {
		String query = "from Users where email=?";
		  Query queryObj = sessionFactory.getCurrentSession().createQuery(query);
		  queryObj.setString(0, email);
		  List<Users> userList = queryObj.list();
		  if(userList.size() > 0){
			  return Constants.SUCCESS;
		  }
		return Constants.FAILURE;
	}

	@Override
	@Transactional
	public boolean checkIfNumberExist(String number) {
		String query = "from Users where contactNo=?";
		  Query queryObj = sessionFactory.getCurrentSession().createQuery(query);
		  queryObj.setString(0, number);
		  List<Users> userList = queryObj.list();
		  if(userList.size() > 0){
			  return true;
		  }
		return false;
	}

	@Override
	@Transactional
	public Users getUserByToken(String token) {
		
		String query = "from Users where token=?";
		  Query queryObj = sessionFactory.getCurrentSession().createQuery(query);
		  queryObj.setString(0, token);
		  List<Users> userList = queryObj.list();
		  if(userList.size() > 0){
			  return userList.get(0);
		  }
		return null;
	}

	@Override
	@Transactional
	public String checkIfUserIdExist(String userId) {
		String query = "from Users where userId=?";
		  Query queryObj = sessionFactory.getCurrentSession().createQuery(query);
		  queryObj.setString(0, userId);
		  List<Users> userList = queryObj.list();
		  if(userList.size() > 0){
			  return "Success";
		  }
		return "Failure";
	}


}