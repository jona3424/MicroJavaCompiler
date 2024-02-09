// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:9:30


package rs.ac.bg.etf.pp1.ast;

public class DesignatorFirst extends Designator {

    private String designatorNamespaceName;
    private String varName;
    private DesignatorList DesignatorList;

    public DesignatorFirst (String designatorNamespaceName, String varName, DesignatorList DesignatorList) {
        this.designatorNamespaceName=designatorNamespaceName;
        this.varName=varName;
        this.DesignatorList=DesignatorList;
        if(DesignatorList!=null) DesignatorList.setParent(this);
    }

    public String getDesignatorNamespaceName() {
        return designatorNamespaceName;
    }

    public void setDesignatorNamespaceName(String designatorNamespaceName) {
        this.designatorNamespaceName=designatorNamespaceName;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public DesignatorList getDesignatorList() {
        return DesignatorList;
    }

    public void setDesignatorList(DesignatorList DesignatorList) {
        this.DesignatorList=DesignatorList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorList!=null) DesignatorList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorFirst(\n");

        buffer.append(" "+tab+designatorNamespaceName);
        buffer.append("\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(DesignatorList!=null)
            buffer.append(DesignatorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorFirst]");
        return buffer.toString();
    }
}
