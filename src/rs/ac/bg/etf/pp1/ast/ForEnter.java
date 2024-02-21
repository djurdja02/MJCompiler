// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class ForEnter implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ForDes ForDes;
    private CondOpt CondOpt;

    public ForEnter (ForDes ForDes, CondOpt CondOpt) {
        this.ForDes=ForDes;
        if(ForDes!=null) ForDes.setParent(this);
        this.CondOpt=CondOpt;
        if(CondOpt!=null) CondOpt.setParent(this);
    }

    public ForDes getForDes() {
        return ForDes;
    }

    public void setForDes(ForDes ForDes) {
        this.ForDes=ForDes;
    }

    public CondOpt getCondOpt() {
        return CondOpt;
    }

    public void setCondOpt(CondOpt CondOpt) {
        this.CondOpt=CondOpt;
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
        if(ForDes!=null) ForDes.accept(visitor);
        if(CondOpt!=null) CondOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForDes!=null) ForDes.traverseTopDown(visitor);
        if(CondOpt!=null) CondOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForDes!=null) ForDes.traverseBottomUp(visitor);
        if(CondOpt!=null) CondOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForEnter(\n");

        if(ForDes!=null)
            buffer.append(ForDes.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondOpt!=null)
            buffer.append(CondOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForEnter]");
        return buffer.toString();
    }
}
