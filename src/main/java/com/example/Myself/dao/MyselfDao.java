package com.example.Myself.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.Myself.entity.Account;
import com.example.Myself.entity.LoginOtp;
import com.example.Myself.entity.Protected;

@Repository
public class MyselfDao {
	@Autowired
	SessionFactory factory;
	@Autowired
	PasswordEncoder passwordencoder;

	public boolean start(Account user) {

		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			Account emailExiting = session.createQuery("From Account where email =:email",Account.class).setParameter("email", user.getEmail()).uniqueResult();
			
			if(emailExiting !=null) {
				return false;
			}
			session.save(user);
			session.getTransaction().commit();
			return true;

		} catch (Exception e) {
			if (session != null && session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			if (session != null)
				session.close();

		}

	}

	public List<Account> findAllAccountData() {
		Session session = null;
		try {
			session = factory.openSession();
			return session.createQuery("From Account", Account.class).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null)
				session.close();
		}

	}

	public boolean deleteAccount(int id) {
		Session session = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			session.createQuery("delete from Account where id = :id").setParameter("id", id).executeUpdate();

			tx.commit();
			return true;

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			if (session != null)
				session.close();
		}
		
	}

	public Account getAccountById(int id) {
		Session session = null;
		try {
			session = factory.openSession();
			return session.get(Account.class, id);
			
		} catch (Exception e) {
				e.printStackTrace();
				return null;
		} finally {
			if (session != null)
				session.close();
		}

	}
	
	public boolean updateAccount(Account account) {
	    Session session = null;
	    Transaction tx = null;
	    try {
	        session = factory.openSession();
	        tx = session.beginTransaction();
	        session.update(account); // or saveOrUpdate()
	        tx.commit();
	        return true;
	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace();
	        return false;
	    } finally {
	        if (session != null)
	            session.close();
	    }
	}

	public Protected getUsernameAndPassword() {
		Session session = null;
		try {
			session = factory.openSession();
			return session.get(Protected.class, 1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null)
				session.close();
		}

	}

	public boolean storedLoginOtp(String otp) {
		Session session =null;
		Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			LoginOtp loginotp = new LoginOtp();
			loginotp.setOtp(otp);
			loginotp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
			session.saveOrUpdate(loginotp);
			tx.commit();
			return true;
			
		} catch (Exception e) {
			if(tx!=null)tx.rollback();
			e.printStackTrace();
			return false;
		}finally {
			if(session!=null)
				session.close();
		}
		
	}

	public boolean verifyOtp(LoginOtp user) {
		Session session = null;

		try {

			session = factory.openSession();
			LoginOtp dbotp = session.get(LoginOtp.class, 1);

			if (dbotp == null)
				return false;

			if (LocalDateTime.now().isAfter(dbotp.getExpiryTime()))
				return false;
			if (dbotp.getOtp().equals(user.getOtp()))
				return true;

			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (session != null)
				session.close();
		}

	}

	public Protected getbyId() {
		Session session = null;
		try {
			session = factory.openSession();
			return session.get(Protected.class,1);
			
		} catch (Exception e) {
			  e.printStackTrace();
			  return null;
		}finally {
			if(session!=null)
				session.close();
		}
		
	}

	public boolean updateProtected(Protected protectedObj) {
		Session session = null;
		Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			if (protectedObj.getPassword() != null && !protectedObj.getPassword().isEmpty()) {
				String Encodepassword = passwordencoder.encode(protectedObj.getPassword());
				protectedObj.setPassword(Encodepassword);
			}
			session.update(protectedObj);
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			if (session != null)
				session.close();
		}

	}

}
