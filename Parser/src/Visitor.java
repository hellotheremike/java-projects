import java.util.*;
public class Visitor {
	public Object visit(Node node) {
		if(node != null) {
			return node.visit(this);
		}
		return null;
	}

	// Vists an assign node
	public Object visitAssign(AssignNode n) {
		
		// Create a hashmap to store the result of the assignment in
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		// Get the lefthand and righthand side of the node by visit it 
		String left = (String)visit(n.left);
		Integer right = (Integer)visit(n.right);
		
		// Store the result in the hashmap
		map.put(left, right);
		
		return map;
	}

	public Object visitExpression(ExpressionNode n) {
		
		// If the expressions lacks an operator, only return the left side of the node
		if(n.operator == null){
			return visit(n.left);
		}
		// Otherwise get the operator (+), do the addition of the left and right side and return the result
		else if(n.operator.equals("+")){
			int left = (Integer)visit(n.left);
			int right = (Integer)visit(n.right);
			return left + right;
		}
		// ... or it its minus, to the subtraction and return the result
		else if(n.operator.equals("-")){
			int left = (Integer)visit(n.left);
			int right = (Integer)visit(n.right);
			return left - right;
		}
		// If its neither of them, the parse tree contains errors
		else{
			
			throw new RuntimeException("Parse expression: Something went wrong...");
		}
	}

	// Visit a term and return the result
	public Object visitTerm(TermNode n) {
		
		// If the term lacks an operator return the value of the left node
		if(n.operator == null){
			return visit(n.left);
		}
		
		// If its a star, return the result of a multiplication of the left and right side
		else if(n.operator.equals("*")){
			int left = (Integer)visit(n.left);
			int right = (Integer)visit(n.right);
			return left * right;
		}
		// ... or division
		else if(n.operator.equals("/")){
			int left = (Integer)visit(n.left);
			int right = (Integer)visit(n.right);
			return left / right;
		}
		else{
			
			// It was neither, the parse tree contains errors
			throw new RuntimeException("Parse term: Something went wrong...");
		}
	}

	// Returns the value of a factor
	public Object visitFactor(FactorNode n) {
		
		
		return visit(n.node);
	}

	// Return the value of an identifier
	public Object visitIdentifier(IdentifierNode n) {
		return n.value;
	}

	// Retrn the value of a number node
	public Object visitNumber(NumberNode n) {
		
		return n.value.intValue();
	}
}