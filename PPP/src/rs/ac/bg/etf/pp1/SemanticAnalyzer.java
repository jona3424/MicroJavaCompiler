package rs.ac.bg.etf.pp1;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	boolean flagNiz=false;
	boolean flagNizInner=false;
	boolean addopMinusFlag=false;
	//ova promjenljiva cuva tipove i za var i za const ako bude neka zezancija sa tipom onda odje valja gledat
	private Struct currentType;
	private int constValue;
	private String currenttype;
	private Object object;
	boolean namespaceflag=false;
	String currNamespace;
	List<String> namespacesList= new ArrayList<String>();
	int nVars ;
	
	Logger log = Logger.getLogger(RuleVisitor.class);
	boolean errorDetected = false;

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}
	
	public void report_info(String message, SyntaxNode info) {
		//SyntaxNode iinfo = (SyntaxNode.)
		
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public boolean passed(){
    	return !errorDetected;
    }
	
	private String printKind(Struct s) {
		switch (s.getKind()) {
		case Struct.None:
			return "notype";
		case Struct.Int:
			return "int";
		case Struct.Char:
			return "char";
		case Struct.Bool:
			return "bool";
		default: return "error";
		}
	}
	
	
public void visit(ProgramName programName) { 
    	   	
		programName.obj=TabWithBool.insert(Obj.Prog, programName.getProgramName(), TabWithBool.noType);
		TabWithBool.openScope();
    }

public void visit(Program program) { 
	nVars = TabWithBool.currentScope.getnVars();
	TabWithBool.chainLocalSymbols(program.getProgramName().obj);
	TabWithBool.closeScope();
	}


//Main scope open

public void visit(MainName mainName) { 
	mainName.obj=TabWithBool.insert(Obj.Meth, "main", TabWithBool.noType);
	TabWithBool.openScope();
}
public void visit(MethodDecl methodDecl) { 
	TabWithBool.chainLocalSymbols(methodDecl.getMainName().obj);
	TabWithBool.closeScope();
}


public void visit(NamespaceName namespaceName) {
	namespaceflag=true;
	currNamespace= namespaceName.getNamespaceName();
	if(namespacesList.contains(currNamespace)) report_error("Namespace sa imenom " + currNamespace +" vec postoji.",null);
	else namespacesList.add(currNamespace);
}
public void visit(Namespace namespace) {
	namespaceflag=false;
}

public void visit(SquareBracesHere squareBracesHere) {
	flagNiz=true;
}
public void visit(SquareBracesInnerLoopHere squareBracesInnerLoop) {
	flagNizInner=true;
}
public void visit(VarDeclType varDeclType) {
	currentType=varDeclType.getType().struct;
}
public void visit(VarDecl varDecl) {
	if(namespaceflag) {
		if(TabWithBool.find(currNamespace+"::"+varDecl.getVarName())!=TabWithBool.noObj) {
			report_error("Promjenljiva " +varDecl.getVarName() + " je vec deklarisana u namespace " + currNamespace , null);	
		}
		else {
			if(flagNiz) {
				Obj varNode=TabWithBool.insert(Obj.Var, currNamespace+"::"+varDecl.getVarName(), new Struct(Struct.Array,currentType));
				flagNiz=false;
			}else {
				Obj varNode=TabWithBool.insert(Obj.Var, currNamespace+"::"+varDecl.getVarName(), currentType);
			}
		}
	}
	else {
		
		if(TabWithBool.findInCurrentScope(varDecl.getVarName())!=TabWithBool.noObj) {
			report_error("Promjenljiva " +varDecl.getVarName() + " je vec deklarisana " + currNamespace , null);	
		}
		else {
			if(flagNiz) {
				Obj varNode=TabWithBool.insert(Obj.Var, varDecl.getVarName(), new Struct(Struct.Array,currentType));
				flagNiz=false;
			}else {
				Obj varNode=TabWithBool.insert(Obj.Var, varDecl.getVarName(), currentType);

			}
			
		}
	}
	
}
public void visit(SquareBracesLoopList squareBracesLoopList) {
	if(namespaceflag) {
		if(TabWithBool.find(currNamespace+"::"+squareBracesLoopList.getVarName())!=TabWithBool.noObj) {
			report_error("Promjenljiva " +squareBracesLoopList.getVarName() + " je vec deklarisana u namespace " + currNamespace , null);	
		}
		else {
			if(flagNizInner) {
				Obj varNode=TabWithBool.insert(Obj.Var, currNamespace+"::"+squareBracesLoopList.getVarName(), new Struct(Struct.Array,currentType));
				flagNizInner=false;
			}else {
				Obj varNode=TabWithBool.insert(Obj.Var, currNamespace+"::"+squareBracesLoopList.getVarName(), currentType);
			}
		}
	}
	else {
		if(flagNizInner) {
			Obj varNode=TabWithBool.insert(Obj.Var, squareBracesLoopList.getVarName(), new Struct(Struct.Array,currentType));
			flagNizInner=false;
		}else {
			Obj varNode=TabWithBool.insert(Obj.Var, squareBracesLoopList.getVarName(), currentType);

		}
	}
}



public void visit(ConstDecl constDecl) {
	if(namespaceflag) {
		if(TabWithBool.find(currNamespace+"::"+constDecl.getConstName())!=TabWithBool.noObj) {
			report_error("Promjenljiva " +constDecl.getConstName() + " je vec deklarisana u namespace " + currNamespace , null);	
		}
		else {
			constDecl.obj=TabWithBool.insert(Obj.Con, currNamespace+"::"+constDecl.getConstName(), currentType);
			constDecl.obj.setAdr(constValue);
		}
	}
	else {
		if(TabWithBool.findInCurrentScope(constDecl.getConstName())!=TabWithBool.noObj) {
			report_error("Promjenljiva " +constDecl.getConstName() + " je vec deklarisana " + currNamespace , null);	
		}
		else {
			constDecl.obj=TabWithBool.insert(Obj.Con, constDecl.getConstName(), currentType);
			constDecl.obj.setAdr(constValue);


		}
	}
	
}
public void visit(ConstDeclType constDeclType) {
	currentType=constDeclType.getType().struct;
}
public void visit(ConstDeclLoopList constDeclLoopList) {
	if(namespaceflag) {
		if(TabWithBool.find(currNamespace+"::"+constDeclLoopList.getConstName())!=TabWithBool.noObj) {
			report_error("Promjenljiva " +constDeclLoopList.getConstName() + " je vec deklarisana u namespace " + currNamespace , null);	
		}
		else {
			constDeclLoopList.obj=TabWithBool.insert(Obj.Con,currNamespace+"::"+constDeclLoopList.getConstName() , currentType);
			constDeclLoopList.obj.setAdr(constValue);
		}
	}
	else {
		constDeclLoopList.obj=TabWithBool.insert(Obj.Con,constDeclLoopList.getConstName() , currentType);
		constDeclLoopList.obj.setAdr(constValue);
	}
}


public void visit(ConstNum constNum) {
	if(!currentType.equals(TabWithBool.intType)){
		report_error("Morate promjenljivoj dodijeliti "+printKind(currentType)+" vrijednost", null);
	}
	constValue= constNum.getN1();
}
public void visit(ConstCh constCh) {
	if(!currentType.equals(TabWithBool.charType)){
		report_error("Morate promjenljivoj dodijeliti "+ printKind(currentType)+" vrijednost", null);
	}
	constValue= constCh.getC1();
}
public void visit(ConstBool constBool) {
	if(!currentType.equals(TabWithBool.boolType)){
		report_error("Morate promjenljivoj dodijeliti "+printKind(currentType)+" vrijednost", null);
	}
	constValue= constBool.getB1()?1:0;
}


public void visit(Typesimple typesimple) {
	currenttype=typesimple.getTypeName();
	Obj typeNode=TabWithBool.find(typesimple.getTypeName());
	if(typeNode ==TabWithBool.noObj) {
		report_error("Nije pronadjen tip "+typesimple.getTypeName()+" u tabeli simbola" , null);
		typesimple.struct=TabWithBool.noType;
	}
	else {
		if(Obj.Type==typeNode.getKind()) {
			typesimple.struct=typeNode.getType();
		}
		else {
			report_error("Greska: Ime "+typesimple.getTypeName()+" ne predstavlja tip" , null);
			typesimple.struct=TabWithBool.noType;
		}
	}
}


public void visit(DesignatorFirst designatorFirst) {
	String napespace=designatorFirst.getDesignatorNamespaceName();
	String var=designatorFirst.getVarName();
	Obj obj = Tab.find(napespace+"::"+var);
	if (obj == Tab.noObj) { 
		report_error("Greska na liniji " + designatorFirst.getLine()+ " : ime "+napespace+"::"+var+" nije deklarisano! ", null);
	}
	designatorFirst.obj = obj;
	 
	DesignatorList list=designatorFirst.getDesignatorList();
	if(list instanceof DesignatorListForArray) {
		DesignatorListForArray  designatorListForArray= (DesignatorListForArray)list;
		if(obj.getType().getKind()!=Struct.Array) {
			report_error("Ne mozes da indeksiras promjenljivu koja nije array", null);
		}
		if(!designatorListForArray.getArrayIndexDsingerica().getExpr().struct.equals(Tab.intType)) {
			report_error("Ne mozes da indeksiras promjenljivu sa tipom koji nije int", null);
		}
		designatorFirst.obj=new Obj(Obj.Elem,designatorFirst.obj.getName(),designatorFirst.obj.getType().getElemType(),designatorFirst.obj.getAdr(),designatorFirst.obj.getLevel());
			
	}
	
	
}
public void visit(DesignatorSecond designatorSecond) {
	String var=designatorSecond.getVarName();
	Obj obj = Tab.find(var);
	if (obj == Tab.noObj) { 
		report_error("Greska na liniji " + designatorSecond.getLine()+ " : ime "+var+" nije deklarisano! ", null);
	}
	designatorSecond.obj = obj;
	
	
	DesignatorList list=designatorSecond.getDesignatorList();
	if(list instanceof DesignatorListForArray) {
		DesignatorListForArray  designatorListForArray= (DesignatorListForArray)list;
		if(obj.getType().getKind()!=Struct.Array) {
			report_error("Ne mozes da indeksiras promjenljivu koja nije array", null);
		}
		if(!designatorListForArray.getArrayIndexDsingerica().getExpr().struct.equals(Tab.intType)) {
			report_error("Ne mozes da indeksiras promjenljivu sa tipom koji nije int", null);
		}
		designatorSecond.obj=new Obj(Obj.Elem,designatorSecond.obj.getName(),designatorSecond.obj.getType().getElemType(),designatorSecond.obj.getAdr(),designatorSecond.obj.getLevel());

	}
	}


public void visit(ExprFirst exprFirst) {

	if(!exprFirst.getMinusTerm().getTerm().struct.equals(Tab.intType)) {
		report_error("Expression mora biti tipa int. Greska na liniji: " + exprFirst.getLine(), exprFirst);
		exprFirst.struct=Tab.noType;
	}
	else {
		OpTermLoop opTermLoop= exprFirst.getOpTermLoop();
		if(opTermLoop instanceof OpTermLoopList) {
			if(!exprFirst.getOpTermLoop().struct.equals(Tab.intType)) {
				report_error("Expression mora biti tipa int. Greska na liniji: " + exprFirst.getLine(), exprFirst);
				exprFirst.struct=Tab.noType;
			}
			else {
				exprFirst.struct=Tab.intType;
			}
		}
	}
	
	addopMinusFlag=false;

}
public void visit(ExprSecond exprSecond) {
	
	OpTermLoop opTermLoop= exprSecond.getOpTermLoop();
	if(opTermLoop instanceof OpTermLoopEpsilon) {
		exprSecond.struct=exprSecond.getTerm().struct;
	}
	else {
		if(exprSecond.getTerm().struct.compatibleWith(exprSecond.getOpTermLoop().struct)) {
			if(addopMinusFlag && !exprSecond.getTerm().struct.equals(Tab.intType)) {
				report_error("Ne moze se oduzimati nesto sto nije int ", null);
				exprSecond.struct=Tab.noType;
			}
			else {
				exprSecond.struct=exprSecond.getOpTermLoop().struct;
			}
		}
		else {
			
			report_error("Expr"+ printKind(exprSecond.getOpTermLoop().struct )+ " i Term "+printKind(exprSecond.getTerm().struct )+"moraju biti kompatibilni.", null);
			exprSecond.struct=Tab.noType;
		}
	}
	addopMinusFlag=false;
	
}
public void visit(AddopMinus addopMinus) {
	addopMinusFlag=true;
}
public void visit(OpTermLoopList opTermLoopList) {	
	OpTermLoop loop=opTermLoopList.getOpTermLoop();
	if(loop instanceof OpTermLoopEpsilon) {
		opTermLoopList.struct=opTermLoopList.getTerm().struct;
	}else {
		if(loop.struct.compatibleWith(opTermLoopList.getTerm().struct)) {
			opTermLoopList.struct=opTermLoopList.getTerm().struct;
		}
		else {
			opTermLoopList.struct=Tab.noType;
		}
	}
	
	
}


public void visit(FactorDes factorDes) {
	factorDes.struct=factorDes.getDesignator().obj.getType();
}
public void visit(FactorNum factorNum) {
	
	factorNum.struct=TabWithBool.intType;
}
public void visit(FactorChar factorChar) {
	factorChar.struct=TabWithBool.charType;
}
public void visit(FactorBool factorBool) {
	factorBool.struct=TabWithBool.boolType;
}
public void visit(FactorNewType factorNewType) {
	if(factorNewType.getArrayIndexDsingerica().getExpr().struct.equals(TabWithBool.intType)) {
		factorNewType.struct=factorNewType.getType().struct;
	}
	else {
		report_error("Tip neterminala Expr mora biti int. Greska na liniji: " + factorNewType.getLine(), factorNewType);
		factorNewType.struct=TabWithBool.noType;
	}
}
public void visit(FactorExpress factorExpress) {
	factorExpress.struct=factorExpress.getExpr().struct;
}


public void visit(Term term) {
	if(term.getMulOpFactorLoop() instanceof MulOpFactorLoopEpsilon) {
		term.struct=term.getFactor().struct;
	}
	else {
		if(term.getFactor().struct.equals(TabWithBool.intType) && term.getFactor().struct.compatibleWith(term.getMulOpFactorLoop().struct)) {
			term.struct=term.getFactor().struct;
		}
		else {
			report_error("Term i Factor moraju biti tipa int. Greska na liniji: " + term.getLine(), term);
			term.struct=TabWithBool.noType;
		}
	}
}

public void visit(MulOpFactorLoopList mulOpFactorLoopList) {
	MulOpFactorLoop loop=mulOpFactorLoopList.getMulOpFactorLoop();
	if(loop instanceof MulOpFactorLoopEpsilon) {
		if(!mulOpFactorLoopList.getFactor().struct.equals(TabWithBool.intType))
			report_error("Factor"+mulOpFactorLoopList.getFactor()+" mora biti tipa int. ",null);
		else {
			mulOpFactorLoopList.struct=mulOpFactorLoopList.getFactor().struct;
		}
	}else {
		if(loop.struct.compatibleWith(mulOpFactorLoopList.getFactor().struct) && loop.struct.equals(TabWithBool.intType) ) {
			mulOpFactorLoopList.struct=loop.struct;
		}
		else {
			mulOpFactorLoopList.struct=Tab.noType;
		}
	}
}

public void visit(ReadStatementSecond readStatementSecond) {
	if(readStatementSecond.getDesignator().obj.getType() != TabWithBool.boolType && readStatementSecond.getDesignator().obj.getType() != TabWithBool.charType && readStatementSecond.getDesignator().obj.getType() != TabWithBool.intType) {
		report_error("Designator mora biti tipa int, char ili bool", readStatementSecond);
	}
}
public void visit(PrintStatementThird printStatementThird) {
	if(printStatementThird.getExpr().struct != TabWithBool.boolType && printStatementThird.getExpr().struct != TabWithBool.charType && printStatementThird.getExpr().struct != TabWithBool.intType) {
		report_error("Designator mora biti tipa int, char ili bool", printStatementThird);
	}
}

public void visit(DesignatorStatementThird designatorStatementThird) {
		if(!designatorStatementThird.getDesignator().obj.getType().equals(TabWithBool.intType)) {
			report_error("Designator mora biti tipa int kada se koristi --", designatorStatementThird);
		}
}
public void visit(DesignatorStatementSecond designatorStatementSecond) {
	if(!designatorStatementSecond.getDesignator().obj.getType().equals(TabWithBool.intType)) {
		report_error("Designator mora biti tipa int kada se koristi ++", designatorStatementSecond);
	}
}

public void visit(DesignatorStatementFirst designatorStatementFirst) {
	
	if(designatorStatementFirst.getDesignator().obj.getType().getKind()==Struct.Array) {
		if(!designatorStatementFirst.getExpr().struct.assignableTo(designatorStatementFirst.getDesignator().obj.getType().getElemType())){
			log.info(designatorStatementFirst.getExpr().struct+"and"+designatorStatementFirst.getDesignator().obj.getType().getElemType());
			report_error("Tip neterminala Expr mora biti kompatibilan pri dodeli sa tipom neterminala Designator", designatorStatementFirst);
		}
	}
	else {
		if(!designatorStatementFirst.getExpr().struct.assignableTo(designatorStatementFirst.getDesignator().obj.getType())){
			report_error("Tip neterminala Expr mora biti kompatibilan pri dodeli sa tipom neterminala Designator", designatorStatementFirst);
		}
	}
	
	
}

}



