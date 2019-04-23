package com.taskmanager.springapp.rest;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.taskmanager.springapp.entity.Task;
import com.taskmanager.springapp.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskRestController {


	@Autowired
	private TaskService taskService;
	
	// add mapping for GET /tasks
	@GetMapping("/tasks")
	public List<Task> getTasks(@RequestParam(required=false, value="priority") String priority,
			                   @RequestParam(required=false, value="start") String start,
			                   @RequestParam(required=false, value="size") Integer size) {
		if(priority!=null) {
			return taskService.getPriorityTasks(priority);
		}
		else if(start!=null&&size!=0) {
			return taskService.getPaginatedTasks(start, size);
					}
		else
		return taskService.getTasks();
	}
	
	
	@GetMapping("/tasks/today")
	public List<Task> getTodaysTasks(@RequestParam(required=true, value="label") String label) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 0);
		java.util.Date utilDate=(java.util.Date) cal.getTime();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
		String today=sqlDate.toString();
		System.out.println(today);
		
		return taskService.getTodaysTasks(today, label);
		}
	
	@GetMapping("/tasks/priorityTasks")
	public List<Task> getNextTwoDaysTasks() {
		
		return taskService.getHCTasks();
		}
	
	
	// add mapping for GET /tasks/{taskId}
	
	@GetMapping("/tasks/{taskId}")
	public Task getTask(@PathVariable int taskId) {
		Task theTask = taskService.getTask(taskId);
		
		if (theTask == null) {
			throw new TaskNotFoundException("Task id not found - " + taskId);
		}
		return theTask;
	}
	
	// add mapping for POST /tasks  - add new task
	
	@PostMapping("/tasks")
	public Task addTask(@RequestBody Task theTask) {
		
		// also just in case the pass an id in JSON ... set id to 0
		// this is force a save of new item ... instead of update
		
		theTask.setId(0);
		
		taskService.saveTask(theTask);
		
		return theTask;
	}
	
	// add mapping for PUT /tasks - update existing task
	
	@PutMapping("/tasks")
	public Task updateCustomer(@RequestBody Task theTask) {
		
		taskService.saveTask(theTask);
		
		return theTask;
		
	}
	
	// add mapping for DELETE /tasks/{taskId} - delete task
	
	@DeleteMapping("/tasks/{taskId}")
	public String deleteTask(@PathVariable int taskId) {
		
		Task tempTask = taskService.getTask(taskId);
		
		// throw exception if null
		
		if (tempTask == null) {
			throw new TaskNotFoundException("Task id not found - " + taskId);
		}
				
		taskService.deleteTask(taskId);
		
		return "Deleted task id - " + taskId;
	}
	
}
