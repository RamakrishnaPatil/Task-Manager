package com.taskmanager.springapp.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taskmanager.springapp.entity.Label;
import com.taskmanager.springapp.entity.Task;

@Repository
public class TaskDAOImpl implements TaskDAO {

	// need to inject the session factory
		@Autowired
		private SessionFactory sessionFactory;
		
		@Override
		public List<Task> getTasks() {
			
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
					
			// create a query  ... sort by dateTime
			Query<Task> theQuery = 
					currentSession.createQuery("from Task order by dateTime",
												Task.class);

			// execute query and get result list
			List<Task> tasks = theQuery.getResultList();
			return tasks;
		}

		@Override
		public void saveTask(Task theTask) {

			// get current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			
			// save/upate the task ...
			currentSession.saveOrUpdate(theTask);
			
		}

		@Override
		public Task getTask(int theTaskId) {
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			
			// now retrieve/read from database using the primary key
			Task theTask = currentSession.get(Task.class, theTaskId);
			
			return theTask;
		}

		@Override
		public void deleteTask(int theTaskId) {
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			Task task=currentSession.get(Task.class, theTaskId);
			currentSession.delete(task);
			// delete object with primary key
/*			Query theQuery = currentSession.createQuery("delete from Task where id=:taskId");
			theQuery.setParameter("taskId", theTaskId);
			theQuery.executeUpdate();*/	
		}

		@Override
		public List<Task> getPriorityTasks(String priority) {
			// get the current hibernate session
						Session currentSession = sessionFactory.getCurrentSession();
						
						Query<Task> theQuery  =currentSession.createQuery("from Task where priority=:prio", Task.class);
						theQuery.setParameter("prio", priority);
						List<Task> tasksList = theQuery.getResultList();
						return tasksList;
		}

		@Override
		public List<Task> getTodaysTasks(String today, String label) {
			Session currentSession = sessionFactory.getCurrentSession();
			
			String sql = "SELECT t.taskId, t.dateTime, t.priority, t.labelId FROM task_manager t, label l "
					+ "where t.dateTime like '%"+today+"%'"+" and l.category='"+label+"' and t.labelId=l.id";
			SQLQuery query = currentSession.createSQLQuery(sql);
			query.addEntity(Task.class);
			List<Task> results = query.list();
		
			return results;
		}

		@Override
		public List<Task> getPaginatedTasks(String start, int size) {
			
			Date startDateSql=Date.valueOf(start);
			int a=startDateSql.getDate()+size;
			java.util.Date lastDateUtil= new java.util.Date(startDateSql.getTime());
			lastDateUtil.setDate(a);
			java.sql.Date lastDateSql = new java.sql.Date(lastDateUtil.getTime());
			
			Session currentSession = sessionFactory.getCurrentSession();
			Query<Task> theQuery  =currentSession.createQuery("from Task where dateTime between '"+startDateSql+"'"+ " and " +"'"+lastDateSql+"'" , Task.class);
			List<Task> paginatedTasksList = theQuery.getResultList();

			return paginatedTasksList;
		}

		@Override
		public List<Task> getHCTasks() {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<Task> theQuery  =currentSession.createQuery("from Task where priority in ('Critical','High')" , Task.class);
			List<Task> list = theQuery.getResultList();
			return list;
		}
	
}
