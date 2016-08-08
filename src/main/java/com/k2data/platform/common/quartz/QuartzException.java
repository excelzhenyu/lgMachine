package com.k2data.platform.common.quartz;

public class QuartzException extends RuntimeException {

	private static final long serialVersionUID = -5313710337264450917L;

	public QuartzException() {
		super();
	}
	
	public QuartzException(String msg) {
		super(msg);
	}
	
	public QuartzException(Throwable e) {
		super(e);
	}
	
	public QuartzException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
