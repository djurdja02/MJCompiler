package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.*;

public class CodeGenerator extends VisitorAdaptor {
	
	private int mainPc;
	private Logger log = Logger.getLogger(getClass());
	private Map<String, Obj> adress = new HashMap<>();
	private boolean minus = false;
	//private String currNamespace=null;
	public int getMainPc() {
		return mainPc;
	}
	
	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	//----------- statement ----------------

	public void visit(StatementPrint sp) {
		if(sp.getNN() instanceof NoNN)Code.loadConst(0);
		else {
			NNcomma nn = (NNcomma)sp.getNN();
			Code.loadConst(nn.getN1());
		}
		if(sp.getExpr().struct == Tab.intType) {
			Code.put(Code.print);
		}else {
			//char
			Code.put(Code.bprint);
		}
	}
	
	public void visit(StatementRead sp) {
		if(sp.getDesignator().obj.getType() == Tab.intType) {
			Code.put(Code.read);
		}else {
			//char
			Code.put(Code.bread);
		}
		Code.store(sp.getDesignator().obj);
	}
	//----------- konstante----------------
	public void visit(ConstantNUM cnst) {
		Code.loadConst(cnst.getN1());
	}
	
	public void visit(ConstantChar cnst) {
		Code.loadConst(cnst.getC1());
	}
	
	public void visit(ConstantBool cnst) {
		Code.loadConst(cnst.getB1().equals("true")? 1:0);
	}
	
	public void visit(Factor2 num) {
		Code.loadConst(num.getN1());
	}
	
	public void visit(Factor3 cnst) {
		Code.loadConst(cnst.getC1());
	}
	
	public void visit(Factor4 cnst) {
		Code.loadConst(cnst.getB1().equals("true")? 1:0);
	}

	public void visit(NNcomma cnst) {
		Code.loadConst(cnst.getN1());
	}
	//----------------------------------------------------
	//-------------metode-----------------------------
	
	public void visit(MethodName mn) {
		if("main".equalsIgnoreCase(mn.getFuncName())) {
			mainPc = Code.pc;
		}
		mn.obj.setAdr(Code.pc);
		int params = mn.obj.getLevel();
		int allLocal = mn.obj.getLocalSymbols().size();
		Code.put(Code.enter);
		Code.put(params);
		Code.put(allLocal);
	}
	
	public void visit(MethodDecl md) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	//-----------------------------------------------------
	//-----------------designator statement----------------
	public void visit(DesignatorStatement1 ds) {
		//assign op
		if(ds.getDSChoose().getClass()== DSChoose1.class) {
			Code.store(ds.getDesignator().obj);
			if(ds.getDesignator().obj.getType().getKind()==Struct.Array) {
				report_info(ds.getDesignator().obj.getName(), ds);
				adress.put(ds.getDesignator().obj.getName(), ds.getDesignator().obj);
			}
			return;
		}//inkrement
		else if(ds.getDSChoose()  instanceof DSChoose3) {
			//ako je u pitanju elemnt niza potrebno je i indeks i adresu niza cuvati
			if(ds.getDesignator().obj.getKind() == Obj.Elem) {
				Code.put(Code.dup2);
			}
			Code.load(ds.getDesignator().obj);
			Code.loadConst(1);
			Code.put(Code.add);
			Code.store(ds.getDesignator().obj);
			return;
		}
		//dekrement
		else if(ds.getDSChoose() instanceof DSChoose4){
			//ako je u pitanju elemnt niza potrebno je i indeks i adresu niza cuvati
			if(ds.getDesignator().obj.getKind() == Obj.Elem) {
				Code.put(Code.dup2);
			}
			Code.load(ds.getDesignator().obj);
			Code.loadConst(1);
			Code.put(Code.sub);
			Code.store(ds.getDesignator().obj);
			return;
		}
		//poziv funkcije
		else if(ds.getDSChoose() instanceof DSChoose2) {
			Obj functionObj = ds.getDesignator().obj;
			int offset = functionObj.getAdr()-Code.pc;
			Code.put(Code.call);		
			Code.put2(offset);
		}
	}	
//	public void visit(DT dt) {
//		Code.load(dt.getDesignator().obj);
//		int i=0;
//		Code.loadConst(0);
//		Code.put(Code.aload);
//		Code.load(dt.getDesignator().obj);
//		Code.put(Code.arraylength);
//		Code.loadConst(1);
//		//niz[0]
//		Code.load(dt.getDesignator().obj);
//		Code.put(Code.arraylength);
//		Code.loadConst(1);
//		int zakrpa0 = Code.pc;
//		Code.putFalseJump(Code.ne, 0);
//		Code.put(Code.dup_x2);
//		Code.put(Code.pop);
//		Code.put(Code.dup_x2);
//		Code.put(Code.pop);
//		// len l niz[0]
//		Code.put(Code.dup2);
//		Code.put(Code.pop);
//		Code.load(dt.getDesignator().obj);
//		Code.put(Code.dup_x1);
//		Code.put(Code.pop);
//		Code.put(Code.aload);
//		Code.put(Code.dup2);
//		//niz[0] niz[1]
//		int zakrpa1 = Code.pc;
//		Code.putFalseJump(Code.lt,0);
//		Code.put(Code.dup_x1);
//		//niz[1] niz[0] niz[1]
//		Code.put(Code.pop);
//		Code.put2(zakrpa1+1,Code.pc-zakrpa1);
//		Code.put(Code.pop);
//		
//		Code.put(Code.dup_x2);
//		Code.put(Code.pop);
//		Code.loadConst(1);
//		Code.put(Code.add);
//		Code.put(Code.dup2);
//		//niz[1]
//		//labela2:
//		Code.putJump(zakrpa0);
//		//labela1:
//		Code.put2(zakrpa0+1,Code.pc-zakrpa0);
//		Code.put(Code.pop);
//		Code.put(Code.pop);
//	}

