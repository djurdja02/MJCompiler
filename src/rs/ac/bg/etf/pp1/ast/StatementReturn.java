// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class StatementReturn extends Statement {

    private ExprL ExprL;

    public StatementReturn (ExprL ExprL) {
        this.ExprL=ExprL;
        if(ExprL!=null) ExprL.setParent(this);
    }

    public ExprL getExprL() {
        return ExprL;
    }

    public void setExprL(ExprL ExprL) {
        this.ExprL=ExprL;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprL!=null) ExprL.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprL!=null) ExprL.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprL!=null) ExprL.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementReturn(\n");

        if(ExprL!=null)
            buffer.append(ExprL.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementReturn]");
        return buffer.toString();
    }
}
