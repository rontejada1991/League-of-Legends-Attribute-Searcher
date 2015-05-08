package com.example.lolobjects;

public class Champion {
	private int _id;
	private int path;
	private String name;
	
	public Champion(String name, int path)	{
		this.name = name;
		this.path = path;
	}
	
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getPath() {
		return path;
	}

	public void setPath(int path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
