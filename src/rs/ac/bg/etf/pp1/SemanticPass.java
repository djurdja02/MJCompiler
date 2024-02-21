package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;

public class SemanticPass extends VisitorAdaptor {

	Logger log = Logger.getLogger(getClass());
	boolean returnFound = false;
	boolean errorDetected = false;
	Obj currMethod = null;
	Type currCType = null;
	Type currVType = null;
	String currNamespace = null;
	boolean mainFound = false;
	HashMap<String, List<Obj>> namespaceNames = new HashMap<>();
	boolean forLoop = false;
	Stack<List<Struct>> params = new Stack<>();
	Struct desig = null;
	int nVars = 0;

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
		
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

//------------------------------------------------------------
	public void visit(VarD var) {
		if (Tab.currentScope().findSymbol(var.getVarName()) == null ) {
			if(currNamespace!=null && Tab.currentScope().findSymbol(currNamespace+"::"+var.getVarName())!=null) {
				report_error("Greska na liniji" +var.getLine()+"Promenljiva je vec definisana u trenutnom prostoru imena", null);
				return;
			} 
			String name = (currNamespace!=null)?currNamespace+"::"+var.getVarName():var.getVarName();
			if (var.getBrackets() instanceof NoBrackets) {
				 var.obj=Tab.insert(Obj.Var, name, var.getVarType().getType().struct);
				if (currMethod == null) {
					report_info("Deklarisana promenljiva " + var.getVarName(), var);
				} else {
					report_info("Deklarisana lokalna promenljiva " + var.getVarName(), var);
				}
			} else {
				var.obj=Tab.insert(Obj.Var, name,
						new Struct(Struct.Array, var.getVarType().getType().struct));
				if (currMethod == null) {
					report_info("Deklarisan niz  " + var.getVarName(), var);
				} else {
					report_info("Deklarisana lokalni niz " + var.getVarName(), var);
				}
			}
			nVars ++;
		} else {
			report_error("Greska na liniji " + var.getLine() + " " + var.getVarName() + " vec postoji", null);
		}
	}
	
	public void visit(StatementPrint sp) {
		if(sp.getExpr().struct != BooleanTab.boolType &&
				sp.getExpr().struct!= Tab.intType &&
				sp.getExpr().struct != Tab.charType) {
			report_error("Greska na liniji " + sp.getLine() + " tip nije dobar", null);
		}
	}

	public void visit(StatementRead sb) {
		if(sb.getDesignator().obj.getType()!= Tab.intType &&
				sb.getDesignator().obj.getType()!= Tab.charType &&
				sb.getDesignator().obj.getType()!=BooleanTab.boolType) {
			report_error("Greska na liniji " + sb.getLine() + " tip nije dobar", null);
		}
		if(sb.getDesignator().obj.getKind()!=Obj.Var && 
				sb.getDesignator().obj.getKind()!=Obj.Elem)
			report_error("Greska na liniji " + sb.getLine() + " vrsta nije dobra", null);
	}
	
	public String TypeName(Type type) {
		String typeName;
		if (type.getIdenL() instanceof IdenLi) {
			typeName = ((IdenLi) type.getIdenL()).getTypeName();
		} else {
			typeName = type.getTypeName();
		}
		return typeName;
	}

	public void visit(ConstType c) {
		currCType = c.getType();
	}

	public void visit(VarType v) {
		currVType = v.getType();
	}

	public boolean checkType(Constant c, String tip) {
		if ((c instanceof ConstantNUM) && (tip.equals("int"))) return true;
		if ((c instanceof ConstantBool) && (tip.equals("boolean")))return true;
		if ((c instanceof ConstantChar) && (tip.equals("char")))return true;
		return false;
	}
	
	private int getValC(Constant c){
		if ((c instanceof ConstantNUM)) {
			return ((ConstantNUM)c).getN1();
		}
		if ((c instanceof ConstantBool)) {
			return ((ConstantBool)c).getB1()=="true"?1:0;
		}
		if ((c instanceof ConstantChar) ) {
			return ((ConstantChar)c).getC1();
		}return 0;
	}

