package exceptions;

@SuppressWarnings("serial")
public class UnknownCardTypeException extends UnexpectedFormatException {

  private String unknownType;

  public UnknownCardTypeException() {}

  public UnknownCardTypeException(String arg0) {
    super(arg0);
  }

  public UnknownCardTypeException(
    String sourceFile,
    int sourceLine,
    String unknownType
  ) {
    super("Unknown card type");
    this.setSourceFile(sourceFile);
    this.setSourceLine(sourceLine);
    this.unknownType = unknownType;
  }

  public String getUnknownType() {
    return unknownType;
  }
}
