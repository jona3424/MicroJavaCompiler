// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:9:30


package rs.ac.bg.etf.pp1.ast;

public class DesignatorListForArray extends DesignatorList {

    private DesignatorList DesignatorList;
    private ArrayIndexDsingerica ArrayIndexDsingerica;

    public DesignatorListForArray (DesignatorList DesignatorList, ArrayIndexDsingerica ArrayIndexDsingerica) {
        this.DesignatorList=DesignatorList;
        if(DesignatorList!=null) DesignatorList.setParent(this);
        this.ArrayIndexDsingerica=ArrayIndexDsingerica;
        if(ArrayIndexDsingerica!=null) ArrayIndexDsingerica.setParent(this);
    }

    public DesignatorList getDesignatorList() {
        return DesignatorList;
    }

    public void setDesignatorList(DesignatorList DesignatorList) {
        this.DesignatorList=DesignatorList;
    }

    public ArrayIndexDsingerica getArrayIndexDsingerica() {
        return ArrayIndexDsingerica;
    }

    public void setArrayIndexDsingerica(ArrayIndexDsingerica ArrayIndexDsingerica) {
        this.ArrayIndexDsingerica=ArrayIndexDsingerica;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.accept(visitor);
        if(ArrayIndexDsingerica!=null) ArrayIndexDsingerica.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorList!=null) DesignatorList.traverseTopDown(visitor);
        if(ArrayIndexDsingerica!=null) ArrayIndexDsingerica.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.traverseBottomUp(visitor);
        if(ArrayIndexDsingerica!=null) ArrayIndexDsingerica.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorListForArray(\n");

        if(DesignatorList!=null)
            buffer.append(DesignatorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ArrayIndexDsingerica!=null)
            buffer.append(ArrayIndexDsingerica.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorListForArray]");
        return buffer.toString();
    }
}
