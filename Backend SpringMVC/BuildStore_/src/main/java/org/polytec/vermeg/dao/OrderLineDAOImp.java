package org.polytec.vermeg.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.polytec.vermeg.controller.BookController;
import org.polytec.vermeg.model.Book;
import org.polytec.vermeg.model.Order;
import org.polytec.vermeg.model.OrderLine;

@Repository
public class OrderLineDAOImp implements OrderLineDAO {


		@Autowired
		private SessionFactory sessionFactory;
	
		@Override 
		public void setSessionFactory(SessionFactory sf) {
			this.sessionFactory = sf;
		}
		@Override
		public List<OrderLine> getOrderLine() {
			Session session = this.sessionFactory.getCurrentSession();
			List<OrderLine> lineList = session.createQuery("from OrderLine").list();
			
			for (OrderLine c: lineList) {
				System.out.println(" Line :"+c.getBook().getTitle());
			}
			
			return lineList;
		}
		@Override
		public OrderLine getLine(int id) {
			Session session = this.sessionFactory.getCurrentSession();
			OrderLine b= (OrderLine) session.get(OrderLine.class, new Integer(id));
			
			return b;
		}

	

		
		@Override
		public void saveOrderLine (OrderLine orderLine) {
			
			
			Session currentSession = sessionFactory.getCurrentSession();
			
			
			currentSession.saveOrUpdate(orderLine);
			
		}

		@Override
		public void deleteLine(int theId) {

		
			Session currentSession = sessionFactory.getCurrentSession();
			
		
			Query theQuery = 
					currentSession.createQuery("delete from OrderLine where id=:lineId");
			theQuery.setParameter("lineId", theId);
			
			theQuery.executeUpdate();		
			
		}	
		@Override
		public void deleteAllLine() {

			
			Session currentSession = sessionFactory.getCurrentSession();
			
		
			Query theQuery = 
					currentSession.createQuery("delete from OrderLine ");
			
			
			theQuery.executeUpdate();		
			
		}
	}


