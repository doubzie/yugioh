package eg.edu.guc.yugioh.exceptions;

public class UnexpectedFormatException extends Exception {
	
		private String sourceFile;
		private int sourceLine;
		
	public UnexpectedFormatException() {
	
	}

	public UnexpectedFormatException(String arg0) {
		super(arg0);
	}
	
	public UnexpectedFormatException(String sourceFile, int sourceLine) {
		this.sourceFile = sourceFile;
		this.sourceLine = sourceLine;
		printStackTrace();
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public int getSourceLine() {
		return sourceLine;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public void setSourceLine(int sourceLine) {
		this.sourceLine = sourceLine;
	}
	

}
