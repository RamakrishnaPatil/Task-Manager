package com.taskmanager.springapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="task_manager")
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="taskId")
	private int id;
	
	@Column(name="dateTime")
	private String dateTime;
	
	@Column(name="priority")
	private String priority;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="labelId")
	private Label labelId;

	public Task(String dateTime, String priority) {
		this.dateTime = dateTime;
		this.priority = priority;
	}

	public Task() {
		}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Label getLabelId() {
		return labelId;
	}

	public void setLabelId(Label labelId) {
		this.labelId = labelId;
	}


	
}