	//---------------designator---------------------------------
	public void visit(Designator des) {
		SyntaxNode parent = des.getParent();
		if(parent instanceof Factor1) {
			if(((Factor1)parent).getFactOpt1() instanceof NoFactOpt1) {
				Code.load(des.obj);
			}
		}
		//designator je element niza nista posto smo to dole radili
//		if(des.getDesigRep() instanceof DesigRepArray) {
//			Code.load(des.obj);
//		}
	}
	//-------------expression-------------------------
	
	//operatori - i + su levo asocijativni
	public void visit(TermList term) {
		if(term.getAddop() instanceof AddopP) {
			Code.put(Code.add);
		}else Code.put(Code.sub);
	}
	//operatori *, / i % su levo asocijativni
	public void visit(MulopFOptYes term) {
		if(term.getMulop() instanceof MulopML) {
			Code.put(Code.mul);
		}else if (term.getMulop() instanceof MulopD) {
			Code.put(Code.div);
		}else Code.put(Code.rem);
	}
	
	public void visit(MinOptYes yes) {
		minus = true;
	}
	
	public void visit(SingleTerm exp) {
		if(minus){
			Code.put(Code.neg);
		}
		minus = false;
	}
	
	//------------factor------------------------------
	public void visit(Factor5 f) {
		//niz, npr: new int[]
		if(f.getFactorOr1() instanceof FactorOr11) {
			Code.put(Code.newarray);
			if(f.getType().struct == Tab.intType) {
				Code.put(1);
			}else Code.put(0);
		}
	}
	
	//---------------za nizove------------------------------------
	public void visit(IdenLi iden) {
		if(iden.getParent().getClass() == Designator.class) {
			if(((Designator)iden.getParent()).getDesigRep().getClass()==DesigRepArray.class) {
				//treba nam adresa niza
				Designator parent = (Designator)iden.getParent();
				String name = parent.getName()+"::"+iden.getTypeName();
				Obj o = adress.get(name);
				if(o==null) {report_info("nula", parent);}
				Code.load(adress.get(name));
				
			}
		}
	}
	
	public void visit(NoIdenL iden) {
		if(iden.getParent().getClass() == Designator.class) {
			if(((Designator)iden.getParent()).getDesigRep().getClass()==DesigRepArray.class) {
				//treba nam adresa niza
				Code.load(adress.get(((Designator)iden.getParent()).getName()));
			}
		}
	}
}
