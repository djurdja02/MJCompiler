// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class Designator implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String name;
    private IdenL IdenL;
    private DesigRep DesigRep;

    public Designator (String name, IdenL IdenL, DesigRep DesigRep) {
        this.name=name;
        this.IdenL=IdenL;
        if(IdenL!=null) IdenL.setParent(this);
        this.DesigRep=DesigRep;
        if(DesigRep!=null) DesigRep.setParent(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public IdenL getIdenL() {
        return IdenL;
    }

    public void setIdenL(IdenL IdenL) {
        this.IdenL=IdenL;
    }

    public DesigRep getDesigRep() {
        return DesigRep;
    }

    public void setDesigRep(DesigRep DesigRep) {
        this.DesigRep=DesigRep;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdenL!=null) IdenL.accept(visitor);
        if(DesigRep!=null) DesigRep.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdenL!=null) IdenL.traverseTopDown(visitor);
        if(DesigRep!=null) DesigRep.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdenL!=null) IdenL.traverseBottomUp(visitor);
        if(DesigRep!=null) DesigRep.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(IdenL!=null)
            buffer.append(IdenL.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesigRep!=null)
            buffer.append(DesigRep.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator]");
        return buffer.toString();
    }
}
