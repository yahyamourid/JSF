/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ma.projet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ma.projet.beans.Service;
import ma.projet.beans.Service;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author YahyaMrd
 */
public class ServiceService implements IDao<Service>{
    @Override
    public boolean create(Service o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Service o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Service o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.flush();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Service getById(int id) {
        Service machine  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        machine  = (Service) session.get(Service.class, id);
        session.getTransaction().commit();
        return machine;
    }

    @Override
    public List<Service> getAll() {
        List <Service> machines = null;
      
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        machines  = session.createQuery("from Service").list();
        session.getTransaction().commit();
        return machines;
    }
    
    public List<Object[]> nbrEmpParServ(){
        List<Object[]> services = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        services  = session.createQuery("select count(e.service), e.service from Employee e group by e.service").list();
        session.getTransaction().commit();
        return services;
    }
    
}
