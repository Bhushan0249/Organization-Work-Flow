package com.main.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.main.entity.Country;
import com.main.entity.Employee;

@Repository
public class MainDAO {

	@Autowired
	SessionFactory factory;

	public String addCountry(Country c) {
		Session session = null;
		Transaction tx = null;
		String msg = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.persist(c);
			tx.commit();
			msg = "Coutry Addedd Successfully..";
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}

	public String updateCountry(Country c, int id) {

		Session session = null;
		Transaction tx = null;
		String msg = null;
		Country country = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			country = session.get(Country.class, id);
			country.setCname(c.getCname());
			session.merge(country);

			tx.commit();
			msg = "Coutry Updated Successfully..";
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}

	public String deleteCountry(int id) {

		Session session = null;
		Transaction tx = null;
		String msg = null;
		Country country = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			country = session.get(Country.class, id);

			session.remove(country);
			tx.commit();
			msg = "Coutry Deleted Successfully..";
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}

	public List<Country> getAllCountry() {
		Session session = null;
		Transaction tx = null;
		List<Country> list = null;
		String hqlQuery = "from Country";
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query<Country> query = session.createQuery(hqlQuery, Country.class);
			list = query.list();
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}

	public Country getParticularCountryById(int id) {
		Session session = null;
		Transaction tx = null;

		Country country = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			country = session.get(Country.class, id);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return country;
	}

	public String addEmployee(Employee emp) {

		Session session = null;
		Transaction tx = null;
		String msg = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			emp.setUpdatedDate(new Date());
			emp.setCreatedDate(new Date());
			session.persist(emp);
			tx.commit();
			msg = "Employee Addedd Successfully..";
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}

	public Employee login(Employee emp) {
		Session session = null;
		Transaction tx = null;
		Employee employee = null;
		String hqlQuery = "from Employee where emailid=:emailid and mobileno=:mobileno";
		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			Query<Employee> query = session.createQuery(hqlQuery, Employee.class);
			query.setParameter("emailid", emp.getEmailid());
			query.setParameter("mobileno", emp.getMobileno());
			employee = query.uniqueResult();
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return employee;
	}

	public List<Employee> salaryRange(double startSal, double endSal) {
		Session session = null;
		Transaction tx = null;
		List<Employee> list = null;
		String hqlQuery = "from Employee where salary between :startSal and :endSal";
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query<Employee> query = session.createQuery(hqlQuery, Employee.class);
			query.setParameter("startSal", startSal);
			query.setParameter("endSal", endSal);
			list = query.list();
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}

	public String updateemp(Employee emp, int id) {

		Session session = null;
		Transaction tx = null;
		String msg = null;
		Employee employee = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			employee = session.get(Employee.class, id);

			employee.setName(emp.getName());
			employee.setStatus(emp.getStatus());
			employee.setDepartment(emp.getDepartment());
			employee.setMobileno(emp.getMobileno());
			employee.setEmailid(emp.getEmailid());
			employee.setUpdatedBy(emp.getUpdatedBy());
			employee.setUpdatedDate(new Date());
			employee.setSalary(emp.getSalary());
			employee.setCountry(emp.getCountry());

			session.persist(employee);
			tx.commit();
			msg = "Employee Updated Successfully..";
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;

	}

	// dao
	public List<Employee> getAllemp() {
		String hqlQuery = "from Employee";
		Session session = factory.openSession();

		Query<Employee> query = session.createQuery(hqlQuery, Employee.class);

		List<Employee> list = query.list();
		return list;

	}

	public Employee getempbyid(int id) {
		// dao
		Session session = factory.openSession();

		Employee bhu = session.get(Employee.class, id);

		return bhu;

	}

	public String deleteEmp(int id) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		try {

			Employee emps = session.get(Employee.class, id);

			session.remove(emps);
			tx.commit();
			return "Data Deleted Successfully....";

		} catch (Exception e) {

			tx.rollback();
			return "due to some error";

		}

	}

}