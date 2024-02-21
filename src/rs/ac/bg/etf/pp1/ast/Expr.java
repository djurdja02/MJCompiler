// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class Expr implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private MinOpt MinOpt;
    private AddopOpt AddopOpt;

    public Expr (MinOpt MinOpt, AddopOpt AddopOpt) {
        this.MinOpt=MinOpt;
        if(MinOpt!=null) MinOpt.setParent(this);
        this.AddopOpt=AddopOpt;
        if(AddopOpt!=null) AddopOpt.setParent(this);
    }

    public MinOpt getMinOpt() {
        return MinOpt;
    }

    public void setMinOpt(MinOpt MinOpt) {
        this.MinOpt=MinOpt;
    }

    public AddopOpt getAddopOpt() {
        return AddopOpt;
    }

    public void setAddopOpt(AddopOpt AddopOpt) {
        this.AddopOpt=AddopOpt;
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
        if(MinOpt!=null) MinOpt.accept(visitor);
        if(AddopOpt!=null) AddopOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MinOpt!=null) MinOpt.traverseTopDown(visitor);
        if(AddopOpt!=null) AddopOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MinOpt!=null) MinOpt.traverseBottomUp(visitor);
        if(AddopOpt!=null) AddopOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Expr(\n");

        if(MinOpt!=null)
            buffer.append(MinOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddopOpt!=null)
            buffer.append(AddopOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Expr]");
        return buffer.toString();
    }
}