	public void visit(ConstD con) {
		if (Tab.currentScope().findSymbol(con.getConstName()) == null) {
			if(currNamespace!=null && Tab.currentScope().findSymbol(currNamespace+"::"+con.getConstName())!=null) {
				report_error("Greska na liniji" +con.getLine()+"Promenljiva je vec definisana u trenutnom prostoru imena", con);
				return;
			}
			String tip = TypeName(con.getConstType().getType());
			Constant c = con.getConstant();
			if (!checkType(c, tip)) {
				report_error("Greska na liniji " + con.getLine() + " tip " + con.getConstName()
						+ "i tip njene vrednosti se ne poklapaju", null);
				return;
			}
			int valC = getValC(c);
			String name = (currNamespace!=null)?currNamespace+"::"+con.getConstName():con.getConstName();
			Obj o =con.obj =Tab.insert(Obj.Con, name, con.getConstType().getType().struct);
			o.setAdr(valC);
			con.obj.setAdr(valC);
			report_info("Deklarisana promenljiva " + con.getConstName(), con);
		} else {
			report_error("Greska na liniji " + con.getLine() + " " + con.getConstName() + " vec postoji", null);
		}
	}


	public void visit(Progname progname) {
		progname.obj = Tab.insert(Obj.Prog, progname.getProgName(), Tab.noType);
		Tab.openScope();
	}

	public void visit(Program program) {
		Tab.chainLocalSymbols(program.getProgname().obj);
		if (!mainFound) {
			report_error("Nije pronadjena main funkcija !", null);
		}
		Tab.closeScope();
		
	}

	public void visit(DesignatorStatement1 tmp) {
		//assign designator
		if(tmp.getDSChoose() instanceof DSChoose1) {
			if(tmp.getDesignator().obj.getKind() != Obj.Elem && tmp.getDesignator().obj.getKind() != Obj.Var) {
				report_error("Greska na liniji:  "+tmp.getLine() +"Promenljiva kojoj se dodeljuje vrednost mora oznacavati promenljivu ili element niza ", tmp);
				return;
			}
			DSChoose1 tmp2 = (DSChoose1)tmp.getDSChoose();
			if(!tmp2.getExpr().struct.assignableTo(tmp.getDesignator().obj.getType()) ) {
				report_error("Greska na liniji:  "+tmp.getLine() + " vrednost koja se dodeljuje promenljivoj ne odgovara njenom tipu", tmp);
				return;
			}
		}
		//increment designator
		else if((tmp.getDSChoose() instanceof DSChoose3) ||(tmp.getDSChoose() instanceof DSChoose4)) {
			if(tmp.getDesignator().obj.getType() != Tab.intType) {
				report_error("Greska na liniji:  "+tmp.getLine() + " inkrement i dekrement moze samo nad promenljivama tipa int da se odradi", tmp);
				return;
			}			
			if(tmp.getDesignator().obj.getKind() != Obj.Elem && tmp.getDesignator().obj.getKind() != Obj.Var) {
				report_error("Greska na liniji:  "+tmp.getLine() + " inkrement i dekrement mogu da se vrse samo nad promenljivama i elemtima niza", tmp);
			}

		}
		//poziv funkcije 
		else {
			Obj func = tmp.getDesignator().obj;
	    	if(Obj.Meth == func.getKind()){
				report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + tmp.getLine(), null);
	    	}else{
				report_error("Greska na liniji " + tmp.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
				return;
	    	}
	    	if((params.size()!=0 && params.peek().size()!=func.getLevel())
	    			||
	    			(params.size() == 0 && func.getLevel()!=0)) {
	    		report_error("Greska na liniji " + tmp.getLine() +" : Broj stvarnih i formalnih parametara se ne poklapa!", null);
	    		return;
	    	}
	    	Iterator<Obj> formalP = func.getLocalSymbols().iterator();
	    	if(params.size()!=0) {
	    		for (int i = 0; i < this.params.peek().size(); i++) {
					if (!this.params.peek().get(i).assignableTo(formalP.next().getType())) {
						report_error("Greska na liniji "+ tmp.getLine()+ " :tipovi stvarnih i formalnih parametara se razlikuju!", tmp);
						return;
					}
				}
	    		params.pop();
	    	}
		}
	}
	public void visit(DesignatorStatement2 ds2) {
		if(ds2.getDesignator().obj.getKind()!=Obj.Var &&
				!(ds2.getDesignator().obj.getType().assignableTo(new Struct(Struct.Array,Tab.noType)))) {
			report_error("Greska na liniji " + ds2.getLine()+" designator sa desne strane dodele mora predstavljati niz! ", null);
			return;
		}
		if(this.desig!=null && !this.desig.assignableTo(ds2.getDesignator().obj.getType().getElemType())) {
			report_error("Greska na liniji " + ds2.getLine()+" elementi designatora sa desne strane dodele moraju biti kompatabilni sa levom stranom! ", null);
			return;
		}
		if(!ds2.getDesignator().obj.getType().getElemType().assignableTo(ds2.getDesignatorStatementMul().getDesignator().obj.getType().getElemType())){
			report_error("Greska na liniji " + ds2.getLine()+" elementi niza sa desne strane dodele moraju biti kompatabilni sa levom stranom! ", null);
			return;
		}
		this.desig = null;
	}
	
