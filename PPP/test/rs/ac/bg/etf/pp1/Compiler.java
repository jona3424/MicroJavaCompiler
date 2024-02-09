package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

public class Compiler {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public  void tsdump() {
		TabWithBool.dump();
	}
	public static void main(String[] args) throws Exception {
		System.out.println("Moguce je mainu proslijediti 2 argumenta za preusmjeravanje sistemskih ispisa");
		if (args.length == 2) {
            
			FileOutputStream f = new FileOutputStream(args[0]);
	
		    System.setOut(new PrintStream(f));
		    FileOutputStream fe = new FileOutputStream(args[1]);
	
		    System.setErr(new PrintStream(fe));
		}
        
		
		Logger log = Logger.getLogger(Compiler.class);
		
		Reader br = null;
		try {
			File sourceCode = new File("test/program.mj");
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
	        
	        if(p.errorDetected)
	        	log.info("Postoji sintaksna greska");
	        else {
	        Program prog = (Program)(s.value); 
	        TabWithBool.init();
			// ispis sintaksnog stabla
			log.info(prog.toString(""));
			log.info("===================================");
			

			// ispis prepoznatih programskih konstrukcija
			SemanticAnalyzer v = new SemanticAnalyzer();
			prog.traverseBottomUp(v); 
			//log.info(" Print count calls = " + v.printCallCount);

			//log.info(" Deklarisanih promenljivih ima = " + v.varDeclCount);
			
			log.info("===================================");
			//TabWithBool.dump();
			
			if(!p.errorDetected && v.passed()){
				File objFile = new File("test/program.obj");
				if(objFile.exists()) objFile.delete();
				
				CodeGenerator codeGenerator = new CodeGenerator();
				Code.dataSize = v.nVars;
				prog.traverseBottomUp(codeGenerator);
				
				Code.mainPc = codeGenerator.getMainPc();
				Code.write(new FileOutputStream(objFile));
				
				log.info("Parsiranje uspesno zavrseno!");
			}else{
				log.error("Parsiranje NIJE uspesno zavrseno!");
			}
			TabWithBool.dump();
	        }
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}

	}
		
		
		
	
	
}
