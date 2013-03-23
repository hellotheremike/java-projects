
public class MyTokenizer implements Tokenizer{
	
	private Scanner m_scanner;				// Reference to the scanner object
	private Token m_lastRead;				// Buffer to store the last read token(current)
	private Token m_nextRead;				// Buffer to store the next token in the tokenizer(for peek and next)
	
	private Token extractToken(){
		
		// Creates a tempoary token
		Token tmp_t;
		
		// Get the current char from the scanner
		char c_in = m_scanner.current();
		
		// If the scanner reaches the end of the file, returns a special end-of-file token
		if(c_in == Scanner.EOF){
			tmp_t = new Token("", null, Token.Type.EOF);
		}
		// If its a digit, build a token of the current char and all subsequent chars that are digits
		else if(Character.isDigit(c_in)){
			
			// Creates a string that stores the current state of the number token
			// Keeps on building by adding the next read char at the end
			String tmp_s = Character.toString(c_in);
			
			// Loop as long as character is still a number
			while(Character.isDigit(m_scanner.peek()))
				tmp_s += Character.toString(m_scanner.next());
			
			// Return a new number node with the parsed value of the built string
			tmp_t = new Token(String.valueOf(tmp_s), Integer.parseInt(tmp_s) , Token.Type.NUMBER);
		}
		else if(Character.isLetter(c_in)){
			
			// Creates a string that stores the current state of the identifier token
			// Keeps on building by adding the next read char at the end
			String tmp_s = Character.toString(c_in);
						
			// Loop as long as character is still a letter			
			while(Character.isLetter(m_scanner.peek()))
				tmp_s += Character.toString(m_scanner.next());
			tmp_t = new Token(tmp_s, tmp_s , Token.Type.IDENTIFIER);
		}
		// Check if the character is found among the collection of operators
		else if(Token.Type.OPERATORS.containsKey(String.valueOf(c_in))){
			tmp_t = new Token(String.valueOf(c_in), c_in , Token.Type.OPERATORS.get(String.valueOf(c_in)));
		}
		else{
			// The character is of unknown type and could not be handled
			throw new RuntimeException("The character is of unknown type and could not be handled: " + c_in + ": " + (int)c_in);
		}
		
		return tmp_t;
	}
	
	// Creates a new tokenizer based on a scanner 
	public MyTokenizer(Scanner scanner){
		
		// Store the reference to the scanner
		m_scanner = scanner;
		
		// Reads the first 
		this.m_scanner.next();
		this.m_nextRead = this.extractToken();
		
	}
	
	// Returns the current 
	public Token current(){
		
		return m_lastRead;
	}
	
	// Get the next token
	public Token next(){
		
		// Toss the value of next read backwards)
		this.m_lastRead = this.m_nextRead;
		
		// Moves the scanner forwards
		this.m_scanner.next();
		
		// Buffers the next token for
		this.m_nextRead = this.extractToken();
		
		// Return the, now, last read value buffer
		return this.m_lastRead;
	}
	
	// Returns next token but dont move the tokenizer forward
	public Token peek(){
		
		return this.m_nextRead;
	}
}
