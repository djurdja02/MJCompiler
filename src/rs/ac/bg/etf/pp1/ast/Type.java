// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class Type implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private String typeName;
    private IdenL IdenL;

    public Type (String typeName, IdenL IdenL) {
        this.typeName=typeName;
        this.IdenL=IdenL;
        if(IdenL!=null) IdenL.setParent(this);
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName=typeName;
    }

    public IdenL getIdenL() {
        return IdenL;
    }

    public void setIdenL(IdenL IdenL) {
        this.IdenL=IdenL;
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
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdenL!=null) IdenL.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdenL!=null) IdenL.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Type(\n");

        buffer.append(" "+tab+typeName);
        buffer.append("\n");

        if(IdenL!=null)
            buffer.append(IdenL.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Type]");
        return buffer.toString();
    }
}
