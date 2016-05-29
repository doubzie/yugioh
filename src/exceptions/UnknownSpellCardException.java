package exceptions;

@SuppressWarnings("serial")
public class UnknownSpellCardException extends UnexpectedFormatException {

	private String unknownSpell;

	public UnknownSpellCardException() {

	}

	public UnknownSpellCardException(String arg0) {
		super(arg0);
	}

	public UnknownSpellCardException(String sourceFile, int sourceLine, String unknownSpell) {

		super("Unknown spell card");
		this.setSourceFile(sourceFile);
		this.setSourceLine(sourceLine);
		this.unknownSpell = unknownSpell;

	}

	public String getUnknownSpell() {
		return unknownSpell;
	}

}
