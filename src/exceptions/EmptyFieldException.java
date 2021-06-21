package exceptions;

@SuppressWarnings("serial")
public class EmptyFieldException extends UnexpectedFormatException {

  private int sourceField;

  public EmptyFieldException() {}

  public EmptyFieldException(String arg0) {
    super(arg0);
  }

  public EmptyFieldException(
    String sourceFile,
    int sourceLine,
    int sourceField
  ) {
    super("Empty field");
    this.setSourceFile(sourceFile);
    this.setSourceLine(sourceLine);
    this.sourceField = sourceField;
  }

  public int getSourceField() {
    return sourceField;
  }
}