	public void visit(NoExprCommaexpr single) {
		this.params.push(new ArrayList<>());
		this.params.peek().add(0,single.getExpr().struct);
	}
	
	public void visit(ExprCommaexpr single) {
		this.params.peek().add(single.getExpr().struct);
	}
	
	public void visit(DDOYes des) {
		if(des.getDesignator().obj.getKind()!=Obj.Elem 
				&& des.getDesignator().obj.getKind()!=Obj.Var) {
			report_error("Greska na liniji " + des.getLine()+" designator sa leve strane dodele mora predstavljati promenljivu ili element niza! ", null);
			return;
		}
		if(desig == null)desig = des.getDesignator().obj.getType();
		else if(!desig.assignableTo(des.getDesignator().obj.getType())) {
			report_error("Greska na liniji " + des.getLine()+" designatori sa leve strane dodele moraju biti kompatabilni! ", null);
			return;
		}
	}
	
	public void visit(ForEnter fd) {
		forLoop = true;
	}
	public void visit(StatementFor sfor) {
		forLoop = false;
	}
	
	public void visit(StatementBreak sb) {
		if (forLoop == false) {
			report_error("Greska na liniji " + sb.getLine()+" break naredba van for petlje ", null);
			return;
		}
	}
	public void visit(StatementContinue sb) {
		if (forLoop == false) {
			report_error("Greska na liniji " + sb.getLine()+" continue naredba van for petlje ", null);
			return;
		}
	}
	
	public void visit(DesignatorStatementMul des) {
		if(des.getDesignator().obj.getType().getKind()!=Struct.Array ) {
			report_error("Greska na liniji " + des.getLine()+" designator sa leve strane dodele mora predstavljati niz! ", null);
			return;
		}
	}
	
	public void visit(StatementReturn sr) {
		if(currMethod==null) {
			report_error("Greska na liniji " + sr.getLine()+" return naredba se nalazi izvan tela funkcije!", null);
			return;
		}//ako nije void a pise samo return;
		if(sr.getExprL() instanceof NoExprL && currMethod.getType()!=Tab.noType) {
			report_error("Greska na liniji " + sr.getLine()+" return naredba ne vraca vrednost za funkciju koja nije void!", null);
			return;
		}
		ExprLi tmp = (ExprLi)sr.getExprL();
		if(!tmp.getExpr().struct.compatibleWith(currMethod.getType()))
			report_error("Greska na liniji " + sr.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currMethod.getName(), null);
		else returnFound= true;
	}

