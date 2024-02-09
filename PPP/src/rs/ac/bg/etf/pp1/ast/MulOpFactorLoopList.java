// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:9:30


package rs.ac.bg.etf.pp1.ast;

public class MulOpFactorLoopList extends MulOpFactorLoop {

    private MulOpFactorLoop MulOpFactorLoop;
    private Mulop Mulop;
    private Factor Factor;

    public MulOpFactorLoopList (MulOpFactorLoop MulOpFactorLoop, Mulop Mulop, Factor Factor) {
        this.MulOpFactorLoop=MulOpFactorLoop;
        if(MulOpFactorLoop!=null) MulOpFactorLoop.setParent(this);
        this.Mulop=Mulop;
        if(Mulop!=null) Mulop.setParent(this);
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
    }

    public MulOpFactorLoop getMulOpFactorLoop() {
        return MulOpFactorLoop;
    }

    public void setMulOpFactorLoop(MulOpFactorLoop MulOpFactorLoop) {
        this.MulOpFactorLoop=MulOpFactorLoop;
    }

    public Mulop getMulop() {
        return Mulop;
    }

    public void setMulop(Mulop Mulop) {
        this.Mulop=Mulop;
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MulOpFactorLoop!=null) MulOpFactorLoop.accept(visitor);
        if(Mulop!=null) Mulop.accept(visitor);
        if(Factor!=null) Factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MulOpFactorLoop!=null) MulOpFactorLoop.traverseTopDown(visitor);
        if(Mulop!=null) Mulop.traverseTopDown(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MulOpFactorLoop!=null) MulOpFactorLoop.traverseBottomUp(visitor);
        if(Mulop!=null) Mulop.traverseBottomUp(visitor);
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MulOpFactorLoopList(\n");

        if(MulOpFactorLoop!=null)
            buffer.append(MulOpFactorLoop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Mulop!=null)
            buffer.append(Mulop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MulOpFactorLoopList]");
        return buffer.toString();
    }
}
