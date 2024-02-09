// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:9:30


package rs.ac.bg.etf.pp1.ast;

public class ExprSecond extends Expr {

    private Term Term;
    private OpTermLoop OpTermLoop;

    public ExprSecond (Term Term, OpTermLoop OpTermLoop) {
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.OpTermLoop=OpTermLoop;
        if(OpTermLoop!=null) OpTermLoop.setParent(this);
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public OpTermLoop getOpTermLoop() {
        return OpTermLoop;
    }

    public void setOpTermLoop(OpTermLoop OpTermLoop) {
        this.OpTermLoop=OpTermLoop;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Term!=null) Term.accept(visitor);
        if(OpTermLoop!=null) OpTermLoop.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(OpTermLoop!=null) OpTermLoop.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(OpTermLoop!=null) OpTermLoop.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprSecond(\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OpTermLoop!=null)
            buffer.append(OpTermLoop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprSecond]");
        return buffer.toString();
    }
}
