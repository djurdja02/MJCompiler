// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class FrmL extends FormL {

    private FormL FormL;
    private FormLiKK FormLiKK;

    public FrmL (FormL FormL, FormLiKK FormLiKK) {
        this.FormL=FormL;
        if(FormL!=null) FormL.setParent(this);
        this.FormLiKK=FormLiKK;
        if(FormLiKK!=null) FormLiKK.setParent(this);
    }

    public FormL getFormL() {
        return FormL;
    }

    public void setFormL(FormL FormL) {
        this.FormL=FormL;
    }

    public FormLiKK getFormLiKK() {
        return FormLiKK;
    }

    public void setFormLiKK(FormLiKK FormLiKK) {
        this.FormLiKK=FormLiKK;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormL!=null) FormL.accept(visitor);
        if(FormLiKK!=null) FormLiKK.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormL!=null) FormL.traverseTopDown(visitor);
        if(FormLiKK!=null) FormLiKK.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormL!=null) FormL.traverseBottomUp(visitor);
        if(FormLiKK!=null) FormLiKK.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FrmL(\n");

        if(FormL!=null)
            buffer.append(FormL.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormLiKK!=null)
            buffer.append(FormLiKK.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FrmL]");
        return buffer.toString();
    }
}
