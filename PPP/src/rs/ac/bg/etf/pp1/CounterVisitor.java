package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;

public class CounterVisitor extends VisitorAdaptor {
	
	protected int count=0;
	
	public int getCount() {
		return count;
	}
	
	
	public static class VarCounter extends CounterVisitor {		
		@Override
		public void visit(VarDecl VarDecl) {
			count++;
		}
	}
	public static class VarCounterinRow extends CounterVisitor {		
		@Override
		public void visit(SquareBracesLoopList SquareBracesLoopList) {
			count++;
		}
	}
	
}
