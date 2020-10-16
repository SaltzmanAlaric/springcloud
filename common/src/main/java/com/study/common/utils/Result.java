package com.study.common.utils;

import java.util.HashMap;

public class Result extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public static Result ok(){
		Result r = new Result();
		r.put("code",HttpStatusCode.SUCCESS);
		r.put("msg","success");
		return r;
	}
	public static Result error(String msg){
		Result r = new Result();
		r.put("code",HttpStatusCode.ERROR);
		r.put("msg",msg);
		return r;
	}
	public Result put(String key, Object value){
		super.put(key,value);
		return this;
	}

}