	public void visit(StatementIF si) {
		if(!si.getCondition().struct.equals(BooleanTab.boolType)) {
			report_error("Greska na liniji :" + si.getLine() +" Uslovni izraz mora biti tipa bool! ",null);
		}
	}
	
	public void visit(NamespaceName nn) {
		currNamespace = nn.getName();
	}

	public void visit(Namespace ns) {
		currNamespace = null;
	}
	
	public void visit(DesigRepArray dc) {
		if(dc.getExpr().struct!=Tab.intType) {
			report_error("Greska na liniji " + dc.getLine() + " indeks mora biti tipa int!", null);
		}
	}

	public void visit(Designator designator) {
		String name;
		if ((designator.getIdenL()) instanceof NoIdenL) {
			name = designator.getName();
			if ((designator.obj = Tab.find(name)) == Tab.noObj) {
				if(currNamespace!=null && (designator.obj = Tab.find(currNamespace+"::"+name)) != Tab.noObj) {
					//
				}
				else{report_error("Greska na liniji " + designator.getLine() + " : ime " + name + " nije deklarisano! ", null);
				return;}
			}
		} else {
			name = designator.getName()+"::"+((IdenLi) designator.getIdenL()).getTypeName();
			if ((designator.obj = Tab.find(name)) == Tab.noObj ) {
				if((designator.obj = Tab.find(designator.getName())) == Tab.noObj ) {
					report_error("Greska na liniji " + designator.getLine() + " : ime " + name + " nije deklarisano! ", null);
				return;}
			}
		}
		if(designator.getDesigRep() instanceof DesigRepArray) {
			designator.obj = new Obj(Obj.Elem, designator.obj.getName(), designator.obj.getType().getElemType());
		}
	}
	
	public void visit(FormParam fp) {
		if (Tab.currentScope().findSymbol(fp.getName()) == null) {
			if (fp.getBrackets() instanceof NoBrackets)
				fp.obj = Tab.insert(Obj.Var, fp.getName(), fp.getType().struct);
			else
				fp.obj = Tab.insert(Obj.Var, fp.getName(), new Struct(Struct.Array, fp.getType().struct));
			report_info("Dodat je argument funkcije " + fp.getName(), fp);
			currMethod.setLevel(currMethod.getLevel() + 1);
		} else {
			report_error("Semanticka greska, ime " + fp.getName() + " vec postoji u tabeli simbola.", fp);
		}
	}

	public void visit(FormLiKK fp) {
		if (Tab.currentScope().findSymbol(fp.getName()) == null) {
			if (fp.getBrackets() instanceof NoBrackets)
				fp.obj = Tab.insert(Obj.Var, fp.getName(), fp.getType().struct);
			else
				fp.obj = Tab.insert(Obj.Var, fp.getName(), new Struct(Struct.Array, fp.getType().struct));
			report_info("Dodat je argument funkcije " + fp.getName(), fp);
			currMethod.setLevel(currMethod.getLevel() + 1);
		} else {
			report_error("Semanticka greska, ime " + fp.getName() + " vec postoji u tabeli simbola.", fp);
		}
	}

	public void visit(Type type) {
		String typeName = TypeName(type);
		Obj findnode = Tab.find(typeName);
		if (findnode == Tab.noObj) {
			report_error("Nije pronadjen tip " + typeName + " u tabeli simbola! ", null);
			type.struct = Tab.noType;
		} else {
			if (Obj.Type == findnode.getKind()) {
				type.struct = findnode.getType();

			} else {
				report_error("Greska: Ime " + typeName + " ne predstavlja tip!", type);
				type.struct = Tab.noType;
			}
		}
	}

