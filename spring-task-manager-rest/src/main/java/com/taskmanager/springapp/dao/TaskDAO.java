package com.taskmanager.springapp.dao;

import java.util.List;

import com.taskmanager.springapp.entity.Task;

public interface TaskDAO {

	public List<Task> getTasks();

	public void saveTask(Task theTask);

	public Task getTask(int theTaskId);

	public void deleteTask(int theTaskId);

	public List<Task> getPriorityTasks(String priority);

	public List<Task> getTodaysTasks(String today,String label);

	public List<Task> getPaginatedTasks(String start, int size);

	public List<Task> getHCTasks();
}
