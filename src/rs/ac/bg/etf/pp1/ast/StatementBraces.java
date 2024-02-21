// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class StatementBraces extends Statement {

    private StateL StateL;

    public StatementBraces (StateL StateL) {
        this.StateL=StateL;
        if(StateL!=null) StateL.setParent(this);
    }

    public StateL getStateL() {
        return StateL;
    }

    public void setStateL(StateL StateL) {
        this.StateL=StateL;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StateL!=null) StateL.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StateL!=null) StateL.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StateL!=null) StateL.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementBraces(\n");

        if(StateL!=null)
            buffer.append(StateL.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementBraces]");
        return buffer.toString();
    }
}
