// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatement2 extends DesignatorStatement {

    private DesignatorStatementMul DesignatorStatementMul;
    private Designator Designator;

    public DesignatorStatement2 (DesignatorStatementMul DesignatorStatementMul, Designator Designator) {
        this.DesignatorStatementMul=DesignatorStatementMul;
        if(DesignatorStatementMul!=null) DesignatorStatementMul.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
    }

    public DesignatorStatementMul getDesignatorStatementMul() {
        return DesignatorStatementMul;
    }

    public void setDesignatorStatementMul(DesignatorStatementMul DesignatorStatementMul) {
        this.DesignatorStatementMul=DesignatorStatementMul;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStatementMul!=null) DesignatorStatementMul.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatementMul!=null) DesignatorStatementMul.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatementMul!=null) DesignatorStatementMul.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatement2(\n");

        if(DesignatorStatementMul!=null)
            buffer.append(DesignatorStatementMul.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatement2]");
        return buffer.toString();
    }
}