	public void visit(ConstDListNotNull cl) {
		if (Tab.currentScope().findSymbol(cl.getConstName()) == null) {
			if(currNamespace!=null && Tab.currentScope().findSymbol(currNamespace+"::"+cl.getConstName())!=null) {
				report_error("Greska na liniji" +cl.getLine()+"Promenljiva je vec definisana u trenutnom prostoru imena", cl);
				return;
			}
			if (currCType == null) {
				report_error("Greska na liniji " + cl.getLine() + " tip promenljive nije lepo deklarisan", null);
				return;
			}
			if (!checkType(cl.getConstant(), TypeName(currCType))) {
				report_error("Greska na liniji " + cl.getLine() + " tip " + cl.getConstName()
						+ " i tip njene vrednosti se ne poklapaju", null);
				this.currCType = null;
				return;
			}
			String name = (currNamespace!=null)?currNamespace+"::"+cl.getConstName():cl.getConstName();
			cl.obj= Tab.insert(Obj.Var, name, this.currCType.struct);
			cl.obj.setAdr(getValC(cl.getConstant()));
			report_info("Deklarisana promenljiva " + cl.getConstName(), cl);
		} else {
			report_error("Greska na liniji " + cl.getLine() + " " + cl.getConstName() + " vec postoji", null);
		}
	}

	public void visit(VarDListNotNull var) {
		if (Tab.currentScope().findSymbol(var.getName()) == null) {
			if(currNamespace!=null && Tab.currentScope().findSymbol(currNamespace+"::"+var.getName())!=null) {
				report_error("Greska na liniji" +var.getLine()+"Promenljiva je vec definisana u trenutnom prostoru imena", var);
				return;
			}
			if (currVType == null) {
				report_error("Greska na liniji " + var.getLine() + " tip promenljive nije lepo deklarisan", null);
				return;
			}
			String name = (currNamespace!=null)?currNamespace+"::"+var.getName():var.getName();
			if (var.getBrackets() instanceof NoBrackets) {
				var.obj = Tab.insert(Obj.Var, name, this.currVType.struct);
				if (currMethod == null) {
					report_info("Deklarisana promenljiva " + var.getName(), var);

				} else {
					report_info("Deklarisana lokalna promenljiva " + var.getName(), var);
				}
			} else {
				var.obj = Tab.insert(Obj.Var, name, new Struct(Struct.Array, this.currVType.struct));
				if (currMethod == null) {
					report_info("Deklarisan niz " + var.getName(), var);
				} else {
					report_info("Deklarisan lokalni niz " + var.getName(), var);
				}
			}
			nVars++;
		} else {
			report_error("Greska na liniji " + var.getLine() + " " + var.getName() + " vec postoji", null);
		}
	}

	public void visit(MethodName mn) {
		String name= mn.getFuncName();
		if(currNamespace!= null) {
			name = currNamespace + "::" + name;
		}
		TypeList tl = mn.getTypeList();
		Type func = null;
		if (tl instanceof TypeListType) {
			func = ((TypeListType) tl).getType();
			mn.obj=currMethod = Tab.insert(Obj.Meth, name, func.struct);
		} else {
			mn.obj=currMethod = Tab.insert(Obj.Meth, name, Tab.noType);
		}
		if (mn.getFuncName().equals("main"))
			mainFound = true;
		Tab.openScope();
		report_info("Obradjuje se funkcija " + mn.getFuncName(), mn);
	}

	public void visit(MethodDecl md) {
		if (!returnFound && (currMethod.getType() != Tab.noType)) {
			report_error("Semanticka greska na liniji " + md.getLine() + ": funkcija " + currMethod.getName()
					+ " nema return iskaz!", null);
		}
		Tab.chainLocalSymbols(currMethod);
		Tab.closeScope();
		currMethod = null;
		returnFound = false;
	}
	
	public void visit(Expr expr) {
		expr.struct = expr.getAddopOpt().struct;
		if((expr.getMinOpt() instanceof MinOptYes) && expr.struct!=Tab.intType) {
			report_error("Greska na liniji: " + expr.getLine() + " minus moguc samo ispred int tipa!", expr);
		}
	}

