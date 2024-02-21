// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class StatementDesignatorStatement extends Statement {

    private DSStatementReplace DSStatementReplace;

    public StatementDesignatorStatement (DSStatementReplace DSStatementReplace) {
        this.DSStatementReplace=DSStatementReplace;
        if(DSStatementReplace!=null) DSStatementReplace.setParent(this);
    }

    public DSStatementReplace getDSStatementReplace() {
        return DSStatementReplace;
    }

    public void setDSStatementReplace(DSStatementReplace DSStatementReplace) {
        this.DSStatementReplace=DSStatementReplace;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DSStatementReplace!=null) DSStatementReplace.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DSStatementReplace!=null) DSStatementReplace.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DSStatementReplace!=null) DSStatementReplace.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementDesignatorStatement(\n");

        if(DSStatementReplace!=null)
            buffer.append(DSStatementReplace.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementDesignatorStatement]");
        return buffer.toString();
    }
}
