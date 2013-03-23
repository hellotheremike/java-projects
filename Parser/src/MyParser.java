
public class MyParser implements Parser{
	private Tokenizer m_tokenizer;
	public MyParser(Tokenizer tmp_tokenizer){
		
		// Store the reference to the tokenizer for future use
		m_tokenizer = tmp_tokenizer;
	}
	
	public Node parse(){
		
		AssignNode node = new AssignNode();
		
		// Store the first and second token
		Token t = m_tokenizer.next();
		Token n = m_tokenizer.next();
		
		// As this is an assign expression, make sure the first token is an identifier and the second one an =operator
		if(t.type() == Token.Type.IDENTIFIER &&n.type() == Token.Type.EQ) {
			node.left = new IdentifierNode();
			
			IdentifierNode tmp_identify = (IdentifierNode)node.left;
			tmp_identify.value = t.text();
			
			// Move to start of expression
			m_tokenizer.next();
			
			// Set the rightside of the assignment to the parsed expression
		    node.right = this.parseExpression();
		    
		} else {
			// The rules for assignment nodes is not fulfilled
			throw new RuntimeException("Invalid start of assignment");
		}
		return node;
	}
	
	// Parse an expression
	private Node parseExpression(){
		
		// Grab the currently selected token from the tokenizer
		Token token = m_tokenizer.current();
		ExpressionNode node = new ExpressionNode();
		
		// Check what kind of token the current one is
		if(token.type() == Token.Type.NUMBER){
			
			// If its a number, parse a term
			node.left = parseTerm();
		}	
		else if(token.type() == Token.Type.LEFT_PAREN){
			
			// If it's a left parenthesis, create a subexpression and parse it accordingly
			Node subNode;
			// Jump to the next token to simply skip the parenthesis sign
			m_tokenizer.next();
			
			// Parse the expression
			subNode = parseExpression();
			node.left = subNode;
		}
		
		// Peek in the tokenizer to see if the expression has an operator that is either plus or minus
		Token token_peek = m_tokenizer.peek();
		if(token_peek.type() == Token.Type.PLUS || token_peek.type() == Token.Type.MINUS){
			
			// Jump to the next token to and check the operator
			m_tokenizer.next();
			node.operator = m_tokenizer.current().text();
			m_tokenizer.next();
			
			// Peek to see if the expression contains more operators and if it does, create sub expressions to handle it
			token_peek = m_tokenizer.peek();
			if(token_peek.type() == Token.Type.PLUS || token_peek.type() == Token.Type.MINUS){
				
				// Create the subexpression
				Node subNode;
				subNode = parseExpression();
				node.right = subNode;
			}
			else{
				// If it doesnt, the righthand side is a simple termnode
				node.right = parseTerm();
			}
			
		}
		
		// Check for right parenthesis sign to determine wether the expression ends here
		// By jumping to the next token, we ignore the sign and make the method end
		token_peek = m_tokenizer.peek();
		if(token_peek.type() == Token.Type.RIGHT_PAREN){
			m_tokenizer.next();
		}
		
		return node;
	}
	
	// Parses a term 
	private Node parseTerm(){
		
		// Grab the currently selected token from the tokenizer
		Token token = m_tokenizer.current();
		TermNode node = new TermNode();
		
		// Grab the currently selected token from the tokenizer
		if(token.type() == Token.Type.NUMBER){
			
			// If its a number, parse a term
			node.left = parseFactor();
		}			
		else if(token.type() == Token.Type.LEFT_PAREN){
			
			// If it's a left parenthesis, create a subexpression and parse it accordingly
			Node subNode;
			// Jump to the next token to simply skip the parenthesis sign
			m_tokenizer.next();
			
			// Parse the expression
			subNode = parseExpression();
			node.left = subNode;
		}
		
		// Peek in the tokenizer to see if the expression has an operator that is either plus or minus
		Token token_peek = m_tokenizer.peek();
		if(token_peek.type() == Token.Type.DIV || token_peek.type() == Token.Type.MULT){
			
			// Jump to the next token to and check the operator
			m_tokenizer.next();
			node.operator = m_tokenizer.current().text();
			m_tokenizer.next();
			
			// Peek to see if the expression contains more operators and if it does, create sub expressions to handle it
			token_peek = m_tokenizer.peek();
			if(token_peek.type() == Token.Type.DIV || token_peek.type() == Token.Type.MULT){
				
				// Create the subexpression
				Node subNode;
				subNode = parseExpression();
				node.right = subNode;
			}
			else{
				// If it doesnt, the righthand side is a simple termnode
				node.right = parseFactor();
			}
		}
		
		// Check for right parenthesis sign to determine wether the expression ends here
		// By jumping to the next token, we ignore the sign and make the method end
		token_peek = m_tokenizer.peek();
		if(token_peek.type() == Token.Type.RIGHT_PAREN){
			m_tokenizer.next();
		}
		
		return node;
	}
	
	// Parse factor
	private Node parseFactor(){
		
		// Grab the currently selected token from the tokenizer
		Token token = m_tokenizer.current();
		FactorNode node = new FactorNode();
		
		// If is number parse to integer
		if(token.type() == Token.Type.NUMBER ){
			node.node = parseInt();
		}
		// If is parenthesis sign parse an expression 
		else if(token.type() == Token.Type.LEFT_PAREN){
			node.node = parseExpression();
		}
		return node;
	}

	// Parse integer
	private Node parseInt(){
		
		// Grab the currently selected token from the tokenizer
		Token token = m_tokenizer.current();
		
		// Create a number node
		NumberNode node = new NumberNode();
		node.value =  (Integer)token.value();
		return node;
	}
}