	public void visit(TermList tl) {
		tl.struct = tl.getTerm().struct;
		if(tl.struct!= Tab.intType) {
			report_error("Greska na liniji: " + tl.getLine() + " operacije sabiranja moguce samo izmedju int tipova!", tl);
		}
	}
	public void visit(SingleTerm st) {
		st.struct = st.getTerm().struct;
	}
	
	public void visit(Term term) {
		term.struct = term.getMulopFOpt().struct;
	}
	
	public void visit(MulopFOptYes t) {
		t.struct = t.getFactor().struct;
		if(t.struct!= Tab.intType) {
			report_error("Greska na liniji: " + t.getLine() + " operacije mnozenja i deljenja moguce samo izmedju int tipova!", t);
		}
	}
	
	public void visit(NoMulopFOpt f) {
		f.struct = f.getFactor().struct;
	}
	
	public void visit(Factor2 f) 
	{
		f.struct = Tab.intType;
	}
	public void visit(Factor3 f) 
	{
		f.struct = Tab.charType;
	}
	public void visit(Factor4 f) 
	{
		f.struct = Tab.find("bool").getType();
	}
	
	public void visit(APmaybeNo a) {
		this.params.push(new ArrayList<>());
	}
	public void visit(Factor1 f) {
		if(f.getFactOpt1() instanceof NoFactOpt1) {
			f.struct = f.getDesignator().obj.getType();
		}
		else {
			if(f.getDesignator().obj.getKind() != Obj.Meth) {
				report_error("Greska na liniji: " + f.getLine() + " promenljiva nije funkcija!", f);
				return;
			}
			f.struct = f.getDesignator().obj.getType();
			Obj func = f.getDesignator().obj;
			if((params.size()!=0 && params.peek().size()!=func.getLevel())
	    			||
	    			(params.size() == 0 && func.getLevel()!=0)) {
	    		report_error("Greska na liniji " + params.peek().size() +" : Broj stvarnih i formalnih parametara se ne poklapa!", null);
	    		return;
	    	}
	    	Iterator<Obj> formalP = func.getLocalSymbols().iterator();
	    	if(params.size()!=0) {
	    		for (int i = 0; i < this.params.peek().size(); i++) {
					if (!this.params.peek().get(i).assignableTo(formalP.next().getType())) {
						report_error("Greska na liniji "+ f.getLine()+ " :tipovi stvarnih i formalnih parametara se razlikuju!", f);
						return;
					}
				}
	    		params.pop();
	    	}
		}
	}
	
	public void visit(CondFactrelop cond) {
		if(!cond.getExpr().struct.compatibleWith(cond.getExpr1().struct)) {
			report_error("Greska na liniji "+ cond.getLine()+ " tipovi nisu kompatabilni!",null);
			return;
		}
		if(cond.getExpr().struct.getKind()== Struct.Array &&
				!(cond.getRelop() instanceof RelopEE || cond.getRelop() instanceof RelopNE)) {
			report_error("Greska na liniji "+ cond.getLine()+ " izmedju nizova jedino je moguce poredjenje na jednakost i nejednakost!",null);
			return;
		}
		cond.struct = Tab.find("bool").getType();
	}
	
	public void visit(RelOptNo r) {
		r.struct = r.getExpr().struct;
	}
	
	public void visit(CondTerm c) {
		c.struct = c.getCondFact().struct;
	}
	
	public void visit(Conditioncond c) {
		c.struct = c.getCondTerm().struct;
	}
	
	public void visit(Factor6 f) {
		f.struct = f.getExpr().struct;
	}
	public void visit(Factor5 f) {
		if(f.getFactorOr1() instanceof FactorOr11) {
			FactorOr11 tmp = (FactorOr11)f.getFactorOr1();
			if(tmp.getExpr().struct != Tab.intType) {
				report_error("Greska na liniji: " + f.getLine() + " indeks nije int tipa!", f);
				return;
			}
			f.struct =new Struct(Struct.Array, f.getType().struct);
			return;
		}
		f.struct = f.getType().struct;
	}
}
