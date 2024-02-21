// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class VrDList extends VarDList {

    private VarDList VarDList;
    private VarDListNotNull VarDListNotNull;

    public VrDList (VarDList VarDList, VarDListNotNull VarDListNotNull) {
        this.VarDList=VarDList;
        if(VarDList!=null) VarDList.setParent(this);
        this.VarDListNotNull=VarDListNotNull;
        if(VarDListNotNull!=null) VarDListNotNull.setParent(this);
    }

    public VarDList getVarDList() {
        return VarDList;
    }

    public void setVarDList(VarDList VarDList) {
        this.VarDList=VarDList;
    }

    public VarDListNotNull getVarDListNotNull() {
        return VarDListNotNull;
    }

    public void setVarDListNotNull(VarDListNotNull VarDListNotNull) {
        this.VarDListNotNull=VarDListNotNull;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDList!=null) VarDList.accept(visitor);
        if(VarDListNotNull!=null) VarDListNotNull.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDList!=null) VarDList.traverseTopDown(visitor);
        if(VarDListNotNull!=null) VarDListNotNull.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDList!=null) VarDList.traverseBottomUp(visitor);
        if(VarDListNotNull!=null) VarDListNotNull.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VrDList(\n");

        if(VarDList!=null)
            buffer.append(VarDList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDListNotNull!=null)
            buffer.append(VarDListNotNull.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VrDList]");
        return buffer.toString();
    }
}
