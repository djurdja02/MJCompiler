// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class CnstDList extends ConstDList {

    private ConstDList ConstDList;
    private ConstDListNotNull ConstDListNotNull;

    public CnstDList (ConstDList ConstDList, ConstDListNotNull ConstDListNotNull) {
        this.ConstDList=ConstDList;
        if(ConstDList!=null) ConstDList.setParent(this);
        this.ConstDListNotNull=ConstDListNotNull;
        if(ConstDListNotNull!=null) ConstDListNotNull.setParent(this);
    }

    public ConstDList getConstDList() {
        return ConstDList;
    }

    public void setConstDList(ConstDList ConstDList) {
        this.ConstDList=ConstDList;
    }

    public ConstDListNotNull getConstDListNotNull() {
        return ConstDListNotNull;
    }

    public void setConstDListNotNull(ConstDListNotNull ConstDListNotNull) {
        this.ConstDListNotNull=ConstDListNotNull;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDList!=null) ConstDList.accept(visitor);
        if(ConstDListNotNull!=null) ConstDListNotNull.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDList!=null) ConstDList.traverseTopDown(visitor);
        if(ConstDListNotNull!=null) ConstDListNotNull.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDList!=null) ConstDList.traverseBottomUp(visitor);
        if(ConstDListNotNull!=null) ConstDListNotNull.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CnstDList(\n");

        if(ConstDList!=null)
            buffer.append(ConstDList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDListNotNull!=null)
            buffer.append(ConstDListNotNull.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CnstDList]");
        return buffer.toString();
    }
}
