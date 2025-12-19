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
    private SessionFactory factory;

    @Autowired
    private PasswordEncoder passwordencoder;

    /* ================= ACCOUNT ================= */

    public boolean start(Account user) {
        Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            Account existing = session
                    .createQuery("from Account where email = :email", Account.class)
                    .setParameter("email", user.getEmail())
                    .uniqueResult();

            if (existing != null) {
                return false;
            }

            session.save(user);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    public List<Account> findAllAccountData() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Account", Account.class).getResultList();
        }
    }

    public boolean deleteAccount(int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            session.createQuery("delete from Account where id = :id")
                   .setParameter("id", id)
                   .executeUpdate();

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    public Account getAccountById(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Account.class, id);
        }
    }

    public boolean updateAccount(Account account) {
        Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();
            session.update(account);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    /* ================= ADMIN (PROTECTED) ================= */

    public Protected getUsernameAndPassword() {
        try (Session session = factory.openSession()) {
            return session.get(Protected.class, 1);
        }
    }

    public Protected getbyId() {
        try (Session session = factory.openSession()) {
            return session.get(Protected.class, 1);
        }
    }

    public boolean updateProtected(Protected protectedObj) {
        Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            Protected db = session.get(Protected.class, protectedObj.getId());
            if (db == null) return false;

            db.setUsername(protectedObj.getUsername());

            if (protectedObj.getPassword() != null && !protectedObj.getPassword().isEmpty()) {
                db.setPassword(passwordencoder.encode(protectedObj.getPassword()));
            }

            tx.commit(); // dirty checking
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    /* ================= OTP ================= */

    public boolean storedLoginOtp(String otp) {
        Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            LoginOtp loginOtp = session.get(LoginOtp.class, 1);
            if (loginOtp == null) {
                loginOtp = new LoginOtp();
                loginOtp.setId(1);
            }

            loginOtp.setOtp(otp);
            loginOtp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
            session.saveOrUpdate(loginOtp);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    public boolean verifyOtp(LoginOtp user) {
        try (Session session = factory.openSession()) {
            LoginOtp dbOtp = session.get(LoginOtp.class, 1);
            if (dbOtp == null) return false;
            if (LocalDateTime.now().isAfter(dbOtp.getExpiryTime())) return false;
            return dbOtp.getOtp().equals(user.getOtp());
        }
    }

    /* ================= INITIAL ADMIN SETUP ================= */

    public void saveProtected(Protected user1) {
        Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            Protected db = session.get(Protected.class, 1);
            if (db == null) {
                user1.setId(1);
                user1.setPassword(passwordencoder.encode(user1.getPassword()));
                session.save(user1);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
}
