package org.polytec.vermeg.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.polytec.vermeg.model.Customer;

@Repository
public class CustomerDAOImp implements CustomerDAO {


		@Autowired
		private SessionFactory sessionFactory;
		
		@Override
		public void setSessionFactory(SessionFactory sf) {
			this.sessionFactory = sf;
		}

		@Override
		public List<Customer> getCustomers() {
			Session session = this.sessionFactory.getCurrentSession();
			List<Customer> custList = session.createQuery("from Customer").list();
		
			for (Customer c: custList) {
				System.out.println("Customer :"+c.getFirstName());
			}
			
			return custList;
		}

		@Override
		public Customer getCustomer(int id) {
			Session session = this.sessionFactory.getCurrentSession();
			Customer b= (Customer) session.get(Customer.class, new Integer(id));
			return b;
		}
		
		@Override
		public void saveCustomer(Customer cust) {

		
			Session currentSession = sessionFactory.getCurrentSession();
			
		
			currentSession.saveOrUpdate(cust);
			
		}
		
	
		@Override
		public void deleteCustomer(int theId) {

			
			Session currentSession = sessionFactory.getCurrentSession();
			
			
			Query theQuery = 
					currentSession.createQuery("delete from Customer where id=:orderId");
			theQuery.setParameter("orderId", theId);
			
			theQuery.executeUpdate();		
		}
	}
