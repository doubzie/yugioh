package exceptions;

@SuppressWarnings("serial")
public class MissingFieldException extends UnexpectedFormatException {

	public MissingFieldException() {

	}

	public MissingFieldException(String arg0) {
		super(arg0);
	}

	public MissingFieldException(String sourceFile, int sourceLine) {

		super("Missing field");
		this.setSourceFile(sourceFile);
		this.setSourceLine(sourceLine);

	}

}
