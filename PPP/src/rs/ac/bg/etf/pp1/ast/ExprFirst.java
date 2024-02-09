// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:9:30


package rs.ac.bg.etf.pp1.ast;

public class ExprFirst extends Expr {

    private MinusTerm MinusTerm;
    private OpTermLoop OpTermLoop;

    public ExprFirst (MinusTerm MinusTerm, OpTermLoop OpTermLoop) {
        this.MinusTerm=MinusTerm;
        if(MinusTerm!=null) MinusTerm.setParent(this);
        this.OpTermLoop=OpTermLoop;
        if(OpTermLoop!=null) OpTermLoop.setParent(this);
    }

    public MinusTerm getMinusTerm() {
        return MinusTerm;
    }

    public void setMinusTerm(MinusTerm MinusTerm) {
        this.MinusTerm=MinusTerm;
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
        if(MinusTerm!=null) MinusTerm.accept(visitor);
        if(OpTermLoop!=null) OpTermLoop.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MinusTerm!=null) MinusTerm.traverseTopDown(visitor);
        if(OpTermLoop!=null) OpTermLoop.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MinusTerm!=null) MinusTerm.traverseBottomUp(visitor);
        if(OpTermLoop!=null) OpTermLoop.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprFirst(\n");

        if(MinusTerm!=null)
            buffer.append(MinusTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OpTermLoop!=null)
            buffer.append(OpTermLoop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprFirst]");
        return buffer.toString();
    }
}
