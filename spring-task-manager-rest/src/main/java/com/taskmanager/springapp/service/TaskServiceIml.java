package com.taskmanager.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskmanager.springapp.dao.TaskDAO;
import com.taskmanager.springapp.entity.Task;

@Service
public class TaskServiceIml implements TaskService {

	// need to inject task dao
	@Autowired
	private TaskDAO taskDAO;
	
	@Override
	@Transactional
	public List<Task> getTasks() {
		return taskDAO.getTasks();
	}

	@Override
	@Transactional
	public void saveTask(Task theTask) {

		taskDAO.saveTask(theTask);
	}

	@Override
	@Transactional
	public Task getTask(int theTaskId) {
		
		return taskDAO.getTask(theTaskId);
	}

	@Override
	@Transactional
	public void deleteTask(int theTaskId) {
		
		taskDAO.deleteTask(theTaskId);
	}

	@Override
	@Transactional
	public List<Task> getPriorityTasks(String priority) {
		return taskDAO.getPriorityTasks(priority);
	}

	@Override
	@Transactional
	public List<Task> getTodaysTasks(String today,String label) {
		return taskDAO.getTodaysTasks(today, label);
	}

	@Override
	@Transactional
	public List<Task> getPaginatedTasks(String start, int size) {
		return taskDAO.getPaginatedTasks(start, size);
	}

	@Override
	@Transactional
	public List<Task> getHCTasks() {
		return taskDAO.getHCTasks();
	}


}





