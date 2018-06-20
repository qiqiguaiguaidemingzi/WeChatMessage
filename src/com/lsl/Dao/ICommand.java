package com.lsl.Dao;

import java.util.List;
import com.lsl.bean.Command;

public interface ICommand {
	public List<Command> queryCommandList(Command command);
}
