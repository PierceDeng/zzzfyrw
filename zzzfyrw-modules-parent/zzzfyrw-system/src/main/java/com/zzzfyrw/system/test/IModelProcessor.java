package com.zzzfyrw.system.test;


import com.zzzfyrw.system.test.body.ModelMessage;

public interface IModelProcessor {


	boolean isSupport(String messageFlag);


	public ModelMessage processor();

}
