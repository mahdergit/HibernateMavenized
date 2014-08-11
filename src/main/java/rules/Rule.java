package rules;

import rules.RulesException;

public interface Rule {
	public void setData(Object ob);
	public void execute() throws RulesException;
}
