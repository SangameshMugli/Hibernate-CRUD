package org.jsp.employeeapp.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.jsp.employeeapp.dto.Employee;

public class EmployeeDao {
	Session s = new Configuration().configure().buildSessionFactory().openSession();

	public Employee saveEmployee(Employee emp) {
		s.save(emp);
		Transaction t = s.beginTransaction();
		t.commit();
		return emp;
	}

	public Employee updateEmployee(Employee emp) {
		s.update(emp);
		Transaction t = s.beginTransaction();
		t.commit();
		return emp;
	}

	public Employee findById(int id) {
		return s.get(Employee.class, id);
	}

	public Employee verifyEmployee(long phone, String password) {
		String hql = "select e from Employee e where e.phone=?1 and e.password=?2";
		Query<Employee> q = s.createQuery(hql);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Employee verifyEmployee(int id, String password) {
		String hql = "select e from Employee e where e.id=?1 and e.password=?2";
		Query<Employee> q = s.createQuery(hql);
		q.setParameter(1, id);
		q.setParameter(2, password);
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean deleteEmployee(int id) {
		Employee e = findById(id);
		if (e != null) {
			Transaction t = s.beginTransaction();
			s.delete(e);
			t.commit();
			return true;
		}
		return false;

	}
	
	public List<Employee> findEmployeeByDesg(String desg){
		String qry="select e from Employee e where e.desg=?1";
		Query<Employee> q=s.createQuery(qry);
		q.setParameter(1, desg);
		return q.getResultList();
				
	}
	
	

}
