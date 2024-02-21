// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class StatementFor extends Statement {

    private ForEnter ForEnter;
    private ForDes ForDes;
    private Statement Statement;

    public StatementFor (ForEnter ForEnter, ForDes ForDes, Statement Statement) {
        this.ForEnter=ForEnter;
        if(ForEnter!=null) ForEnter.setParent(this);
        this.ForDes=ForDes;
        if(ForDes!=null) ForDes.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForEnter getForEnter() {
        return ForEnter;
    }

    public void setForEnter(ForEnter ForEnter) {
        this.ForEnter=ForEnter;
    }

    public ForDes getForDes() {
        return ForDes;
    }

    public void setForDes(ForDes ForDes) {
        this.ForDes=ForDes;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForEnter!=null) ForEnter.accept(visitor);
        if(ForDes!=null) ForDes.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForEnter!=null) ForEnter.traverseTopDown(visitor);
        if(ForDes!=null) ForDes.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForEnter!=null) ForEnter.traverseBottomUp(visitor);
        if(ForDes!=null) ForDes.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementFor(\n");

        if(ForEnter!=null)
            buffer.append(ForEnter.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForDes!=null)
            buffer.append(ForDes.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementFor]");
        return buffer.toString();
    }
}
