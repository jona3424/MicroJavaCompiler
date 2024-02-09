package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.CounterVisitor.*;
import rs.ac.bg.etf.pp1.ast.*;

import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	Logger log = Logger.getLogger(RuleVisitor.class);

	private int mainPc;
	
	public int getMainPc() {
		return mainPc;
	}
	
	@Override
	public void visit(MainName mainName) {
		mainPc = Code.pc;
		
		mainName.obj.setAdr(Code.pc);
		
		// Collect arguments and local variables.
		SyntaxNode methodNode = mainName.getParent();
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		VarCounterinRow varCounterinRow= new VarCounterinRow();
		methodNode.traverseTopDown(varCounterinRow);

		log.info(varCnt.getCount()+varCounterinRow.getCount());

		// Generate the entry.
		Code.put(Code.enter);
    	Code.put(0);
		Code.put(varCnt.getCount()+varCounterinRow.getCount());
	}

	@Override
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	
	public void visit(FactorNum factorNum) {
		Code.loadConst(factorNum.getN1()); 
	}
	public void visit(FactorBool factorBool) {
		Code.loadConst(factorBool.getB1()?1:0); 
	}
	public void visit(FactorChar factorChar) {
		Code.loadConst(factorChar.getC1()); 
	}
	public void visit(FactorDes dactorDes) {
		Code.load(dactorDes.getDesignator().obj);	
	}
	public void visit(FactorNewType factorNewType) {
		if(factorNewType.getType() instanceof Typesimple) {
			Typesimple t= (Typesimple)factorNewType.getType();
			Code.put(Code.newarray);
			if(t.getTypeName()=="int") {
				Code.put(1);
			}
			else {
				Code.put(0);
			}
		}
		
	}
	
	
	public void visit(MinusTerm minusTerm) {
		Code.loadConst(-1);
		Code.put(Code.mul);
	}
	
	
	
	public void visit(MulOpFactorLoopList mulOpFactorLoopList) {
		if(mulOpFactorLoopList.getMulop() instanceof Mul) {
			Code.put(Code.mul);
		}
		else if(mulOpFactorLoopList.getMulop() instanceof Div) {
			Code.put(Code.div);

		}
		else if(mulOpFactorLoopList.getMulop() instanceof Mod) {
			Code.put(Code.rem);
		}
		
	}

	public void visit(OpTermLoopList opTermLoopList) {
		if(opTermLoopList.getAddop() instanceof AddopPlus) {
			Code.put(Code.add);

		}
		else {
			Code.put(Code.sub);

		}
	}
//	@Override
//	public void visit(ReturnExpr ReturnExpr) {
//		Code.put(Code.exit);
//		Code.put(Code.return_);
//	}
//	
//	@Override
//	public void visit(ReturnNoExpr ReturnNoExpr) {
//		Code.put(Code.exit);
//		Code.put(Code.return_);
//	}
//	
//	@Override
//	public void visit(Assignment Assignment) {
//		Code.store(Assignment.getDesignator().obj);
//	}
//	
//	@Override
//	public void visit(Const Const) {
//		Code.load(new Obj(Obj.Con, "$", Const.struct, Const.getN1(), 0));
//	}

	public void visit(DesignatorStatementFirst designatorStatementFirst) {
		
	}
	
	public void visit(DesignatorStatementSecond designatorStatementSecond) {
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(designatorStatementSecond.getDesignator().obj);

	}
	public void visit(DesignatorStatementThird designatorStatementThird) {
		Code.loadConst(-1);
		Code.put(Code.add);
		Code.store(designatorStatementThird.getDesignator().obj);

	}
	
	public void visit(ReadStatementSecond readStatementSecond) {
		Code.put(Code.read);
		Code.store(readStatementSecond.getDesignator().obj);
	}
	
	public void visit(DesignatorFirst designatorFirst) {
		if(designatorFirst.obj.getKind()==5) {
			Obj o=new Obj(Obj.Var,designatorFirst.obj.getName(),designatorFirst.obj.getType(), designatorFirst.obj.getAdr(), designatorFirst.obj.getLevel());
			Code.load(o);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
		}
		else {
			if(designatorFirst.getParent().getClass()== DesignatorStatementSecond.class || designatorFirst.getParent().getClass()== DesignatorStatementThird.class)
			Code.load(designatorFirst.obj);

		}
		
		

	}
	public void visit(DesignatorSecond designatorSecond) {	
		if(designatorSecond.obj.getKind()==5) {
			Obj o=new Obj(Obj.Var,designatorSecond.obj.getName(),designatorSecond.obj.getType(), designatorSecond.obj.getAdr(), designatorSecond.obj.getLevel());
			Code.load(o);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
		}else {
			if(designatorSecond.getParent().getClass()== DesignatorStatementSecond.class || designatorSecond.getParent().getClass()== DesignatorStatementThird.class)
			Code.load(designatorSecond.obj);

		}
	
		}

//	
//	@Override
//	public void visit(FuncCall FuncCall) {
//		Obj functionObj = FuncCall.getDesignator().obj;
//		int offset = functionObj.getAdr() - Code.pc; 
//		Code.put(Code.call);
//		Code.put2(offset);
//	}
//	
	 @Override
	    public void visit(PrintStatementThird printStatementThird) {
	        if(printStatementThird.getExpr().struct.equals(TabWithBool.intType)) {
	        	Code.loadConst(5);
	        	Code.put(Code.print);
	        }
	        else {
	        	Code.loadConst(1);
	        	Code.put(Code.bprint);
	        }
	    }
//	
//	@Override
//	public void visit(AddExpr AddExpr) {
//		Code.put(Code.add);
//	}
}
