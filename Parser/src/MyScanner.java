import java.io.StringReader;

public class MyScanner implements  Scanner{
	
	
	private StringReader stringreader; 		//Reference to stringreader
	private char m_lastRead;				//Buffers the last read char from the stream
	
	public MyScanner(StringReader expression){
		
		// Store the reference to the stringreader
		stringreader = expression;
	}
	
	/**
	 * Return the current character in the stream
	 *
	 */
	public char current(){
		return m_lastRead;
	}
	
	/**
	 * Return the next character in the stream. Subsequent calls to
	 * current() will return the same character. Scanner.EOF is return
	 * if the end of the file is reached.
	 *
	 */
	public char next(){
		try{
			// Continue to read in the stream until a character that is neither a space or EOL sign
			// Make it ignore whitespace and linebreaks
			do{
				// Stores the last read character in the buffer
				m_lastRead = (char)stringreader.read();
			}
			while(m_lastRead == Scanner.EOL || m_lastRead == ' ');
		}catch(Exception e){
			System.out.print(e.toString());
		}
		
		// If the last read character is 65536(maxint) we have reached the end of the file
		if(m_lastRead == 65535) 
			m_lastRead = Scanner.EOF;
		
		return m_lastRead;
	}
	
	/**
	 * Return the next character in the stream. Subsequent calls to
	 * next() will return the same character, but calls to current()
	 * will be unaffected.
	 *
	 */
	public char peek(){
		
		// Creates a variable to store the next read character from the stream
		char tmp_peek = 0;
		try{
			// Create a mark in the stringreader that allows a max of 1 steps forward(as we only read 1 character at the time)
			stringreader.mark(1);
			
			// Get the next value from the stream
			tmp_peek = next();
			
			// Reset the stringreader to the state it was
			stringreader.reset();
		}catch(Exception e){
			System.out.print(e.toString());
		}
		return tmp_peek;
	}
}
