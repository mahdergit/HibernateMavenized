package rules;

public class RulesException extends Exception {
	public RulesException() {
		super();
	}
	public RulesException(String msg) {
		super(msg);
	}
	public RulesException(Exception e) {
		super(e);
	}
	private static final long serialVersionUID = 4948285295953313819L;
}
